package net.towerquest.engine.gui;

import java.util.Set;

import net.towerquest.engine.physics.Rectangle;
import net.towerquest.engine.render.Renderable;
import net.towerquest.engine.system.Renderer;
import net.towerquest.serialization.Serializable;

public abstract class GuiElement implements Renderable, Serializable {
	Set<GuiElement> children;
	GuiElement parent;
	GuiDim dimensions;
	
	@Override
	public final void render(Renderer r) {
		for(GuiElement e : children) {
			e.render(r);
		}
		_render(r);
	}
	public abstract void _render(Renderer r);
	protected Rectangle getDimensionsOnScreenOfChild(GuiElement child) {
		return child._getDimensionsOnScreen();
	}
	public Rectangle _getDimensionsOnScreen() {
		Rectangle parentDim = parent.getDimensionsOnScreen();
		return dimensions.toRectangle(parentDim.x, parentDim.y, parentDim.width, parentDim.height);
	}
	public Rectangle getDimensionsOnScreen() {
		return parent.getDimensionsOnScreenOfChild(this);
	}
}
