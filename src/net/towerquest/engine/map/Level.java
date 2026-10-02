package net.towerquest.engine.map;

import java.util.ArrayList;
import java.util.List;

import net.towerquest.engine.GameInstance;
import net.towerquest.engine.entity.Entity;
import net.towerquest.engine.event.BindableEvent;
import net.towerquest.engine.render.WorldRenderable;
import net.towerquest.engine.render.WorldRenderer;
import net.towerquest.engine.util.NFunction.Consumer;
import net.towerquest.serialization.Serializable;

public class Level implements WorldRenderable, Serializable {
	private List<Entity> entities = new ArrayList<>();
	public BindableEvent<Consumer<WorldRenderer>> renderEvent = new BindableEvent<>();
	public BindableEvent<Consumer<Double>> updateEvent = new BindableEvent<>();
	public GameInstance g;
	
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
