package net.towerquest.engine.physics;

// TODO make this equivalent to point

public class IntPoint implements CollisionCheckable {
	public int x;
	public int y;
	
	public IntPoint() {}
	public IntPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean isTouching(CollisionCheckable other) {
		// TODO implement
		return false;
	}
	
	@Override
	public boolean contains(CollisionCheckable other) {
		return false;
	}
}
