package net.towerquest.engine.gui;

import java.util.Set;

import net.towerquest.engine.physics.Rectangle;
import net.towerquest.engine.render.Renderable;
import net.towerquest.engine.system.Renderer;

public abstract class GuiElement extends GuiObject implements Renderable {
	Set<GuiObject> children;
	GuiDim dimensions;
	
	@Override
	public void render(Renderer r) {
		for(GuiObject o : children) {
			if (o instanceof GuiElement)
				((GuiElement)o).render(r);
		}
	}
	protected Rectangle getDimensionsOnScreenOfChild(GuiElement child) {
		return child.getDimensionsOnScreen();
	}
	public Rectangle getDimensionsOnScreen() {
		Rectangle parentDim = parent.getDimensionsOnScreen();
		return dimensions.toRectangle(parentDim.x, parentDim.y, parentDim.width, parentDim.height);
	}
}
