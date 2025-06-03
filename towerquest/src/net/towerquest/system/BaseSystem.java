package net.towerquest.system;

import java.io.File;

/** this provides an abstract library for doing stuff like window creation, the generics are so that things like images don't have to be cast constantly
  * <p> i'm trying to do more documentation this time
  * <p> i'm not writing comments on anything where it's really obvious what it does
  */
public abstract class BaseSystem<WindowType extends Window, RendererType extends Renderer<ImageType>, ImageType extends Image> {
	
	
	public abstract void init();
	/** Exits properly, freeing any resources */
	public abstract void exit();
	public abstract WindowType createWindow(int sizeX, int sizeY, String title);
	/** Creates a blank image */
	public abstract ImageType createImage(int sizeX, int sizeY);
	public abstract ImageType loadPNG(File pngFile);
}
