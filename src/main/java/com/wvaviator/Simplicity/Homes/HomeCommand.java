package com.wvaviator.Simplicity.Homes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.scenario.DelayedRunnable;
import com.wvaviator.Simplicity.Chat;
import com.wvaviator.Simplicity.Database;

import sun.net.NetworkServer;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MinecraftError;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.Teleporter;

public class HomeCommand implements ICommand {
	
	private List aliases;
	public HomeCommand() {
		this.aliases = new ArrayList();
		this.aliases.add("home");
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "home";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/home";
	}

	@Override
	public List getAliases() {
		// TODO Auto-generated method stub
		return this.aliases;
	}

	@Override
	public void execute(ICommandSender sender, String[] args)
			throws CommandException {
		
		if(!(sender instanceof EntityPlayerMP)) {
			Chat.toChat(sender, Chat.noConsole);
			return;
		}
		
		EntityPlayerMP player = (EntityPlayerMP) sender;
		
		String uuid = player.getUniqueID().toString();
		
		String query = "SELECT * FROM homes WHERE uuid = '" + uuid + "'";
		Connection c = null;
		
		try {
			c = Database.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		Statement stmt = null;
		
		try {
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (!rs.next()) {
				Chat.toChat(player, Chat.noHome);
				return;
			}	
			
			double x = rs.getDouble("x");
			double y = rs.getDouble("y");
			double z = rs.getDouble("z");
			float yaw = rs.getFloat("yaw");
			float pitch = rs.getFloat("pitch");
			
			
			player.setPositionAndUpdate(x, y, z);
			player.setRotationYawHead(yaw);
			return;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean canCommandSenderUse(ICommandSender sender) {
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
