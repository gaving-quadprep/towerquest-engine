package net.towerquest.engine.gui;

import net.towerquest.engine.system.Renderer;
import net.towerquest.engine.util.Color;

public class GuiRectangle extends GuiElement {
	Color color;
	@Override
	public void _render(Renderer r) {
		super.render(r);
		r.drawRect(color, getDimensionsOnScreen());
	}
}
