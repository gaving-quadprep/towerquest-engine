package net.towerquest.save;

public interface ISerializable {
	public default SerializedData serialize() {
		SerializedData sd = new SerializedData();
		
		return sd;
	}
	public default void deserialize(SerializedData sd) {
		
	}
}
