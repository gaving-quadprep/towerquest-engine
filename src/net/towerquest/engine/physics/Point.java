package net.towerquest.engine.physics;

import net.towerquest.serialization.Serializable;

public class Point implements Serializable, CollisionCheckable {
	public double x;
	public double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean isTouching(CollisionCheckable other, boolean ispassed) {
		if (other == null)
			return false;
		if (other instanceof Point) {
			Point p = (Point)other;
			return (p.x == x && p.y == y);
		} else {
			return CollisionCheckable.passTo(this, other, ispassed);
		}
	}

	@Override
	public boolean contains(CollisionCheckable other) {
		return false;
	}
	
	@Override
	public Point getPosition() {
		return this;
	}

	@Override
	public void setPosition(Point p) {
		this.x = p.x;
		this.y = p.y;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Point) {
			return ((Point) other).x == x &&
					((Point) other).y == y;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

}
