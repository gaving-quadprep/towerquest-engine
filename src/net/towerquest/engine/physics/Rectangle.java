package net.towerquest.engine.physics;

import net.towerquest.serialization.Serializable;

public class Rectangle implements Serializable, Cloneable, CollisionCheckable {
	public double x;
	public double y;
	public double width;
	public double height;
	
	public Rectangle(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Rectangle(java.awt.Rectangle rect) {
		this(rect.x, rect.y, rect.width, rect.height);
	}
	
	public static Rectangle fromPositions(double x0, double y0, double x1, double y1) {
		double minX = Math.min(x0, x1);
		double maxX = Math.max(x0, x1);
		double minY = Math.min(y0, y1);
		double maxY = Math.max(y0, y1);
		return new Rectangle(minX, minY, (maxX - minX), (maxY - minY));
	}
	
	public static Rectangle fromPoints(Point p0, Point p1) {
		return fromPositions(p0.x, p0.y, p1.x, p1.y);
	}

	@Override
	public boolean isTouching(CollisionCheckable other, boolean ispassed) {
		if (other == null)
			return false;
		// this does not handle rectangles with negative dimensions (but why would you do that)
		// another reason why java needs unsigned
		if (other instanceof Point) {
			Point p = (Point)other;
			return (p.x >= x && p.x <= x + width) &&
					(p.y >= y && p.y <= y + height);
		} else if (other instanceof Rectangle) {
			Rectangle r = (Rectangle)other;
			return (r.x + r.width > x) && (r.x < x + width) &&
					(r.y + r.height > y) && (r.y < y + height);
		} else {
			return CollisionCheckable.passTo(this, other, ispassed);
		}
	}
	
	@Override
	public boolean contains(CollisionCheckable other) {
		if (other instanceof Rectangle) {
			Rectangle rect = (Rectangle)other;
			return (rect.x > x) && (rect.y > y) && (rect.x + rect.width < x + width)
					&& (rect.y + rect.height < y + height);
		} else if (other instanceof Point) {
			Point p = (Point)other;
			return (p.x > x && p.x < x + width) &&
					(p.y > y && p.y < y + height);
		}
		return false;
	}

	@Override
	public Point getPosition() {
		return new Point(x + (width / 2), y + (height / 2));
	}
	
	@Override
	public void setPosition(Point p) {
		this.x = p.x - (width / 2);
		this.y = p.y - (height / 2);
	}
	
	public void copyFrom(Rectangle other) {
		x = other.x;
		y = other.y;
		width = other.width;
		height = other.height;
	}
	
	public Point topLeft() {
		return new Point(x, y);
	}
	
	public Point topRight() {
		return new Point(x + width, y);
	}
	
	public Point bottomLeft() {
		return new Point(x, y + height);
	}
	
	public Point bottomRight() {
		return new Point(x + width, y + height);
	}

}
