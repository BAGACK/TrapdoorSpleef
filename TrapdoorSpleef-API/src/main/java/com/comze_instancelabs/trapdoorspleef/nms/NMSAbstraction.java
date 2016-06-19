package com.comze_instancelabs.trapdoorspleef.nms;

import org.bukkit.World;
import org.bukkit.entity.Player;

public interface NMSAbstraction {
	public boolean setWoodenTrapDoorFast(World world, int x, int y, int z, byte data);
	
	public void forceBlockLightLevel(World world, int x, int y, int z, int level);
	public void recalculateBlockLighting(World world, int x, int y, int z);
	
	public int getWoodenTrapDoorLightEmission();
	public int getWoodenTrapDoorLightBlocking();
	
	public void queueChunkForUpdate(Player player, int cx, int cz);

	public int getLightBlocking(int oldBlockId);

	public int getLightEmission(int oldBlockId);
}
