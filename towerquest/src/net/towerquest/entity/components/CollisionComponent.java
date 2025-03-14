package net.towerquest.entity.components;

import net.towerquest.util.Rectangle;
import net.towerquest.entity.Component;
import net.towerquest.save.SerializeMe;

public class CollisionComponent extends Component {
	@SerializeMe
	public Rectangle hitbox;
	public CollisionComponent() {
		super();
	}
	
	public Class<? extends Component>[] getDependencies(){
		return new Class[] {
			PositionComponent.class
		};
	}
}
