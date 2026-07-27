package net.towerquest.engine.system;

import net.towerquest.engine.util.Color;

public interface Renderer<ImageType extends Image> {
	public void beginRendering();
	public void drawImage(ImageType im, int x, int y);
	public void drawImage(ImageType im, int x, int y, int w, int h);
	public void drawTile(ImageType im, int x0, int y0, int x1, int y1, int tilex0, int tiley0, int tilex1, int tiley1);
	public void drawRect(Color color, int x0, int y0, int x1, int y1);
	public void fillRect(Color color, int x0, int y0, int x1, int y1);
	public void drawLine(Color color, int x0, int y0, int x1, int y1);
	public default void drawTri(Color color, int x0, int y0, int x1, int y1, int x2, int y2) {
		drawLine(color, x0, y0, x1, y1);
		drawLine(color, x1, y1, x2, y2);
		drawLine(color, x2, y2, x0, y0);
	}
	public void fillTri(Color color, int x0, int y0, int x1, int y1, int x2, int y2);
	public void endRendering();
	// only used for openglrenderer
	public default void addTexture(ImageType im) {};
}
