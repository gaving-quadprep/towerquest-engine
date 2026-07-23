package net.towerquest.towerquest.util;

/**
 * 
 * i dont know how to java
 * 
 * @see java.util.NFunction
 * @param <R> the type of the result of the function
 */

@FunctionalInterface
public interface MultiFunction<R> extends NFunction.Function<Object[],R> {
	@Override
	public R exec(Object... t);
}
