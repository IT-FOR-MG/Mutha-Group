<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.ArrayList"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Add Action</title>
<!--======================== Design Script ====================================-->
<!--============================================================================-->
<meta name="keywords"
	content="graphite theme, free templates, website templates, CSS, HTML" />
<meta name="description"
	content="Graphite Theme, Contact page, free CSS template provided by templatemo.com" />
<link href="css/templatemo_style.css" rel="stylesheet" type="text/css" />

<style>
div.scroll {
	background-color: #F0EBF2;
	width: 1010px;
	height: 600px;
	overflow: scroll;
}
</style>

<script language="javascript" type="text/javascript">
	function clearText(field) {
		if (field.defaultValue == field.value)
			field.value = '';
		else if (field.value == '')
			field.value = field.defaultValue;
	}
</script>

<link rel="stylesheet" type="text/css" href="css/ddsmoothmenu.css" />

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/ddsmoothmenu.js">
	/***********************************************
	 * Smooth Navigational Menu- (c) Dynamic Drive DHTML code library (www.dynamicdrive.com)
	 * This notice MUST stay intact for legal use
	 * Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
	 ***********************************************/
</script>

<script type="text/javascript">
	ddsmoothmenu.init({
		mainmenuid : "templatemo_menu", //menu DIV id
		orientation : 'h', //Horizontal or vertical menu: Set to "h" or "v"
		classname : 'ddsmoothmenu', //class added to menu's outer DIV
		//customtheme: ["#1c5a80", "#18374a"],
		contentsource : "markup" //"markup" or ["container_id", "path_to_menu_file"]
	});
</script>

<!--////// CHOOSE ONE OF THE 3 PIROBOX STYLES  \\\\\\\-->
<link href="css_pirobox/white/style.css" media="screen" title="shadow"
	rel="stylesheet" type="text/css" />
<!--<link href="css_pirobox/white/style.css" media="screen" title="white" rel="stylesheet" type="text/css" />
<link href="css_pirobox/black/style.css" media="screen" title="black" rel="stylesheet" type="text/css" />-->
<!--////// END  \\\\\\\-->

<!--////// INCLUDE THE JS AND PIROBOX OPTION IN YOUR HEADER  \\\\\\\-->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/piroBox.1_2.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$().piroBox({
			my_speed : 600, //animation speed
			bg_alpha : 0.5, //background opacity
			radius : 4, //caption rounded corner
			scrollImage : false, // true == image follows the page, false == image remains in the same open position
			pirobox_next : 'piro_next', // Nav buttons -> piro_next == inside piroBox , piro_next_out == outside piroBox
			pirobox_prev : 'piro_prev',// Nav buttons -> piro_prev == inside piroBox , piro_prev_out == outside piroBox
			close_all : '.piro_close',// add class .piro_overlay(with comma)if you want overlay click close piroBox
			slideShow : 'slideshow', // just delete slideshow between '' if you don't want it.
			slideSpeed : 4
		//slideshow duration in seconds(3 to 6 Recommended)
		});
	});
</script>
<script src="js/script.js"></script>

<script type="text/javascript">
	function ChangeColor(tableRow, highLight) {
		if (highLight) {
			tableRow.style.backgroundColor = '#CFCFCF';
		} else {
			tableRow.style.backgroundColor = '#EDEDED';
		}
	}

	/* function DoNav(theUrl) {
		document.location.href = theUrl;
		//	document.getElementById("frm1").submit();
	} */
</script>
<script language="javascript">
	function button1(val) {
		var val1 = val;
		//alert(val1);
		document.getElementById("hid").value = val1;
		action.submit();
	}
</script>
<!--////// END  \\\\\\\-->
<!--============================================================================-->
<!--============================================================================-->
</head>

<%@page import="com.muthagroup.connectionUtility.Connection_Utility"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>


<body id="sub_page">
	<div id="templatemo_wrapper">
		<div id="templatemo_top"></div>
		<!-- end of top -->

