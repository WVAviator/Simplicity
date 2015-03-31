package com.wvaviator.Simplicity.Spawn;

import java.sql.SQLException;

import com.wvaviator.Simplicity.Simplicity;
import com.wvaviator.Simplicity.Players.UUIDManager;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.common.network.internal.EntitySpawnHandler;

public class SpawnHandler {

	@SubscribeEvent
	public void onPlayerSpawn(EntityJoinWorldEvent e) {
		
		if (!(e.entity instanceof EntityPlayerMP)) {
			return;
		}
		
		World world = e.world;
		
		EntityPlayerMP player = (EntityPlayerMP) e.entity;
		
		try {
			if (UUIDManager.isPlayerInDatabase(player) == false) {

				UUIDManager.addUsername(player);
				BlockPos pos = world.getSpawnPoint();
				player.setSpawnPoint(pos, true);
				
				AsyncFirstSpawn afs = new AsyncFirstSpawn(player, pos);
				afs.start();
				
			}
		
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		try {
			if (UUIDManager.didUsernameChange(player) == true) {
				
				UUIDManager.updateUsername(player);
				
			}
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		
		
		if (Simplicity.allowBed == false) {	
			BlockPos pos = world.getSpawnPoint();
			player.setSpawnPoint(pos, true);
			return;
		}
		
		//System.out.println("Getting spawn location...");
		
		BlockPos pos = null;
		try {
		pos = player.getBedSpawnLocation(player.getEntityWorld(), player.getBedLocation(), true);
		} catch (NullPointerException e1) {
			player.setSpawnPoint(world.getSpawnPoint(), true);
			return;
		}
		//System.out.println("Player spawns at: " + pos.getX() + " " + pos.getY() + " " + pos.getZ());
		player.setSpawnPoint(pos, true);
	}

}
