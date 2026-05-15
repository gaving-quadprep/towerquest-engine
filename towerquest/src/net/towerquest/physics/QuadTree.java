package net.towerquest.physics;

import java.util.HashSet;
import java.util.Set;

import net.towerquest.util.ArrayUtils;

public class QuadTree {
	int maxDepth = 6;
	int maxItemsPerNode = 8;
	double quadTreeScale = 32;
	Point offset = new Point(0d, 0d);
	QuadTreeNode[][] nodes;
	QuadTreeLeaf external = new QuadTreeLeaf(null, null);
	
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
			case NORTHWEST: break;
			case NORTHEAST:
				ret.x += ret.width;
				break;
			case SOUTHEAST:
				ret.y += ret.height;
			case SOUTHWEST:
				ret.x += ret.width;
			}
			return ret;
		}
	}
	
	abstract class QuadTreeNode {
		Rectangle area;
		QuadTreeBranch parent;
		QuadTreeNode(Rectangle area, QuadTreeBranch parent) {
			this.area = area;
			this.parent = parent;
		}
	}
	
	class QuadTreeBranch extends QuadTreeNode {
		QuadTreeBranch(Rectangle area, QuadTreeBranch parent) {
			super(area, parent);
			for (QuadTreeBranchDirection d : QuadTreeBranchDirection.values()) {
				set(d, new QuadTreeLeafDividable(d.getSubsection(area), this));
			}
		}
		QuadTreeNode northeast;
		QuadTreeNode northwest;
		QuadTreeNode southeast;
		QuadTreeNode southwest;
		
		QuadTreeNode getBranch(double x, double y) {
			double halfwayX = area.x + (area.width / 2);
			double halfwayY = area.y + (area.height / 2);
			if (x < halfwayX) {
				return (y < halfwayY) ? northeast : southeast;
			} else {
				return (y < halfwayY) ? northwest : southwest;
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
			case NORTHWEST:
				northwest = node;
			case SOUTHEAST:
				southeast = node;
			case SOUTHWEST:
				southwest = node;
			}
		}
	}

	class QuadTreeLeaf extends QuadTreeNode {
		Set<CollisionCheckable> items = new HashSet<>();
		QuadTreeLeaf(Rectangle area, QuadTreeBranch parent) {
			super(area, parent);
		}
		void add(CollisionCheckable cc) {
			items.add(cc);
		}
	}
	
	class QuadTreeLeafDividable extends QuadTreeLeaf {
		QuadTreeLeafDividable(Rectangle area, QuadTreeBranch parent) {
			super(area, parent);
		}
		@Override
		void add(CollisionCheckable cc) {
			super.add(cc);
			/* ignore any rectangles that cover the entirety of the leaf, because they
			 * will keep dividing further, wasting resources
			 */
			int itemCount = items.size();
			for (CollisionCheckable cc2 : items) {
				if (cc2 instanceof Rectangle)
					if (((Rectangle)cc2).contains(area))
						itemCount--;
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
			for (QuadTreeBranchDirection d : QuadTreeBranchDirection.values()) {
				QuadTreeLeafDividable leaf = (QuadTreeLeafDividable) replacement.get(d);
				for (CollisionCheckable item : items) {
					if (leaf.area.isTouching(item))
						leaf.add(item);
				}
			}
		}
	}
	
	QuadTreeNode getFirstLevelNodeAt(double x, double y) {
		int indexX = (int) Math.ceil((x + offset.x) / quadTreeScale);
		int indexY = (int) Math.ceil((y + offset.y) / quadTreeScale);
		if (ArrayUtils.isOutOfBounds2D(nodes, indexX, indexY))
			return null;
		return nodes[indexX][indexY];
	}
	
	QuadTreeLeaf getLeafAtPoint(Point p) {
		QuadTreeNode node = getFirstLevelNodeAt(p.x, p.y);
		while (node instanceof QuadTreeBranch) {
			node = ((QuadTreeBranch)node).getBranch(p.x, p.y);
		}
		return (QuadTreeLeaf) node;
	}
	public void addPoint(Point p) {
		getLeafAtPoint(p).items.add(p);
	}
	Set<QuadTreeLeaf> getLeavesAtRect(Rectangle r) {
		Set<QuadTreeLeaf> ret = new HashSet<>();
		int startPosX = (int) ((r.x / quadTreeScale) + offset.x);
		int endPosX = (int) Math.ceil((r.x + r.width / quadTreeScale) + offset.x);
		int startPosY = (int) ((r.y / quadTreeScale) + offset.y);
		int endPosY = (int) Math.ceil((r.y + r.width / quadTreeScale) + offset.y);
		for (int x = startPosX; x < endPosX; x++) {
			for (int y = startPosY; y < endPosY; y++) {
				// TODO implement this
			}
		}
		return ret;
	}
	public void addRect(Rectangle r) {
		
	}
	
}
