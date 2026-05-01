package net.towerquest.LWJGLSystem;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import net.towerquest.system.Renderer;
import net.towerquest.util.Color;

public class OpenGLRenderer implements Renderer<TextureImage> {
	LWJGLWindow parent;
	// to avoid repeatedly calling
	private int height;
	OpenGLRenderer(LWJGLWindow parent) {
		this.parent = parent;
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, parent.getWidth(), 0, parent.getHeight(), 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	private void shapeStart(Color color) {
		glDisable(GL_TEXTURE_2D);
		glColor3ub((byte)color.red, (byte)color.green, (byte)color.blue);
	}

	@Override
	public void drawImage(TextureImage im, int x, int y) {
		// TODO Auto-generated method stub
		drawImage(im, x, y, im.width, im.height);
	}

	@Override
	public void drawImage(TextureImage im, int x, int y, int w, int h) {
		glEnable(GL_TEXTURE_2D);
		glColor3f(1, 1, 1);
		glBindTexture(GL_TEXTURE_2D, im.textureId);
		glBegin(GL_QUADS);
		glTexCoord2f(0.0f, 0.0f);
		glVertex2f(x, height-y);
		glTexCoord2f(1.0f, 0.0f);
		glVertex2f(x+w, height-y);
		glTexCoord2f(1.0f, 1.0f);
		glVertex2f(x+w, height-(y+h));
		glTexCoord2f(0.0f, 1.0f);
		glVertex2f(x, height-(y+h));
		glEnd();
	}

	@Override
	public void drawTile(TextureImage im, int x0, int y0, int x1, int y1, int tilex0, int tiley0, int tilex1,
			int tiley1) {
		// TODO ifx it using the bottom instead of top left as 0,0
		float scaled_tilex0 = 1 - ((float) tilex0 / (float) im.width);
		float scaled_tilex1 = 1 - ((float) tilex1 / (float) im.width);
		float scaled_tiley1 = ((float) tiley0 / (float) im.height);
		float scaled_tiley0 = ((float) tiley1 / (float) im.height);
		glEnable(GL_TEXTURE_2D);
		glColor3f(1, 1, 1);
		glBindTexture(GL_TEXTURE_2D, im.textureId);
		glBegin(GL_QUADS);
		glTexCoord2f(scaled_tilex0, scaled_tiley1);
		glVertex2f(x0, height-y0);
		glTexCoord2f(scaled_tilex1, scaled_tiley1);
		glVertex2f(x1, height-y0);
		glTexCoord2f(scaled_tilex1, scaled_tiley0);
		glVertex2f(x1, height-y1);
		glTexCoord2f(scaled_tilex0, scaled_tiley0);
		glVertex2f(x0, height-y1);
		glEnd();
	}

	@Override
	public void drawRect(Color color, int x0, int y0, int x1, int y1) {
		shapeStart(color);
		glBegin(GL_LINE_LOOP);
		glVertex2f(x0, height-y0);
		glVertex2f(x1, height-y0);
		glVertex2f(x1, height-y1);
		glVertex2f(x0, height-y1);
		glEnd();
	}

	@Override
	public void fillRect(Color color, int x0, int y0, int x1, int y1) {
		shapeStart(color);
		glBegin(GL_QUADS);
		glVertex2f(x0, height-y0);
		glVertex2f(x1, height-y0);
		glVertex2f(x1, height-y1);
		glVertex2f(x0, height-y1);
		glEnd();
	}

	@Override
	public void beginRendering() {
		// TODO Auto-generated method stub
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		height = parent.getHeight();
	}

	@Override
	public void drawLine(Color color, int x0, int y0, int x1, int y1) {
		shapeStart(color);
		glBegin(GL_LINES);
		glVertex2f(x0, height-y0);
		glVertex2f(x1, height-y1);
		glEnd();
	}

	@Override
	public void drawTri(Color color, int x0, int y0, int x1, int y1, int x2, int y2) {
		shapeStart(color);
		glBegin(GL_LINE_LOOP);
		glVertex2f(x0, height-y0);
		glVertex2f(x1, height-y1);
		glVertex2f(x2, height-y2);
		glEnd();
	}

	@Override
	public void fillTri(Color color, int x0, int y0, int x1, int y1, int x2, int y2) {
		shapeStart(color);
		glBegin(GL_TRIANGLES);
		glVertex2f(x0, height-y0);
		glVertex2f(x1, height-y1);
		glVertex2f(x2, height-y2);
		glEnd();
	}

	@Override
	public void endRendering() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTexture(TextureImage im) {
		// TODO Auto-generated method stub
		glEnable(GL_TEXTURE_2D);
		
		im.textureId = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, im.textureId);
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, im.width, im.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, im.imageData);
	}
	
}
