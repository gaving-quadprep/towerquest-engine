package net.towerquest.engine.entity.components;

import java.util.ArrayList;
import java.util.List;

import net.towerquest.engine.entity.Component;
import net.towerquest.engine.entity.Entity;
import net.towerquest.engine.physics.CollisionCheckable;

public class TileMapComponent extends AbstractCollisionComponent {
	public Entity[][] entitymap;

	public int height() {
		return entitymap[0].length;
	}
	public int width() {
		return entitymap.length;
	}
	
	@Override
	public void update(double delta) {
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
}
