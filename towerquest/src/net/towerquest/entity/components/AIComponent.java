package net.towerquest.entity.components;

import net.towerquest.entity.Component;
import net.towerquest.entity.Entity;
import net.towerquest.save.Pointer;
import net.towerquest.save.SerializeMe;

public class AIComponent extends Component {
	// TODO no
	@SerializeMe
	@Pointer
	Entity target;
	
}
