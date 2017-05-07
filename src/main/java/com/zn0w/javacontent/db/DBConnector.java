package com.zn0w.javacontent.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

public class DBConnector {
	
	private Connection connection;
	
	private final String URL = "jdbc:mysql://localhost:3306/recipe_website_schema";
	private final String USERNAME = "root";
	private final String PASSWORD = "root";
	
	public DBConnector() {
		try {
			Driver driver = new FabricMySQLDriver();
			DriverManager.registerDriver(driver);
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
}
