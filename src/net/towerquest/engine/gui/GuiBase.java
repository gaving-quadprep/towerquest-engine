package net.towerquest.engine.gui;

import net.towerquest.engine.physics.Rectangle;
import net.towerquest.engine.system.Renderer;

public class GuiBase extends GuiElement {
	int w, h;
	@Override
	public Rectangle getDimensionsOnScreen() {
		return new Rectangle(0, 0, w, h);
	}
	
	@Override
	public void _render(Renderer r) {
		
	}
}
