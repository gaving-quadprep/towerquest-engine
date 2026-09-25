package net.towerquest.engine.physics;

public interface CollisionCheckable {
	public static class CollisionCheckableUnimplementedException extends RuntimeException {
		public CollisionCheckableUnimplementedException(String str) {
			super(str);
		}
	}
	public boolean isTouching(CollisionCheckable other);
	public boolean contains(CollisionCheckable other);
}
