package net.towerquest.engine.mod;

public class ModInfo {
	public final String modId;
	public final String modName;
	public final String modDescription;
	public final int minMinorVersion;
	public final int minMajorVersion;
	public final int maxMinorVersion;
	public final int maxMajorVersion;
	
	public ModInfo(String modId, String modName, String modDescription,
			int minMinorVersion, int minMajorVersion, int maxMinorVersion, int maxMajorVersion) {
		this.modId = modId;
		this.modName = modName;
		this.modDescription = modDescription;
		this.minMinorVersion = minMinorVersion;
		this.minMajorVersion = minMajorVersion;
		this.maxMinorVersion = maxMinorVersion;
		this.maxMajorVersion = maxMajorVersion;
	}
	
	public ModInfo(String modId, String modName,String modDescription,
			int minorVersion, int majorVersion) {
		this(modId, modName, modDescription, minorVersion, majorVersion, minorVersion, majorVersion);
	}
}
