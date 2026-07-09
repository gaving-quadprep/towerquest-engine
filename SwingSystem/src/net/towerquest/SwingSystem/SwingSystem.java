package net.towerquest.SwingSystem;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import net.towerquest.towerquest.system.BaseSystem;

public class SwingSystem implements BaseSystem<JFrameWindow,Graphics2DRenderer,BufferedImageWrapper,KeyListenerEventHandler> {

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JFrameWindow createWindow(int sizeX, int sizeY, String title) {
		JFrameWindow window = new JFrameWindow();
		window.setSize(sizeX, sizeY);
		window.setTitle(title);
		return window;
	}

	@Override
	public BufferedImageWrapper createImage(int sizeX, int sizeY) {
		return new BufferedImageWrapper(new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_ARGB));
	}

	@Override
	public BufferedImageWrapper loadPNG(InputStream pngFile) throws IOException {
		return new BufferedImageWrapper(ImageIO.read(pngFile));
	}

}
