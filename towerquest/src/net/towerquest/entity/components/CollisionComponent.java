package net.towerquest.entity.components;

import net.towerquest.util.Rectangle;
import net.towerquest.entity.Component;

public class CollisionComponent extends Component {
	public Rectangle hitbox;
	
	public CollisionComponent() {
		super();
	}
	
	// TODO we need a better method of getting dependencies than this
	// maybe this but static for performance reasons
	@Override
	public Class<? extends Component>[] getDependencies() {
		return new Class[] {
			PositionComponent.class
		};
	}
}
