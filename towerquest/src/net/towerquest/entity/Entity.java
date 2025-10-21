package net.towerquest.entity;

import java.util.ArrayList;
import java.util.List;

import net.towerquest.entity.components.DependsOn;
import net.towerquest.entity.components.MissingDependenciesException;
import net.towerquest.event.Event;
import net.towerquest.event.RenderEvent;
import net.towerquest.map.Level;
import net.towerquest.render.WorldRenderable;
import net.towerquest.render.WorldRenderer;
import net.towerquest.serialization.Serializable;
import net.towerquest.util.Logger;

public class Entity implements Serializable, WorldRenderable {
	private List<Component> components = new ArrayList<Component>();

	private transient Level level;
	
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
	public void update(double delta) {
		for (Component c : components) {
			c.update(delta);
		}
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public Level getLevel() {
		return this.level;
	}
	
	public void addComponent(Component c) {
		DependsOn dependencies = c.getClass().getAnnotation(DependsOn.class);
		if (dependencies != null) {
			for (Class<? extends Component> d : dependencies.value()) {
				boolean hasDependency = false;
				for (Component c2 : components) {
					if (d.isInstance(c2)) {
						hasDependency = true;
						Logger.instance.log(c2.getClass().getName() + " is an instance of " + d.getName());
					}
				}
				if (!hasDependency)
					throw new MissingDependenciesException(d.getName());
			}
		} else {
			Logger.instance.log("no deps for "+c.getClass().getName());
		}
		
		components.add(c);
		c.setParent(this);
	}

	public <T extends Component> T getComponent(Class<T> clazz) {
		for (Component c : components) {
			if (clazz.isInstance(c))
				return (T)c;
			
		}
		return null;
	}
	public <T extends Component> List<T> getComponents(Class<T> clazz) {
		List<T> ret = new ArrayList<T>();
		
		for (Component c : components) {
			if (clazz.isInstance(c))
				ret.add((T) c);
			
		}
		return ret;
	}
}
