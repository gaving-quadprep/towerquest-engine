package net.towerquest.engine.gui;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import net.towerquest.engine.physics.Rectangle;
import net.towerquest.engine.render.Renderable;
import net.towerquest.engine.system.Renderer;
import net.towerquest.serialization.Serializable;

public abstract class GuiElement implements Renderable, Serializable {
	Collection<GuiElement> children = new LinkedHashSet<>();
	GuiElement parent;
	GuiDim dimensions;
	
	@Override
	public final void render(Renderer r) {
		for(GuiElement e : children) {
			e.render(r);
		}
		_render(r);
	}
	public void _render(Renderer r) {}
	
	public void onAdded(GuiElement parent) {
		this.parent = parent;
	}
	public void add(GuiElement child) {
		children.add(child);
		child.onAdded(this);
	}
	
	protected Rectangle getDimensionsOnScreenOfChild(GuiElement child) {
		return child._getDimensionsOnScreen();
	}
	protected Rectangle _getDimensionsOnScreen() {
		Rectangle parentDim = parent.getDimensionsOnScreen();
		return dimensions.toRectangle(parentDim.x, parentDim.y, parentDim.width, parentDim.height);
	}
	public Rectangle getDimensionsOnScreen() {
		return parent == null ? _getDimensionsOnScreen()
				: parent.getDimensionsOnScreenOfChild(this);
	}
}
