package net.towerquest.engine.gui;

import net.towerquest.engine.physics.Rectangle;
import net.towerquest.serialization.Serializable;

public class GuiDim implements Serializable {
	public int offsetX, offsetY, offsetW, offsetH;
	public double scaleX, scaleY, scaleW, scaleH;
	
	public Rectangle toRectangle(double xOffset, double yOffset, double xScale, double yScale) {
		return new Rectangle(
				(scaleX * xScale) + offsetX,
				(scaleY * yScale) + offsetY,
				(scaleW * xScale) + offsetW,
				(scaleH * yScale) + offsetH
		);
	}
	public Rectangle toRectangle(double xScale, double yScale) {
		return toRectangle(0, 0, xScale, yScale);
	}
}
