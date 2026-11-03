package net.towerquest.engine.entity.components;

import net.towerquest.engine.entity.Component;
import net.towerquest.engine.physics.CollisionCheckable;

@DependsOn(PositionComponent.class)
public abstract class AbstractCollisionComponent extends Component {
	public abstract Iterable<CollisionCheckable> getHitboxes();
}
