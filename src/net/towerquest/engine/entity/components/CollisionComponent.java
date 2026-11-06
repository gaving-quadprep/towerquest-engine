package net.towerquest.engine.entity.components;

import java.util.ArrayList;
import java.util.List;

import net.towerquest.engine.physics.CollisionCheckable;
import net.towerquest.engine.physics.Point;

public class CollisionComponent extends AbstractCollisionComponent {
	public List<CollisionCheckable> hitboxes = new ArrayList<>();
	
	@Override
	public Iterable<CollisionCheckable> getHitboxes() {
		return hitboxes;
	}
	
	// TODO faster way for things with a lot of hitboxes
	@Override
	public Point getPosition() {
		double x = 0, y = 0, c = 0;
		for(CollisionCheckable hb : hitboxes) {
			Point p = hb.getCenter();
			x += p.x;
			y += p.y;
			c++;
		}
		return new Point(x/c, y/c);
	}

	@Override
	public void setPosition(Point p) {
		// TODO Auto-generated method stub
		
	}

}
