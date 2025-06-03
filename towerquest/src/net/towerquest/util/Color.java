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
	public int red;
	public int green;
	public int blue;
	public int alpha;
}
