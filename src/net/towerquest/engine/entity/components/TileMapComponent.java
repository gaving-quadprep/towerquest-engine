package net.towerquest.engine.entity.components;

import java.util.ArrayList;
import java.util.List;

import net.towerquest.engine.entity.Component;
import net.towerquest.engine.entity.Entity;
import net.towerquest.engine.physics.CollisionCheckable;
import net.towerquest.engine.physics.Point;
import net.towerquest.engine.physics.Rectangle;

public class TileMapComponent extends AbstractCollisionComponent {
	private Entity[][] entitymap;
	/** x and y are the offset of the tilemap, width and height are the size of tiles */
	public Rectangle tiledim;
	public boolean lockEntitiesToGrid;

	public int height() {
		return entitymap[0].length;
	}
	public int width() {
		return entitymap.length;
	}
	
	@Override
	public void update(double delta) {
		for (int x = 0; x < width(); x++) {
			for (int y = 0; y < height(); y++) {
				Entity e = entitymap[x][y];
				if (e != null) {
					e.update(delta);
				}
			}
		}
	}

	/* TODO don't calculate this every update to avoid high memory usage
	 * (this would require making the entity array private and adding getters and setters)
	 */
	@Override
	public Iterable<CollisionCheckable> getHitboxes() {
		List<CollisionCheckable> hitboxes = new ArrayList<>();
		for (int x = 0; x < width(); x++) {
			for (int y = 0; y < height(); y++) {
				AbstractCollisionComponent ac = entitymap[x][y].getComponent(AbstractCollisionComponent.class);
				if (ac != null) {
					// TODO is this slow
					ac.getHitboxes().forEach(hitboxes::add);
				}
			}
		}
		return hitboxes;
	}
	
	@Override
	public Point getPosition() {
		return new Point(
				tiledim.x + ((width() * tiledim.width) / 2),
				tiledim.y + ((height() * tiledim.height) / 2)
		);
	}
	@Override
	public void setPosition(Point p) {
		tiledim.x = p.x - ((width() * tiledim.width) / 2);
		tiledim.y = p.y - ((height() * tiledim.height) / 2);
	}
}
