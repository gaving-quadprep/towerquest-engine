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
	public boolean isTouching(CollisionCheckable other, boolean ispassed) {
		return new Point(x, y).isTouching(other, ispassed);
	}
	
	@Override
	public boolean contains(CollisionCheckable other) {
		return false;
	}
	
	@Override
	public Point getPosition() {
		return new Point(x, y);
	}
	@Override
	public void setPosition(Point p) {
		this.x = (int)p.x;
		this.y = (int)p.y;
	}
}
