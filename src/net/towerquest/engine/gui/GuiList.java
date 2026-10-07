package net.towerquest.engine.gui;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import net.towerquest.engine.physics.Rectangle;

public class GuiList extends GuiElement {
	// TODO add com.google.code.findbugs:jsr305 once maven is fullly configured
	GuiDim childDimensions;
	private Map<GuiElement, Integer> heightCache = new HashMap<>();
	
	{
		this.children = new TreeSet<>();
	}
	
	public void recalculatePositions() {
		int y = 0;
		for (GuiElement child : children) {
			Rectangle dim = child.getDimensionsOnScreen();
			heightCache.put(child, y);
			y += dim.height;
		}
	}
	
	public void setSortFunction(Comparator<GuiElement> sortFunction) {
		Collection<GuiElement> old = children;
		this.children = new TreeSet<>(sortFunction);
		this.children.addAll(old);
	}
	
	@Override
	public void add(GuiElement child) {
		super.add(child);
	}
	
	@Override
	protected Rectangle getDimensionsOnScreenOfChild(GuiElement child) {
		if (childDimensions == null) {
			Rectangle dim = child.getDimensionsOnScreen();
			dim.y = heightCache.get(child);
			return dim;
		}
	}
	
}
