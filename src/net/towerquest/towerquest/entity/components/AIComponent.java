package net.towerquest.towerquest.entity.components;

import net.towerquest.serialization.Pointer;
import net.towerquest.towerquest.entity.Component;
import net.towerquest.towerquest.entity.Entity;

public class AIComponent extends Component {
	@Pointer
	Entity target;
}
