package net.towerquest.system;

public abstract class Window<ImageType extends Image> {
	public abstract void destroy();
	public abstract Renderer<ImageType> getRenderer();
	
	// optional
	public void setIcon(ImageType icon) {}
	public void setTitle(String title) {}
	public void center() {}
	public void setResizable(boolean resizable) {}
}
