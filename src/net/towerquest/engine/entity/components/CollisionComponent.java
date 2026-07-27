package net.towerquest.engine.entity.components;

import net.towerquest.engine.entity.Component;
import net.towerquest.engine.physics.CollisionCheckable;

@DependsOn(PositionComponent.class)
public class CollisionComponent extends Component {
	public Iterable<CollisionCheckable> hitboxes;
	
	public CollisionComponent() {
		super();
	}
}
