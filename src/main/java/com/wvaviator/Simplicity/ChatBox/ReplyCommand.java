package com.wvaviator.Simplicity.ChatBox;

import java.util.ArrayList;
import java.util.List;

import com.wvaviator.Simplicity.Chat;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ReplyCommand implements ICommand {
	
	private List aliases;
	public ReplyCommand() {
		this.aliases = new ArrayList();
		this.aliases.add("reply");
		this.aliases.add("r");
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "reply";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/reply <message>";
	}

	@Override
	public List getAliases() {
		// TODO Auto-generated method stub
		return this.aliases;
	}

	@Override
	public void execute(ICommandSender sender, String[] args)
			throws CommandException {
		
		if (!(sender instanceof EntityPlayerMP)) {
			Chat.toChat(sender, "Console cannot use the reply command");
		}
		
		if (args.length == 0) {
			getCommandUsage(sender);
		}
		
		EntityPlayerMP player = (EntityPlayerMP) sender;
		
		 if (ReplyManager.hasMessaged(player) == false) {
			 Chat.toChat(player, Chat.noReply);
			 return;
		 }
		 
		 EntityPlayerMP receiver = ReplyManager.getLastReply(player);
		 
		 boolean isOnline = false;
		 
		 List<EntityPlayerMP> playerList = (List<EntityPlayerMP>) MinecraftServer.getServer().getConfigurationManager().playerEntityList;
			for ( EntityPlayerMP onlinePlayer : playerList ) {
				if (onlinePlayer == receiver) isOnline = true;
			}
			
		 if (isOnline == false) {
			 Chat.toChat(player, Chat.notOnline);
			 return;
		 }
		 
		 String messageStart = EnumChatFormatting.WHITE + "";
			StringBuilder builder = new StringBuilder();
			builder.append(messageStart);
			
			boolean loopedOnce = false;
			
			for(String s : args) {

				builder.append(" ");
				builder.append(s);
			
			}
			
			String message = builder.toString();
			
			String senderMessage = EnumChatFormatting.LIGHT_PURPLE + "<You -> " + receiver.getName() + "> " + EnumChatFormatting.GRAY + ":" + message;
			String receiverMessage = EnumChatFormatting.LIGHT_PURPLE + "<" + sender.getName() + " -> You> " + EnumChatFormatting.GRAY + ": " + message;
			
			ChatComponentText sM = new ChatComponentText(senderMessage);
			ChatComponentText rM = new ChatComponentText(receiverMessage);
			
			sender.addChatMessage(sM);
			receiver.addChatMessage(rM);
		
			ReplyManager.saveReply(player, receiver);
		
	}

	@Override
	public boolean canCommandSenderUse(ICommandSender sender) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args,
			BlockPos pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return false;
	}

}
