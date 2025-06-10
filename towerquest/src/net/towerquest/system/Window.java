package net.towerquest.system;

public abstract class Window<ImageType extends Image, RendererType extends Renderer<ImageType>> {
	public abstract void destroy();
	public abstract RendererType getRenderer();
	public abstract int getWidth();
	public abstract int getHeight();
	
	// optional
	public void setIcon(ImageType icon) {}
	public void setTitle(String title) {}
	/* just puts the window in the center of the screen */
	public void center() {}
	public void setResizable(boolean resizable) {}
	/* 0 = no cap */
	public void setFPSCap(int fpsCap) {}
	/* overrides fpscap */
	public void setVSync(boolean vSync) {}
}
