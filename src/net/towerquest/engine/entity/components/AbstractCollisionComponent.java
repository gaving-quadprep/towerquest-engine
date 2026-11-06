package net.towerquest.engine.entity.components;

import net.towerquest.engine.entity.Component;
import net.towerquest.engine.physics.CollisionCheckable;

public abstract class AbstractCollisionComponent extends AbstractPositionComponent {
	public abstract Iterable<CollisionCheckable> getHitboxes();
}
