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

public class MsgCommand implements ICommand {
	
	private List aliases;
	public MsgCommand() {
		this.aliases = new ArrayList();
		this.aliases.add("msg");
		this.aliases.add("pm");
		this.aliases.add("tell");
		this.aliases.add("whisper");
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "msg";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/msg <player> <message>";
	}

	@Override
	public List getAliases() {
		// TODO Auto-generated method stub
		return this.aliases;
	}

	@Override
	public void execute(ICommandSender sender, String[] args)
			throws CommandException {
		
		if (args.length < 2) {
			getCommandUsage(sender);
			return;
		}
		
		String senderName = sender.getName();
		String receiverName = null;
		EntityPlayerMP receiver = null;
		
		List<EntityPlayerMP> playerList = (List<EntityPlayerMP>) MinecraftServer.getServer().getConfigurationManager().playerEntityList;
		for ( EntityPlayerMP onlinePlayer : playerList ) {
			if (onlinePlayer.getName().equalsIgnoreCase(args[0])) {
				receiverName = args[0];
				receiver = onlinePlayer;
			}
		}
		
		if (receiver == null) {
			Chat.toChat(sender, Chat.playerNotFound);
			return;
		}
		
		String messageStart = EnumChatFormatting.WHITE + "";
		StringBuilder builder = new StringBuilder();
		builder.append(messageStart);
		
		boolean loopedOnce = false;
		
		for(String s : args) {
			
		if (loopedOnce == true) {	
				builder.append(" ");
				builder.append(s);
			}
		
		loopedOnce = true;
		
		}
		
		String message = builder.toString();
		
		String senderMessage = EnumChatFormatting.LIGHT_PURPLE + "<You -> " + receiver.getName() + "> " + EnumChatFormatting.GRAY + ":" + message;
		String receiverMessage = EnumChatFormatting.LIGHT_PURPLE + "<" + sender.getName() + " -> You> " + EnumChatFormatting.GRAY + ": " + message;
		
		ChatComponentText sM = new ChatComponentText(senderMessage);
		ChatComponentText rM = new ChatComponentText(receiverMessage);
		
		sender.addChatMessage(sM);
		receiver.addChatMessage(rM);
		
		
		if (sender instanceof EntityPlayerMP && receiver instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP) sender;
		ReplyManager.saveReply(player, receiver);
		}

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
