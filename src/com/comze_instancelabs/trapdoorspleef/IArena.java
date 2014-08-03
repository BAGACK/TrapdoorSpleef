package com.comze_instancelabs.trapdoorspleef;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import com.comze_instancelabs.minigamesapi.Arena;
import com.comze_instancelabs.trapdoorspleef.nms.CraftMassBlockUpdate;
import com.comze_instancelabs.trapdoorspleef.nms.MassBlockUpdate;

public class IArena extends Arena {

	Main m = null;
	int c = 30;

	public IArena(Main m, String arena) {
		super(m, arena);
		this.m = m;
	}

	public void setRadius(int i) {
		this.c = i;
	}

	public void generateArena(Location start) {
		final MassBlockUpdate mbu = CraftMassBlockUpdate.createMassBlockUpdater(m, start.getWorld());

		mbu.setRelightingStrategy(MassBlockUpdate.RelightingStrategy.NEVER);

		int x = start.getBlockX();
		int y = start.getBlockY();
		int z = start.getBlockZ();

		int c_2 = c * c;

		for (int x_ = -c; x_ <= c; x_++) {
			for (int z_ = -c; z_ <= c; z_++) {
				if ((x_ * x_) + (z_ * z_) <= c_2) {
					mbu.setBlock(x - x_, y, z - z_, 96, 8);
					// Block b = start.getWorld().getBlockAt(new Location(start.getWorld(), x - x_, y, z - z_));
					// b.setType(Material.TRAP_DOOR);
					// b.setData((byte) 8);
				}
			}
		}
	}

	@Override
	public void stop() {
		super.stop();
		final IArena a = this;
		Bukkit.getScheduler().runTaskLater(m, new Runnable() {
			public void run() {
				c = Main.global_arenas_size;
				a.generateArena(a.getSpawns().get(0).clone().add(0D, -1D, 0D));
			}
		}, 10L);
	}

}
