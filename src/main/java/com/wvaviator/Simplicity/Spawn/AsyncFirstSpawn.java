package com.wvaviator.Simplicity.Spawn;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;

public class AsyncFirstSpawn extends Thread{

	private EntityPlayerMP player;
	private BlockPos pos;
	public AsyncFirstSpawn(EntityPlayerMP player, BlockPos pos) {
		this.player = player;
		this.pos = pos;
		
	}
	
	public void run(){
		
		System.out.println("Executing async task...");
		try {
			this.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Async task complete");
		
		player.setPositionAndUpdate(pos.getX() + 0.50, pos.getY(), pos.getZ() + 0.50);
		
		
	}
	
}
