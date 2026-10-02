package net.towerquest.engine.gui;

import net.towerquest.engine.physics.Rectangle;
import net.towerquest.serialization.Serializable;

public class GuiDim implements Serializable {
	public int offsetX, offsetY, offsetW, offsetH;
	public double scaleX, scaleY, scaleW, scaleH;
	
	public Rectangle toRectangle(int xScale, int yScale) {
		return new Rectangle(
				(scaleX * (double)xScale) + offsetX,
				(scaleY * (double)yScale) + offsetY,
				(scaleW * (double)xScale) + offsetW,
				(scaleH * (double)yScale) + offsetH
		);
	}
}
