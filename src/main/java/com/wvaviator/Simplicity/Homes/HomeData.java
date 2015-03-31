package com.wvaviator.Simplicity.Homes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.wvaviator.Simplicity.Database;

import net.minecraft.entity.player.EntityPlayerMP;

public class HomeData {
	
	public static boolean hasHome(EntityPlayerMP player) throws SQLException {
		String uuid = player.getUniqueID().toString();
		String query = "SELECT uuid FROM homes WHERE uuid = '" + uuid + "'";
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
	
	public static void addHome(EntityPlayerMP player) throws SQLException {
		
		double x = player.getPosition().getX();
		double y = player.getPosition().getY();
		double z = player.getPosition().getZ();
		float yaw = player.rotationYaw;
		float pitch = player.rotationPitch;

		String uuid = player.getUniqueID().toString();
		
		String update = "INSERT INTO homes VALUES ('" + uuid + "', " + x + ", " + y + ", " + z + ", " + yaw + ", " + pitch + ")";
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
	
	public static void updateHome(EntityPlayerMP player) throws SQLException {
		
		double x = player.getPosition().getX();
		double y = player.getPosition().getY();
		double z = player.getPosition().getZ();
		float yaw = player.rotationYaw;
		float pitch = player.rotationPitch;

		String uuid = player.getUniqueID().toString();
		
		String update = "UPDATE homes SET x = " + x + ", y = " + y + ", z = " + z + ", yaw = " + yaw + ", pitch = " + pitch + " WHERE uuid = '" + uuid + "'";
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