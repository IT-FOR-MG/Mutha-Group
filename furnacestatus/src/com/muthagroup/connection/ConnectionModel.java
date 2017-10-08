package com.muthagroup.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionModel 
{
	static Connection con=null;
public static Connection getERPServerConnection()
{
try{
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	con = DriverManager.getConnection("jdbc:sqlserver://192.168.0.6:1433;databaseName=FOUNDRYERP;user=BWAYS;password=BWAYSKING321 ");	
   
}
catch(Exception e)
{
	e.printStackTrace();
}
return con;
}  
public static Connection getMIDIServerConnection()
{
try{
	Class.forName("com.mysql.jdbc.Driver");
	con = DriverManager.getConnection("jdbc:mysql://192.168.0.7/complaintzilla", "root","root");
}
catch(Exception e)
{
	e.printStackTrace();
}
return con;
}
public static Connection getLinuxServerConnection()
{
try{
	Class.forName("com.mysql.jdbc.Driver");
	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/odk_prod", "root","root");
}
catch(Exception e)
{
	e.printStackTrace();
}
return con;
}  
}
