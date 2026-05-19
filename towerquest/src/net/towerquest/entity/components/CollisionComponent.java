package net.towerquest.entity.components;

import net.towerquest.entity.Component;
import net.towerquest.physics.CollisionCheckable;

@DependsOn(PositionComponent.class)
public class CollisionComponent extends Component {
	public Iterable<CollisionCheckable> hitboxes;
	
	public CollisionComponent() {
		super();
	}
}
