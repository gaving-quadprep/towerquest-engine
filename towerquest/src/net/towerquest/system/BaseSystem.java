package net.towerquest.system;

import java.io.File;

/** this provides an abstract library for doing stuff like window creation, the generics are so that things like images don't have to be cast constantly
  * <p> i'm trying to do more documentation this time
  * <p> i'm not writing comments on anything where it's really obvious what it does
  */
public interface BaseSystem<WindowType extends Window<ImageType, RendererType, KeyboardType>, RendererType extends Renderer<ImageType>, ImageType extends Image, KeyboardType extends KeyboardEventHandler> {
	
	/** Called when the program starts. */
	public void init();
	/** Exits properly, freeing any resources */
	public void exit();
	public WindowType createWindow(int sizeX, int sizeY, String title);
	/** Creates a blank image */
	public ImageType createImage(int sizeX, int sizeY);
	public ImageType loadPNG(File pngFile);
	
	/** Creates and initializes a sound system. Returns null if sound is not available. */
	public default SoundSystem<?> getSoundSystem() {
		return null;
	}
}
