package com.muthagroup.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionModel 
{
	static Connection con1=null;
	static Connection con2=null;
public static Connection getConnection1()
{
try{
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	con1 = DriverManager.getConnection("jdbc:sqlserver://192.168.0.6:1433;databaseName=FOUNDRYERP;user=UDAY;password=12345");	
	
}
catch(Exception e)
{
	e.printStackTrace();
}
return con1;
}  
public static Connection getConnection2()
{
try{
	Class.forName("com.mysql.jdbc.Driver");
	con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/odk_prod", "root","root");
}
catch(Exception e)
{
	e.printStackTrace();
}
return con2;
}  
}