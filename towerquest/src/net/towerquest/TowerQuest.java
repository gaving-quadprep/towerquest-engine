package net.towerquest;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import net.towerquest.entity.Entity;
import net.towerquest.entity.components.EnemyAIComponent;
import net.towerquest.entity.components.PositionComponent;
import net.towerquest.map.Level;
import net.towerquest.save.SerializationUtils;
import net.towerquest.system.BaseSystem;
import net.towerquest.system.Renderer;
import net.towerquest.system.Window;
import net.towerquest.util.Logger;

import net.towerquest.LWJGLSystem.LWJGLSystem;
 
public class TowerQuest {
	
	int maxFPS = 0;
	
	/** position of quad */
	float x = 400, y = 300;
	/** angle of quad rotation */
	float rotation = 0;
	 
	/** time at last frame */
	long lastFrame;
	 
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;
	
	
	// private and not static, not making that mistake again
	private Level level;
	
	private BaseSystem<?, ?, ?, ?> system = new LWJGLSystem();
 
	public void start() {
		// delete later
		level = new Level();
		Entity entity = new Entity();
		entity.addComponent(new PositionComponent());
		level.addEntity(entity);
		
		system.init();
		
		Window window = system.createWindow(640, 480, "TowerQuest");
		Logger.instance.log("Window Created");
 
		Renderer renderer = window.getRenderer();
		Logger.instance.log("OpenGL initialized");
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTimeInMilliseconds(); // call before loop to initialise fps timer
 
		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			 
			update(delta);
			renderGL();
 
			Display.update();
			Display.sync(maxFPS); // cap fps to 60fps
		}
		
		Logger.instance.log("Stopping");
 
		Display.destroy();
		System.exit(0);
	}
	 
	public void update(int delta) {
		// rotate quad
		rotation += (0.5f * delta);
		
		Keyboard.poll();
		
		while (Keyboard.next()) {
			System.out.print(String.format("0x%02X", Keyboard.getEventKey()));
			System.out.print(':');
			System.out.println(Keyboard.getEventCharacter());
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) x -= 0.35f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) x += 0.35f * delta;
		 
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) y += 0.35f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) y -= 0.35f * delta;
		 
		// keep quad on the screen
		if (x < 0) x = 0;
		if (x > 640) x = 640;
		if (y < 0) y = 0;
		if (y > 480) y = 480;
		
		level.update(1 / 60f);
		
		updateFPS(); // update FPS Counter
	}
	 
	/** 
	 * Calculate how many milliseconds have passed 
	 * since last frame.
	 * 
	 * @return milliseconds passed since last frame 
	 */
	public int getDelta() {
		long time = getTimeInMilliseconds();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
	  
		return delta;
	}
	 
	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTimeInMilliseconds() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	 
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
	 
	public void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 640, 0, 480, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
 
	public void renderGL() {
		// Clear The Screen And The Depth Buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		GL11.glColor3f((float) Math.abs(Math.sin((double)System.currentTimeMillis()/1000)), 
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
		GL11.glPopMatrix();
	}
	 
	public static void main(String[] argv) {
		Logger.instance.log("Game started");
		
		// for testing, delete
		SerializationUtils.getAllFields(EnemyAIComponent.class);
		
		TowerQuest towerQuest = new TowerQuest();
		towerQuest.start();
	}
}