<!--============================================================================-->
<!--===================== Menu Bar =====================================-->
		<div id="templatemo_header" class="ddsmoothmenu">
			<ul>
				<li><a href="Cab_Home.jsp">Home</a></li>
				<li><a href="New_Request.jsp">New Request</a></li>
				<li><a href="Cab_Edit_Request.jsp">Edit Request</a></li>
				<li><a href="Add_Action.jsp">Add Action</a></li>
				<li><a href="My_Approvals.jsp">My Approvals</a></li>
				<li><a href="Cab_Search_Request.jsp">Search Request</a></li>
				<li><a href="Reports.jsp">Reports</a></li>
				<li><a href="logout.jsp">Log Out</a></li>
			</ul>
			<br style="clear: left" />
		</div>
		<!--============================================================================-->
		<!--============================================================================-->
		<!-- end of templatemo_menu -->
		<div id="templatemo_menu">
			<div id="site_title">
				<h1 style="color: orange;">ECN</h1>
			</div>
		</div>

		<!-- end of header -->

		<div id="templatemo_main">
			<h4 style="color: white;">Add Action Customer</h4>
			<div class="col_w630 float_l">
				<div id="contact_form">
				<div id="templatemo_header" class="ddsmoothmenu">

						<ul>

							<li style="background-color: #B3A6AA;"><a
								href="Add_Action.jsp">Add Action Internal</a></li>
							<li style="background-color: #B3A6AA;"><a
								href="Add_Action_Customer.jsp">Add Action Customer</a></li>
						</ul>
				</div>
					<%
						try {

							int uid = 0;
							uid = Integer.parseInt(session.getAttribute("uid").toString());

							System.out.print("user id at action " + uid);

							Connection con = Connection_Utility.getConnection();
					%>
					
					<form method="post" name="action" action="Add_Action_Details_Customer.jsp"
						id="action">
							<%
								int cr_no = 0;

								ArrayList appr_id_list1 = new ArrayList();
								ArrayList appr_id_list = new ArrayList();
								ArrayList cr_no_list = new ArrayList();

								PreparedStatement ps_Cr_No = con
										.prepareStatement("select CRC_no from crc_tbl where U_Id="
												+ uid);
								ResultSet rs_Cr_No = ps_Cr_No.executeQuery();
								while (rs_Cr_No.next())
								{
									ArrayList appr_list = new ArrayList();

									int cnt1 = 0;
									int cnt2 = 0;
									int cnt3 = 0;
									
									cr_no = rs_Cr_No.getInt("CRC_NO");
									
									System.out.print("\n req no... " + cr_no);
									
									
									PreparedStatement ps_appr_list = con
											.prepareStatement("select U_Id from crc_tbl_approver_rel where CRC_No="
													+ cr_no);

									ResultSet rs_appr_list = ps_appr_list.executeQuery();

									while (rs_appr_list.next()) 
									{
										System.out.print("\n Approvers ... for CRC NO "+cr_no+" is ... "+ rs_appr_list.getInt("U_Id"));

										appr_list.add(rs_appr_list.getInt("U_Id"));
									}

									appr_id_list.clear();
									
									for (int appr = 0; appr < appr_list.size(); appr++) {
										PreparedStatement ps_appr = con
												.prepareStatement("select Approval_Id from crc_tbl_approval where CRC_No="
														+ cr_no
														+ " and U_Id="
														+ Integer.parseInt(appr_list.get(appr)
																.toString()));

										ResultSet rs_appr = ps_appr.executeQuery();

										while (rs_appr.next()) 
										{
											//System.out.print("\n loop for approvers list ... " + appr_list.size());
											System.out.print("\n what are the Approvals ... "+ rs_appr.getInt("Approval_Id"));
											appr_id_list.add(rs_appr.getInt("Approval_Id"));
										}

									}
									String Status = null;

									boolean flag = false;
									System.out.print(" \n  approvers id size :"+ appr_id_list.size());
									
									
									for (int appr_id = 0; appr_id < appr_id_list.size(); appr_id++) 
									{
										int id = 0;

										id = Integer.parseInt(appr_id_list.get(appr_id)
												.toString());

										if (id == 3) 
										{
											cnt3++;
											//flag=true;
											break;
										} else if (id == 2) 
										{
											cnt2++;
											continue;
										} else 
										{
											cnt1++;
											continue;
										}

									}

									if (cnt3 > 0) {
										System.out.print("\n  Declined");

									} else if (cnt1 == appr_id_list.size()) {
										System.out.print("\n  Approved. . . " + cr_no);
										cr_no_list.add(cr_no);

									} else {
										System.out.print("\n Pending  . . . ");
									}

								}
						%>


						<div class="scroll">
							<table width="1000px">
							<thead style="color: #FAF7F2; background-color: #2B2A29;">
								<tr>
									<td align="center" width="50px">CR NO</td>
									<td align="center" width="50px">Supplier Name</td>
									<td align="center" width="210px">Customer Name</td>
									<td align="center" width="210px">Item Name</td>
									<td align="center" width="50px">Change For</td>
									<td align="center" width="60px">Change Request Date</td>
									<td align="center" width="50px">Total Stock</td>
									<td align="center" width="60px">Targeted Impl. Date</td>
									<td align="center" width="50px">Approval Status</td>
									<!--  	<td align="center" width="110px">Action</td>-->
								</tr>
							</thead>
								<tbody>
									<%
											PreparedStatement ps_action;
											ResultSet rs_action;

											for (int cr = 0; cr < cr_no_list.size(); cr++) {
												System.out.print("into loop .. " + cr_no_list.get(cr));
												int crno = Integer.parseInt(cr_no_list.get(cr).toString());

												ps_action = con
														.prepareStatement("select * from crc_tbl where crc_no="
																+ crno);

												rs_action = ps_action.executeQuery();

												while (rs_action.next()) {
									%>
									<tr onmouseover="ChangeColor(this, true);"
										onmouseout="ChangeColor(this, false);"
										onclick="button1('<%=crno%>');">
										<%
											
										%>
										<td align="center" width="50px"><%=crno%></td>
										<%
											PreparedStatement ps_company = con
																.prepareStatement("select Company_Name from User_tbl_Company where Company_Id="
																		+ rs_action.getInt("Company_Id"));
														ResultSet rs_company = ps_company.executeQuery();
														while (rs_company.next()) {
										%>
										<td align="center" width="50px"><%=rs_company.getString("Company_Name")%></td>
										<%
											}
														PreparedStatement ps_Cust = con
																.prepareStatement("select Cust_Name from customer_tbl where Cust_Id="
																		+ rs_action.getInt("Cust_Id"));
														ResultSet rs_Cust = ps_Cust.executeQuery();
														while (rs_Cust.next()) {
														
										%>
										
										
										<td align="center" width="210px"><%=rs_Cust.getString("Cust_Name")%></td>
										
										<%				
														}
														
														PreparedStatement ps_item = con
																.prepareStatement("select Item_Name from customer_tbl_item where Item_Id="
																		+ rs_action.getInt("Item_Id"));
														ResultSet rs_item = ps_item.executeQuery();
														while (rs_item.next()) {
										%>
										<td align="center" width="210px"><%=rs_item.getString("Item_Name")%></td>
									
										<%
													}
										%>
										<td align="center" width="60px"><%=rs_action.getString("Change_For") %></td>
										<td align="center" width="65px"><%=rs_action.getString("CRC_Date")%>
										<input type="hidden" name="req_date"
											value="<%=rs_action.getString("CRC_Date")%>"></td>
										
										
										<td align="center" width="60px"><%=rs_action.getDouble("Total_Stock")%></td>
										
										<%
											if(rs_action.getString("Targated_Impl_Date").equals("0002-11-30 00:00:00.0"))
											{
										%>
												<td align="center" width="65px">0000-00-00 00:00:00.0</td>
										<%
											}
											else
											{
										%>
										
											<td align="center" width="65px"><%=rs_action.getString("Targated_Impl_Date")%></td>
										
										<%
											}
										
										%>
										<td align="center" width="50px">Approved</td>
										<%--		<td align="center" width="110px">
											<button onclick="button1(this.value)" name="action_button"
												id="action_button" value="<%=crno%>">Action</button>
										</td> --%>

										<input type="hidden" name="hid" id="hid">
									</tr>
									<%
										}
											}

										} catch (Exception e) {
											e.printStackTrace();
										}
									%>
								</tbody>

							</table>
						</div>
					</form>
				</div>
			</div>

			<div class="cleaner"></div>
		</div>
		<!-- end of main -->
	</div>
	<!-- end of wrapper -->

	<div id="templatemo_footer_wrapper">
		<div id="templatemo_footer">
			| Copyright 2013 <a href="http://www.muthagroup.com">Muthagroup
				Satara</a> |
			<div class="cleaner"></div>
		</div>
	</div>

</body>
</html>