package net.towerquest;


import net.towerquest.entity.Entity;
import net.towerquest.entity.components.CollisionComponent;
import net.towerquest.entity.components.PositionComponent;
import net.towerquest.map.Level;
import net.towerquest.render.WorldRenderer;
import net.towerquest.system.BaseSystem;
import net.towerquest.system.Image;
import net.towerquest.system.KeyboardEventHandler;
import net.towerquest.system.KeyboardEventHandler.KeyCode;
import net.towerquest.system.Renderer;
import net.towerquest.system.Window;
import net.towerquest.util.Color;
import net.towerquest.util.Logger;

import java.io.FileInputStream;

import SwingSystem.SwingSystem;
import net.towerquest.LWJGLSystem.LWJGLSystem;
 
public class TowerQuest {
	
	int maxFPS = 0;
	
	float x = 400, y = 300;
	 
	// time at last frame
	double lastFrame;
	 
	// frames per second
	int fps;
	// last fps time
	double lastFPS;
	
	boolean gameRunning = true;
	Window window;
	Renderer renderer;
	KeyboardEventHandler kbd;
	
	// private and not static, not making that mistake again
	private Level level;
	private WorldRenderer wr;
	private Image playerImage;
	
	private BaseSystem<?, ?, ?, ?> system = new SwingSystem();
	
	private static double getTimeInMilliseconds() {
		return ((double)System.nanoTime()) / 1000000.0;
		// System.currentTimeMillis() is less accurate
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
		Logger.instance.log("Window created");
 
		renderer = window.getRenderer();
		Logger.instance.log("Renderer initialized");
		getDelta(); // call once before loop to initialize lastFrame
		lastFPS = getTimeInMilliseconds(); // call before loop to initialize fps timer
		
		kbd = window.getKeyboardEventHandler();
 
		window.onWindowClose(() -> gameRunning = false);
		
		playerImage = system.loadPNGOrNull(TowerQuest.class.getResourceAsStream("/net/towerquest/assets/player.png"));
		
		
		while (gameRunning) {
			double delta = getDelta();
			 
			update(delta);
			render();
		}
		
		Logger.instance.log("Stopping");
 
		window.destroy();
		System.exit(0);
	}
	 
	public void update(double delta) {
		kbd.update();
		
		if (kbd.isKeyDown(KeyCode.KEY_LEFT)) x -= 0.35f * delta;
		if (kbd.isKeyDown(KeyCode.KEY_RIGHT)) x += 0.35f * delta;
		 
		if (kbd.isKeyDown(KeyCode.KEY_UP)) y += 0.35f * delta;
		if (kbd.isKeyDown(KeyCode.KEY_DOWN)) y -= 0.35f * delta;
		
		level.update(delta);
		
		updateFPS(); // update FPS Counter
	}
	
	public void updateFPS() {
		if (getTimeInMilliseconds() - lastFPS > 1000) {
			Logger.instance.log("FPS: " + fps);
			//Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
 
	public void render() {
		level.render(wr);
		
		renderer.beginRendering();
		renderer.fillRect(new Color(0, 0, 0), 0, 0, 640, 480);
		renderer.drawRect(new Color(255, 255, 0), 100, 100, 250, 250);
		renderer.fillRect(new Color(255, 0, 0), (int) x - 50,(int) y - 50,(int) x + 50,(int) y + 50);
		renderer.drawTile(playerImage, (int) x - 16,(int) y - 16,(int) x + 16,(int) y + 16, 0, 0, 16, 16);
		renderer.drawImage(playerImage, (int) x,(int) y);
		renderer.endRendering();
		window.update();
		window.sync();
	}
	 
	public static void main(String[] argv) {
		Logger.instance.log("Game started");
		
		TowerQuest towerQuest = new TowerQuest();
		towerQuest.start();
	}
}