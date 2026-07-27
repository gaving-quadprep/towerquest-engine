package net.towerquest.engine.entity.components;

import net.towerquest.engine.entity.Component;
import net.towerquest.engine.map.Level;
import net.towerquest.engine.render.WorldRenderable;
import net.towerquest.engine.render.WorldRenderer;
import net.towerquest.engine.system.Image;

public class RenderComponent extends Component implements WorldRenderable {
	Image sprite;
	
	public RenderComponent() {
		super();
	}

	@Override
	public void render(WorldRenderer wr) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addToLevel(Level level) {
		// this is necessary because java treats void and Void as different :blunder:
		level.renderEvent.bind((wr) -> render(wr));
	}
	
	@Override
	public void removeFromLevel(Level level) {}
}
