package net.towerquest.towerquest.entity;

/**
 * Used to represent an entity that has a component of a specific type.
 * @param <T> the component type
 */
public class EntityWith<T extends Component> {
	private Entity entity;
	private Class<T> clazz;
	private EntityWith() {}
	public EntityWith(Entity entity, Class<T> clazz) {
		if (entity.getComponent(clazz) == null) {
			throw new IllegalArgumentException("Entity does not contain a component of type " + clazz.getName());
		} else {
			this.entity = entity;
			this.clazz = clazz;
		}
	}
	public Entity getEntity() {
		return entity;
	}
	public T getComponent() {
		return entity.getComponent(clazz);
	}
}
