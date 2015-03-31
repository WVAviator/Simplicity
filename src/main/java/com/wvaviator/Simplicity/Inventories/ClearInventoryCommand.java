package com.wvaviator.Simplicity.Inventories;

import java.util.ArrayList;
import java.util.List;

import com.wvaviator.Simplicity.Chat;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.BlockPos;

public class ClearInventoryCommand implements ICommand {
	
	private List aliases;
	public ClearInventoryCommand() {
		this.aliases = new ArrayList();
		this.aliases.add("clearinventory");
		this.aliases.add("clearinv");
		this.aliases.add("ci");
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "clearinventory";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/clearinventory";
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

		InventoryPlayer inv = player.inventory;
		inv.clear();

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
