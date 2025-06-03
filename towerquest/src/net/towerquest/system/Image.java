package net.towerquest.system;

public abstract class Image {
	// TODO good way of distinguishing between transparent and opaque images, and different color formats
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract boolean supportsTransparency();
}
