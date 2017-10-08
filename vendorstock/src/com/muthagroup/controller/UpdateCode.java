package com.muthagroup.controller;


import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.muthagroup.connection.ConnectionModel;

/**
 * Servlet implementation class UpdateCode
 */
@WebServlet("/UpdateCode")
public class UpdateCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

	    if(!request.getParameter("indexval").equals(null))
	    {
		int index = Integer.parseInt(request.getParameter("indexval"));
		index--;
		try{
			Connection con1 = ConnectionModel.getConnection1();
			Connection con2=ConnectionModel.getConnection2();
			String sql=null;
			String ref_code=null;
			String mat_code=null;
			Statement stmt = con2.createStatement();
		    sql = "TRUNCATE stock_mat";
		    stmt.executeUpdate(sql);
		    sql = "TRUNCATE stock_cust";
		    stmt.executeUpdate(sql);
		    sql = "TRUNCATE stock_vendor";
		    stmt.executeUpdate(sql);
			for(int i=0;i<index;i++)
			{  
				mat_code=request.getParameter("mat"+(i+1));
				sql = "select REF_MATCODE from MSTMATBOM where MAT_CODE='"+request.getParameter("mat"+(i+1))+"'";
				PreparedStatement ps3=con1.prepareStatement(sql);
				ResultSet rs3=ps3.executeQuery();
				if(rs3.next())
				{
						ref_code=rs3.getString("REF_MATCODE");	
				}
				else
				{
						ref_code=request.getParameter("mat"+(i+1));
				}
				
				PreparedStatement ps=null;
				sql="insert into stock_mat (mat_code,comm_name) values('"+request.getParameter("mat"+(i+1))+"','"+request.getParameter("comm_name"+(i+1))+"')";
				ps =con2.prepareStatement(sql);
				ps.executeUpdate();
				String	cust_code[]=request.getParameterValues("cust"+(i+1));
				
				for(int j=0;j<cust_code.length;j++)
				{
					PreparedStatement ps1=null;
					sql="insert into stock_cust (cust_code,mat_code) value('"+cust_code[j]+"','"+request.getParameter("mat"+(i+1))+"')";	
					
					ps1 =con2.prepareStatement(sql);
					ps1.executeUpdate();
				}
				String  vendor_code[]=request.getParameterValues("vendor"+(i+1));
				
				for(int k=0;k<vendor_code.length;k++)
				{
					PreparedStatement ps2=null;
					sql="insert into stock_vendor (vendor_code,mat_code,ref_code) value('"+vendor_code[k]+"','"+mat_code+"','"+ref_code+"')";	
					
					ps2 =con2.prepareStatement(sql);
					ps2.executeUpdate();
				}
			}
			}
	    	catch(Exception e){e.printStackTrace();}
	    }
	    try{
	    response.sendRedirect("index.jsp");
	    }catch(Exception e){}
	}
	 
}
