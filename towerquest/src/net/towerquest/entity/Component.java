package net.towerquest.entity;

import java.util.ArrayList;
import java.util.List;

import net.towerquest.event.Event;
import net.towerquest.event.RenderEvent;
import net.towerquest.event.UpdateEvent;
import net.towerquest.render.WorldRenderer;
import net.towerquest.save.ISerializable;
import net.towerquest.save.SerializedData;

public abstract class Component implements ISerializable {
	private List<Event> events = new ArrayList<Event>();
	
	private Entity parent;
	public void setParent(Entity e) {
		this.parent = e;
	}
	public Entity getParent() {
		 return this.parent;
	}
	
	public Event[] getEvents() {
		return (Event[]) events.toArray();
	}
	
	public <T extends Event> T[] getEventsOfType(Class<T> clazz) {
		List<T> ret = new ArrayList<T>();
		for (Event event : events) {
			if (clazz.isInstance(event)) {
				ret.add((T)event);
			}
		}
		return (T[])ret.toArray();
	}
	
	public void bindEvent(Event e) {
		events.add(e);
	}
	
	public Class<? extends Component>[] getDependencies() {
		return new Class[] {};
	}
	
	public void update() {
		for (Event e : this.getEvents()) {
			if(e instanceof UpdateEvent)
				((UpdateEvent)e).fire(null);
		}
	}


	@Override
	public SerializedData serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deserialize(SerializedData sd) {
		// TODO Auto-generated method stub
		
	}
	
}
