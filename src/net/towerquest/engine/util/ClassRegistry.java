package net.towerquest.engine.util;

import java.lang.reflect.InvocationTargetException;

public class ClassRegistry<T> extends Registry<Class<? extends T>> {
	public T createByName(String name, Class<?>[] paramc, Object[] param) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		T t = null;

		Class<? extends T> clazz = this.get(name);
		if(clazz != null) {
			t = (T)clazz.getConstructor(paramc).newInstance(param);
		}

		return t;
	}
	
	public String getClassName(Class<? extends T> clazz) {
		return this.getName(clazz);
	}
}
