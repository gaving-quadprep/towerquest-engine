package net.towerquest.engine.entity.components;

import java.util.ArrayList;
import java.util.List;

import net.towerquest.engine.physics.CollisionCheckable;

public class CollisionComponent extends AbstractCollisionComponent {
	public List<CollisionCheckable> hitboxes = new ArrayList<>();
	
	@Override
	public Iterable<CollisionCheckable> getHitboxes() {
		return hitboxes;
	}

}
