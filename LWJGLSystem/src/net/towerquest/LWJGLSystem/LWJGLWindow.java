package net.towerquest.LWJGLSystem;

import javax.swing.Renderer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import net.towerquest.system.Window;

public class LWJGLWindow extends Window<TextureImage> {
	LWJGLWindow(int width, int height, String title) {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			Display.setTitle(title);
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Display.destroy();
	}

	@Override
	public OpenGLRenderer getRenderer() {
		return null;
	}
	
}
