package net.towerquest.map;

import java.util.ArrayList;
import java.util.List;

import net.towerquest.entity.Entity;
import net.towerquest.render.WorldRenderable;
import net.towerquest.render.WorldRenderer;
import net.towerquest.serialization.Deserializer;
import net.towerquest.serialization.Serializable;
import net.towerquest.serialization.SerializedData;
import net.towerquest.serialization.SerializedDataType;
import net.towerquest.serialization.Serializer;

public class Level implements WorldRenderable, Serializable {
	private List<Entity> entities = new ArrayList<Entity>();
	
	@Override
	public void render(WorldRenderer wr) {
		// TODO Auto-generated method stub
		for (Entity e : entities) {
			e.render(wr);
		}
	}
	
	public void update(double delta) {
		for (Entity e : entities) {
			e.update(delta);
		}
	}
	
	// TODO must be updated
	public void addEntity(Entity e) {
		entities.add(e);
		e.setLevel(this);
	}

}
