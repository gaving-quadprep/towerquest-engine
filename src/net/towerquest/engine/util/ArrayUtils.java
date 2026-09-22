package net.towerquest.engine.util;

import java.lang.reflect.Array;

public abstract class ArrayUtils {
	public static boolean isOutOfBounds(Object[] array, int index) {
		return (index < 0) || (index >= array.length);
	}
	public static boolean isOutOfBounds2D(Object[][] array, int indexX, int indexY) {
		return ((indexX < 0) || (indexX >= array.length)) ||
				((indexY < 0) || (indexY >= array[0].length));
	}
}
