package com.comze_instancelabs.trapdoorspleef.nms;


import org.bukkit.World;
import org.bukkit.craftbukkit.v1_9_R1.CraftChunk;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_9_R1.Block;
import net.minecraft.server.v1_9_R1.BlockPosition;
import net.minecraft.server.v1_9_R1.BlockTrapdoor;
import net.minecraft.server.v1_9_R1.BlockTrapdoor.EnumTrapdoorHalf;
import net.minecraft.server.v1_9_R1.Chunk;
import net.minecraft.server.v1_9_R1.EnumDirection;
import net.minecraft.server.v1_9_R1.EnumSkyBlock;
import net.minecraft.server.v1_9_R1.IBlockData;
import net.minecraft.server.v1_9_R1.PacketPlayOutMapChunk;

public class NMSHandler19 implements NMSAbstraction {

	@Override
	public boolean setWoodenTrapDoorFast(World world, int x, int y, int z, byte data) {
		net.minecraft.server.v1_9_R1.World w = ((CraftWorld) world).getHandle();
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
		net.minecraft.server.v1_9_R1.World w = ((CraftWorld) world).getHandle();
		w.a(EnumSkyBlock.BLOCK, new BlockPosition(x, y, z), level);
	}

	@Override
	public int getWoodenTrapDoorLightEmission() {
		return Block.getById(96).o(null);
	}

	@Override
	public int getWoodenTrapDoorLightBlocking() {
		return Block.getById(96).m(null);
	}

	@Override
	public int getLightBlocking(int oldBlockId) {
		return Block.getById(oldBlockId).o(null);
	}

	@Override
	public int getLightEmission(int oldBlockId) {
		return Block.getById(oldBlockId).m(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void queueChunkForUpdate(Player player, int cx, int cz) {
		final org.bukkit.Chunk chunk = player.getWorld().getChunkAt(cx, cz);
		if (chunk == null || !chunk.isLoaded()) return;
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutMapChunk(((CraftChunk)chunk).getHandle(), true, '\uffff'));
	}

	@Override
	public void recalculateBlockLighting(World world, int x, int y, int z) {
		net.minecraft.server.v1_9_R1.World w = ((CraftWorld) world).getHandle();
		w.w(new BlockPosition(x, y, z));
	}
}
