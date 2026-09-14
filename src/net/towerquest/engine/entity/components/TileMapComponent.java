package net.towerquest.engine.entity.components;

import net.towerquest.engine.entity.Component;
import net.towerquest.engine.entity.Entity;

@DependsOn(CollisionComponent.class)
public class TileMapComponent extends Component {
	// either of these could work
	public Entity[] tiles;
	public int[][] map;
	// public Entity[][] map;
}
