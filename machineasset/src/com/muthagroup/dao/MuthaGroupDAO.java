package com.muthagroup.dao;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.muthagroup.connection.ConnectionModel;
import com.muthagroup.vo.MuthaGroupVO;
import com.mysql.jdbc.ResultSetMetaData;

public class MuthaGroupDAO {
	Connection con=ConnectionModel.getConnection2();
	String sql =null;
	PreparedStatement ps=null;
	ResultSet rs =null;
	public boolean validateUser(MuthaGroupVO vo){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con2=null;
				con2 = DriverManager.getConnection("jdbc:mysql://192.168.0.7/complaintzilla", "root","root");
			sql = "select * from user_tbl where Login_Name='"+vo.getUserName()+"' and Login_Password='"+vo.getPassword()+"'";
			ps = con2.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next())
			{
				String cmp=rs.getString("Company_Id");
				switch(cmp)
				{
				case "1":
					vo.setCmp("101");
				break;
				case "2":
					vo.setCmp("102");
				break;
				case "3":
					vo.setCmp("103");
				break;
				case "4":
					vo.setCmp("106");
				break;
				case "5":
					vo.setCmp("105");
				break;
				case "7":
					vo.setCmp("108");
				break;
				}	
				
				return true;
			}
			return false;
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			return false;
		}
	public boolean getMachineStatus(MuthaGroupVO vo)
		{
			boolean flag = false;
			try {
			ArrayList<ArrayList<String>>allList=new ArrayList<ArrayList<String>>();
			sql="select * from machine_data";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next())
				{
				ArrayList<String>list=new ArrayList<String>();
				for(int i=1;i<=8;i++)
				{
				list.add(rs.getString(i)); 
				}
				allList.add(list);
				vo.setList(allList);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return flag;	
		}
	public int getBarcode(MuthaGroupVO vo) {
		int machine_id=0;
		try{
        	
        	Calendar cal = Calendar.getInstance();
        	int year = cal.get(Calendar.YEAR);
   			int month = cal.get(Calendar.MONTH)+1;
   			int day=cal.get(Calendar.DAY_OF_MONTH);
   			String machineName=vo.getMachineName();
   			String machineCategory=vo.getMachineCategory();
   			String machineMake=vo.getMachineMake();
   			String purchaseDate=vo.getPurchaseDate();
   			String purchaseCost=vo.getPurchaseCost();
   			String location=vo.getLocation();
   			String condtion=vo.getCondtion();
        	Connection con= ConnectionModel.getConnection2();
        	String SQL = "SELECT MAX(CAST((machine_id) AS UNSIGNED)) FROM machine_data "; 
        	Statement stmt = con.createStatement(); 
        	ResultSet rs = stmt.executeQuery(SQL);
        	if(rs.next())
   			{
   			if(rs.getString(1)!= null)
        	{
   				machine_id=Integer.parseInt(rs.getString(1))+1;
        	}
   			else
        	{
   				machine_id=0001;
        	}
   			}    	 
   			con= ConnectionModel.getConnection2();
   			PreparedStatement ps =con.prepareStatement("insert into machine_data values(?,?,?,?,?,?,?,?)");
 			ps.setInt(1,machine_id);
   			ps.setString(2,machineName);
   			ps.setString(3,machineCategory);
   			ps.setString(4,machineMake);
   			ps.setString(5,purchaseDate);
   			ps.setString(6,purchaseCost);
   			ps.setString(7,location);
   			ps.setString(8,condtion);
   			ps.executeUpdate();   
        }
        catch(Exception e) {
          e.printStackTrace();
        } 
		return machine_id;
	}  
}

