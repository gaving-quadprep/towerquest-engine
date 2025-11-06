package net.towerquest.LWJGLSystem;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import net.towerquest.system.Renderer;
import net.towerquest.util.Color;

public class OpenGLRenderer implements Renderer<TextureImage> {
	LWJGLWindow parent;
	OpenGLRenderer(LWJGLWindow parent) {
		this.parent = parent;
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, parent.getWidth(), 0, parent.getHeight(), 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}

	@Override
	public void drawImage(TextureImage im, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawImage(TextureImage im, int x, int y, int w, int h) {
		glColor3f(1, 1, 1);
		glBindTexture(GL_TEXTURE_2D, im.textureId);
		glBegin(GL_QUADS);
		glVertex2f(x, y);
		glVertex2f(x+w, y);
		glVertex2f(x+w, y+h);
		glVertex2f(x, y+h);
		glEnd();
	}

	@Override
	public void drawTile(TextureImage im, int x0, int y0, int x1, int y1, int tilex0, int tiley0, int tilex1,
			int tiley1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawRect(Color color, int x0, int y0, int x1, int y1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fillRect(Color color, int x0, int y0, int x1, int y1) {
		// TODO figure out if 3b is different than 3ub
		glColor3ub((byte)color.red, (byte)color.green, (byte)color.blue);
		glBegin(GL_QUADS);
		glVertex2f(x0, y0);
		glVertex2f(x1, y0);
		glVertex2f(x1, y1);
		glVertex2f(x0, y1);
		glEnd();
	}

	@Override
	public void beginRendering() {
		// TODO Auto-generated method stub
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}

	@Override
	public void endRendering() {
		// TODO Auto-generated method stub
		Display.update();
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
