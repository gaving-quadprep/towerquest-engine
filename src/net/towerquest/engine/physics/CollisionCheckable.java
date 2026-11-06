package net.towerquest.engine.physics;

public interface CollisionCheckable {
	public static boolean passTo(CollisionCheckable _this, CollisionCheckable other, boolean ispassed) {
		if (ispassed)
			throw new CollisionCheckableUnimplementedException(_this, other);
		return other.isTouching(_this, true);
	}
	
	public boolean isTouching(CollisionCheckable other, boolean ispassed);
	
	public default boolean isTouching(CollisionCheckable other) {
		return isTouching(other, false);
	}
	
	public boolean contains(CollisionCheckable other);
	public Point getCenter();
}
