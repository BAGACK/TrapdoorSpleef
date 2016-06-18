package com.comze_instancelabs.trapdoorspleef.nms;

import java.util.HashSet;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class NMSEffectManager {

	public static void createParticles(Location l, int i, int j){
		try{
			String version = Bukkit.getServer().getClass().getPackage().getName().substring(Bukkit.getServer().getClass().getPackage().getName().lastIndexOf(".") + 1);
			if(version.contains("1_7_R1")){
				NMSFunctions172 explosion = NMSFunctions172.HUGE_EXPLOSION;
				explosion.animateAtLocation(l, i, j);
			}else if(version.contains("1_7_R2")){
				NMSFunctions175 explosion = NMSFunctions175.HUGE_EXPLOSION;
				explosion.animateAtLocation(l, i, j);
			}else if(version.contains("1_7_R3")){
				NMSFunctions178 explosion = NMSFunctions178.HUGE_EXPLOSION;
				explosion.animateAtLocation(l, i, j);
			}else if(version.contains("1_7_R4")){
				NMSFunctions1710 explosion = NMSFunctions1710.HUGE_EXPLOSION;
				explosion.animateAtLocation(l, i, j);
			}else if(version.contains("1_8_R1")){
				NMSFunctions18 explosion = NMSFunctions18.HUGE_EXPLOSION;
				explosion.animateAtLocation(l, i, j);
			}else if(version.contains("1_9_R1")){
				NMSFunctions19 explosion = NMSFunctions19.HUGE_EXPLOSION;
				explosion.animateAtLocation(l, i, j);
			}else if(version.contains("1_9_R2")){
				NMSFunctions194 explosion = NMSFunctions194.HUGE_EXPLOSION;
				explosion.animateAtLocation(l, i, j);
			}else if(version.contains("1_10_R1")){
				NMSFunctions110 explosion = NMSFunctions110.HUGE_EXPLOSION;
				explosion.animateAtLocation(l, i, j);
			}else{
				//fallback
				NMSFunctions172 explosion = NMSFunctions172.HUGE_EXPLOSION;
				explosion.animateAtLocation(l, i, j);
			}
		}catch(Exception e){
			System.out.println("Your Bukkit build appears to be unsupported! Please post a comment with the following string on the project page: " + Bukkit.getVersion());
		}
	}
	
	public static ItemStack fakeGlow(ItemStack item){
		try{
			String version = Bukkit.getServer().getClass().getPackage().getName().substring(Bukkit.getServer().getClass().getPackage().getName().lastIndexOf(".") + 1);
			if(version.contains("1_7_R1")){
				return NMSFunctions172.addGlow(item);
			}else if(version.contains("1_7_R2")){
				return NMSFunctions175.addGlow(item);
			}else if(version.contains("1_7_R3")){
				return NMSFunctions178.addGlow(item);
			}else if(version.contains("1_7_R4")){
				return NMSFunctions1710.addGlow(item);
			}else if(version.contains("1_8_R1")){
				return NMSFunctions18.addGlow(item);
			}else if(version.contains("1_9_R1")){
				return NMSFunctions19.addGlow(item);
			}else if(version.contains("1_9_R2")){
				return NMSFunctions194.addGlow(item);
			}else if(version.contains("1_10_R1")){
				return NMSFunctions110.addGlow(item);
			}else{
				//fallback
				return NMSFunctions172.addGlow(item);
			}
		}catch(Exception e){
			System.out.println("Your Bukkit build appears to be unsupported! Please post a comment with the following string on the project page: " + Bukkit.getVersion());
			return item;
		}
	}
	
	public static void createSheepFreenzyEffect(Location t){
		t.getWorld().playEffect(t, Effect.POTION_BREAK, 1);
	}
	
	public static void createMinefieldEffect(Location t){
		try{
			String version = Bukkit.getServer().getClass().getPackage().getName().substring(Bukkit.getServer().getClass().getPackage().getName().lastIndexOf(".") + 1);
			if(version.contains("1_7_R1")){
				NMSFunctions172 effect = NMSFunctions172.HUGE_EXPLOSION;
				effect.animateAtLocation(t, 1, 1);
			}else if(version.contains("1_7_R2")){
				NMSFunctions175 effect = NMSFunctions175.HUGE_EXPLOSION;
				effect.animateAtLocation(t, 1, 1);
			}else if(version.contains("1_7_R3")){
				NMSFunctions175 effect = NMSFunctions175.HUGE_EXPLOSION;
				effect.animateAtLocation(t, 1, 1);
			}else if(version.contains("1_7_R4")){
				NMSFunctions1710 effect = NMSFunctions1710.HUGE_EXPLOSION;
				effect.animateAtLocation(t, 1, 1);
			}else if(version.contains("1_8_R1")){
				NMSFunctions18 effect = NMSFunctions18.HUGE_EXPLOSION;
				effect.animateAtLocation(t, 1, 1);
			}else if(version.contains("1_9_R1")){
				NMSFunctions19 effect = NMSFunctions19.HUGE_EXPLOSION;
				effect.animateAtLocation(t, 1, 1);
			}else if(version.contains("1_9_R2")){
				NMSFunctions194 effect = NMSFunctions194.HUGE_EXPLOSION;
				effect.animateAtLocation(t, 1, 1);
			}else if(version.contains("1_10_R1")){
				NMSFunctions110 effect = NMSFunctions110.HUGE_EXPLOSION;
				effect.animateAtLocation(t, 1, 1);
			}else {
				//fallback
				NMSFunctions172 effect = NMSFunctions172.HUGE_EXPLOSION;
				effect.animateAtLocation(t, 1, 1);
			}
		}catch(Exception e){
			System.out.println("Your Bukkit build appears to be unsupported! Please post a comment with the following string on the project page: " + Bukkit.getVersion());
		}
	}

}
