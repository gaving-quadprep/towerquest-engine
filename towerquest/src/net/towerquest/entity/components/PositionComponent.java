package net.towerquest.entity.components;

import net.towerquest.entity.Component;
import net.towerquest.save.SerializeMe;
import net.towerquest.event.UpdateEvent;

public class PositionComponent extends Component {
	public PositionComponent() {
		super();
		// TODO temporary, delete
		this.bindEvent(new UpdateEvent(this::update, (v) -> true));
	}
	@SerializeMe
	public double x;
	@SerializeMe
	public double y;
	// TODO also temporary
	public void update(Void v) {
		
	}
}
