package net.towerquest.util;

import net.towerquest.save.ISerializable;
import net.towerquest.save.SerializeAll;

@SerializeAll
public class Rectangle implements ISerializable {
	public double x;
	public double y;
	public double width;
	public double height;
	
	public Rectangle(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
}
