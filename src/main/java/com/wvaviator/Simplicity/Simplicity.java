package com.wvaviator.Simplicity;

import java.io.File;
import java.sql.SQLException;

import com.wvaviator.Simplicity.ChatBox.BroadcastCommand;
import com.wvaviator.Simplicity.ChatBox.MsgCommand;
import com.wvaviator.Simplicity.Homes.HomeCommand;
import com.wvaviator.Simplicity.Homes.SetHomeCommand;
import com.wvaviator.Simplicity.Inventories.ClearInventoryCommand;
import com.wvaviator.Simplicity.Spawn.SetSpawn;
import com.wvaviator.Simplicity.Spawn.Spawn;
import com.wvaviator.Simplicity.Spawn.SpawnHandler;

import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = "simplicity", name = "Simplicity", version = "0.0.3", acceptableRemoteVersions = "*")
public class Simplicity {

	public static String modDirectory = null;
	Configuration config;
	boolean databaseExists = false;
	public static boolean allowBed = true;
	
	@EventHandler
	public void onPreInit(FMLPreInitializationEvent e) {
		
		
		this.config = new Configuration(e.getSuggestedConfigurationFile());
		this.config.load();
		
		Property bedSpawn = this.config.get(Configuration.CATEGORY_GENERAL, "Allow Bed Spawns", true);
		allowBed = bedSpawn.getBoolean();
		
		this.config.save();
		
		
		modDirectory = e.getModConfigurationDirectory().getPath() + "/Simplicity/";
		File directory = new File(modDirectory);
		
		if (!(directory.exists())) {
			directory.mkdir();
		}
		
		File database = new File(modDirectory + "simplicity.db");
		
		if (database.exists()) {
			databaseExists = true;
		}
	
		
	}
	
	@EventHandler
	public void onInit(FMLInitializationEvent e) {
		
		try {
			Database.getConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if (databaseExists == false) {
			try {
				Database.establishTables();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		MinecraftForge.EVENT_BUS.register(new SpawnHandler());
		
	}
	
	@EventHandler
	public void onStart(FMLServerStartingEvent e) {
		
		e.registerServerCommand(new HomeCommand());
		e.registerServerCommand(new SetHomeCommand());
		e.registerServerCommand(new SetSpawn());
		e.registerServerCommand(new Spawn());
		e.registerServerCommand(new ClearInventoryCommand());
		e.registerServerCommand(new BroadcastCommand());
		e.registerServerCommand(new MsgCommand());
		
		
	}
	
	
}
