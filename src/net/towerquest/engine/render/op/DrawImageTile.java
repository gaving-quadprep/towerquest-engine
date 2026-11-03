package net.towerquest.engine.render.op;

import net.towerquest.engine.system.Image;
import net.towerquest.engine.system.Renderer;

public class DrawImageTile extends RenderOperation {
	public Image image;
	int x0, y0, x1, y1;
	int tilex0, tiley0, tilex1, tiley1;

	public DrawImageTile(Image image, int x0, int y0, int x1, int y1, int tilex0, int tiley0, int tilex1, int tiley1) {
		this.image = image;
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
		this.tilex0 = tilex0;
		this.tiley0 = tiley0;
		this.tilex1 = tilex1;
		this.tiley1 = tiley1;
	}
	
	@Override
	public void execute(Renderer r) {
		r.drawTile(image, x0, y0, x1, y1, tilex0, tiley0, tilex1, tiley1);
	}
	
}
