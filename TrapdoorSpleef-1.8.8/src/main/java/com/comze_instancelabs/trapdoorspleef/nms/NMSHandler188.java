package com.comze_instancelabs.trapdoorspleef.nms;


import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.BlockTrapdoor;
import net.minecraft.server.v1_8_R3.BlockTrapdoor.EnumTrapdoorHalf;
import net.minecraft.server.v1_8_R3.Chunk;
import net.minecraft.server.v1_8_R3.ChunkCoordIntPair;
import net.minecraft.server.v1_8_R3.EnumDirection;
import net.minecraft.server.v1_8_R3.EnumSkyBlock;
import net.minecraft.server.v1_8_R3.IBlockData;

public class NMSHandler188 implements NMSAbstraction {

	@Override
	public boolean setWoodenTrapDoorFast(World world, int x, int y, int z, byte data) {
		net.minecraft.server.v1_8_R3.World w = ((CraftWorld) world).getHandle();
		Chunk chunk = w.getChunkAt(x >> 4, z >> 4);
		IBlockData blockdata = Block.getById(96).getBlockData();
		switch (data & 3)
		{
		default:
		case 0:
			blockdata = blockdata.set(BlockTrapdoor.FACING, EnumDirection.NORTH);
			break;
		case 1:
			blockdata = blockdata.set(BlockTrapdoor.FACING, EnumDirection.SOUTH);
			break;
		case 2:
			blockdata = blockdata.set(BlockTrapdoor.FACING, EnumDirection.WEST);
			break;
		case 3:
			blockdata = blockdata.set(BlockTrapdoor.FACING, EnumDirection.EAST);
			break;
		}
		blockdata = blockdata.set(BlockTrapdoor.OPEN, Boolean.valueOf((data & 4) == 4));
		blockdata = blockdata.set(BlockTrapdoor.HALF, ((data & 8) == 0) ? EnumTrapdoorHalf.BOTTOM : EnumTrapdoorHalf.TOP);
		return chunk.a(new BlockPosition(x & 0x0f, y, z & 0x0f), blockdata) != null;
	}

	@Override
	public void forceBlockLightLevel(World world, int x, int y, int z, int level) {
		net.minecraft.server.v1_8_R3.World w = ((CraftWorld) world).getHandle();
		w.a(EnumSkyBlock.BLOCK, new BlockPosition(x, y, z), level);
	}

	@Override
	public int getWoodenTrapDoorLightEmission() {
		return Block.getById(96).r();
	}

	@Override
	public int getWoodenTrapDoorLightBlocking() {
		return Block.getById(96).p();
	}

	@Override
	public int getLightBlocking(int oldBlockId) {
		return Block.getById(oldBlockId).r();
	}

	@Override
	public int getLightEmission(int oldBlockId) {
		return Block.getById(oldBlockId).p();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void queueChunkForUpdate(Player player, int cx, int cz) {
		((CraftPlayer) player).getHandle().chunkCoordIntPairQueue.add(new ChunkCoordIntPair(cx, cz));
	}

	@Override
	public void recalculateBlockLighting(World world, int x, int y, int z) {
		net.minecraft.server.v1_8_R3.World w = ((CraftWorld) world).getHandle();
		w.x(new BlockPosition(x, y, z));
	}
}
