package net.towerquest.system;

import java.awt.Color;

public abstract class Renderer<ImageType extends Image> {
	public abstract void drawImage(ImageType im, int x, int y);
	public abstract void drawImage(ImageType im, int x, int y, int w, int h);
	public abstract void drawTile(ImageType im, int x0, int y0, int x1, int y1, int tilex0, int tiley0, int tilex1, int tiley1);
	public abstract void drawRect(Color color, int x0, int y0, int x1, int y1);
	public abstract void fillRect(Color color, int x0, int y0, int x1, int y1);
}
