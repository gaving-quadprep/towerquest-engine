package net.towerquest.towerquest.entity.components;

import net.towerquest.towerquest.entity.Component;
import net.towerquest.towerquest.physics.CollisionCheckable;

@DependsOn(PositionComponent.class)
public class CollisionComponent extends Component {
	public Iterable<CollisionCheckable> hitboxes;
	
	public CollisionComponent() {
		super();
	}
}
