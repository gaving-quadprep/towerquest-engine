package net.towerquest.physics;

import net.towerquest.serialization.Serializable;

public class Rectangle implements Serializable, CollisionCheckable {
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

	@Override
	public boolean isTouching(CollisionCheckable other) {
		if (other == null)
			return false;
		// this does not handle rectangles with negative dimensions (but why would you do that)
		// another reason why java needs unsigned
		if (other instanceof Point) {
			Point p = (Point)other;
			return (p.x > x && p.x < x + width) &&
					(p.y > y && p.y < y + height);
		} else if (other instanceof Rectangle) {
			Rectangle r = (Rectangle)other;
			return (r.x + r.width > x) && (r.x < x + width) &&
					(r.y + r.height > y) && (r.y < y + height);
		} else {
			return other.isTouching(this);
		}
	}
	
	public boolean contains(Rectangle other) {
		return (other.x > x) && (other.y > y) && 
				(other.x + other.width < x + width) && (other.y + other.height < y + height);
	}
	
}
