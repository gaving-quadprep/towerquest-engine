package net.towerquest.towerquest.entity;

import net.towerquest.serialization.Pointer;
import net.towerquest.serialization.Serializable;
import net.towerquest.towerquest.map.Level;

public abstract class Component implements Serializable {
	
	@Pointer
	private Entity parent;
	
	public void setParent(Entity e) {
		this.parent = e;
	}
	public Entity getParent() {
		 return this.parent;
	}
	
	public void addToLevel(Level level) {}
	public void removeFromLevel(Level level) {}
	
}
