package net.towerquest.LWJGLSystem;

import java.nio.ByteBuffer;

import javax.swing.Renderer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import net.towerquest.system.Window;
import net.towerquest.util.Color;

public class LWJGLWindow implements Window<TextureImage, OpenGLRenderer, LWJGLKeyboard> {
	OpenGLRenderer renderer;
	LWJGLKeyboard keyboard;
	int fpsCap;
	LWJGLWindow(int width, int height, String title) {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			Display.setTitle(title);
			
			renderer = new OpenGLRenderer(this);
			keyboard = new LWJGLKeyboard();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override	
	public int getWidth() {
		return Display.getWidth();
	}
	@Override
	public int getHeight() {
		return Display.getHeight();
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Display.destroy();
	}

	@Override
	public OpenGLRenderer getRenderer() {
		return renderer;
	}
	
	@Override
	public void setIcon(TextureImage icon) {
		ByteBuffer[] icons = new ByteBuffer[3];
		icons[0] = ((TextureImage)icon.getScaledImage(16, 16)).imageData;
		icons[1] = ((TextureImage)icon.getScaledImage(32, 32)).imageData;
		icons[2] = ((TextureImage)icon.getScaledImage(128, 128)).imageData;
		Display.setIcon(icons);
	}
	
	@Override
	public void setFPSCap(int fpsCap) {
		this.fpsCap = fpsCap;
	}
	@Override
	public void setVSync(boolean vSync) {
		Display.setVSyncEnabled(vSync);
	}
	
	@Override
	public void sync() {
		Display.sync(fpsCap);
	}
	
	@Override
	public LWJGLKeyboard getKeyboardEventHandler() {
		return keyboard;
	}
}
