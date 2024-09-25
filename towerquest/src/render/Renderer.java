package render;

import java.awt.image.BufferedImage;

public abstract class Renderer {
	public abstract void drawImage(BufferedImage im, int x, int y);
	public abstract void drawImage(BufferedImage im, int x, int y, int w, int h);
	public abstract void drawTile(BufferedImage im, int x0, int y0, int x1, int y1, int tilex0, int tiley0, int tilex1, int tiley1);
}
