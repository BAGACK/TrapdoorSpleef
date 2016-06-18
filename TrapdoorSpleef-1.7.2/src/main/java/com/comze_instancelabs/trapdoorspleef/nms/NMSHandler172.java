package com.comze_instancelabs.trapdoorspleef.nms;

import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_7_R1.Block;
import net.minecraft.server.v1_7_R1.Chunk;
import net.minecraft.server.v1_7_R1.ChunkCoordIntPair;
import net.minecraft.server.v1_7_R1.EnumSkyBlock;

public class NMSHandler172 implements NMSAbstraction {

	@Override
	public boolean setWoodenTrapDoorFast(World world, int x, int y, int z, byte data) {
		net.minecraft.server.v1_7_R1.World w = ((CraftWorld) world).getHandle();
		Chunk chunk = w.getChunkAt(x >> 4, z >> 4);
		return chunk.a(x & 0x0f, y, z & 0x0f, Block.e(96), data);
	}

	@Override
	public void forceBlockLightLevel(World world, int x, int y, int z, int level) {
		net.minecraft.server.v1_7_R1.World w = ((CraftWorld) world).getHandle();
		w.b(EnumSkyBlock.BLOCK, x, y, z, level);
	}

	@Override
	public int getWoodenTrapDoorLightEmission() {
		return Block.e(96).m();
	}

	@Override
	public int getWoodenTrapDoorLightBlocking() {
		return Block.e(96).k();
	}

	@Override
	public int getLightBlocking(int oldBlockId) {
		return Block.e(oldBlockId).m();
	}

	@Override
	public int getLightEmission(int oldBlockId) {
		return Block.e(oldBlockId).k();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void queueChunkForUpdate(Player player, int cx, int cz) {
		((CraftPlayer) player).getHandle().chunkCoordIntPairQueue.add(new ChunkCoordIntPair(cx, cz));
	}

	@Override
	public void recalculateBlockLighting(World world, int x, int y, int z) {
		net.minecraft.server.v1_7_R1.World w = ((CraftWorld) world).getHandle();
		w.t(x, y, z);
	}
}
