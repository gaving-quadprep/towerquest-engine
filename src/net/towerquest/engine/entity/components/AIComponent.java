package net.towerquest.engine.entity.components;

import net.towerquest.engine.entity.Component;
import net.towerquest.engine.entity.Entity;
import net.towerquest.serialization.Pointer;

public class AIComponent extends Component {
	@Pointer
	Entity target;
}
