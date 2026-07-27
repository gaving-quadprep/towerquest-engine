package net.towerquest.engine.util;

import net.towerquest.serialization.Serializable;

/**
 * 
 * they call me Roy G. Biv because I'm visibly on the spectrum
 *
 */

public class Color implements Serializable {
	// i would use byte but java can't have unsigned values
	// which is stupid, because why would you want a SIGNED byte
	// i can't think of any situation where you would use something with such a small data range
	// and still want negative values
	// </rant>
	public int red;
	public int green;
	public int blue;
	//
	public int alpha = 255; // opaque by default
	
	public Color() {}
	public Color(byte red, byte green, byte blue) {
		this();
		this.red = red < 0 ? 256 + red : red;
		this.green = green < 0 ? 256 + green : green;
		this.blue = blue < 0 ? 256 + blue : blue;
	}
	public Color(int red, int green, int blue) {
		this((byte)red, (byte)green, (byte)blue);
	}
	public Color(byte red, byte green, byte blue, byte alpha) {
		this(red, green, blue);
		this.alpha = alpha < 0 ? 256 + alpha : alpha;
	}
	public Color(int red, int green, int blue, int alpha) {
		this((byte)red, (byte)green, (byte)blue, (byte)alpha);
	}
	
	public Color(int argb) {
		alpha = (argb >> 24) & 0xFF;
		red = (argb >> 16) & 0xFF;
		green = (argb >> 8) & 0xFF;
		blue = (argb >> 0) & 0xFF;
	}
	
	public int toARGB() {
		return (alpha << 24) + (red << 16) + (green << 8) + blue;
	}
}
