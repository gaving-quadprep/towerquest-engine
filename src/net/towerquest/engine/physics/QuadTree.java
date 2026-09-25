package net.towerquest.engine.physics;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import net.towerquest.engine.physics.CollisionCheckable.CollisionCheckableUnimplementedException;
import net.towerquest.engine.system.Renderer;
import net.towerquest.engine.util.ArrayUtils;
import net.towerquest.engine.util.Color;
import net.towerquest.engine.util.NFunction.Consumer;

public class QuadTree {
	int maxDepth = 5;
	int maxItemsPerNode = 16;
	double quadTreeScale = 64;
	Point offset = new Point(0d, 0d);
	QuadTreeNode[][] nodes;
	QuadTreeLeaf external = new QuadTreeLeaf(null, null);
	Map<Rectangle, Set<QuadTreeLeaf>> rectCache = new HashMap<>();
	
	public QuadTree(double sizeX, double sizeY) {
		int nodesX = (int) Math.ceil(sizeX / quadTreeScale);
		int nodesY = (int) Math.ceil(sizeY / quadTreeScale);
		
		nodes = new QuadTreeNode[nodesX][nodesY];
		for (int x = 0; x < nodesX; x++) {
			for (int y = 0; y < nodesY; y++) {
				nodes[x][y] = new QuadTreeLeafDividable(new Rectangle((x * quadTreeScale) + offset.x, 
						(y * quadTreeScale) + offset.y, quadTreeScale, quadTreeScale), null);
			}
		}
	}
	
	static enum QuadTreeBranchDirection {
		NORTHEAST,
		NORTHWEST,
		SOUTHEAST,
		SOUTHWEST;
		Rectangle getSubsection(Rectangle r) {
			Rectangle ret = new Rectangle(r.x, r.y, r.width / 2, r.height / 2);
			switch (this) {
			case NORTHEAST:
				ret.x += ret.width;
				break;
			case NORTHWEST:
				break;
			case SOUTHEAST:
				ret.y += ret.height;
				ret.x += ret.width;
				break;
			case SOUTHWEST:
				ret.y += ret.height;
			}
			return ret;
		}
	}

	void forEachDir(Consumer<QuadTreeBranchDirection> fn) {
		fn.exec(QuadTreeBranchDirection.NORTHEAST);
		fn.exec(QuadTreeBranchDirection.NORTHWEST);
		fn.exec(QuadTreeBranchDirection.SOUTHEAST);
		fn.exec(QuadTreeBranchDirection.SOUTHWEST);
	}
	
	abstract class QuadTreeNode {
		Rectangle area;
		QuadTreeBranch parent;
		QuadTreeNode(Rectangle area, QuadTreeBranch parent) {
			this.area = area;
			this.parent = parent;
		}
		abstract void add(CollisionCheckable cc);
		
	}
	
	class QuadTreeBranch extends QuadTreeNode {
		QuadTreeBranch(Rectangle area, QuadTreeBranch parent) {
			super(area, parent);
			forEachDir((d) -> set(d, new QuadTreeLeafDividable(d.getSubsection(area), this)));
		}
		QuadTreeNode northeast;
		QuadTreeNode northwest;
		QuadTreeNode southeast;
		QuadTreeNode southwest;
		
