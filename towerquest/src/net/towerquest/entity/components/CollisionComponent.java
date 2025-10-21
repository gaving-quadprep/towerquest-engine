package net.towerquest.entity.components;

import net.towerquest.util.Rectangle;
import net.towerquest.entity.Component;

@DependsOn(PositionComponent.class)
public class CollisionComponent extends Component {
	public Rectangle[] hitboxes;
	
	public CollisionComponent() {
		super();
	}
}
