package net.towerquest.engine.game;

import java.util.concurrent.atomic.AtomicBoolean;

import net.towerquest.engine.map.Level;
import net.towerquest.engine.render.WorldRenderer;
import net.towerquest.engine.system.Renderer;
import net.towerquest.engine.system.Window;

public class GameContainer {
	public final AtomicBoolean isRunning = new AtomicBoolean(true);
	private long lastFrameTime = System.nanoTime();
	private Level level;
	private Window window;
	private Renderer r;
	private WorldRenderer wr;

	public GameContainer(Window window) {
		this.window = window;
		this.r = window.getRenderer();
		this.wr = new WorldRenderer(r);
	}
	
	public GameContainer(Level level, Window window) {
		this(window);
		this.level = level;
	}
	
	public Level getLevel() {
		return level;
	}
	
	
	public void start() {
		while (isRunning.get()) {
			long time = System.nanoTime();
			double timeDiff = ((double)time - lastFrameTime) / 1_000_000_000d;
			lastFrameTime = time;
			
			/* level can be null if it hasn't loaded,
			or if the player didn't select a level yet*/
			if (level != null) {
				level.update(timeDiff);
				
				if (level.playerCamera != null) {
					wr.dimensions.copyFrom(level.playerCamera);
				}
			}
			
			wr.canvasWidth = window.getWidth();
			wr.canvasHeight = window.getHeight();
			wr.updatePPT();
			
			// TODO i forgot if this should be called before or after rendering
			window.sync();
			r.beginRendering();
			if (level != null)
				level.render(wr);
			// any GUI would be rendered here (once i implement that)
			r.endRendering();
			window.update();
		}
	}
}
