package net.towerquest.engine.render.op;

import net.towerquest.engine.physics.Rectangle;
import net.towerquest.engine.system.Image;
import net.towerquest.engine.system.Renderer;

public class DrawImageScaled extends RenderOperation {
	public Image image;
	public int x;
	public int y;
	public int w;
	public int h;

	public DrawImageScaled(Image image, int x, int y, int w, int h) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public DrawImageScaled(Image image, Rectangle r) {
		this(image, (int) r.x, (int) r.y, (int) r.width, (int) r.height);
	}
	
	@Override
	public void execute(Renderer r) {
		r.drawImage(image, x, y, w, h);
	}
	
}
