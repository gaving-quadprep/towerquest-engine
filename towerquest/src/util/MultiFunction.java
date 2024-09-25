package util;

/**
 * 
 * @see java.util.Function
 * 
 * @param <R> the type of the result of the function
 */

@FunctionalInterface
public interface MultiFunction<R> {
	R apply(Object... t);
}
