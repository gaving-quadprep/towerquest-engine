package net.towerquest.entity;

import java.lang.reflect.Method;
import java.util.function.Function;

public abstract class EntityType {
	public abstract Entity create();
	public abstract Method getCustomCreateFunction();
}
