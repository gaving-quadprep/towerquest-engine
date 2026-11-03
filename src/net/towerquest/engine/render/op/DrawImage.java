package net.towerquest.engine.render.op;

import net.towerquest.engine.system.Image;
import net.towerquest.engine.system.Renderer;

public class DrawImage extends RenderOperation {
	public Image image;
	public int x;
	public int y;
	
	public DrawImage(Image image, int x, int y) {
		this.image = image;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void execute(Renderer r) {
		r.drawImage(image, x, y);
	}
	
}
