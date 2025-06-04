package net.towerquest.LWJGLSystem;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;

import net.towerquest.system.Image;

public class TextureImage extends Image {
	ByteBuffer imageData;
	int textureId;
	int width;
	int height;
	int pixelFormat = GL11.GL_RGBA;
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public boolean supportsTransparency() {
		// TODO Auto-generated method stub
		return true;
	}

}
