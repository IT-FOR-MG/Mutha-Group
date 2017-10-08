<%@page import="com.muthagroup.dao.MuthaGroupDAO"%>
<%@page import="com.itextpdf.text.List"%>
<%@page import="com.muthagroup.connection.ConnectionModel"%>
<%@page import="com.muthagroup.vo.MuthaGroupVO"%>
<%@page import="java.util.ArrayList"%>
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
<script>
function myNewFunction(sel)
{
    document.getElementById('pname').value=sel.options[sel.selectedIndex].text;
}
</script>


<script>
	$(document).ready(function(){
		var date_input=$('input[name="purchaseDate"]'); //our date input has the name "date"
		var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
		date_input.datepicker({
			format: 'yyyy/mm/dd',
			startView: "date", 
          minViewMode: "date",
			container: container,
			todayHighlight: true,
			autoclose: true,
			orientation: 'top right',
		})
	})
</script>


  <style>
  .modal-dialog {
  width: 100%;
}
.table-condensed{
  font-size: 10px;
  white-space: nowrap;
  width: 100%;
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
  padding: 0 10px;
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

<body>
<%
if ((session.getAttribute("user")!=null)) 
{ 
	String username=(String)session.getAttribute("user"); 
	MuthaGroupVO vo=(MuthaGroupVO)session.getAttribute("vo");
%>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand"><font size="2">Machine Asset</font><img src="images/mutha.jpg" class="img-circle" alt="Mutha logo" width=50 height=30></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a data-toggle="tab" href="#barcodereport">Records</a></li>     
      </ul>
      <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#barcodeModal" >Bar-code Generation Form</button>
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
				  <input type="hidden" name="type" value="logout"/>
					<input type="submit" name="logout" class="login loginmodal-submit" value="Logout">
				  </form>
			</div>
		</div>
</div>
<div class="modal fade active" id="barcodeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    	 <div class="modal-dialog">
<div class="barcodemodal-container">
				

<b>
<label>Machine Status Bar-code Generation Form</label>
</b>
<Form action="FirstServlet" method="post" target="_blank" onsubmit="setTimeout(function () { window.location.reload(); }, 10)" >
<table align="center">
  <tr align="left">
      <td>
        <label>Select Machine Name</label>
      </td>
      <td>
      <input type="text" name="machineName" id="machineName" required  >
      </td>
       <td>
        <label>Select Machine Category</label>
      </td>
      <td>
      <input type="text" name="machineCategory" id="machineCategory" required >
      </td>
      <td>
    	<label>Manufacturer</label>
      </td>
    <td>
    <input type="text" id="machineMake" name="machineMake" required>
    </td>
    </tr>
    <tr align="left" >
    <td>
    <label>Purchase date</label>
    </td>
    <td> 
   <input  class="form-control" id="purchaseDate" name="purchaseDate" value="" type="text"  data-date-end-date="0d" required />
    </td>
     <td>
    <label>Purchase Cost</label>
    </td>
    <td> 
    <input type="number" id="purchaseCost" name="purchaseCost" required width=100% required >
    </td>
    </tr>
    <tr align="left" >
    <td>
    <label>
    Location
    </label>
    </td>
    <td>
    <input type="text" id="location" name="location" required >
    </td>
    </tr>
    <tr align="left">
     <td >
    <label>
    Condition
    </label>
    </td>
    <td colspan="4" align="left">
    <input type="text" id="condtion" name="condtion" required >
    </td>
    </tr>
    <tr>
    <td colspan="4" align="center">
    <input type="submit" value="Generate Barcode" >
    </td>
    </tr>
</table>
<input type="hidden" name="type" value="barcode"/>
</Form>
</div>
</div>
</div>
<div class="tab-content">
<div class="tab-pane fade in active" id="barcodereport">    
  <script>
 $(document).ready(function(){
	   var checkboxes = $(" #myForm input[type='checkbox']"),
	   submitButt = $(" #myForm input[type='submit']");

	checkboxes.click(function() {
	   submitButt.attr("disabled", !checkboxes.is(":checked"));
	});
	 
	    $("#all").change(function(){
	        $('.che').prop('checked', $(this).prop('checked')); 
	        submitButt.attr("disabled",!$(this).prop('checked'));
	    });
	});
 </script>  
 <form action="FirstServlet" id="myForm"  method="post" >
 <input type="hidden" name="type" value="records"/>
 <table class="table table-bordered table-condensed " >
 <tr>      
 <th>Bar-code ID</th><th>Machine Name</th><th>Machine Type</th><th>Machine Make</th><th>Purchase Date</th><th>Purchase Cost</th><th>Location</th><th>Status</th>
 </tr>
<%   
if(vo.getList()!=null)
{
	ArrayList<ArrayList<String>>list=vo.getList();

for(int i=0;i<list.size();i++) 
{
%>
<tr>
<%
	for(int j=0;j<8;j++)
	{
		%>
			<td>
		 <h6>
		<%=list.get(i).get(j)%>
         </h6>
		</td>
		<%
	}
}
}
%>

 </table>
 </form>
 </div>
</div>
<div class="modal fade active" id="ERPModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
			<div class="ERPmodal-container">
			ERP Upload Records
			</div>
	</div>
</div>

<%
}
else
{
response.sendRedirect("index.jsp"); 
} 
%>
</body>
</html>
