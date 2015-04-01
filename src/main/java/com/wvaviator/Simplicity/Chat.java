package com.wvaviator.Simplicity;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Chat {
	
	public static String noHome = EnumChatFormatting.LIGHT_PURPLE + "You haven't set a home! Do /sethome first!";
	public static String noConsole = "Only players can do that!";
	public static String home = EnumChatFormatting.LIGHT_PURPLE + "Welcome home!";
	public static String setHome = EnumChatFormatting.LIGHT_PURPLE + "You have set your home!";
	public static String spawnSet = EnumChatFormatting.LIGHT_PURPLE + "Spawn set!";
	public static String clearInv = EnumChatFormatting.LIGHT_PURPLE + "Inventory cleared!";
	public static String playerNotFound = EnumChatFormatting.LIGHT_PURPLE + "Player not found!";
	public static String noReply = EnumChatFormatting.LIGHT_PURPLE +  "Nobody to reply to!";
	public static String notOnline = EnumChatFormatting.LIGHT_PURPLE + "That player is no longer online!";
	
	
	public static void toChat(ICommandSender sender, String message) {
		ChatComponentText text = new ChatComponentText(message);
		sender.addChatMessage(text);
	}
	
	public static void toChat(EntityPlayerMP player, String message) {
		ChatComponentText text = new ChatComponentText(message);
		player.addChatMessage(text);
	}
	
}
