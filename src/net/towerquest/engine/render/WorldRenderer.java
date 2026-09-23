package net.towerquest.engine.render;

import net.towerquest.engine.physics.IntPoint;
import net.towerquest.engine.physics.Point;
import net.towerquest.engine.physics.Rectangle;
import net.towerquest.engine.system.Image;
import net.towerquest.engine.system.Renderer;
import net.towerquest.engine.util.Color;

public class WorldRenderer {
	public Renderer renderer;

	private final Rectangle dimensions;
	private int canvasWidth;
	private int canvasHeight;
	
	// these are in order to avoid doing the same calculation multiple times
	private double pixelsPerTileX;
	private double pixelsPerTileY;
	
	// default scale is 16 pixels per tile
	public WorldRenderer(double x, double y, double w, double h) {
		this.dimensions = new Rectangle(x, y, w, h);
		this.canvasWidth = (int) (w * 16);
		this.canvasHeight = (int) (w * 16);
		updatePPT();
	}
	
	void updatePPT() {
		pixelsPerTileX = canvasWidth / dimensions.width;
		pixelsPerTileY = canvasHeight / dimensions.height;
	}
	
	public IntPoint toPixel(double x, double y) {
		return new IntPoint((int) Math.round((x-dimensions.x)*pixelsPerTileX),
				(int) Math.round((y-dimensions.y)*pixelsPerTileY)); 
	}
	public IntPoint toPixel(Point p) {
		return toPixel(p.x, p.y); 
	}

	public void drawImage(Image image, double x, double y) {
		IntPoint point = toPixel(x, y);
		renderer.drawImage(image, point.x, point.y);
	}

	public void drawImage(Image image, double x, double y, double w, double h) {
		IntPoint point0 = toPixel(x, y);
		IntPoint point1 = toPixel(x+w, y+h);
		renderer.drawImage(image, point0.x, point0.y, point1.x, point1.y);
	}
	
	public void drawTile(Image image, double x0, double y0, double x1, double y1,
			int tilex0, int tiley0, int tilex1, int tiley1) {
		IntPoint point0 = toPixel(x0, y0);
		IntPoint point1 = toPixel(x1, y1);
		renderer.drawTile(image, point0.x, point0.y, point1.x, point1.y,
				tilex0, tiley0, tilex1, tiley1);
	}
	
	public void drawRect(Color color, double x, double y, double w, double h) {
		IntPoint point0 = toPixel(x, y);
		IntPoint point1 = toPixel(x+w, y+h);
		renderer.drawRect(color, point0.x, point0.y, point1.x, point1.y);
	}
	
	public void fillRect(Color color, double x, double y, double w, double h) {
		IntPoint point0 = toPixel(x, y);
		IntPoint point1 = toPixel(x+w, y+h);
		renderer.fillRect(color, point0.x, point0.y, point1.x, point1.y);
	}

	public void drawLine(Color color, double x0, double y0, double x1, double y1) {
		IntPoint point0 = toPixel(x0, y0);
		IntPoint point1 = toPixel(x1, y1);
		renderer.drawLine(color, point0.x, point0.y, point1.x, point1.y);
	}
	
	public void drawTri(Color color, double x0, double y0, double x1, double y1, double x2, double y2) {
		IntPoint point0 = toPixel(x0, y0);
		IntPoint point1 = toPixel(x1, y1);
		IntPoint point2 = toPixel(x2, y2);
		renderer.drawTri(color, point0.x, point0.y, point1.x, point1.y, point2.x, point2.y);
	}
	
	public void fillTri(Color color, double x0, double y0, double x1, double y1, double x2, double y2) {
		IntPoint point0 = toPixel(x0, y0);
		IntPoint point1 = toPixel(x1, y1);
		IntPoint point2 = toPixel(x2, y2);
		renderer.fillTri(color, point0.x, point0.y, point1.x, point1.y, point2.x, point2.y);
	}
}
