package net.towerquest.engine.gui;

import net.towerquest.engine.physics.Rectangle;
import net.towerquest.engine.system.Renderer;

public class GuiImage9slice extends GuiImage {
	int leftBorder, rightBorder, topBorder, bottomBorder;
	
	@Override
	public void _render(Renderer re) {
		Rectangle dim = getDimensionsOnScreen();
		int l = (int) (dim.x + leftBorder), r = (int) ((dim.x + dim.width) - rightBorder),
				t = (int) (dim.y + rightBorder), b = (int) ((dim.y - dim.height) - rightBorder);
		int w = image.getWidth(), h = image.getHeight();
		
		// untested (probably doesnt work)

		re.drawTile(image, (int) dim.x, (int) dim.y, l, t, 0, 0, leftBorder, topBorder);
		re.drawTile(image, l, (int) dim.y, r, t, leftBorder, 0, w - rightBorder, topBorder);
		re.drawTile(image, r, (int) dim.y, (int) (dim.x + dim.width), t, w - rightBorder, 0, w, topBorder);

		re.drawTile(image, (int) dim.x, t, l, b, 0, topBorder, leftBorder, h - bottomBorder);
		re.drawTile(image, l, t, r, b, leftBorder, topBorder, w - rightBorder, h - bottomBorder);
		re.drawTile(image, r, t, (int) (dim.x + dim.width), b, w - rightBorder, topBorder, w, h - bottomBorder);
		
		re.drawTile(image, (int) dim.x, b, l, (int) (dim.y + dim.height), 0, h - bottomBorder, leftBorder, h);
		re.drawTile(image, l, b, r, (int) (dim.y + dim.height), leftBorder, h - bottomBorder, w - rightBorder, h);
		re.drawTile(image, r, b, (int) (dim.x + dim.width), b, w - rightBorder, h - bottomBorder, w, h);
	}
}
