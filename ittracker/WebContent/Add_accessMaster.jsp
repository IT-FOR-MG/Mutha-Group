<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="it.muthagroup.connectionUtility.Connection_Utility"%>
<%@page import="java.sql.PreparedStatement"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
<script type="text/javascript">
function validateForm() {
	var name_access = document.getElementById("name_access"); 
	 
		if (name_access.value=="0" || name_access.value==null || name_access.value=="" || name_access.value=="null") {
			alert("Please Provide New Access Name !!!"); 
			document.getElementById("ADD").disabled = false;
			return false;
		} 
		document.getElementById("ADD").disabled = true;
		return true;
}		
</script>
<link rel="stylesheet" type="text/css" href="styles.css" /> 
<title>Access Master</title> 
</head>
<body>

<%
try {	
	int dept_id=0;
	int uid = Integer.parseInt(session.getAttribute("uid").toString());
	String uname="";
	Connection con = Connection_Utility.getConnection();
	PreparedStatement ps_uname=con.prepareStatement("select * from User_tbl where U_Id="+uid);
	ResultSet rs_uname=ps_uname.executeQuery();
	while(rs_uname.next())
	{
		uname=rs_uname.getString("U_Name");
		dept_id = rs_uname.getInt("Dept_Id");
	}
%>

<div id="container">
  <div id="top">
    <h3><strong>Add Access</strong> </h3> 
  </div>
  <div id="menu">
			<ul>
				<li><a href="IT_index.jsp">Home</a></li>
				<li><a href="IT_New_Requisition.jsp">New Requisitions</a></li>
				<li><a href="Closed_Requisitions.jsp">Closed Requisition</a></li>
				<li><a href="IT_All_Requisitions.jsp">All Requisitions</a></li> 
				<li><a href="Asset_info.jsp">Asset Info </a></li>
				<li><a href="Asset_Master.jsp">Asset Master </a></li> 
				<li><a href="Software_Access.jsp">Software Access</a></li>
				<li><a href="MISAccess.jsp">MIS Access</a></li> 
				<li><a href="IT_Reports.jsp">Reports</a></li>
				<li><a href="Graphs.jsp">Graphs</a></li>
				<li><a href="Logout.jsp">Logout<strong style="color: blue; font-size: x-small;"> <%=uname%></strong></a></li>
			</ul>
		</div>
 
  <div style="width:50%; height: 85%; padding-left: 10px;padding-bottom: 5px;padding-top: 5px;float: left;"> 
  <br/> 
  	<form action="Add_NewAccess_Controller" method="post"  onSubmit="return validateForm();">
		<table border="0">
			<tr>
				<td>Access Name :</td>
				<td> <input type="text" name="name_access" id="name_access" size="35" style="height: 20px;"/> </td>
			</tr> 
			<tr> 
			<td colspan="2" align="center"><br/><input type="submit" name="ADD" id="ADD" value="ADD" style="width: 155px;height: 35px;"/> </td>
			</tr>
			<tr>  
				<td colspan="2">
						<%
						if(request.getParameter("avail")!=null){
						%>
						<span style="color: red;"><%=request.getParameter("avail") %></span>
						<% 	
						}if(request.getParameter("success")!=null){
						%>
						<span style="color: green;"><%=request.getParameter("success") %></span>	
						<%	
						}
						else{
						%>
						&nbsp;
						<%	
						}
						%>
				</td> 
			</tr>
		</table>
	</form>	 
		<br/> 
	<strong style="font-size: 25px;"><a href="Asset_Master.jsp">BACK</a></strong>
  </div>
  <br/> 
  <div style="width:40%; height: 500px;float: right;padding-left: 10px;padding-bottom: 5px;padding-top: 5px;overflow: scroll;"> 
 	<table border="1" style="font-size: 12px; color: #333333; width: 80%; border-width: 1px; border-color: #729ea5; border-collapse: collapse;">
	<tr>
	<td style="background-color: #acc8cc; border-width: 1px; padding: 8px; border-style: solid; border-color: #729ea5; text-align: center;"><b>Available Access List</b></td> 
	</tr>
	<%
	PreparedStatement ps_acc=con.prepareStatement("select * from it_asset_accesslist_tbl");
	ResultSet rs_acc=ps_acc.executeQuery();
	while(rs_acc.next()){  
	%>
	<tr align="center">	
	<td style="border-width: 1px; padding: 8px; border-style: solid; border-color: #729ea5; text-align: center;"><%=rs_acc.getString("access_name") %></td>
	</tr>
	<% 
		}
	ps_acc.close();
	rs_acc.close();
	%>
	</table>	
	 
  </div>
  <div id="footer">
    <p class="style2"><a href="IT_index.jsp">Home</a> <a href="IT_New_Requisition.jsp">New Requisition</a> <a href="Closed_Requisitions.jsp">Closed Requisition</a> <a href="IT_All_Requisitions.jsp">All Requisitions</a> <a href="IT_Reports.jsp">Reports</a> <a href="Software_Access.jsp">Software Access</a> <a href="Logout.jsp">Logout</a><br />
    <a href="http://www.muthagroup.com">Mutha Group, Satara </a></p>
  </div>
</div>
  <%
  con.close();
   	} catch (Exception e) {
   		e.printStackTrace();
   	}
   %>
</body>
</html>