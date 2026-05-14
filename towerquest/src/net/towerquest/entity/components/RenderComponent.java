package net.towerquest.entity.components;

import net.towerquest.entity.Component;
import net.towerquest.map.Level;
import net.towerquest.render.WorldRenderable;
import net.towerquest.render.WorldRenderer;
import net.towerquest.system.Image;

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
		level.renderEvent.bind((wr) -> {render(wr); return null;});
	}
	
	@Override
	public void removeFromLevel(Level level) {}
}
