package map;

import java.util.ArrayList;
import java.util.List;

import entity.Entity;
import render.WorldRenderable;
import render.WorldRenderer;
import save.ISerializable;
import save.SerializedData;

public class Level implements WorldRenderable, ISerializable {
	private List<Entity> entities = new ArrayList<Entity>();
	@Override
	public void render(WorldRenderer wr) {
		// TODO Auto-generated method stub
		for (Entity e : entities) {
			
		}
	}
	
	public void addEntity(Entity e) {
		
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
