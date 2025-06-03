package net.towerquest.util;

/**
 * 
 * i dont know how to java
 * 
 * @see java.util.Function
 * @param <R> the type of the result of the function
 */

@FunctionalInterface
public interface MultiFunction<R> {
	R apply(Object... t);
}
