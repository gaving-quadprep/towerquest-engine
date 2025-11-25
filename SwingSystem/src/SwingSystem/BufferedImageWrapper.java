package SwingSystem;

import java.awt.image.BufferedImage;

import net.towerquest.system.Image;
import net.towerquest.util.Color;

public class BufferedImageWrapper implements Image {
	BufferedImage image;
	
	public BufferedImageWrapper(BufferedImage image) {
		this.image = image;
	}
	
	@Override
	public int getWidth() {
		return image.getWidth();
	}

	@Override
	public int getHeight() {
		return image.getHeight();
	}

	@Override
	public int getBPP() {
		return image.getColorModel().getPixelSize();
	}

	@Override
	public boolean supportsTransparency() {
		return image.getColorModel().hasAlpha();
	}

	@Override
	public Color getColorAt(int x, int y) {
		return new Color(image.getRGB(x, y));
	}

	@Override
	public void setColorAt(int x, int y, Color color) {
		image.setRGB(x, y, color.toARGB());
	}

	@Override
	public BufferedImageWrapper getScaledImage(int newWidth, int newHeight) {
		// untested
		return new BufferedImageWrapper((BufferedImage) image.getScaledInstance(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB));
	}
	
	public BufferedImage getBufferedImage() {
		return this.image;
	}
}
