package net.towerquest.entity;

import java.util.ArrayList;
import java.util.List;

import net.towerquest.event.Event;
import net.towerquest.event.RenderEvent;
import net.towerquest.map.Level;
import net.towerquest.render.WorldRenderable;
import net.towerquest.render.WorldRenderer;
import net.towerquest.save.ISerializable;
import net.towerquest.save.SerializeMe;
import net.towerquest.save.SerializedData;

public class Entity implements ISerializable, WorldRenderable {
	@SerializeMe
	private List<Component> components = new ArrayList<Component>();
	
	private Level level;
	
	@Override
	public void render(WorldRenderer wr) {
		for (Component c : components) {
			for (Event e : c.getEvents()) {
				if(e instanceof RenderEvent)
					((RenderEvent)e).fire(wr);
			}
		}
	}
	
	// TODO make it use delta
	public void update() {
		for (Component c : components) {
			c.update();
		}
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public Level getLevel() {
		return this.level;
	}
	
	public void addComponent(Component c) {
		components.add(c);
		c.setParent(this);
	}
	
	public <T extends Component> T getComponent(Class<T> clazz) {
		List<T> ret = new ArrayList<T>();
		
		for (Component c : components) {
			if (clazz.isInstance(c))
				return (T)c;
			
		}
		return null;
	}

	@Override
	public SerializedData serialize() {
		// TODO Auto-generated method stub
		SerializedData sd = new SerializedData();
		for(Component c : components)
			c.serialize();
		
		return sd;
	}

	@Override
	public void deserialize(SerializedData sd) {
		// TODO Auto-generated method stub
		
	}
	
}
