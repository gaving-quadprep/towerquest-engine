package net.towerquest.engine.mod;

import net.towerquest.engine.GameInstance;

public interface TowerQuestMod {
	public ModInfo getModInfo();
	public void init(GameInstance inst);
}
