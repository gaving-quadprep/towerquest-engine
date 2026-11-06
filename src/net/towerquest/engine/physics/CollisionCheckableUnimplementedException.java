package net.towerquest.engine.physics;

public class CollisionCheckableUnimplementedException extends RuntimeException {
	private static final long serialVersionUID = 3791341168404370821L;
	
	public CollisionCheckableUnimplementedException(String str) {
		super(str);
	}
	
	public CollisionCheckableUnimplementedException(CollisionCheckable c1, CollisionCheckable c2) {
		this(c1.getClass().getName() + " is not compatible with " + c2.getClass().getName());
	}
}
