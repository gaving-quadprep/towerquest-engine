package net.towerquest.engine.entity.components;

import java.util.ArrayList;
import java.util.List;

import net.towerquest.engine.physics.CollisionCheckable;
import net.towerquest.engine.physics.Point;

public class CollisionComponent extends AbstractCollisionComponent {
	public List<CollisionCheckable> hitboxes = new ArrayList<>();
	private CollisionCheckable mainHitbox;
	
	@Override
	public Iterable<CollisionCheckable> getHitboxes() {
		return hitboxes;
	}
	
	// TODO faster way for things with a lot of hitboxes
	@Override
	public Point getPosition() {
		if (mainHitbox != null)
			return mainHitbox.getPosition();
		
		double x = 0, y = 0, c = 0;
		for(CollisionCheckable hb : hitboxes) {
			Point p = hb.getPosition();
			x += p.x;
			y += p.y;
			c++;
		}
		return new Point(x/c, y/c);
	}

	@Override
	public void setPosition(Point p) {
		Point pos = getPosition();
		Point offset = new Point(p.x - pos.x, p.y - pos.y);
		for(CollisionCheckable hb : hitboxes) {
			Point hpos = hb.getPosition();
			hpos.x += offset.x;
			hpos.y += offset.y;
			hb.setPosition(hpos);
		}
	}

}
