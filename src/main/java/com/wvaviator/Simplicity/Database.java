package com.wvaviator.Simplicity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModClassLoader;

public class Database {
	
	public static Connection c = null;
	static File driverFile = null;
	static String driver = "sqlite-jdbc-3.8.7.jar";
	public static boolean madeConnection = false;

	public static Connection getConnection() throws SQLException {
		
		if(madeConnection == true && (!(c.isClosed()))) {
			return c;
		}
		
		driverFile = new File(Simplicity.modDirectory + driver);
		if (!(driverFile.exists())) {
			copyDriverFromJar();
		}
		
		try {
		((ModClassLoader) Loader.instance().getModClassLoader()).addFile(driverFile);
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + Simplicity.modDirectory + "simplicity.db");
		madeConnection = true;
		return c;
		
		} catch (Exception e) {
			System.out.println("Exception in establishing database connection");
			return c;
		}
	}
	
	public static void establishTables() throws SQLException {
		
		String update = "CREATE TABLE homes (uuid VARCHAR(40), x DOUBLE NOT NULL, y DOUBLE NOT NULL, z DOUBLE NOT NULL, yaw REAL NOT NULL, pitch REAL NOT NULL, PRIMARY KEY (uuid))";
		String update2 = "CREATE TABLE players (uuid VARCHAR(40), name VARCHAR(20), PRIMARY KEY (uuid))";
		Connection c = getConnection();
		Statement stmt = null;
		
		try {
			
			stmt = c.createStatement();
			stmt.executeUpdate(update);
			stmt.executeUpdate(update2);
			
		} finally {
			
			stmt.close();
			c.close();
		}
				
	}

	private static void copyDriverFromJar() {
		
		FileOutputStream output = null;
		InputStream input = null;
		
		try {
			output = new FileOutputStream(Simplicity.modDirectory + driver);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		input = Database.class.getResourceAsStream("/" + driver);
		
		byte[] buffer = new byte[4096];
		int bytesRead = 0;
		
		try{
		
		bytesRead = input.read(buffer);
		while (bytesRead != -1) {
			output.write(buffer, 0 ,bytesRead);
			bytesRead = input.read(buffer);
		}
		output.close();
		input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
