package net.towerquest.engine.gui;

import net.towerquest.engine.physics.Rectangle;
import net.towerquest.engine.system.Image;
import net.towerquest.engine.system.Renderer;

public class GuiImage extends GuiElement {
	Image image;
	@Override
	public void _render(Renderer r) {
		super.render(r);
		Rectangle dim = getDimensionsOnScreen();
		r.drawImage(image, (int) dim.x, (int) dim.y, (int) dim.width, (int) dim.height);
	}
}
