package net.towerquest.save;

import java.lang.reflect.Field;

public interface ISerializable {
	public default SerializedData serialize() {
		SerializedData sd = new SerializedData();
		
		
		
		return sd;
	}
	
	public default void deserialize(SerializedData sd) {
		
	}
}
