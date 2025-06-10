package net.towerquest.system;

import net.towerquest.util.Color;

public abstract class Image {
	// TODO good way of distinguishing between transparent and opaque images, and different color formats
	public abstract int getWidth();
	public abstract int getHeight();
	/** this refers to bits per pixel, not bytes */
	public abstract int getBPP();
	public abstract boolean supportsTransparency();
	
	public abstract Color getColorAt(int x, int y);
	public abstract void setColorAt(int x, int y, Color color);
	
	/** this should always use nearest neighbor when scaling up */
	public abstract Image getScaledImage(int newWidth, int newHeight);
}
