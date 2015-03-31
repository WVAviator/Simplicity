package com.wvaviator.Simplicity.Spawn;

import java.util.ArrayList;
import java.util.List;

import com.wvaviator.Simplicity.Chat;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;

public class Spawn implements ICommand {
	
	private List aliases;
	public Spawn() {
		this.aliases = new ArrayList();
		this.aliases.add("spawn");
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "spawn";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/spawn";
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
			Chat.toChat(sender,  Chat.noConsole);
			return;
		}
		EntityPlayerMP player = (EntityPlayerMP) sender;
		
		BlockPos spawn = player.getEntityWorld().getSpawnPoint();
		player.setPositionAndUpdate(spawn.getX() + 0.50, spawn.getY(), spawn.getZ() + 0.50);
		
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
