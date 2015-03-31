package com.wvaviator.Simplicity.Players;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.minecraft.entity.player.EntityPlayerMP;

import com.wvaviator.Simplicity.Database;

public class UUIDManager {
	
	public static String getUUID(String name) throws SQLException {
		
		String username = name.toUpperCase();
		String uuid = null;
		
		String query = "SELECT * FROM players WHERE name = '" + username + "'";
		Connection c = Database.getConnection();
		Statement stmt = null;
		
		try {
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (!rs.next()) {
				return null;
			}
			
			uuid = rs.getString("uuid");
			return uuid;
			
		} finally {
			stmt.close();
			c.close();
		}
		
	}
	
	public static boolean isPlayerInDatabase(EntityPlayerMP player) throws SQLException {
		String uuid = player.getUniqueID().toString();
		String query = "SELECT * FROM players WHERE uuid = '" + uuid + "'";
		Connection c = Database.getConnection();
		Statement stmt = null;
	
		try {
		
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (!rs.next()) {
				return false;
			}
		
			return true;
		
		} finally {
			stmt.close();
			c.close();
		}
	}
	
	public static boolean didUsernameChange(EntityPlayerMP player) throws SQLException {
		String uuid = player.getUniqueID().toString();
		String query = "SELECT * FROM players WHERE uuid = '" + uuid + "'";
		Connection c = Database.getConnection();
		Statement stmt = null;
	
		try {
		
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			String oldName = rs.getString("name");
			String newName = player.getName().toUpperCase();
			
			if (oldName.equals(newName)) {
				return false;
			}
		
			return true;
		
		} finally {
			stmt.close();
			c.close();
		}
	}
	
	public static void updateUsername(EntityPlayerMP player) throws SQLException {
		String uuid = player.getUniqueID().toString();
		String name = player.getName().toUpperCase();
		String update = "UPDATE players SET name = '" + name + "' WHERE uuid = '" + uuid + "'";
		Connection c = Database.getConnection();
		Statement stmt = null;
		
		try {
			stmt = c.createStatement();
			stmt.executeUpdate(update);
		} finally {
			stmt.close();
			c.close();
		}
	}
	
	public static void addUsername(EntityPlayerMP player) throws SQLException {
		String uuid = player.getUniqueID().toString();
		String name = player.getName().toUpperCase();
		String update = "INSERT INTO players VALUES ('" + uuid + "', '" + name + "')";
		Connection c = Database.getConnection();
		Statement stmt = null;
		
		try {
			stmt = c.createStatement();
			stmt.executeUpdate(update);
		} finally {
			stmt.close();
			c.close();
		}
	}
}
