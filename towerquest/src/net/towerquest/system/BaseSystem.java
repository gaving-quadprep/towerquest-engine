package net.towerquest.system;

import java.io.File;

public abstract class BaseSystem<WindowType extends Window, RendererType extends Renderer<ImageType>, ImageType extends Image> {
	public abstract void init();
	public abstract void exit();
	public abstract WindowType createWindow(int sizeX, int sizeY, String title);
	public abstract ImageType createImage(int sizeX, int sizeY);
	public abstract ImageType loadPNG(File pngFile);
}
