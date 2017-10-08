<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.CallableStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.muthagroup.connection.ConnectionModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AJAX</title>
</head>
<body>

 <% 
 String mat_code = request.getParameter("mat_code");
 String index= request.getParameter("index");
 String ref_code=null;
 String sql=null;
 PreparedStatement ps=null,ps1=null,ps2=null,ps3=null,ps4=null;
 ResultSet rs=null,rs1=null,rs2=null,rs3=null,rs4=null,rs5=null,rs6=null,rs7=null;
 try{
Connection con1=ConnectionModel.getConnection1();
Connection con2=ConnectionModel.getConnection2();
sql = "select REF_MATCODE from MSTMATBOM where MAT_CODE='"+mat_code+"'";
ps3=con1.prepareStatement(sql);
rs3=ps3.executeQuery();
if(rs3.next())
{
		ref_code=rs3.getString("REF_MATCODE");	
}
else
{
		ref_code=mat_code;
}
%>
<select multiple name="vendor<%=index%>" id="vendor<%=index%>" required multiple >
<%
sql = "select SUB_GLACNO from MSTMATERIALGLSUB where MAT_CODE='"+ref_code+"' ";
ps = con1.prepareStatement(sql);
rs = ps.executeQuery();
while(rs.next())
{
	sql = "select SUB_GLACNO,SUBGL_LONGNAME from MSTACCTGLSUB  where SUB_GLACNO='"+rs.getString(1)+"' and SUB_GLCODE='12'  and SUBGL_LONGNAME not like 'ZZZ %'  order by SUBGL_LONGNAME ";

	ps2=con1.prepareStatement(sql);
	rs2= ps2.executeQuery();
	while(rs2.next())
	{%>
		<option<%sql="select * from stock_vendor where ref_code='"+ref_code+"' and mat_code='"+mat_code+"'";	
		ps1=con2.prepareStatement(sql);
		rs1= ps1.executeQuery();
		while(rs1.next())
		{ if(rs2.getString("SUB_GLACNO").equals(rs1.getString("vendor_code"))){%> <%="selected='selected'"%> <%}} %>  value="<%=rs2.getString("SUB_GLACNO")%>"><%=rs2.getString("SUBGL_LONGNAME")%></option>
	<%}
}	 
 }catch(Exception e){
 e.printStackTrace();
 }%>      
</select>
</body>
</html>