package com.comze_instancelabs.trapdoorspleef;

import com.comze_instancelabs.minigamesapi.config.ClassesConfig;

public class IClassesConfig extends ClassesConfig {

	public IClassesConfig(Main m){
		super(m, true);
		this.getConfig().options().header("Used for saving classes. Default class:");

    	this.getConfig().addDefault("config.kits.default.name", "default");
    	this.getConfig().addDefault("config.kits.default.items", "280#KNOCKBACK:1*1");
    	this.getConfig().addDefault("config.kits.default.lore", "Default");
    	this.getConfig().addDefault("config.kits.default.requires_money", false);
    	this.getConfig().addDefault("config.kits.default.requires_permission", false);
    	this.getConfig().addDefault("config.kits.default.money_amount", 100);
    	this.getConfig().addDefault("config.kits.default.permission_node", "minigames.kits.default");

    	this.getConfig().addDefault("config.kits.pro.name", "pro");
    	this.getConfig().addDefault("config.kits.pro.items", "280#KNOCKBACK:3*1");
    	this.getConfig().addDefault("config.kits.pro.lore", "Pro");
    	this.getConfig().addDefault("config.kits.pro.requires_money", false);
    	this.getConfig().addDefault("config.kits.pro.requires_permission", false);
    	this.getConfig().addDefault("config.kits.pro.money_amount", 100);
    	this.getConfig().addDefault("config.kits.pro.permission_node", "minigames.kits.pro");
    	
    	this.getConfig().options().copyDefaults(true);
    	this.saveConfig();
	}
	
}
