package net.towerquest.entity;

import net.towerquest.event.RenderEvent;
import net.towerquest.render.WorldRenderable;
import net.towerquest.render.WorldRenderer;

public class RenderComponent extends Component implements WorldRenderable {
	public RenderComponent() {
		super();
		this.bindEvent(new RenderEvent(this::render, (wr) -> true));
	}

	@Override
	public void render(WorldRenderer wr) {
		// TODO Auto-generated method stub
		
	}
}
