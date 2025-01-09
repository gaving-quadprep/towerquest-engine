package net.towerquest.map;

import java.util.ArrayList;
import java.util.List;

import net.towerquest.entity.Entity;
import net.towerquest.render.WorldRenderable;
import net.towerquest.render.WorldRenderer;
import net.towerquest.save.ISerializable;
import net.towerquest.save.SerializedData;

public class Level implements WorldRenderable, ISerializable {
	private List<Entity> entities = new ArrayList<Entity>();
	@Override
	public void render(WorldRenderer wr) {
		// TODO Auto-generated method stub
		for (Entity e : entities) {
			e.render(wr);
		}
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
		e.setLevel(this);
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
