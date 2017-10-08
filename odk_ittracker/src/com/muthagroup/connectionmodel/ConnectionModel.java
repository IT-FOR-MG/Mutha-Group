package com.muthagroup.connectionmodel;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionModel {
	static Connection con = null;

	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://192.168.0.7:3306/complaintzilla", "root","root");  
		} catch (Exception e) {
			e.printStackTrace(); 
		}

		return con; // returns Connection

	}

}
