<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.CallableStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap-datepicker3.css"/>
<link rel="stylesheet" href="css/bootstrap.min.css" >
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script>
</head>
<body>
<%
try{
LinkedHashMap<Long,String> item=new LinkedHashMap<Long,String>(); 
ArrayList<ArrayList<String>> customer=new ArrayList<ArrayList<String>>(); 
ArrayList<ArrayList<String>> vendor=new ArrayList<ArrayList<String>>(); 
LinkedHashMap<Long,String> castrec=new LinkedHashMap<Long,String>(); 
LinkedHashMap<Long,String> balence=new LinkedHashMap<Long,String>(); 
Calendar c = Calendar.getInstance();
int  year = c.get(Calendar.YEAR);
int  month = c.get(Calendar.MONTH)+1;
int  day=c.get(Calendar.DAY_OF_MONTH);
int  hour=c.get(Calendar.HOUR_OF_DAY);
int  minute=c.get(Calendar.MINUTE);
String date=String.valueOf(year)+String.format("%02d", month)+String.format("%02d", day);
String yestdate=String.valueOf(year)+String.format("%02d", month)+String.format("%02d", day-1);
String time=String.format("%02d", hour)+":"+String.format("%02d", minute);
Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
Connection con=DriverManager.getConnection("jdbc:sqlserver://192.168.0.6:1433;databaseName=FOUNDRYERP;user=BWAYS;password=BWAYSKING321 ");
/* String sql = "select CODE,NAME from MSTMATERIALS where CODE in (1010100286,1010100894,1010101209,1010101210,1010101390,1010101559,1010100286,1010101365,1010101084,1010100280,1010101075,1010101370) "; */
//String sql = "select CODE,NAME from MSTMATERIALS where CODE in (1010100280,1010101165,1010101084,1010101075,1010100646,1010100633,1010101365,1010101370,1010101371,1010101557,1010101210,1010101209) ORDER BY CHARINDEX(CAST(CODE AS VARCHAR),'1010100280,1010101165,1010101084,1010101075,1010100646,1010100633,1010101365,1010101370,1010101371,1010101557,1010101210,1010101209') ";
  String sql = "select CODE,NAME from MSTMATERIALS where CODE in (1010100280,1010101243,1010101084,1010101075,1010100894,1010100286,1010101365,1010101370,1010101390,1010101559,1010101210,1010101209) ORDER BY CHARINDEX(CAST(CODE AS VARCHAR),'1010100280,1010101165,1010101084,1010101075,1010100646,1010100633,1010101365,1010101370,1010101371,1010101557,1010101210,1010101209') ";
PreparedStatement ps = con.prepareStatement(sql);
ResultSet rs = ps.executeQuery();
 while(rs.next())
{
item.put(Long.parseLong(rs.getString("CODE")),rs.getString("NAME"));
}
CallableStatement cs1 = con.prepareCall("{call Sel_RptDespatchPlanSale('103', '0', '101110566101110492101110414101110377101110238101110237101110190101110189101110082101110081101110048101110048101110005101110004', '"+date+"')} where MAT_CODE in (1010100280,1010101165,1010101084,1010101075,1010100646,1010100633,1010101365,1010101370,1010101371,1010101557,1010101210,1010101209) ORDER BY CHARINDEX(CAST(CODE AS VARCHAR),'1010100280,1010101165,1010101084,1010101075,1010100646,1010100633,1010101365,1010101370,1010101371,1010101557,1010101210,1010101209')");
ResultSet rs1 = cs1.executeQuery();
while(rs1.next())
{
	ArrayList<String> customerstock =new ArrayList<String>();
	customerstock.add(rs1.getString("MAT_CODE"));
	customerstock.add(rs1.getString("CUST_NAME"));
	customerstock.add(rs1.getString("NOS_QTY"));
	customerstock.add(rs1.getString("SALE_QTY"));
	customer.add(customerstock);
}
CallableStatement cs2 = con.prepareCall("{call Sel_DayWiseSubContractStockLedgerFinal('103','0','"+date+"','"+date+"')} where MAT_CODE in (1010100280,1010101165,1010101084,1010101075,1010100646,1010100633,1010101365,1010101370,1010101371,1010101557,1010101210,1010101209) TRNTYPE=0 ORDER BY CHARINDEX(CAST(CODE AS VARCHAR),'1010100280,1010101165,1010101084,1010101075,1010100646,1010100633,1010101365,1010101370,1010101371,1010101557,1010101210,1010101209'");
ResultSet rs2 = cs2.executeQuery();
while(rs2.next())
{
	ArrayList<String> vendorstock =new ArrayList<String>();
	vendorstock.add(rs2.getString("MAT_CODE"));
	vendorstock.add(rs2.getString("AC_NO"));
	vendorstock.add(rs2.getString("AC_NAME"));
	vendorstock.add(rs2.getString("TOTQT"));
	vendor.add(vendorstock);
}
CallableStatement cs3 = con.prepareCall("{call Sel_DayWiseSubContractStockLedgerFinal('103','0','"+yestdate+"','"+yestdate+"')} where MAT_CODE in (1010100280,1010101165,1010101084,1010101075,1010100646,1010100633,1010101365,1010101370,1010101371,1010101557,1010101210,1010101209) TRNTYPE=1 ORDER BY CHARINDEX(CAST(CODE AS VARCHAR),'1010100280,1010101165,1010101084,1010101075,1010100646,1010100633,1010101365,1010101370,1010101371,1010101557,1010101210,1010101209'");
ResultSet rs3 = cs3.executeQuery();
while(rs3.next())
{
	castrec.put(rs3.getLong("Trn_Desp"), rs3.getString("AC_NO"));
}
/* CallableStatement cs2 = con.prepareCall("{call Sel_DayWiseSubContractStockLedgerFinal('103','0','"+date+"','"+date+"')} where MAT_CODE in (1010100280,1010101165,1010101084,1010101075,1010100646,1010100633,1010101365,1010101370,1010101371,1010101557,1010101210,1010101209) TRNTYPE=0 ORDER BY CHARINDEX(CAST(CODE AS VARCHAR),'1010100280,1010101165,1010101084,1010101075,1010100646,1010100633,1010101365,1010101370,1010101371,1010101557,1010101210,1010101209'");
ResultSet rs2 = cs2.executeQuery(); 
CallableStatement cs3 = con.prepareCall("{call Sel_DayWiseSubContractStockLedgerFinal('103','0','"+yestdate+"','"+yestdate+"')} where MAT_CODE in (1010100280,1010101165,1010101084,1010101075,1010100646,1010100633,1010101365,1010101370,1010101371,1010101557,1010101210,1010101209) TRNTYPE=1 ORDER BY CHARINDEX(CAST(CODE AS VARCHAR),'1010100280,1010101165,1010101084,1010101075,1010100646,1010100633,1010101365,1010101370,1010101371,1010101557,1010101210,1010101209'");
ResultSet rs3 = cs3.executeQuery(); */
%>
<table class="table table-condensed table-bordered small" >
<tr>
<th>Item</th><th>Customer</th><th>Schedule</th><th>Dispatch</th><th>Vendor</th><th>ERP Stock</th><th>Casting Received</th>
</tr>
<% for (Map.Entry<Long,String> entry : item.entrySet()) {%>
<tr>
<%
int count=0;
for(int i=0;i<customer.size();i++){
if(customer.get(i).get(0).equals((entry.getKey()).toString()))
{
	count++;
}
}
%>
<td <%if(count!=0){ %>rowspan="<%=count%><%}%>" <%-- <%if(entry.getKey().toString().equals("1010100286")||entry.getKey().toString().equals("1010101084")){ %> rowspan="2"<%} %> --%> ><%=entry.getKey()%> <%=entry.getValue()%></td>
<%if(count==0){%>
<td>-</td><td>-</td><td>-</td></tr><tr>	
<%}else{%>

<%for(int i=0;i<customer.size();i++){
if(customer.get(i).get(0).equals((entry.getKey()).toString()))
{
	for(int j=1;j<=3;j++){
%>
			<td>
			<%=customer.get(i).get(j)%>
			</td>
<%}%>

<%if(count>=2){%></tr><tr><%}%>
<%}
}

for(int i=0;i<vendor.size();i++){
if(vendor.get(i).get(0).equals((entry.getKey()).toString()))
{
	for(int j=2;j<=3;j++){
%>
			<td>
			<%=vendor.get(i).get(j)%>
			</td>
<%}%>

<%}
}
}
%>
</tr>
<%} %>
</table>
<%-- <table class="table table-condensed table-bordered small" >
<tr>
<th>Item</th><th>Customer</th><th>Schedule</th><th>Dispatch</th>
<%while(rs.next()) 
{
%>
<tr>
<td>
<%=rs.getString("NAME")%>
</td>
<td>
<%=rs1.getString("CUST_NAME")%>
</td>
<td>
<%=rs1.getString("NOS_QTY")%>
</td>
<td>
<%=rs1.getString("SALE_QTY")%>
</td>
<%}%>
</tr>
</table>
</td>
<td>
<table class="table table-condensed table-bordered small" >
<tr>
<th>Item</th><th>Vendor</th><th>ERP Stock</th>
</tr>
<% 
while(rs2.next())
{
	//if((rs2.getString("MAT_CODE").equals("1010101165")||rs2.getString("MAT_CODE").equals("1010100286")||rs2.getString("MAT_CODE").equals("1010100894")||rs2.getString("MAT_CODE").equals("1010101209")||rs2.getString("MAT_CODE").equals("1010101210")||rs2.getString("MAT_CODE").equals("1010101390")||rs2.getString("MAT_CODE").equals("1010101559")||rs2.getString("MAT_CODE").equals("1010101365")||rs2.getString("MAT_CODE").equals("1010101084")||rs2.getString("MAT_CODE").equals("1010100280")||rs2.getString("MAT_CODE").equals("1010101075")||rs2.getString("MAT_CODE").equals("1010101370"))&& rs2.getString("TRNTYPE").equals("0"))
	//{
%>

<tr>
<td>
<%=rs2.getString("MAT_NAME")%>
</td>
<td>
<%=rs2.getString("AC_NAME")%>
</td>
<td>
<%=rs2.getString("TOTQT")%>
</td>
</tr>
<%
	//}
}
%>
</table>
</td>
<td>
<table class="table table-condensed table-bordered small" >
<tr>
<th>Item</th><th>Vendor</th><th>Casting Rec</th>
</tr>
<% 
while(rs3.next())
{
	/* if((rs3.getString("MAT_CODE").equals("1010101165")||rs3.getString("MAT_CODE").equals("1010100286")||rs3.getString("MAT_CODE").equals("1010100894")||rs3.getString("MAT_CODE").equals("1010101209")||rs3.getString("MAT_CODE").equals("1010101210")||rs3.getString("MAT_CODE").equals("1010101390")||rs3.getString("MAT_CODE").equals("1010101559")||rs3.getString("MAT_CODE").equals("1010101365")||rs3.getString("MAT_CODE").equals("1010101084")||rs3.getString("MAT_CODE").equals("1010100280")||rs3.getString("MAT_CODE").equals("1010101075")||rs3.getString("MAT_CODE").equals("1010101370"))&& rs3.getString("TRNTYPE").equals("1"))
	{ */
%>

<tr>
<td>
<%=rs3.getString("MAT_NAME")%>
</td>
<td>
<%=rs3.getString("AC_NAME")%>
</td>
<td>
<%=rs3.getString("Trn_Desp")%>
</td>
</tr>
<%
	/* } */
}
%>
</table>
</td>
</tr>
</table> --%>
<%
}
catch(Exception e){e.printStackTrace();}
%>
</body>
</html>