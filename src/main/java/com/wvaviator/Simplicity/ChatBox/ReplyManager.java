package com.wvaviator.Simplicity.ChatBox;

import java.util.HashMap;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class ReplyManager {

	public static HashMap replies = new HashMap();
	
	public static void saveReply(EntityPlayerMP sender, EntityPlayerMP receiver) {
		
		if (replies.containsKey(sender)) replies.remove(sender);
		if (replies.containsKey(receiver)) replies.remove(receiver);
		
		replies.put(sender, receiver);
		replies.put(receiver, sender);		
		
	}
	
	public static EntityPlayerMP getLastReply(EntityPlayerMP sender) {
		
		if (replies.containsKey(sender)) return (EntityPlayerMP) replies.get(sender);
		
		return null;
		
		
	}
	
	public static boolean hasMessaged(EntityPlayerMP sender) {
		return replies.containsKey(sender);
	}
	
	
}
