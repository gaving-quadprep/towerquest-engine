package net.towerquest.system;

public interface Window<ImageType extends Image, RendererType extends Renderer<ImageType>, KeyboardType extends KeyboardEventHandler> {
	public void destroy();
	public RendererType getRenderer();
	public int getWidth();
	public int getHeight();
	public void update();
	
	// optional
	public default void setIcon(ImageType icon) {}
	public default void setTitle(String title) {}
	/* just puts the window in the center of the screen */
	public default void center() {}
	public default void setPositionOnScreen(int x, int y) {}
	public default void setResizable(boolean resizable) {}
	/* 0 = no cap */
	public default void setFPSCap(int fpsCap) {}
	/* overrides fpscap */
	public default void setVSync(boolean vSync) {}
	public default void sync() {}
	
	public default void onWindowClose(Runnable action) {}
	
	KeyboardType getKeyboardEventHandler();
}
