<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="com.muthagroup.vo.MuthaGroupVO"%>
<html lang="en">
<head>
<title>Mutha Group of Engineering Satara</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/excel-bootstrap-table-filter-bundle.js"></script>
  <script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script>
  <link rel="stylesheet" href="css/excel-bootstrap-table-filter-style.css" />
   <link rel="stylesheet" href="css/bootstrap.min.css" />
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
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
    footer {
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
  max-width: 350px;
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
  border: 1px solid #b9b9b9;
  border-top: 1px solid #a0a0a0;
  -moz-box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
  -webkit-box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
  box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
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


.login-help{
  font-size: 12px;
}
  </style>
 <script type="text/javascript">
  function loadvalues()
{
var xmlhttp;
var k=document.getElementById("mobileNo").value;
var urls="value.jsp?ver="+k;
if (window.XMLHttpRequest)
{// code for IE7+, Firefox, Chrome, Opera, Safari
xmlhttp=new XMLHttpRequest();
}
else
{// code for IE6, IE5
xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState==4)
{
	document.getElementById("updated").innerHTML = xmlhttp.responseText;
}
}
xmlhttp.open("GET",urls,true);
xmlhttp.send();
}
</script>
</head>
<body>
<%
if ((session.getAttribute("user")==null)) 
{
	request.getRequestDispatcher("/index.jsp").forward(request,response);
}
else
{
	String userName=(String)session.getAttribute("user"); 
	MuthaGroupVO vo=(MuthaGroupVO)session.getAttribute("vo"); 
	
%>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
    <a class="navbar-brand"><font size="2">Visitors Gate-Pass</font><img src="images/mutha.jpg" class="img-circle" alt="Mutha logo" width=50 height=30></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <!-- <li><a data-toggle="modal" href="#RegisterModal">New Registration</a></li> -->
        <li><a data-toggle="modal" href="#EntryModal">New Entry</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a  data-toggle="modal" href="#logoutModal"><span class="glyphicon glyphicon-log-out"></span><b> <%=userName%></b> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
  
<div class="container">
    <%
    ArrayList<ArrayList<String>> visitorRecords=vo.getVisitorRecords();
    ArrayList<String> visitorMob=vo.getVisitorMob();
    ArrayList<String> empUser = vo.getEmpUser();
    if(visitorRecords.size()>0)
    {
    %>
      <table id="table" class="table table-bordered table-intel">
        <thead>
          <tr><th class="filter" >SR.No.</th><th class="filter" >First Name</th><th class="filter" >Middle Name</th><th class="filter" >Last Name</th><th class="filter" >Address</th><th class="filter" >Mobile no</th><th class="filter" >Meeting with</th><th class="filter" >Purpose</th><th class="filter" >Date</th><th class="filter" >In-Time</th><th class="filter" >Out-Time</th></tr>
   </thead>
    <%
    
    for(int i=0;i<visitorRecords.size();i++)
    {
    	%>
    	 <tbody>
    	<tr>
    	<td><%=i+1%></td>
    	<%
    	for(int j=1;j<=10;j++)
    	{
    		%>
    	    <td>
    	    <%
    	    if(j==10 && visitorRecords.get(i).get(10).equals("-")){%> 
    	    <form method="post" action="FirstServlet">
    		<input type="hidden" name="type" value="out">
    		<input type="hidden" name="outId" value=<%=visitorRecords.get(i).get(0)%>>
    		<input type="submit" value="Out" > 
    		</form>	
    		<%}else{%>
    	    <%=visitorRecords.get(i).get(j)%>
    	    <%}%>
    	    </td>
    	   <% 
    	}
    	%>
    	</tr>
    	 </tbody>
    	<%
    }
    %>
    </table>
    <%} %>
    </div>
<script>
    // Use the plugin once the DOM has been loaded.
    $(function () {
      // Apply the plugin 
      $('#table').excelTableFilter();
    });
  </script>
<div class="modal fade active" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    	 <div class="modal-dialog">
			<div class="loginmodal-container">
				<h1>Are you want to Logout</h1><br>
				  <form action="FirstServlet" method="post">
				  <input type="hidden" name="type" value="logout" />
				  <input type="submit" name="logout" class="login loginmodal-submit" value="Logout">
				  </form>
			</div>
		</div>
</div>
<!-- <div class="modal fade active" id="RegisterModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    	 <div class="modal-dialog">
			<div class="loginmodal-container">
				<h1>New Registration Form</h1><br>
				  <form action="FirstServlet" method="post">
				  <input type="hidden" name="type" value="registration" />
				  <input type="submit" name="register" class="login loginmodal-submit" value="Register">
				  </form>
			</div>
		</div>
</div> -->
<div class="modal fade active" id="EntryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    	 <div class="modal-dialog">
			<div class="loginmodal-container">
				<h1>New Entry Form</h1><br>
				  <form action="FirstServlet" method="post" id="formentry" >
				  <input type="text" list="mobi" id="mobileNo" name="mobileNo" value="" pattern="[0-9]{10}" placeholder="Mobile Number" required onchange="loadvalues()">
				  <datalist id="mobi">
				  <%
				  for(int i=0;i<visitorMob.size();i++) 
				  {
					  %>
					  <option><%=visitorMob.get(i) %></option>
					  <%
				  }
				  %>
				  </datalist>
				  <span id="updated">
				  <input type="text" name="firstName" id="firstName" placeholder="First Name" required>
				  <input type="text" name="middleName"id="middleName" placeholder="Middle Name" required>
				  <input type="text" name="lastName"  id="lastName" placeholder="Last Name" required>
				  <input type="text" name="address"   id="address" placeholder="Address" required>	
				  </span>		  
				  <input type="text" list="emp" name="empToMeet" id="empToMeet" placeholder="Person to Meet" required>
				  <datalist id="emp">
				  <%
				  for(int i=0;i<empUser.size();i++) 
				  {
					  %>
					  <option><%=empUser.get(i) %></option>
					  <%
				  }
				  %>
				  </datalist> 
				  <input type="text" name="purpose"   id="purpose" placeholder="Purpose" required> 
				  <input type="hidden" name="type" value="entry" />
				  <input type="submit" name="entry" class="login loginmodal-submit" value="Submit">
				  </form>
			</div>
		</div>
</div>
<div class="modal fade active" id="ContactsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    	 <div class="modal-dialog">
			<div class="loginmodal-container">
				<h1>Contacts</h1><br>
			</div>
		</div>
</div>
</body>
</html>
<%} %>
