package net.towerquest.engine.system;

import net.towerquest.engine.util.Color;

public interface Image {
	// TODO good way of distinguishing between transparent and opaque images, and different color formats
	public int getWidth();
	public int getHeight();
	/** this refers to bits per pixel, not bytes */
	public int getBPP();
	public boolean supportsTransparency();
	
	public Color getColorAt(int x, int y);
	public void setColorAt(int x, int y, Color color);
	
	/** this should always use nearest neighbor when scaling up */
	public Image getScaledImage(int newWidth, int newHeight);
}
