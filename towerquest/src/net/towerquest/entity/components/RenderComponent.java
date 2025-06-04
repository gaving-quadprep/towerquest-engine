package net.towerquest.entity.components;

import net.towerquest.entity.Component;
import net.towerquest.event.RenderEvent;
import net.towerquest.render.WorldRenderable;
import net.towerquest.render.WorldRenderer;
import net.towerquest.save.SerializeMe;
import net.towerquest.system.Image;

public class RenderComponent extends Component implements WorldRenderable {
	@SerializeMe
	Image sprite;
	
	public RenderComponent() {
		super();
		this.bindEvent(new RenderEvent(this::render, (wr) -> true));
	}

	@Override
	public void render(WorldRenderer wr) {
		// TODO Auto-generated method stub
		
	}
}
