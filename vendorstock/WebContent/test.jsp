<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
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
<%@page import="com.muthagroup.dao.MuthaGroupDAO"%>
<%@page import="com.muthagroup.vo.MuthaGroupVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Mutha Group of Engineering Satara</title>
<link rel="stylesheet" href="css/bootstrap-datepicker3.css"/>
<link rel="stylesheet" href="css/bootstrap.min.css" >
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script>
</head>
<body>
<%
MuthaGroupVO vo=new MuthaGroupVO();
MuthaGroupDAO dao=new MuthaGroupDAO();

session=null;	
session=request.getSession();  
session.setAttribute("vo",vo);
dao.setAllItems(vo);
dao.setallCustomersAndVendors(vo);
dao.setItems(vo);
dao.setCutomers(vo);
dao.setVendors(vo);
dao.setCustomerRecords(vo);
dao.setVendorRecords(vo);
dao.setInternalRecords(vo);
dao.setDate(vo);


String date=vo.getDate();
LinkedHashMap<Long,String> allItem=vo.getAllItem();
LinkedHashMap<Long,String> item=vo.getItem();
LinkedHashMap<Long,String> customer=vo.getAllCustomersAndVendors();
ArrayList<ArrayList<String>> customerstock=vo.getCustomerRecords();
ArrayList<ArrayList<String>> vendorstock=vo.getVendorRecords(); 
ArrayList<ArrayList<String>> internalStock=vo.getInternalRecords(); 
int index=1; 
%>
<form action="PrintDoc" method="post" target="_blank">
<ul class="nav navbar-nav">
  <li class="active"><a data-toggle="tab" href="#customer">Customer</a></li>
  <li><a data-toggle="tab" href="#vendor">Vendor</a></li>
  <li><a data-toggle="tab" href="#internal">Internal</a></li>
</ul>
<ul class="nav navbar-nav navbar-right">
<li><a href="#" onclick="$(this).closest('form').submit()"><span class="glyphicon glyphicon-print"></span> Print</a></li>
  <li><a  data-toggle="modal" href="#confModal"><span class="glyphicon glyphicon-log-out"></span> Configuration</a></li>
</ul>
</form>
<div class="modal fade active small" id="confModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style=" background: #fff;height: 600px;text-align: center;margin: 5px;">
Configuration Setting
<script>
$(document).ready(function(){
	$("select").trigger('change');
});
</script>
 <script>
function giveSelection(mat_code,index) {
	var xmlhttp,xmlhttp1;
	if (window.XMLHttpRequest) {
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
		xmlhttp1 = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		xmlhttp1 = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.getElementById("cust"+(index)).innerHTML = xmlhttp.responseText;
			
		}
	};
	xmlhttp1.onreadystatechange = function() {
		if (xmlhttp1.readyState == 4 && xmlhttp1.status == 200) {
			
			document.getElementById("vendor"+(index)).innerHTML = xmlhttp1.responseText;
		}
	};
	xmlhttp.open("POST", "select_cust.jsp?mat_code="+mat_code+"&index="+index , true);
	xmlhttp1.open("POST", "select_vendor.jsp?mat_code="+mat_code+"&index="+index , true);
	xmlhttp.send();
	xmlhttp1.send();
};
 </script>
 <script>
 function add_row() {
	 var i = parseInt(document.getElementById('indexval').value);
	 document.getElementById('addr'+i).innerHTML="<td>"+i+"</td><td><select name= mat"+i+" id=mat"+i+" required onChange=giveSelection(this.value,"+i+")> <% for (Map.Entry<Long,String> matentry : allItem.entrySet()) {%> <option  value=<%=matentry.getKey()%>><%=matentry.getValue()%></option> <%}%></select><input type=text name=comm_name"+i+" id=comm_name"+i+" required ></td><td><select multiple name= cust"+i+" id=cust"+i+" required></select></td><td><select multiple name= vendor"+i+" id=vendor"+i+" required></select>";	 
	 var table = document.getElementById('tab_logic');
	 var row=table.insertRow(-1);
	 row.id='addr'+(i+1);
	 document.getElementById('indexval').value=(i+1);
 };
 function delete_row(){
	 var i = parseInt(document.getElementById('indexval').value);
	 if(i>1){
		 document.getElementById('addr'+(i-1)).innerHTML='';
		 document.getElementById('indexval').value=(i-1);
	 }
 };
 </script>