		QuadTreeNode getBranch(double x, double y) {
			double halfwayX = area.x + (area.width / 2);
			double halfwayY = area.y + (area.height / 2);
			if (x < halfwayX) {
				return (y < halfwayY) ? northwest : southwest;
			} else {
				return (y < halfwayY) ? northeast : southeast;
			}
		}
		QuadTreeBranchDirection getDirection(QuadTreeNode node) {
			if (northeast == node)
				return QuadTreeBranchDirection.NORTHEAST;
			if (northwest == node)
				return QuadTreeBranchDirection.NORTHWEST;
			if (southeast == node)
				return QuadTreeBranchDirection.SOUTHEAST;
			if (southwest == node)
				return QuadTreeBranchDirection.SOUTHWEST;
			return null;
		}
		QuadTreeNode get(QuadTreeBranchDirection dir) {
			switch (dir) {
			case NORTHEAST:
				return northeast;
			case NORTHWEST:
				return northwest;
			case SOUTHEAST:
				return southeast;
			case SOUTHWEST:
				return southwest;
			default:
				return null;
			}
		}
		void set(QuadTreeBranchDirection dir, QuadTreeNode node) {
			switch (dir) {
			case NORTHEAST:
				northeast = node;
				break;
			case NORTHWEST:
				northwest = node;
				break;
			case SOUTHEAST:
				southeast = node;
				break;
			case SOUTHWEST:
				southwest = node;
				break;
			}
		}
		public void forAllChildren(Consumer<QuadTreeLeaf> fn) {
			forEachDir((d) -> {
				QuadTreeNode node = get(d);
				if (node instanceof QuadTreeLeaf) {
					fn.exec((QuadTreeLeaf) node);
				} else {
					((QuadTreeBranch)node).forAllChildren(fn);
				}
			});
		}
		@Override
		void add(CollisionCheckable cc) {
			if (cc instanceof Point) {
				addPoint((Point) cc);
			} else if (cc instanceof Rectangle) {
				addRect((Rectangle) cc);
			} else {
				throw new CollisionCheckableUnimplementedException("New shape has been added but does not have a QuadTreeBranch add method");
			}
		}
		void addPoint(Point p) {
			getBranch(p.x, p.y).add(p);
		}
		void addRect(Rectangle rect) {
			if (rect.contains(this.area)) {
				addRectWithoutChecking(rect);
			} else {
				forEachDir((d) -> {
					get(d).add(rect);
				});
			}
		}
		
		/** If a branch is fully contained within a rectangle, all nodes within it must
		 * also be contained within the rectangle. Not checking all of these is faster.
		 */
		void addRectWithoutChecking(Rectangle rect) {
			forEachDir((d) -> {
				QuadTreeNode node = get(d);
				if (node instanceof QuadTreeBranch) {
					((QuadTreeBranch)node).addRectWithoutChecking(rect);
				} else {
					((QuadTreeLeaf)node).add(rect);
				}
			});
		}
	}

	class QuadTreeLeaf extends QuadTreeNode {
		// LinkedHashSets are faster to iterate over
		Set<CollisionCheckable> items = new LinkedHashSet<>();
		QuadTreeLeaf(Rectangle area, QuadTreeBranch parent) {
			super(area, parent);
		}
		@Override
		void add(CollisionCheckable cc) {
			items.add(cc);
			if (cc instanceof Rectangle) {
				Set<QuadTreeLeaf> s = rectCache.get(cc);
				if (s != null)
					s.add(this);
			}
		}
	}
	
	class QuadTreeLeafDividable extends QuadTreeLeaf {
		QuadTreeLeafDividable(Rectangle area, QuadTreeBranch parent) {
			super(area, parent);
		}
		private void addWithoutDividing(CollisionCheckable cc) {
			super.add(cc);	
		}
		@Override
		void add(CollisionCheckable cc) {
			addWithoutDividing(cc);
			/* ignore any rectangles that cover the entirety of the leaf, because they
			 * will keep dividing further, wasting resources
			 * also, for large rectangles, being split into a lot of leaves causes a performance reduction
			 */
			int itemCount = items.size();
			for (CollisionCheckable cc2 : items) {
				if (cc2 instanceof Rectangle) {
					Rectangle r = (Rectangle)cc2;
					if (r.contains(area) || 
							(r.width > area.width * 2 || r.height > area.height * 2))
						itemCount--;
				}
			}
			if (itemCount > maxItemsPerNode) {
				int parents = 0;
				QuadTreeBranch nextParent = parent;
				while (nextParent != null) {
					nextParent = nextParent.parent;
					parents++;
				}
				if (parents < maxDepth)
					divide();
			}
		}
		void divide() {
			QuadTreeBranch replacement = new QuadTreeBranch(area, parent);
			forEachDir((d) -> {
				QuadTreeNode node = replacement.get(d);
				for (CollisionCheckable item : items) {
					if (node.area.isTouching(item))
						node.add(item);
				}
			});
			if (parent != null) {
				parent.set(parent.getDirection(this), replacement);
			} else {
				// TODO should a function translate this
				int indexX = (int) Math.floor((area.x + offset.x) / quadTreeScale);
				int indexY = (int) Math.floor((area.y + offset.y) / quadTreeScale);
				nodes[indexX][indexY] = replacement;
			}
		}
	}
	
	QuadTreeNode getFirstLevelNodeAt(double x, double y) {
		int indexX = (int) Math.floor((x + offset.x) / quadTreeScale);
		int indexY = (int) Math.floor((y + offset.y) / quadTreeScale);
		if (ArrayUtils.isOutOfBounds2D(nodes, indexX, indexY))
			return external;
		return nodes[indexX][indexY];
	}
	
