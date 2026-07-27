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
	public boolean isTouching(CollisionCheckable other) {
		if (other == null)
			return false;
		if (other instanceof Point) {
			Point p = (Point)other;
			return (p.x == x && p.y == y);
		} else {
			return other.isTouching(this);
		}
	}

	@Override
	public boolean contains(CollisionCheckable other) {
		return false;
	}
}
