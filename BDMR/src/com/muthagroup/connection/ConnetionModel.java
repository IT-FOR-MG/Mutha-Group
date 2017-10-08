package com.muthagroup.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnetionModel {
	static Connection con = null;	
	public static Connection getMSSqlConnection() {

		try {		
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection("jdbc:sqlserver://192.168.0.6:1433;database=ENGERP;user=UDAY;password=12345");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con; 

	} 

}
