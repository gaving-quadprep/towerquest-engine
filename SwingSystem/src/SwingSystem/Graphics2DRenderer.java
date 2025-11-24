package SwingSystem;

import java.awt.Graphics2D;

import net.towerquest.system.Renderer;
import net.towerquest.util.Color;

public class Graphics2DRenderer implements Renderer<BufferedImageWrapper> {
	Graphics2D g2d;
	@Override
	public void beginRendering() {
		// do nothing
	}

	@Override
	public void drawImage(BufferedImageWrapper im, int x, int y) {
		g2d.drawImage(im.image, x, y, null);
	}

	@Override
	public void drawImage(BufferedImageWrapper im, int x, int y, int w, int h) {
		g2d.drawImage(im.image, x, y, w, h, null);
	}

	@Override
	public void drawTile(BufferedImageWrapper im, int x0, int y0, int x1, int y1, int tilex0, int tiley0, int tilex1,
			int tiley1) {
		g2d.drawImage(im.image, x0, y0, x1, y1, tilex0, tiley0, tilex1, tiley1, null);
	}

	@Override
	public void drawRect(Color color, int x0, int y0, int x1, int y1) {
		g2d.setColor(new java.awt.Color(color.red, color.green, color.blue, color.alpha));
		g2d.drawRect(x0, y0, x1-x0, y1-y0);
	}

	@Override
	public void fillRect(Color color, int x0, int y0, int x1, int y1) {
		g2d.setColor(new java.awt.Color(color.red, color.green, color.blue, color.alpha));
		g2d.fillRect(x0, y0, x1-x0, y1-y0);
	}

	@Override
	public void endRendering() {
		// TODO Auto-generated method stub
		
	}

}
