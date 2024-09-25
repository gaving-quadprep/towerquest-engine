package entity;

import event.RenderEvent;
import render.WorldRenderable;
import render.WorldRenderer;

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
