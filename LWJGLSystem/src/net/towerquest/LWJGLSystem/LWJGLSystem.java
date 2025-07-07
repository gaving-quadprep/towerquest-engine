package net.towerquest.LWJGLSystem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;

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
			window = new LWJGLWindow(sizeX, sizeY, title);
		return window;
	}

	@Override
	public TextureImage createImage(int sizeX, int sizeY) {
		// TODO Auto-generated method stub
		return null;
	}
	public ByteBuffer convertImageData(BufferedImage bufferedImage, Texture texture) {
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
 
        texture.setTextureHeight(texHeight);
        texture.setTextureWidth(texWidth);
 
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
        g.setColor(new Color(0f,0f,0f,0f));
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
			
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public SoundSystem<?> getSoundSystem() {
		// TODO Auto-generated method stub
		return null;
	}

}
