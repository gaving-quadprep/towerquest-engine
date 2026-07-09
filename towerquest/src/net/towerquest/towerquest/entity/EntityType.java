package net.towerquest.towerquest.entity;

import java.lang.reflect.Method;

/**
 * A class that allows for creating many entities from a template, like how classes worked in the last engine.
 * Helpful for level editors or things that spawn many of the same entity.
 */
public abstract class EntityType {
	/** Creates an entity with the default settings. */
	public abstract Entity create();
	/** Returns a {@code Method} that can be used to apply custom settings.
	 * The {@code Method}'s {@code getParameterTypes} method can be used to get a list of parameters,
	 * which is helpful for making a level editor.
	 */
	public abstract Method getCustomCreateFunction();
}
