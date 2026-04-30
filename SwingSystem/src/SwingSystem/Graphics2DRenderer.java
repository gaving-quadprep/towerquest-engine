package SwingSystem;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import net.towerquest.system.Renderer;
import net.towerquest.util.Color;

public class Graphics2DRenderer implements Renderer<BufferedImageWrapper> {
	BufferStrategy bs;
	Graphics2D g2d;
	JFrameWindow parent;
	
	Graphics2DRenderer(JFrameWindow parent) {
		this.parent = parent;
	}
	
	public static java.awt.Color toNativeColor(Color color) {
		return new java.awt.Color(color.red, color.green, color.blue, color.alpha);
	}
	
	@Override
	public void beginRendering() {
		bs = parent.canvas.getBufferStrategy();
		g2d = (Graphics2D) bs.getDrawGraphics();
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
		g2d.setColor(toNativeColor(color));
		g2d.drawRect(x0, y0, x1-x0, y1-y0);
	}

	@Override
	public void fillRect(Color color, int x0, int y0, int x1, int y1) {
		g2d.setColor(toNativeColor(color));
		g2d.fillRect(x0, y0, x1-x0, y1-y0);
	}

	@Override
	public void drawLine(Color color, int x0, int y0, int x1, int y1) {
		g2d.setColor(toNativeColor(color));
		g2d.drawLine(x0, y0, x1, y1);
	}

	@Override
	public void fillTri(Color color, int x0, int y0, int x1, int y1, int x2, int y2) {
		g2d.setColor(toNativeColor(color));
		g2d.fillPolygon(new int[] {x0, x1, x2}, new int[] {y0, y1, y2}, 3);
	}

	@Override
	public void endRendering() {
		// TODO contentsLost/contentsRestored
		g2d.dispose();
		bs.show();
	}

}
