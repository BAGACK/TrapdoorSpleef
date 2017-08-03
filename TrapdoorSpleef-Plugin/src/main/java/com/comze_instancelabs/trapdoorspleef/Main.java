package com.comze_instancelabs.trapdoorspleef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.material.TrapDoor;
import org.bukkit.plugin.java.JavaPlugin;

import com.comze_instancelabs.minigamesapi.Arena;
import com.comze_instancelabs.minigamesapi.ArenaConfigStrings;
import com.comze_instancelabs.minigamesapi.ArenaSetup;
import com.comze_instancelabs.minigamesapi.ArenaState;
import com.comze_instancelabs.minigamesapi.MinigamesAPI;
import com.comze_instancelabs.minigamesapi.PluginInstance;
import com.comze_instancelabs.minigamesapi.commands.CommandHandler;
import com.comze_instancelabs.minigamesapi.config.ArenasConfig;
import com.comze_instancelabs.minigamesapi.config.DefaultConfig;
import com.comze_instancelabs.minigamesapi.config.MessagesConfig;
import com.comze_instancelabs.minigamesapi.config.StatsConfig;
import com.comze_instancelabs.minigamesapi.util.PlayerPickupItemHelper;
import com.comze_instancelabs.minigamesapi.util.Util;
import com.comze_instancelabs.minigamesapi.util.Validator;
import com.comze_instancelabs.trapdoorspleef.nms.CraftMassBlockUpdate;
import com.comze_instancelabs.trapdoorspleef.nms.MassBlockUpdate;

public class Main extends JavaPlugin implements Listener {

	MinigamesAPI api = null;
	PluginInstance pli = null;
	static Main m = null;
	static int global_arenas_size = 30;

	HashMap<String, String> lastdamager = new HashMap<String, String>();

	public void onEnable() {
		m = this;
		api = MinigamesAPI.getAPI().setupAPI(this, "trapdoor", IArena.class, new ArenasConfig(this), new MessagesConfig(this), new IClassesConfig(this), new StatsConfig(this, false), new DefaultConfig(this, false), false);
		PluginInstance pinstance = api.pinstances.get(this);
		pinstance.addLoadedArenas(loadArenas(this, pinstance.getArenasConfig()));
		Bukkit.getPluginManager().registerEvents(this, this);
		pinstance.arenaSetup = new ArenaSetup();
		pli = pinstance;

		getConfig().addDefault("config.global_arenas_size", 30);
		getConfig().options().copyDefaults(true);
		this.saveConfig();
		global_arenas_size = getConfig().getInt("config.global_arenas_size");
		
		new PlayerPickupItemHelper(this, this::onPlayerPickup);
	}

	public static ArrayList<Arena> loadArenas(JavaPlugin plugin, ArenasConfig cf) {
		ArrayList<Arena> ret = new ArrayList<Arena>();
		FileConfiguration config = cf.getConfig();
		if (!config.isSet("arenas")) {
			return ret;
		}
		for (String arena : config.getConfigurationSection(ArenaConfigStrings.ARENAS_PREFIX).getKeys(false)) {
			if (Validator.isArenaValid(plugin, arena, cf.getConfig())) {
				ret.add(initArena(arena));
			}
		}
		return ret;
	}

	public static IArena initArena(String arena) {
		IArena a = new IArena(m, arena);
		ArenaSetup s = MinigamesAPI.getAPI().pinstances.get(m).arenaSetup;
		a.init(Util.getSignLocationFromArena(m, arena), Util.getAllSpawns(m, arena), Util.getMainLobby(m), Util.getComponentForArena(m, arena, "lobby"), s.getPlayerCount(m, arena, true), s.getPlayerCount(m, arena, false), s.getArenaVIP(m, arena));
		a.setRadius(global_arenas_size);
		return a;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		CommandHandler ch = new ICommandHandler();
		return ch.handleArgs(this, MinigamesAPI.getAPI().getPermissionGamePrefix("trapdoor"), "/" + cmd.getName(), sender, args);
	}

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
			final Player p = (Player) event.getEntity();
			if (pli.global_players.containsKey(p.getName())) {
				p.setHealth(20D);
			}
		}
	}

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		Player p = event.getPlayer();
		if (pli.global_players.containsKey(p.getName())) {
			event.setCancelled(true);
		}
	}

	public void onPlayerPickup(PlayerPickupItemHelper.CustomPickupEvent event) {
		Player p = event.getPlayer();
		if (pli.global_players.containsKey(p.getName())) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		final Player p = event.getPlayer();
		if (pli.global_players.containsKey(p.getName())) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		if (pli.global_players.containsKey(p.getName())) {
			if (pli.global_lost.containsKey(p.getName())) {
				event.setCancelled(true);
				return;
			}
			IArena a = (IArena) pli.global_players.get(p.getName());
			if (a.getArenaState() != ArenaState.INGAME) {
				event.setCancelled(true);
				return;
			}
			if (event.hasBlock()) {
				if (event.getClickedBlock().getType() == Material.TRAP_DOOR) {
					final MassBlockUpdate mbu = CraftMassBlockUpdate.createMassBlockUpdater(m, event.getClickedBlock().getWorld());
					mbu.setRelightingStrategy(MassBlockUpdate.RelightingStrategy.NEVER);
					Location origin = event.getClickedBlock().getLocation();
					for (int i = -1; i < 2; i++) {
						for (int j = -1; j < 2; j++) {
							Block b = origin.clone().add(i, 0D, j).getBlock();
							if (b.getType() == Material.TRAP_DOOR) {
								TrapDoor td = (TrapDoor) b.getType().getNewData(b.getData());
								if (td.isOpen() != true) {
									byte data = (byte) (td.getData() ^ 4);
									try {
										mbu.setWoodenTrapDoor(origin.getBlockX() + i, origin.getBlockY(), origin.getBlockZ() + j, data);
									} catch (Exception e) {
										getLogger().log(Level.WARNING, "failed setting trap door", e);
									}
								}
							}
						}
					}
					mbu.notifyClients();
					event.setCancelled(true);
				}
			}
		}
	}
}
