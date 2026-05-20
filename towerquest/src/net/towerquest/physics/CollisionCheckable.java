package net.towerquest.physics;

public interface CollisionCheckable {
	public boolean isTouching(CollisionCheckable other);
	public boolean contains(CollisionCheckable other);
}
