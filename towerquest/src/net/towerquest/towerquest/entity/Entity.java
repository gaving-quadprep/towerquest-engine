package net.towerquest.towerquest.entity;

import java.util.ArrayList;
import java.util.List;

import net.towerquest.serialization.Pointer;
import net.towerquest.serialization.Serializable;
import net.towerquest.towerquest.entity.components.DependsOn;
import net.towerquest.towerquest.entity.components.MissingDependenciesException;
import net.towerquest.towerquest.entity.components.RenderComponent;
import net.towerquest.towerquest.map.Level;
import net.towerquest.towerquest.render.WorldRenderable;
import net.towerquest.towerquest.render.WorldRenderer;
import net.towerquest.towerquest.util.Logger;

public class Entity implements Serializable, WorldRenderable {
	private List<Component> components = new ArrayList<>();
	
	@Pointer
	protected Level level;
	
	@Override
	public void render(WorldRenderer wr) {
		for (Component c : components) {
			if (c instanceof RenderComponent)
				((RenderComponent)c).render(wr);
		}
	}
	
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public Level getLevel() {
		return this.level;
	}
	
	/** Called when the component is added to a level.
	 *  Doesn't add it to the level, instead use Level's addEntity.
	 */
	void addToLevel(Level level) {
		setLevel(level);
		for (Component c : components)
			c.addToLevel(level);
	}
	
	/** Likewise, called when the component is removed from the level. */
	void removeFromLevel(Level level) {
		for (Component c : components)
			c.removeFromLevel(level);
	}
	
	public void addComponent(Component c) {
		DependsOn dependencies = c.getClass().getAnnotation(DependsOn.class);
		if (dependencies != null) {
			for (Class<? extends Component> d : dependencies.value()) {
				boolean hasDependency = false;
				for (Component c2 : components) {
					if (d.isInstance(c2)) {
						hasDependency = true;
						Logger.instance.log(Logger.Level.DEBUG, c2.getClass().getSimpleName() + " is an instance of " + d.getSimpleName());
					}
				}
				if (!hasDependency)
					throw new MissingDependenciesException(d.getName());
			}
		} else {
			Logger.instance.log(Logger.Level.DEBUG, "no deps for "+c.getClass().getSimpleName());
		}
		
		components.add(c);
		c.setParent(this);
	}

	@SuppressWarnings("unchecked")
	public <T extends Component> T getComponent(Class<T> clazz) {
		for (Component c : components) {
			if (clazz.isInstance(c))
				return (T)c;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Component> List<T> getComponents(Class<T> clazz) {
		List<T> ret = new ArrayList<>();
		for (Component c : components) {
			if (clazz.isInstance(c))
				ret.add((T) c);
			
		}
		return ret;
	}
}
