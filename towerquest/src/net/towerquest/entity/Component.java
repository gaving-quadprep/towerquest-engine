package net.towerquest.entity;

import java.util.ArrayList;
import java.util.List;

import net.towerquest.event.Event;
import net.towerquest.event.RenderEvent;
import net.towerquest.event.UpdateEvent;
import net.towerquest.render.WorldRenderer;
import net.towerquest.save.ISerializable;
import net.towerquest.save.Pointer;
import net.towerquest.save.SerializeMe;
import net.towerquest.save.SerializedData;

public abstract class Component implements ISerializable {
	@SerializeMe
	private List<Event<?>> events = new ArrayList<Event<?>>();
	
	@Pointer
	private Entity parent;
	
	public void setParent(Entity e) {
		this.parent = e;
	}
	public Entity getParent() {
		 return this.parent;
	}
	
	// TODO no
	public Event[] getEvents() {
		return events.toArray(new Event[] {});
	}
	
	public <T extends Event<?>> T[] getEventsOfType(Class<T> clazz) {
		List<T> ret = new ArrayList<T>();
		for (Event<?> event : events) {
			if (clazz.isInstance(event)) {
				ret.add((T)event);
			}
		}
		return (T[])ret.toArray();
	}
	
	public void bindEvent(Event<?> e) {
		events.add(e);
		e.setParent(this);
	}
	
	public Class<? extends Component>[] getDependencies() {
		return new Class[] {};
	}
	
	public void update() {
		for (Event<?> e : this.getEvents()) {
			if(e instanceof UpdateEvent)
				((UpdateEvent)e).fire(null);
		}
	}
	
}
