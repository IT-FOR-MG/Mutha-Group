<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.muthagroup.conn_Url.Connection_Util"%>
<%@page import="java.sql.Connection"%>
<html>
<head>
<title>All Company Report</title>
</head>
<body>
<%
try{
Connection con =null;	
	
String comp = request.getParameter("q"); 
if(comp.equalsIgnoreCase("")){ 
%>
	<span id="getParts">
		<select name="sbOne" id="sbOne" multiple="multiple" style="height: 200px;width: 400px;overflow: scroll;font-family: Arial;font-size: 12px;"> 
		<option value="">Please Select Company Name</option> 
		</select>
	</span>
<%
}else{
		if(comp.equalsIgnoreCase("101")){	
			con = Connection_Util.getMEPLH21ERP();
		}
		if(comp.equalsIgnoreCase("102")){	
			con = Connection_Util.getMEPLH25ERP();
		}		 
		if(comp.equalsIgnoreCase("103")){
			con = Connection_Util.getFoundryERPNEWConnection();
		}
		if(comp.equalsIgnoreCase("105")){
			con = Connection_Util.getDIERPConnection();
		}
		if(comp.equalsIgnoreCase("106")){
			con = Connection_Util.getK1ERPConnection();
		}
%>
<span id="getParts"> 
		<select name="sbOne" id="sbOne" multiple="multiple" style="height: 200px;width: 400px;overflow: scroll;font-family: Arial;font-size: 12px;">
	 <%
	 PreparedStatement ps = con.prepareStatement("select * from MSTMATERIALS where MATERIAL_TYPE in(101,103) order by NAME");
	 ResultSet rs = ps.executeQuery();
	 while(rs.next()){
	 %>
       <option value="<%=rs.getString("CODE")%>"><%=rs.getString("NAME") %></option>
     <%
	 }
     %>   
   </select>
</span>
<%	
}
}catch(Exception e){
	e.printStackTrace();
}
%>
</body>
</html>