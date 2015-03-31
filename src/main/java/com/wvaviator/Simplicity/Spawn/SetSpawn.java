package com.wvaviator.Simplicity.Spawn;

import java.util.ArrayList;
import java.util.List;

import com.wvaviator.Simplicity.Chat;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;

public class SetSpawn implements ICommand {

	private List aliases;
	public SetSpawn() {
		this.aliases = new ArrayList();
		this.aliases.add("setspawn");
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "setspawn";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/setspawn";
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
			Chat.toChat(sender, Chat.noConsole);
			return;
		}
		EntityPlayerMP player = (EntityPlayerMP) sender;
		
		double x = player.getPosition().getX() + 0.50;
		double y = player.getPosition().getY();
		double z = player.getPosition().getZ() + 0.50;
		
		BlockPos spawn = new BlockPos(x, y, z);
		
		player.getEntityWorld().setSpawnPoint(spawn);
		
		Chat.toChat(player, Chat.spawnSet);
		
	}

	@Override
	public boolean canCommandSenderUse(ICommandSender sender) {
		if (sender.canUseCommand(4, getName())) {
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
