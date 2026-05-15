package net.towerquest.map;

import java.util.ArrayList;
import java.util.List;

import net.towerquest.entity.Entity;
import net.towerquest.event.BindableEvent;
import net.towerquest.render.WorldRenderable;
import net.towerquest.render.WorldRenderer;
import net.towerquest.serialization.Serializable;
import net.towerquest.util.NFunction.Consumer;

public class Level implements WorldRenderable, Serializable {
	private List<Entity> entities = new ArrayList<>();
	public BindableEvent<Consumer<WorldRenderer>> renderEvent = new BindableEvent<>();
	public BindableEvent<Consumer<Double>> updateEvent = new BindableEvent<>();
	
	@Override
	public void render(WorldRenderer wr) {
		renderEvent.fire(wr);
	}
	
	public void update(double delta) {
		updateEvent.fire(delta);
	}
	
	// TODO must be updated
	public void addEntity(Entity e) {
		entities.add(e);
		e.setLevel(this);
	}

}
