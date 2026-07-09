package net.towerquest.towerquest.entity.components;

import net.towerquest.towerquest.entity.Component;
import net.towerquest.towerquest.map.Level;
import net.towerquest.towerquest.render.WorldRenderable;
import net.towerquest.towerquest.render.WorldRenderer;
import net.towerquest.towerquest.system.Image;

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
