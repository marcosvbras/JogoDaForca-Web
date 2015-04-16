package br.com.forca.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection() {
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: " + e.getStackTrace());
		}
		
		try{
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/forca", "root", "");						
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			throw new RuntimeException(ex);
		}
	}
}
