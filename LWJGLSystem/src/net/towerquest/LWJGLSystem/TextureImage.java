package net.towerquest.LWJGLSystem;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;

import net.towerquest.system.Image;
import net.towerquest.util.Color;

public class TextureImage implements Image {
	ByteBuffer imageData;
	int textureId;
	int width;
	int height;
	int pixelFormat = GL11.GL_RGBA;

	TextureImage() {
		this(LWJGLSystem.instance.createTextureID());
	}
	
	TextureImage(int textureId) {
		this.textureId = textureId;
	}
	
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public int getTextureId() {
		return textureId;
	}

	@Override
	public boolean supportsTransparency() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getBPP() {
		switch(this.pixelFormat) {
		case GL11.GL_R3_G3_B2:
		case GL11.GL_RGBA2:
			return 8;
		case GL11.GL_RGB5_A1:
		case GL11.GL_RGBA4:
			return 16;
		case GL11.GL_RGB8:
			return 24;
		case GL11.GL_RGBA16:
			return 64;
		case GL11.GL_RGBA8:
		default:
			return 32;
		}
	}

	@Override
	public Color getColorAt(int x, int y) {
		int index = ((y * width) + x) * 4;
		return new Color(imageData.get(index),
				imageData.get(index + 1),
				imageData.get(index + 2),
				imageData.get(index + 3));
	}
	@Override
	public void setColorAt(int x, int y, Color color) {
		int index = ((y * width) + x) * 4;
		imageData.put(index, (byte) color.red);
		imageData.put(index + 1, (byte) color.green);
		imageData.put(index + 2, (byte) color.blue);
		imageData.put(index + 3, (byte) color.alpha);
	}

	@Override
	public TextureImage getScaledImage(int newWidth, int newHeight) {
		ByteBuffer b = ByteBuffer.allocate(newWidth * newHeight * 4);
		float widthRatio = ((float) width) / ((float) newWidth);
		float heightRatio = ((float) height) / ((float) newHeight);
		int index = 0;
		for (int y = 0; y < newHeight; y++) {
			int sourceY = (int) (((float)y) * heightRatio);
			for (int x = 0; x < newHeight; x++) {
				int sourceX = (int) (((float)x) * widthRatio);
				Color color = getColorAt(sourceX, sourceY);
				b.put(index++, (byte)color.red);
				b.put(index++, (byte)color.green);
				b.put(index++, (byte)color.blue);
				b.put(index++, (byte)color.alpha);
			}
		}
		
		TextureImage image = new TextureImage();
		image.imageData = b;
		image.width = newWidth;
		image.height = newHeight;
		image.pixelFormat = GL11.GL_RGBA8;
		return image;
	}
}
