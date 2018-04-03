package com.design.patterns.SingletonPattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class SingletonDatabaseConnection {
	
	private Connection connection;
	
	private String url = "jdbc:mysql://localhost:3306/company";
	private String user = "punamg";
	private String password = "root123";
	
	//private constructor: prevent to instantiate the Singleton class from outside the class.
	private SingletonDatabaseConnection(){
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection(url,user,password);
		
		} catch (ClassNotFoundException | SQLException ex) {
           
			System.out.println("Database Connection Creation Failed : " + ex.getMessage());

		} 
	}
	
	//private inner static class which contains instance of singleton class
	private static class DbSingleton {
		private static final SingletonDatabaseConnection INSTANCE = new SingletonDatabaseConnection();
	}
	
	//global access method for signleton instance
	public static SingletonDatabaseConnection getInstance() {
		return DbSingleton.INSTANCE;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
}

