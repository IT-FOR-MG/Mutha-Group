<%@page import="com.itextpdf.text.List"%>
<%@page import="com.muthagroup.connection.ConnectionModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Mutha Group of Engineering Satara</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap-datepicker3.css"/>
<link rel="stylesheet" href="css/bootstrap.min.css" >
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script>
  <style>
  .modal-dialog {
  width: 90%;
}


    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
      text-align: center;
    }
   
    
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 450px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      padding-top: 20px;
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {datepicker.css
      background-color: #555;
      color: white;
      padding: 1px;
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height:auto;} 
    }
    @import url(http://fonts.googleapis.com/css?family=Roboto);

/****** LOGIN MODAL ******/
.loginmodal-container {
  padding: 30px;
  max-width: 450px;
  width: 100% !important;
  background-color: #F7F7F7;
  margin: 0 auto;
  border-radius: 2px;
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  overflow: hidden;
  font-family: roboto;
}

.loginmodal-container h1 {
  text-align: center;
  font-size: 1.8em;
  font-family: roboto;
}

.loginmodal-container input[type=submit] {
  width: 100%;
  display: block;
  margin-bottom: 10px;
  position: relative;
}

.loginmodal-container input[type=text], input[type=password] {
  height: 44px;
  font-size: 16px;
  width: 100%;
  margin-bottom: 10px;
  -webkit-appearance: none;
  background: #fff;
  border: 1px solid #d9d9d9;
  border-top: 1px solid #c0c0c0;
  /* border-radius: 2px; */
  padding: 0 8px;
  box-sizing: border-box;
  -moz-box-sizing: border-box;
}

.loginmodal-container input[type=text]:hover, input[type=password]:hover {
  height: 30px;
  font-size: 16px;
  width: type="text/javascript"100%;
  margin-bottom: 10px;
  -webkit-appearance: none;
  background: #fff;
  border: 1px solid #d9d9d9;
  border-top: 1px solid #c0c0c0;
  /* border-radius: 2px; */
  padding: 0 8px;
  box-sizing: border-box;
  -moz-box-sizing: border-box;
}

.loginmodal {
  text-align: center;
  font-size: 14px;
  font-family: 'Arial', sans-serif;
  font-weight: 700;
  height: 36px;
  padding: 0 8px;
/* border-radius: 3px; */
/* -webkit-user-select: none;
  user-select: none; */
}

.loginmodal-submit {
  /* border: 1px solid #3079ed; */
  border: 0px;
  color: #fff;
  text-shadow: 0 1px rgba(0,0,0,0.1); 
  background-color: #4d90fe;
  padding: 17px 0px;
  font-family: roboto;
  font-size: 14px;
  /* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#4787ed)); */
}

.loginmodal-submit:hover {
  /* border: 1px solid #2f5bb7; */
  border: 0px;
  text-shadow: 0 1px rgba(0,0,0,0.3);
  background-color: #357ae8;
  /* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#357ae8)); */
}

.loginmodal-container a {
  text-decoration: none;
  color: #666;
  font-weight: 400;
  text-align: center;
  display: inline-block;
  opacity: 0.6;
  transition: opacity ease 0.5s;
} 

.barcodemodal-container {
  text-align: center;
  height: 200px;
  font-size: 16px;
  width: 100%;
  margin-bottom: 10px;
  -webkit-appearance: none;
  background: #fff;
  border: 1px solid #d9d9d9;
  border-top: 1px solid #c0c0c0;
  /* border-radius: 2px; */
  padding: 0 8px;
  box-sizing: border-box;
  -moz-box-sizing: border-box;
}
.ERPmodal-container {
  text-align: center;
  max-height: 100%;
  font-size: 16px;
  width: 100%;
  margin-bottom: 10px;
  -webkit-appearance: none;
  background: #fff;
  border: 1px solid #d9d9d9;
  border-top: 1px solid #c0c0c0;
  /* border-radius: 2px; */
  padding: 0 8px;
  box-sizing: border-box;
  -moz-box-sizing: border-box;
}

.login-help{
  font-size: 12px;
}
  </style>
 
</head>

<body >
<%
if ((session.getAttribute("user")!=null)) 
{ 
	String username=(String)session.getAttribute("user"); 
	  
	%>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"><img src="images/mutha.jpg" class="img-circle" alt="Mutha logo" width=50 height=30> </a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Daily Vendor Stock</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a  data-toggle="modal" href="#loginModal"><span class="glyphicon glyphicon-log-out"></span><b> <%=username%></b> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="modal fade active" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    	 <div class="modal-dialog">
			<div class="loginmodal-container">
				<h1>Are you want to Logout</h1><br>
				  <form action="Logout" method="post">
					<input type="submit" name="logout" class="login loginmodal-submit" value="Logout">
				  </form>
			</div>
		</div>
</div>

<table class="table table-condensed table-bordered small" >      
 <tr><th style="width:1%">Item Name</th><th style="width:25%"> Customer</th><th style="width:3%">Schedule</th><th style="width:3%">Dispatch</th><th style="width:25%">Vendor</th><th style="width:3%">ERP Stock</th><th style="width:3%">Max Limit</th><th style="width:3%">Balence</th><th style="width:3%">Casting Rec.</th><th style="width:3%">Pour</th><th style="width:3%">Prod</th><th style="width:3%">Packed</th><th style="width:3%">For Pack</th><th style="width:3%">For Insp</th><th style="width:3%">Total</th>
  <%
  LinkedHashMap<Long,String> item=new LinkedHashMap<Long,String>();  
  item=(LinkedHashMap<Long,String>)request.getAttribute("itemList");
 %>
 </tr>
 <%
    for (Map.Entry<Long,String> entry : item.entrySet()) {
%>
<tr>
    <td><%=entry.getValue()%></td>
    <td>
    <table class="table table-condensed table-bordered small" >      
  <%
  LinkedHashMap<Long,String> customer=new LinkedHashMap<Long,String>();  
  customer=(LinkedHashMap<Long,String>)request.getAttribute("customerList"+entry.getKey());
    for (Map.Entry<Long,String> custentry : customer.entrySet()) {
%>
 <tr>
<td><%=custentry.getValue()%></td>
</tr>
 <%
 }
 %>
 </table>
 </td>
 <td>
 <table class="table table-condensed table-bordered small" > 
 <%
 ArrayList<Long> schedule=new ArrayList<Long>();
 schedule=(ArrayList<Long>)request.getAttribute("schedule"+entry.getKey());
 for(int j=0;j<schedule.size();j++)
 {
 %>
 <tr>
<td><%=schedule.get(j)%></td>
</tr>
 <%
 }
 %>
 </table>
 </td>
  <td>
 <table class="table table-condensed table-bordered small" > 
 <%
 ArrayList<Long> dispatch=new ArrayList<Long>();
 dispatch=(ArrayList<Long>)request.getAttribute("dispatch"+entry.getKey());
 for(int j=0;j<dispatch.size();j++)
 {
 %>
 <tr>
<td><%=dispatch.get(j)%></td>
</tr>
 <%
 }
 %>
 </table>
 </td>
 <td>
 <table class="table table-condensed table-bordered small" >      
  <%
  LinkedHashMap<Long,String> vendor=new LinkedHashMap<Long,String>();  
  vendor=(LinkedHashMap<Long,String>)request.getAttribute("vendors"+entry.getKey());
  for (Map.Entry<Long,String> vendorentry : vendor.entrySet()) {
%>
 <tr>
<td><%=vendorentry.getValue()%></td>
</tr>
 <%
 }
 %>
 </table>
 </td>
  <td>
  <table class="table table-condensed table-bordered small" >      
  <%
  LinkedHashMap<Long,Long> vendorstock=new LinkedHashMap<Long,Long>(); 
  boolean flag1=false;
  vendorstock=(LinkedHashMap<Long,Long>)request.getAttribute("vendorStock"+entry.getKey());
  for (Map.Entry<Long,String> vendorentry : vendor.entrySet()) {
	  flag1=false;
  for (Map.Entry<Long,Long> vendorstockentry : vendorstock.entrySet()) {
%>
<%if(vendorentry.getKey().equals(vendorstockentry.getValue())){ %> 
 <tr>
<td>
       <%=vendorstockentry.getKey()%>
       </td>
</tr>
       <%
       flag1=true;
       }
%>
 <%
  }
  if(flag1==false){%>
  <tr><td>
  <%=0%>
  </td></tr>
  <% }
  }
 %>
 </table> 
 </td>
 <td>
 0
 </td>
 <td>
0
 </td>
 <td>
 <table class="table table-condensed table-bordered small" > 
 <%
 
 LinkedHashMap<Long,Long> castrec=new LinkedHashMap<Long,Long>();
 boolean flag2=false;
 castrec=(LinkedHashMap<Long,Long>)request.getAttribute("castrec"+entry.getKey());
 for (Map.Entry<Long,String> vendorentry : vendor.entrySet()) {
	 flag2=false;
 for (Map.Entry<Long,Long> castrecentry : castrec.entrySet()) {	  
 %>
 <%if(vendorentry.getKey().equals(castrecentry.getValue()))
 { %> 
 <tr><td><%=castrecentry.getKey()%></td></tr>
  
  <%
  flag2=true;
  }
}
 if(flag2==false){%>
      <tr><td><%=0%></td></tr>
      <% }} %>
<%
 %>
 </table>
 </td>
 <td>
0
 </td>
 <td>
 0
 </td>
 <td>
 0
 </td>
 <td>
 0
 </td>
 <td>
 0
 </td>
 <td>
0
 </td>
 </tr>
 <%
 }
 %>
 </table>
 
 <% 
}
else
{
	response.sendRedirect("index.jsp"); 
} 
%>
</body>
</html>
