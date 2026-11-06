package net.towerquest.engine.physics;

import net.towerquest.serialization.Serializable;

public class Circle implements Serializable, Cloneable, CollisionCheckable {
	public Point pos;
	public double radius;
	@Override
	public boolean isTouching(CollisionCheckable other, boolean ispassed) {
		if (other instanceof Point) {
			Point p = (Point)other;
			double dx = p.x - pos.x;
			double dy = p.y - pos.y;
			return ((dx * dx) + (dy * dy)) <= (radius * radius);
		} else if (other instanceof Rectangle) {
			Rectangle r = (Rectangle)other;
			double dx = Math.max(r.x, Math.min(pos.x, r.x + r.width)) - pos.x;
			double dy = Math.max(r.y, Math.min(pos.y, r.y + r.height)) - pos.y;
			return ((dx * dx) + (dy * dy)) <= (radius * radius);
		} else {
			return CollisionCheckable.passTo(this, other, ispassed);
		}
	}

	@Override
	public boolean contains(CollisionCheckable other) {
		if (other instanceof Point) {
			Point p = (Point)other;
			double dx = p.x - pos.x;
			double dy = p.y - pos.y;
			return ((dx * dx) + (dy * dy)) < (radius * radius);
		} else if (other instanceof Rectangle) {
			Rectangle r = (Rectangle)other;
			return contains(r.topLeft()) && contains(r.topRight()) && contains(r.bottomLeft()) && contains(r.bottomRight());
		} else {
			return false;
		}
	}

	@Override
	public Point getCenter() {
		return pos;
	}

}
