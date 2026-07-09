package net.towerquest.towerquest.mod;

public class IncompatibleModException extends Exception {
	private static final long serialVersionUID = -679480603381207769L;
	public IncompatibleModException(String message) {
		super(message);
	}
	
	public IncompatibleModException(ModInfo info) {
		/* this is long because you can't have any statements before the super call
		 * basically it makes it say 'version 0.7.0' instead of 'versions from 0.7.0 to 0.7.0' 
		 */
		super(String.format("Mod %s (%s) is incompatible with this version, it supports %s", 
				info.modName, info.modId, (info.minMajorVersion == info.maxMajorVersion) && 
				(info.minMinorVersion == info.maxMinorVersion)
				? String.format("version 0.%i.%i", info.minMajorVersion, info.minMinorVersion)
				: String.format("versions 0.%i.%i to 0.%i.%i",
				info.minMajorVersion, info.minMinorVersion, info.maxMajorVersion, info.maxMinorVersion)));
	}
}
