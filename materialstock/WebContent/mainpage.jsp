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
		var date_input=$('input[name="date"]'); //our date input has the name "date"
		var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
		date_input.datepicker({
			format: 'M yyyy',
			startView: "months", 
          minViewMode: "months",
			container: container,
			todayHighlight: true,
			autoclose: true,
			orientation: 'top right',
		})
	})
</script>


  <style>
  .modal-dialog {
  width: 90%;
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
	MuthaGroupVO vo=(MuthaGroupVO)session.getAttribute("vo");
	ArrayList<String> matcode=vo.getMATCODE(); 
	ArrayList<String> matname=vo.getMATNAME();
	ArrayList<String> mattype=vo.getMATTYPE(); 
	ArrayList<String> matgroupcode=vo.getMATGROUPCODE();
	ArrayList<String> matgroupname=vo.getMATGROUPNAME();
%>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand"><font size="2">Material Stock</font><img src="images/mutha.jpg" class="img-circle" alt="Mutha logo" width=50 height=30></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a data-toggle="tab" href="#barcodereport">Bar-code id wise</a></li>     
        <li><a data-toggle="tab" href="#itemreport">Items wise</a></li>
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
<label>Material Bar-code Generation Form</label>
</b>
<Form action=BarcodeExcel method=post target="_blank" onsubmit="setTimeout(function () { window.location.reload(); }, 10)">
<table><tr><td><input type="file" name=excelsheet></td><td><input type="submit" value="Upload Excel Sheet"></td></tr></table>
</Form>
<Form action="Barcode" method="post" target="_blank" onsubmit="setTimeout(function () { window.location.reload(); }, 10)" >
<table >
  <tr align="left">
      <td>
        <label>Select Material Category</label>
      </td>
      <td>
      <select name="mat_group" id="mat_group" required onChange="giveSelection(this.value)">
      <%
     for(int i=0;i<matgroupcode.size();i++) 
      {
      %>
      <option value="<%=matgroupcode.get(i)%>"><%=matgroupname.get(i)%> </option>
      <%
      }
      %>
      </select>
      </td>
    </tr>
   <tr align="left">
      <td>
        <label>Select Material Name</label>
      </td>
      <td>
      <input type="hidden" name="pname" id="pname" value="test" >
      <select name="mat" id="mat" required onChange="myNewFunction(this)">
      <%
     for(int i=0;i<matcode.size();i++) 
      {
      %>
      <option data-option="<%=mattype.get(i)%>" value="<%=matcode.get(i)%>"><%=matname.get(i)%> </option>
      <%
      }
      %>
      </select>
      <script type="text/javascript">
		var sel1 = document.querySelector('#mat_group');	
		var sel2 = document.querySelector('#mat');
		var options2 = sel2.querySelectorAll('option');
		function giveSelection(selValue) {
  		sel2.innerHTML = '';
  		for(var i = 0; i < options2.length; i++) {
    	if(options2[i].dataset.option === selValue.substring(4, 7)) {
     	 sel2.appendChild(options2[i]);
   		 }
	  }
	}
giveSelection(sel1.value);
</script>
      </td>
    </tr>
    <tr align="left" >
    <td>
    <label>Enter Common Name for Material</label>
    </td>
    <td> 
    <input type="text" name="cname" required width=100% >
    </td>
    </tr>
    <tr align="left" >
    <td>
    <label>
    Select No of Quantities
    </label>
    </td>
    <td>
    <input type="number" step="1" value="1" name="qty"  min="1" max="10">
    </td>
    </tr>
    <tr>
    <td colspan="2" align="center">
    <input type="submit" value="Get Barcode" >
    </td>
    </tr>
</table>
</Form>
</div>
</div>
</div>
 <div id="menubar" align="center">
    <form action="FirstServlet" class="form-inline" method="post" >
    <input type="hidden" name="type" value="date"/>
    <input  class="form-control" id="date" name="date" value="<%=vo.getDate()%>" type="text"  data-date-end-date="0d" pattern="(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) [0-9]{4}" required />
    <input type="submit" name="submit" value="Get Stock Status" >
    </form>

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
 <tr><th>Date</th><th>Company</th><th>Bar-code-ID</th><th>Material Name</th><th>Material Type</th><th>Stock Site</th><th>Location</th><th>Quantity</th>
 <th>
 <input type="submit" class='sub' id="uploadMe" value="Upload to ERP"  disabled  /><br/>
 
 <% 
 if(vo.getList1()!=null)
 {
	 boolean flag=false;
	 ArrayList<ArrayList<String>>list1=vo.getList1();
	  for(int i=0;i<list1.size();i++) 
	  {
		  if(list1.get(i).get(6)!=null)
		  {
			  flag=true;
	      }
	  }
	 if(flag==true){%>
	 <input id= "all" name="all" type="checkbox" /> Select All <% }} %></th>
</tr>
<%   
if(vo.getList1()!=null)
{
	ArrayList<ArrayList<String>>list1=vo.getList1();

for(int i=0;i<list1.size();i++) 
{
%>
<tr>
<%
	for(int j=0;j<8;j++)
	{
		
		%>
		<td>
		 <h6>
		<%=list1.get(i).get(j)%>
         </h6>
		</td>
		<%
	}
%>
<td><%if(list1.get(i).get(7)!="Un Defined"){ %><input type="checkbox" id="records" name="records" value="<%=list1.get(i).get(7) %>" class='che'><%} %></td>
</tr>
<% 
}
}
 %>
 </table>
 </form>
 </div>
<div class="tab-pane fade " id="itemreport">
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
 <form action="FirstServlet" id="myForm1"  method="post" >
 <table class="table table-bordered table-condensed" >      
 <tr><th>Material Name</th><th>As Cast</th><th>Fettled</th><th>Machined</th><th>Rejected</th><th>Rework</th><th>F4 Out</th><th>Other</th><th>Casting Defect</th><th>Machining Defect</th><th>Semi-Finish</th><th>Row</th><th>In Process</th><th>WIP2</th><th>Total</th>

<% 
if(vo.getList1()!=null)
{
   ArrayList<ArrayList<String>>list2=vo.getList2();
	  
for(int i=0;i<list2.size();i++) 
{
%>
<tr>
<%
	for(int j=0;j<15;j++)
	{
		
		%>
		<td>
		 <h6>
		<%=list2.get(i).get(j)%>
         </h6>
		</td>
		<%
	}
%>
</tr>
<% 
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
