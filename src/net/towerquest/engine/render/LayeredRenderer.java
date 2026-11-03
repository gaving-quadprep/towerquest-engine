package net.towerquest.engine.render;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.towerquest.engine.render.op.RenderOperation;
import net.towerquest.engine.system.Image;
import net.towerquest.engine.system.Renderer;
import net.towerquest.engine.util.Color;

public class LayeredRenderer<R extends Renderer<Image>> implements Renderer<Image> {
	private R r;
	private Map<Integer, List<RenderOperation>> layers = new TreeMap<>();
	public LayeredRenderer(R r) {
		this.r = r;
	}
	
	@Override
	public void beginRendering() {
		layers.clear();
		r.beginRendering();
	}

	@Override
	public void drawImage(Image im, int x, int y) {
		r.drawImage(im, x, y);
	}

	@Override
	public void drawImage(Image im, int x, int y, int w, int h) {
		r.drawImage(im, x, y, w, h);
	}

	@Override
	public void drawTile(Image im, int x0, int y0, int x1, int y1, int tilex0, int tiley0, int tilex1, int tiley1) {
		r.drawTile(im, x0, y0, x1, y1, tilex0, tiley0, tilex1, tiley1);
	}

	@Override
	public void drawRect(Color color, int x0, int y0, int x1, int y1) {
		r.drawRect(color, x0, y0, x1, y1);
	}

	@Override
	public void fillRect(Color color, int x0, int y0, int x1, int y1) {
		r.fillRect(color, x0, y0, x1, y1);
	}

	@Override
	public void drawLine(Color color, int x0, int y0, int x1, int y1) {
		r.drawLine(color, x0, y0, x1, y1);
	}

	@Override
	public void fillTri(Color color, int x0, int y0, int x1, int y1, int x2, int y2) {
		r.fillTri(color, x0, y0, x1, y1, x2, y2);
	}

	@Override
	public void endRendering() {
		for (Map.Entry<Integer, List<RenderOperation>> layer : layers.entrySet()) {
			for(RenderOperation op : layer.getValue())
				op.execute(r);
		}
		r.endRendering();
	}

}
