package net.towerquest.LWJGLSystem;

import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import net.towerquest.system.BaseSystem;
import net.towerquest.system.SoundSystem;

public class LWJGLSystem implements BaseSystem<LWJGLWindow, OpenGLRenderer, TextureImage, LWJGLKeyboard> {
	LWJGLWindow window;
	static LWJGLSystem instance;
	private IntBuffer textureIDBuffer = BufferUtils.createIntBuffer(1);
	private static final ColorModel glAlphaColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),
            new int[] {8,8,8,8},
            true,
            false,
            ComponentColorModel.TRANSLUCENT,
            DataBuffer.TYPE_BYTE);

	private static final ColorModel glColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),
            new int[] {8,8,8,0},
            false,
            false,
            ComponentColorModel.OPAQUE,
            DataBuffer.TYPE_BYTE);
	int createTextureID() {
		GL11.glGenTextures(textureIDBuffer);
		return textureIDBuffer.get(0);
	}
	
	public LWJGLSystem() {
		instance = this;
	}
	
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
			window = new LWJGLWindow(sizeX, sizeY, title);
		return window;
	}

	@Override
	public TextureImage createImage(int sizeX, int sizeY) {
		// TODO Auto-generated method stub
		return null;
	}
	public ByteBuffer convertImageData(BufferedImage bufferedImage) {
        ByteBuffer imageBuffer;
        WritableRaster raster;
        BufferedImage texImage;
 
        int texWidth = 2;
        int texHeight = 2;
 
        // find the closest power of 2 for the width and height
        // of the produced texture
        while (texWidth < bufferedImage.getWidth()) {
            texWidth *= 2;
        }
        while (texHeight < bufferedImage.getHeight()) {
            texHeight *= 2;
        }
 
        // create a raster that can be used by OpenGL as a source
        // for a texture
        if (bufferedImage.getColorModel().hasAlpha()) {
            raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE,texWidth,texHeight,4,null);
            texImage = new BufferedImage(glAlphaColorModel,raster,false,new Hashtable());
        } else {
            raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE,texWidth,texHeight,3,null);
            texImage = new BufferedImage(glColorModel,raster,false,new Hashtable());
        }
 
        // copy the source image into the produced image
        Graphics g = texImage.getGraphics();
        g.setColor(new java.awt.Color(0f,0f,0f,0f));
        g.fillRect(0,0,texWidth,texHeight);
        g.drawImage(bufferedImage,0,0,null);
 
        // build a byte buffer from the temporary image
        // that be used by OpenGL to produce a texture.
        byte[] data = ((DataBufferByte) texImage.getRaster().getDataBuffer()).getData();
 
        imageBuffer = ByteBuffer.allocateDirect(data.length);
        imageBuffer.order(ByteOrder.nativeOrder());
        imageBuffer.put(data, 0, data.length);
        imageBuffer.flip();
 
        return imageBuffer;
    }

	@Override
	public TextureImage loadPNG(InputStream pngFile) {
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(pngFile);
			
			TextureImage texture = new TextureImage(createTextureID());
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.textureId);
			texture.width = bufferedImage.getWidth();
			texture.height = bufferedImage.getHeight();
			int format;
			if (bufferedImage.getColorModel().hasAlpha()) {
				format = GL11.GL_RGBA;
			} else {
				format = GL11.GL_RGB;
			}

			texture.imageData = convertImageData(bufferedImage);
			
			//still unfinished (i copied the one for the lwjgl example because i cant figure out how textures work)
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D,
                    0,
                    format,
                    get2Fold(bufferedImage.getWidth()),
                    get2Fold(bufferedImage.getHeight()),
                    0,
                    format,
                    GL11.GL_UNSIGNED_BYTE,
                    texture.imageData);
			
			return texture;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	// i stole this from the lwjgl example
	private static int get2Fold(int fold) {
        int ret = 2;
        while (ret < fold) {
            ret *= 2;
        }
        return ret;
    }

	@Override
	public SoundSystem<?> getSoundSystem() {
		// TODO Auto-generated method stub
		return null;
	}

}
