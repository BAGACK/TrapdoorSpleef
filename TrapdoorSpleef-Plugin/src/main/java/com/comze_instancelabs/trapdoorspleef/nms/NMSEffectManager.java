package com.comze_instancelabs.trapdoorspleef.nms;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import com.comze_instancelabs.minigamesapi.MinigamesAPI;

public class NMSEffectManager {

	public static void createParticles(Location l, int i, int j){
		switch (MinigamesAPI.SERVER_VERSION)
		{
		case Unknown:
		default:
			MinigamesAPI.getAPI().getLogger().severe("Your Bukkit build appears to be unsupported! Please post a comment with the following string on the project page: " + Bukkit.getVersion());
			break;
		case V1_10:
		case V1_10_R1:
			NMSFunctions110.HUGE_EXPLOSION.animateAtLocation(l, i, j);
			break;
		case V1_7:
		case V1_7_R1:
			NMSFunctions172.HUGE_EXPLOSION.animateAtLocation(l, i, j);
			break;
		case V1_7_R2:
			NMSFunctions175.HUGE_EXPLOSION.animateAtLocation(l, i, j);
			break;
		case V1_7_R3:
			NMSFunctions178.HUGE_EXPLOSION.animateAtLocation(l, i, j);
			break;
		case V1_7_R4:
			NMSFunctions1710.HUGE_EXPLOSION.animateAtLocation(l, i, j);
			break;
		case V1_8:
		case V1_8_R1:
			NMSFunctions18.HUGE_EXPLOSION.animateAtLocation(l, i, j);
			break;
		case V1_8_R2:
			NMSFunctions185.HUGE_EXPLOSION.animateAtLocation(l, i, j);
			break;
		case V1_8_R3:
			NMSFunctions188.HUGE_EXPLOSION.animateAtLocation(l, i, j);
			break;
		case V1_9:
		case V1_9_R1:
			NMSFunctions19.HUGE_EXPLOSION.animateAtLocation(l, i, j);
			break;
		case V1_9_R2:
			NMSFunctions194.HUGE_EXPLOSION.animateAtLocation(l, i, j);
			break;
		}
	}
	
	public static ItemStack fakeGlow(ItemStack item){
		switch (MinigamesAPI.SERVER_VERSION)
		{
		case Unknown:
		default:
			MinigamesAPI.getAPI().getLogger().severe("Your Bukkit build appears to be unsupported! Please post a comment with the following string on the project page: " + Bukkit.getVersion());
			return null;
		case V1_10:
		case V1_10_R1:
			return NMSFunctions110.addGlow(item);
		case V1_7:
		case V1_7_R1:
			return NMSFunctions172.addGlow(item);
		case V1_7_R2:
			return NMSFunctions175.addGlow(item);
		case V1_7_R3:
			return NMSFunctions178.addGlow(item);
		case V1_7_R4:
			return NMSFunctions1710.addGlow(item);
		case V1_8:
		case V1_8_R1:
			return NMSFunctions18.addGlow(item);
		case V1_8_R2:
			return NMSFunctions185.addGlow(item);
		case V1_8_R3:
			return NMSFunctions188.addGlow(item);
		case V1_9:
		case V1_9_R1:
			return NMSFunctions19.addGlow(item);
		case V1_9_R2:
			return NMSFunctions194.addGlow(item);
		}
	}
	
	public static void createSheepFreenzyEffect(Location t){
		t.getWorld().playEffect(t, Effect.POTION_BREAK, 1);
	}
	
	public static void createMinefieldEffect(Location t){
		switch (MinigamesAPI.SERVER_VERSION)
		{
		case Unknown:
		default:
			MinigamesAPI.getAPI().getLogger().severe("Your Bukkit build appears to be unsupported! Please post a comment with the following string on the project page: " + Bukkit.getVersion());
			break;
		case V1_10:
		case V1_10_R1:
			NMSFunctions110.HUGE_EXPLOSION.animateAtLocation(t, 1, 1);
			break;
		case V1_7:
		case V1_7_R1:
			NMSFunctions172.HUGE_EXPLOSION.animateAtLocation(t, 1, 1);
			break;
		case V1_7_R2:
			NMSFunctions175.HUGE_EXPLOSION.animateAtLocation(t, 1, 1);
			break;
		case V1_7_R3:
			NMSFunctions178.HUGE_EXPLOSION.animateAtLocation(t, 1, 1);
			break;
		case V1_7_R4:
			NMSFunctions1710.HUGE_EXPLOSION.animateAtLocation(t, 1, 1);
			break;
		case V1_8:
		case V1_8_R1:
			NMSFunctions18.HUGE_EXPLOSION.animateAtLocation(t, 1, 1);
			break;
		case V1_8_R2:
			NMSFunctions185.HUGE_EXPLOSION.animateAtLocation(t, 1, 1);
			break;
		case V1_8_R3:
			NMSFunctions188.HUGE_EXPLOSION.animateAtLocation(t, 1, 1);
			break;
		case V1_9:
		case V1_9_R1:
			NMSFunctions19.HUGE_EXPLOSION.animateAtLocation(t, 1, 1);
			break;
		case V1_9_R2:
			NMSFunctions194.HUGE_EXPLOSION.animateAtLocation(t, 1, 1);
			break;
		}
	}

}
