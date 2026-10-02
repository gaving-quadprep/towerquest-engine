package net.towerquest.engine.gui;

import java.util.Set;

import net.towerquest.engine.render.Renderable;

public abstract class GuiElement extends GuiObject implements Renderable {
	Set<GuiObject> children;
	GuiDim dimensions;
}
