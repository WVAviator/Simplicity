package com.wvaviator.Simplicity.ChatBox;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class BroadcastCommand implements ICommand {
	
	private List aliases;
	public BroadcastCommand() {
		this.aliases = new ArrayList();
		this.aliases.add("broadcast");
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "broadcast";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/broadcast <message>";
	}

	@Override
	public List getAliases() {
		// TODO Auto-generated method stub
		return this.aliases;
	}

	@Override
	public void execute(ICommandSender sender, String[] args)
			throws CommandException {
		
		int length = args.length;
		
		if (length == 0) {
			getCommandUsage(sender);
			return;
		}
		
		String message = EnumChatFormatting.LIGHT_PURPLE + "<Broadcast>" + EnumChatFormatting.RED;

		StringBuilder builder = new StringBuilder();
		
		builder.append(message);
		
		for(String s : args) {
			builder.append(" ");
			builder.append(s);
		}
		
		
		ChatComponentText broadcast = new ChatComponentText(builder.toString());
		
		List<EntityPlayerMP> playerList = (List<EntityPlayerMP>) MinecraftServer.getServer().getConfigurationManager().playerEntityList;
		
		for ( EntityPlayerMP onlinePlayer : playerList ) {
			
			onlinePlayer.addChatComponentMessage(broadcast);
			
		}

	}

	@Override
	public boolean canCommandSenderUse(ICommandSender sender) {
		 if (sender.canUseCommand(2, getName())) {
			return true;
		}
		return false;
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
