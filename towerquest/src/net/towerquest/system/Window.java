package net.towerquest.system;

import javax.swing.Renderer;

public abstract class Window {
	public abstract void destroy();
	public abstract Renderer getRenderer();
	
	// optional
	public void setIcon(Image icon) {}
	public void setTitle(String title) {}
	public void center() {}
	public void setResizable(boolean resizable) {}
}