	QuadTreeLeaf getLeafAt(double x, double y) {
		QuadTreeNode node = getFirstLevelNodeAt(x, y);
		if (node instanceof QuadTreeLeaf)
			return (QuadTreeLeaf)node;
		while (node instanceof QuadTreeBranch) {
			node = ((QuadTreeBranch)node).getBranch(x, y);
		}
		return (QuadTreeLeaf) node;
	}
	
	public void forAllFirstLevelNodes(Consumer<QuadTreeNode> fn) {
		for (int x = 0; x < nodes.length; x++) {
			for (int y = 0; y < nodes[x].length; y++) {
				fn.exec(nodes[x][y]);
			}
		}
	}
	
	public void forAllLeaves(Consumer<QuadTreeLeaf> fn) {
		forAllFirstLevelNodes((node) -> {
			if (node instanceof QuadTreeBranch) {
				((QuadTreeBranch)node).forAllChildren(fn);
			} else {
				fn.exec((QuadTreeLeaf) node);
			}
		});
	}
	
	public void addPoint(Point p) {
		getFirstLevelNodeAt(p.x, p.y).add(p);
	}

	public void addRect(Rectangle r) {
		Set<QuadTreeLeaf> leaves = new HashSet<>();
		rectCache.put(r, leaves);
		for (double x = Math.floor(r.x); x < Math.ceil(r.x + r.width); x += quadTreeScale) {
			for (double y = Math.floor(r.y); y < Math.ceil(r.y + r.height); y += quadTreeScale) {
				getFirstLevelNodeAt(x, y).add(r);
			}
		}
	}
	
	public void add(CollisionCheckable cc) {
		if (cc instanceof Point) {
			addPoint((Point)cc);
		} else if (cc instanceof Rectangle) {
			addRect((Rectangle)cc);
		} else {
			throw new CollisionCheckableUnimplementedException(cc.getClass().getSimpleName() + "is not implemented");
		}
	}

	public void removePoint(Point p) {
		QuadTreeLeaf containingLeaf = getLeafAt(p.x, p.y);
		containingLeaf.items.remove(p);
	}
	
	public void removeRect(Rectangle r) {
		Set<QuadTreeLeaf> containingLeaves = rectCache.get(r);
		if (containingLeaves == null)
			return;
		for (QuadTreeLeaf leaf : containingLeaves) {
			leaf.items.remove(r);
		}
		rectCache.remove(r);
	}

	public Set<CollisionCheckable> getTouching(CollisionCheckable cc) {
		Set<CollisionCheckable> touching = new HashSet<>();
		if (cc instanceof Point) {
			Point p = (Point)cc;
			QuadTreeLeaf leaf = getLeafAt(p.x, p.y);
			for (CollisionCheckable cc2 : leaf.items) {
				if (!touching.contains(cc2))
					if (cc2.isTouching(p))
						touching.add(cc2);
			}
		} else if (cc instanceof Rectangle) {
			for (QuadTreeLeaf leaf : rectCache.get((Rectangle)cc)) {
				for (CollisionCheckable cc2 : leaf.items) {
					if (!touching.contains(cc2))
						if (cc2.isTouching(cc))
							touching.add(cc);
				}
			}
		} else {
			throw new CollisionCheckableUnimplementedException(cc.getClass().getSimpleName() + "is not implemented");
		}
		return touching;
	}
	
	public void visualize(Renderer r) {
		Color color = new Color(255, 0, 0);
		Color color2 = new Color(0, 255, 0);
		Color color3 = new Color(0, 0, 255);
		forAllLeaves((leaf) -> {
			r.drawRect(color, (int) leaf.area.x, (int) leaf.area.y,
					(int) (leaf.area.x + leaf.area.width), (int) (leaf.area.y + leaf.area.height));
			for (CollisionCheckable cc : leaf.items) {
				if (cc instanceof Point) {
					Point p = (Point)cc;
					r.fillRect(leaf.area.isTouching(p) ? color2 : color3, (int) p.x, (int) p.y, (int) p.x+1, (int) p.y+1);
				}
			}
		});
		for (Rectangle r2 : rectCache.keySet()) {
			r.drawRect(color3, (int) r2.x, (int) r2.y, (int) (r2.x + r2.width), (int) (r2.y + r2.height));
		}
	}
	
}
