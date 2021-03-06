package com.designpatterns.app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static Database instance = new Database();
	private Connection conn;
	
	public Database(){ }

	public static Database getInstance(){
		return instance;
	}
	
	public Connection getConnection(){
		return conn;
	}
	
	public void connect() throws Exception{
		if(conn != null)
			return;
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			throw new Exception("Driver not found");
		}
		String url = String.format("jdbc:mysql://localhost:%d/test", 3306);
		conn = DriverManager.getConnection(url, "root", "");
	}
	
	public void disconnect(){
		if(conn != null){
			try { 
				conn.close();
			}catch (SQLException e){
				System.out.println("Can't close connection");
			}
		}
		conn = null;
	}
}
