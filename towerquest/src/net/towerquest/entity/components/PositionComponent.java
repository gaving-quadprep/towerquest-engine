package net.towerquest.entity.components;

import net.towerquest.entity.Component;
import net.towerquest.save.SerializeMe;

public class PositionComponent extends Component {
	@SerializeMe
	public double x;
	@SerializeMe
	public double y;
}
