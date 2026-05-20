package net.towerquest;


import net.towerquest.entity.Entity;
import net.towerquest.entity.components.CollisionComponent;
import net.towerquest.entity.components.PositionComponent;
import net.towerquest.map.Level;
import net.towerquest.physics.Point;
import net.towerquest.physics.QuadTree;
import net.towerquest.physics.Rectangle;
import net.towerquest.render.WorldRenderer;
import net.towerquest.system.BaseSystem;
import net.towerquest.system.Image;
import net.towerquest.system.KeyboardEventHandler;
import net.towerquest.system.KeyboardEventHandler.KeyCode;
import net.towerquest.system.Renderer;
import net.towerquest.system.Window;
import net.towerquest.util.Color;
import net.towerquest.util.Logger;

import java.util.Random;

import net.towerquest.LWJGLSystem.LWJGLSystem;
import net.towerquest.SwingSystem.SwingSystem;
 
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
	
	private BaseSystem<?, ?, ?, ?> system = new LWJGLSystem();
	
	private QuadTree testTree = new QuadTree(640, 480);
	private Random testRandom = new Random();
	
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
		
		kbd.onKeyTyped((key) -> {
			int bx = testRandom.nextInt(639);
			int by = testRandom.nextInt(479);
			testTree.addRect(new Rectangle(bx, by, testRandom.nextInt(640-bx), testRandom.nextInt(480-by)));
		});
		
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
		 
		if (kbd.isKeyDown(KeyCode.KEY_UP)) y -= 0.35f * delta;
		if (kbd.isKeyDown(KeyCode.KEY_DOWN)) y += 0.35f * delta;
		
		level.update(delta);
		
		testTree.addPoint(new Point(testRandom.nextInt(testRandom.nextInt(639) + 1),testRandom.nextInt(testRandom.nextInt(479) + 1)));
		
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
		renderer.beginRendering();
		level.render(wr);
		
		renderer.drawRect(new Color(255, 255, 0), 1, 1, 100, 100);
		renderer.drawTile(playerImage, (int) x - 16,(int) y - 16,(int) x + 16,(int) y + 16, 0, 0, 16, 16);
		renderer.drawImage(playerImage, (int) x,(int) y);
		
		testTree.visualize(renderer);
		
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