<% try{ %>
<form action=UpdateCode method="post">
 <table class='table table-bordered table-condensed table-responsive ' id='tab_logic'>
              <thead>
                    <tr id='addr0' >
                        <th class='text-center'>
                            #
                        </th>
                        <th class='text-center'>
                           Material
                        </th>
                        <th class='text-center'>
                            Customer
                        </th>
                        <th class='text-center'>
                            Vendor
                        </th>
                    </tr>
             </thead>
   <tbody>        
    <%  	
    	boolean empty=true;
	    for (Map.Entry<Long,String> itementry : item.entrySet()){
	    	empty=false;
	    %>
		    <tr id='addr<%=index%>'>
		    <td><%=index%></td>
	    	<td><select name="mat<%=index%>" id="mat<%=index%>" required onChange="giveSelection(this.value,<%=index%>)" autocomplete="off">
	    <%
	    for (Map.Entry<Long,String> matentry : allItem.entrySet())
	    	  {
	    	  %>
	    	   <option <% if(matentry.getKey().toString().equals(itementry.getKey().toString())){%> <%="selected='selected'"%> <%} %>  value="<%=matentry.getKey()%>" ><%=matentry.getValue()%></option>
	    	 <%}
	    %>
		</select><input type="text" name="comm_name<%=index%>" id="comm_name<%=index%>" value='<%=itementry.getValue().toString()%>' required ></td>
		
	<td><select multiple name= "cust<%=index%>" id="cust<%=index%>" required>
		
		</select>
		</td>
		<td><select multiple name= "vendor<%=index%>" id="vendor<%=index%>" required>
		
		</select></td>
		<%
		index++;
	    }
	    %>
		<tr id='addr<%=index%>'>
 </tbody> 
 </table>
 <input type=hidden name="indexval" id="indexval" value=<%=index%>> 
 <input value="Update" type="submit" class="btn btn-default pull-center" id="submit">
 <a id="add_row" class="btn btn-default pull-left" onclick="add_row()" >Add Row</a><a id='delete_row' class="pull-right btn btn-default" onclick="delete_row()">Delete Row</a>
 </form>
<%
    }catch(Exception e){
    	e.printStackTrace();
    }
%>
</div>
<div id="menubar" align="center">
    <form action="FirstServlet" class="form-inline" method="post" >
    <label>Date:<%=date%></label>
    <!-- <input type="submit" name="submit" value="Get Records" > -->
    </form>
</div>
<div class="tab-content">
<div id="customer" class="tab-pane fade in active">
<table class="table table-condensed table-bordered small">
<tr>
<th>Item</th><th>Customer</th><th>Schedule</th><th>Dispatch</th><th>Item</th><th>Vendor</th><th>ERP Stock</th><th>Casting Received</th><th>Production</th><th>Packed</th><th>For Packing</th><th>For Insp.</th><th>Total</th><th>Item</th><th>Yest. Invoice</th><th>Yest. Dispatch</th><th>Pending Invoice</th><th>Today Plan</th><th>Yest. Pouring</th><th>Commu.</th>
</tr>
<% for (Map.Entry<Long,String> entry : item.entrySet()) {%>
<tr>
<%
System.out.println(entry.getKey());
int intr=0;
int count=Collections.frequency(customerstock,entry.getKey().toString());
System.out.println(count);
%>
<td <%if(count!=0){ %>rowspan="<%=count%><%}%>" ><%=entry.getValue()%></td>
<%if(count==0){%>
<td>-</td><td>-</td><td>-</td></tr><tr>	
<%}else{%>

<%for(int i=0;i<customerstock.size();i++){
if(customerstock.get(i).get(0).equals(entry.getKey().toString()))
{
	for(int j=1;j<=3;j++){
%>
			<td>
			<%=customerstock.get(i).get(j)%>
			</td>
<%}%>

<%if(count>=2){%></tr><tr><%}%>
<%}
}
}
%>
</tr>
<%intr++;} %>
</table>
</div>
<div id="vendor" class="tab-pane fade">
<table class="table table-condensed table-bordered small" >
<tr>
<th>Item</th><th>Vendor</th><th>ERP Stock</th><th>Casting Received</th><th>Production</th><th>Packed</th><th>For Packing</th><th>For Insp.</th><th>Total</th>
</tr>
<% for (Map.Entry<Long,String> entry : item.entrySet()) {%>
<tr>
<%
int count=0;
for(int i=0;i<vendorstock.size();i++){
if(vendorstock.get(i).get(0).equals(entry.getKey().toString()))
{
	count++;
}
}
%>
<td <%if(count!=0){ %>rowspan="<%=count%><%}%>" ><%=entry.getValue()%></td>
<%if(count==0){%>
<td>-</td><td>-</td></tr><tr>	
<%}else{%>

<%for(int i=0;i<vendorstock.size();i++){
if(vendorstock.get(i).get(0).equals(entry.getKey().toString()))
{
	for(int j=2;j<=9;j++){
%>
			<td>
			<%=vendorstock.get(i).get(j)%>
			</td>
<%}%>

<%if(count>=2){%></tr><tr><%}%>
<%}
}
}
%>
</tr>
<%} %>
</table>
</div>
<div id="internal" class="tab-pane fade">
 <table class="table table-condensed table-bordered small" >
<tr>
<th>Item</th><th>Yest. Invoice</th><th>Yest. Dispatch</th><th>Pending Invoice</th><th>Today Plan</th><th>Yest. Pouring</th><th>Commu.</th>
</tr>
<% for (Map.Entry<Long,String> entry : item.entrySet()) {%>
<tr>
<%
int count=0;
for(int i=0;i<internalStock.size();i++){
if(internalStock.get(i).get(0).equals(entry.getKey().toString()))
{
	count++;
}
}
%>
<td <%if(count!=0){ %>rowspan="<%=count%><%}%>" ><%=entry.getValue()%></td>
<%if(count==0){%>
<td>-</td><td>-</td></tr><tr>	
<%}else{%>

<%for(int i=0;i<internalStock.size();i++){
if(internalStock.get(i).get(0).equals(entry.getKey().toString()))
{
	for(int j=1;j<=6;j++){
%>
			<td>
			<%=internalStock.get(i).get(j)%>
			</td>
<%}%>

<%if(count>=2){%></tr><tr><%}%>
<%}
}
}
%>
</tr>
<%} %>
</table>
</div>
</div>
</body>
</html>