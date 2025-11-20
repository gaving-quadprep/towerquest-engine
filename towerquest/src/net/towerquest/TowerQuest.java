package net.towerquest;


import net.towerquest.entity.Entity;
import net.towerquest.entity.components.CollisionComponent;
import net.towerquest.entity.components.PositionComponent;
import net.towerquest.map.Level;
import net.towerquest.system.BaseSystem;
import net.towerquest.system.Image;
import net.towerquest.system.KeyboardEventHandler;
import net.towerquest.system.KeyboardEventHandler.KeyCode;
import net.towerquest.system.Renderer;
import net.towerquest.system.Window;
import net.towerquest.util.Color;
import net.towerquest.util.Logger;

import java.io.FileInputStream;

import net.towerquest.LWJGLSystem.LWJGLSystem;
 
public class TowerQuest {
	
	int maxFPS = 0;
	
	/** position of quad */
	float x = 400, y = 300;
	/** angle of quad rotation */
	float rotation = 0;
	 
	/** time at last frame */
	double lastFrame;
	 
	/** frames per second */
	int fps;
	/** last fps time */
	double lastFPS;
	
	boolean gameRunning = true;
	Window window;
	Renderer renderer;
	KeyboardEventHandler kbd;
	
	// private and not static, not making that mistake again
	private Level level;
	
	private Image playerImage;
	
	private BaseSystem<?, ?, ?, ?> system = new LWJGLSystem();
	
	private static double getTimeInMilliseconds() {
		return ((double)System.nanoTime()) / 1000000.0;
		//return System.currentTimeMillis();
	}
	
	public double getDelta() {
		double time = getTimeInMilliseconds();

		double delta = time - lastFrame;

		lastFrame = time;
		return delta;

	}
 
	public void start() {
		// delete later
		level = new Level();
		Entity entity = new Entity();
		entity.addComponent(new PositionComponent());
		entity.addComponent(new CollisionComponent());
		level.addEntity(entity);
		
		system.init();
		
		window = system.createWindow(640, 480, "TowerQuest");
		Logger.instance.log("Window Created");
 
		renderer = window.getRenderer();
		Logger.instance.log("OpenGL initialized");
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTimeInMilliseconds(); // call before loop to initialise fps timer
		
		kbd = window.getKeyboardEventHandler();
 
		window.onWindowClose(() -> gameRunning = false);
		
		playerImage = system.loadPNG(TowerQuest.class.getResourceAsStream("/net/towerquest/assets/player.png"));
		
		
		while (gameRunning) {
			double delta = getDelta();
			 
			update(delta);
			renderGL();
		}
		
		Logger.instance.log("Stopping");
 
		window.destroy();
		System.exit(0);
	}
	 
	public void update(double delta) {
		// rotate quad
		rotation += (0.5f * delta);
		
		kbd.update();
		
		if (kbd.isKeyDown(KeyCode.KEY_LEFT)) x -= 0.35f * delta;
		if (kbd.isKeyDown(KeyCode.KEY_RIGHT)) x += 0.35f * delta;
		 
		if (kbd.isKeyDown(KeyCode.KEY_UP)) y += 0.35f * delta;
		if (kbd.isKeyDown(KeyCode.KEY_DOWN)) y -= 0.35f * delta;
		 
		// keep quad on the screen
		if (x < 0) x = 0;
		if (x > 640) x = 640;
		if (y < 0) y = 0;
		if (y > 480) y = 480;
		
		level.update(delta);
		
		updateFPS(); // update FPS Counter
	}
	 
	/** 
	 * Calculate how many milliseconds have passed 
	 * since last frame.
	 * 
	 * @return milliseconds passed since last frame 
	 */
	 
	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	 
	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTimeInMilliseconds() - lastFPS > 1000) {
			Logger.instance.log("FPS: " + fps);
			//Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
 
	public void renderGL() {
		
		/*GL11.glColor3f((float) Math.abs(Math.sin((double)System.currentTimeMillis()/1000)), 
				0.5f, 1.0f);

		// draw quad
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0);
		GL11.glRotatef(rotation, 0f, 0f, 1f);
		GL11.glTranslatef(-x, -y, 0);
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x - 50, y - 50);
		GL11.glVertex2f(x + 50, y - 50);
		GL11.glVertex2f(x + 50, y + 50);
		GL11.glVertex2f(x - 50, y + 50);
		GL11.glEnd();
		GL11.glPopMatrix();*/
		
		renderer.beginRendering();
		renderer.fillRect(new Color(255, 0, 0), (int) x - 50,(int) y - 50,(int) x + 50,(int) y + 50);
		renderer.drawTile(playerImage, (int) x - 50,(int) y - 50,(int) x + 50,(int) y + 50, 0, 0, 9, 9);
		renderer.endRendering();
		window.update();
	}
	 
	public static void main(String[] argv) {
		Logger.instance.log("Game started");
		
		TowerQuest towerQuest = new TowerQuest();
		towerQuest.start();
	}
}