package net.towerquest.entity.components;

import net.towerquest.entity.Component;
import net.towerquest.physics.Rectangle;

@DependsOn(PositionComponent.class)
public class CollisionComponent extends Component {
	public Rectangle[] hitboxes;
	
	public CollisionComponent() {
		super();
	}
}
