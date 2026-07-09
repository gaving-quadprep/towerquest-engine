package net.towerquest.towerquest.mod;

import net.towerquest.towerquest.GameInstance;

public interface TowerQuestMod {
	public ModInfo getModInfo();
	public void init(GameInstance inst);
}
