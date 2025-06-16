package net.towerquest.LWJGLSystem;

import java.io.File;

import net.towerquest.system.BaseSystem;
import net.towerquest.system.Image;
import net.towerquest.system.KeyboardEventHandler;
import net.towerquest.system.SoundSystem;
import net.towerquest.system.Window;

public class LWJGLSystem implements BaseSystem<LWJGLWindow, OpenGLRenderer, TextureImage, LWJGLKeyboard> {
	LWJGLWindow window;
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LWJGLWindow createWindow(int sizeX, int sizeY, String title) {
		if(window == null)
			window = new LWJGLWindow(sizeY, sizeY, title);
		return window;
	}

	@Override
	public TextureImage createImage(int sizeX, int sizeY) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TextureImage loadPNG(File pngFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SoundSystem<?> getSoundSystem() {
		// TODO Auto-generated method stub
		return null;
	}

}
