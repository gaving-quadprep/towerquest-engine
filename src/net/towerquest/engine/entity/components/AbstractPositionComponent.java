package net.towerquest.engine.entity.components;

import net.towerquest.engine.entity.Component;
import net.towerquest.engine.physics.Point;

public abstract class AbstractPositionComponent extends Component {
	public abstract Point getPosition();
	public abstract void setPosition(Point p);
}
