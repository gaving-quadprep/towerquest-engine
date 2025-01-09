package net.towerquest.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import net.towerquest.util.Logger;

public abstract class EntityFactory {
	private final Map<String, Supplier<Entity>> registry = new HashMap<String, Supplier<Entity>>();
	public Entity makeEntity(String entityType) {
		Logger.instance.log("Created entity of type " + entityType);
		return registry.get(entityType).get();
	}
	public void addRegistry(Supplier<Entity> fn, String entityType) {
		if(registry.put(entityType, fn) != null)
			Logger.instance.log("Replaced entity type " + entityType);
		else
			Logger.instance.log("Created entity type " + entityType);
	}
}
