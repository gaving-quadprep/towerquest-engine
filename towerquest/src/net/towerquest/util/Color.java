package net.towerquest.util;

import net.towerquest.save.ISerializable;
import net.towerquest.save.SerializeAll;

/**
 * 
 * they call me Roy G. Biv because I'm visibly on the spectrum
 * - the kid next to me
 */

@SerializeAll
public class Color implements ISerializable {
	// i would use byte but java can't have unsigned values
	// which is stupid, because why would you want an UNSIGNED byte
	// i can't think of any situation where you would use something with such a small data range
	// and still want negative values
	// </rant>
	public int red;
	public int green;
	public int blue;
	// i can never remember if 0 or 255 alpha is transparent
	public int alpha;
}
