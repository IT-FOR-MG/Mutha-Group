package com.muthagroup.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import com.muthagroup.connection.ConnectionModel;
import com.muthagroup.vo.MuthaGroupVO;

public class MuthaGroupDAO {
	LinkedHashMap<Long,String> item=null;  
	LinkedHashMap<Long,String> customer1=null;   
	LinkedHashMap<Long,String> customer2=null;  
	LinkedHashMap<Long,String> customer3=null;  
	LinkedHashMap<Long,String> customer4=null;  
	LinkedHashMap<Long,String> customer5=null;  
	LinkedHashMap<Long,String> customer6=null;  
	LinkedHashMap<Long,String> customer7=null;  
	LinkedHashMap<Long,String> customer8=null;  
	LinkedHashMap<Long,String> customer9=null;  
	LinkedHashMap<Long,String> customer10=null;  
	LinkedHashMap<Long,String> customer11=null;  
	LinkedHashMap<Long,String> customer12=null;  
	
	LinkedHashMap<Long,Long> vendorstock1=null;
	LinkedHashMap<Long,Long> vendorstock2=null;
	LinkedHashMap<Long,Long> vendorstock3=null;
	LinkedHashMap<Long,Long> vendorstock4=null;
	LinkedHashMap<Long,Long> vendorstock5=null;
	LinkedHashMap<Long,Long> vendorstock6=null;
	LinkedHashMap<Long,Long> vendorstock7=null;
	LinkedHashMap<Long,Long> vendorstock8=null;
	LinkedHashMap<Long,Long> vendorstock9=null;
	LinkedHashMap<Long,Long> vendorstock10=null;
	LinkedHashMap<Long,Long> vendorstock11=null;
	LinkedHashMap<Long,Long> vendorstock12=null;
	
	LinkedHashMap<Long,String> vendors1=null;
	LinkedHashMap<Long,String> vendors2=null;
	LinkedHashMap<Long,String> vendors3=null;
	LinkedHashMap<Long,String> vendors4=null;
	LinkedHashMap<Long,String> vendors5=null;
	LinkedHashMap<Long,String> vendors6=null;
	LinkedHashMap<Long,String> vendors7=null;
	LinkedHashMap<Long,String> vendors8=null;
	LinkedHashMap<Long,String> vendors9=null;
	LinkedHashMap<Long,String> vendors10=null;
	LinkedHashMap<Long,String> vendors11=null;
	LinkedHashMap<Long,String> vendors12=null;
	
	
	ArrayList<Long> schedule1=null;  
	ArrayList<Long> schedule2=null;  
	ArrayList<Long> schedule3=null;  
	ArrayList<Long> schedule4=null;  
	ArrayList<Long> schedule5=null;  
	ArrayList<Long> schedule6=null;  
	ArrayList<Long> schedule7=null;  
	ArrayList<Long> schedule8=null;  
	ArrayList<Long> schedule9=null;  
	ArrayList<Long> schedule10=null;  
	ArrayList<Long> schedule11=null;  
	ArrayList<Long> schedule12=null;  
	
	ArrayList<Long> dispatch1=null;  
	ArrayList<Long> dispatch2=null;  
	ArrayList<Long> dispatch3=null;  
	ArrayList<Long> dispatch4=null;  
	ArrayList<Long> dispatch5=null;  
	ArrayList<Long> dispatch6=null;  
	ArrayList<Long> dispatch7=null;  
	ArrayList<Long> dispatch8=null;  
	ArrayList<Long> dispatch9=null;  
	ArrayList<Long> dispatch10=null;  
	ArrayList<Long> dispatch11=null;  
	ArrayList<Long> dispatch12=null; 
	  
	LinkedHashMap<Long,Long> castrec1=null;  
	LinkedHashMap<Long,Long> castrec2=null;  
	LinkedHashMap<Long,Long> castrec3=null;  
	LinkedHashMap<Long,Long> castrec4=null;  
	LinkedHashMap<Long,Long> castrec5=null;  
	LinkedHashMap<Long,Long> castrec6=null;  
	LinkedHashMap<Long,Long> castrec7=null;  
	LinkedHashMap<Long,Long> castrec8=null;  
	LinkedHashMap<Long,Long> castrec9=null;  
	LinkedHashMap<Long,Long> castrec10=null;  
	LinkedHashMap<Long,Long> castrec11=null;  
	LinkedHashMap<Long,Long> castrec12=null;  
	
	Connection con=null;
	String sql =null;
	PreparedStatement ps=null;
	ResultSet rs =null;
	ResultSet rs1 =null;
	CallableStatement cs ;
	Calendar c = Calendar.getInstance();
	String date=null;
	String yestdate=null;
	String time=null;
	int year=0;
	int month=0;
	int day=0;
	int hour=0;
	int minute=0;
	public boolean validateUser(MuthaGroupVO vo){
		try {
		con=ConnectionModel.getConnection2();	
		sql = "select * from user_tbl where Login_Name='"+vo.getUsername()+"' and Login_Password='"+vo.getPassword()+"'";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs.next())
		{
			
			return true;
		}
		return false;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}
	public boolean getReport(MuthaGroupVO vo) {
		try {
			 item=new LinkedHashMap<Long,String>();  
			 
			 vendorstock1=new LinkedHashMap<Long,Long>();  
			 vendorstock2=new LinkedHashMap<Long,Long>();
			 vendorstock3=new LinkedHashMap<Long,Long>();
			 vendorstock4=new LinkedHashMap<Long,Long>();
			 vendorstock5=new LinkedHashMap<Long,Long>();
			 vendorstock6=new LinkedHashMap<Long,Long>();
			 vendorstock7=new LinkedHashMap<Long,Long>();
			 vendorstock8=new LinkedHashMap<Long,Long>();
			 vendorstock9=new LinkedHashMap<Long,Long>();
			 vendorstock10=new LinkedHashMap<Long,Long>();
			 vendorstock11=new LinkedHashMap<Long,Long>();
			 vendorstock12=new LinkedHashMap<Long,Long>();
			 
			 vendors1=new LinkedHashMap<Long,String>();  
			 vendors2=new LinkedHashMap<Long,String>();
			 vendors3=new LinkedHashMap<Long,String>();
			 vendors4=new LinkedHashMap<Long,String>();
			 vendors5=new LinkedHashMap<Long,String>();
			 vendors6=new LinkedHashMap<Long,String>();
			 vendors7=new LinkedHashMap<Long,String>();
			 vendors8=new LinkedHashMap<Long,String>();
			 vendors9=new LinkedHashMap<Long,String>();
			 vendors10=new LinkedHashMap<Long,String>();
			 vendors11=new LinkedHashMap<Long,String>();
			 vendors12=new LinkedHashMap<Long,String>();
			 
			 customer1=new LinkedHashMap<Long,String>();  
			 customer2=new LinkedHashMap<Long,String>();
			 customer3=new LinkedHashMap<Long,String>();
			 customer4=new LinkedHashMap<Long,String>();
			 customer5=new LinkedHashMap<Long,String>();
			 customer6=new LinkedHashMap<Long,String>();
			 customer7=new LinkedHashMap<Long,String>();
			 customer8=new LinkedHashMap<Long,String>();
			 customer9=new LinkedHashMap<Long,String>();
			 customer10=new LinkedHashMap<Long,String>();
			 customer11=new LinkedHashMap<Long,String>();
			 customer12=new LinkedHashMap<Long,String>();
			
			 schedule1=new ArrayList<Long>();
			 schedule2=new ArrayList<Long>();
			 schedule3=new ArrayList<Long>();
			 schedule4=new ArrayList<Long>();
			 schedule5=new ArrayList<Long>();
			 schedule6=new ArrayList<Long>();
			 schedule7=new ArrayList<Long>();
			 schedule8=new ArrayList<Long>();
			 schedule9=new ArrayList<Long>();
			 schedule10=new ArrayList<Long>();
			 schedule11=new ArrayList<Long>();
			 schedule12=new ArrayList<Long>();
			 
			 dispatch1=new ArrayList<Long>();
			 dispatch2=new ArrayList<Long>();
			 dispatch3=new ArrayList<Long>();
			 dispatch4=new ArrayList<Long>(); 
			 dispatch5=new ArrayList<Long>(); 
			 dispatch6=new ArrayList<Long>(); 
		     dispatch7=new ArrayList<Long>();  
			 dispatch8=new ArrayList<Long>();  
			 dispatch9=new ArrayList<Long>();  
			 dispatch10=new ArrayList<Long>();  
			 dispatch11=new ArrayList<Long>();  
			 dispatch12=new ArrayList<Long>();
			 
			 castrec1=new LinkedHashMap<Long,Long>();
			 castrec2=new LinkedHashMap<Long,Long>();
			 castrec3=new LinkedHashMap<Long,Long>();
			 castrec4=new LinkedHashMap<Long,Long>();
			 castrec5=new LinkedHashMap<Long,Long>();
			 castrec6=new LinkedHashMap<Long,Long>();
			 castrec7=new LinkedHashMap<Long,Long>();
			 castrec8=new LinkedHashMap<Long,Long>();
			 castrec9=new LinkedHashMap<Long,Long>();
			 castrec10=new LinkedHashMap<Long,Long>();
			 castrec11=new LinkedHashMap<Long,Long>();
			 castrec12=new LinkedHashMap<Long,Long>();			 
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH)+1;
			day=c.get(Calendar.DAY_OF_MONTH);
			hour=c.get(Calendar.HOUR_OF_DAY);
			minute=c.get(Calendar.MINUTE);
			date=String.valueOf(year)+String.format("%02d", month)+String.format("%02d", day);
			yestdate=String.valueOf(year)+String.format("%02d", month)+String.format("%02d", day-1);
			time=String.format("%02d", hour)+":"+String.format("%02d", minute);
			con=ConnectionModel.getConnection1();
			sql = "select CODE,NAME from MSTMATERIALS where CODE in (1010100280,1010101165,1010101084,1010101075,1010100646,1010100633,1010101365,1010101370,1010101371,1010101557,1010101210,1010101209) ORDER BY CHARINDEX(CAST(CODE AS VARCHAR),'1010100280,1010101165,1010101084,1010101075,1010100646,1010100633,1010101365,1010101370,1010101371,1010101557,1010101210,1010101209') ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
	       while(rs.next())
	         {
	    	   item.put(Long.parseLong(rs.getString(1)),rs.getString(2));  
	         }
	       	vo.setItem(item);
	       	cs = con.prepareCall("{call Sel_DayWiseSubContractStockLedgerFinal('103','0','"+date+"','"+date+"')}");
	       	rs = cs.executeQuery();
	       	while(rs.next()){
	       		if(rs.getString("MAT_CODE").equals("1010100280") && rs.getString("TRNTYPE").equals("0"))
		       	{
	       			vendorstock1.put(rs.getLong("TOTQT"), rs.getLong("AC_NO"));
	       			vendors1.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
	       			
		       	}
	       		if(rs.getString("MAT_CODE").equals("1010101165")&& rs.getString("TRNTYPE").equals("0"))
		       	{
	       			vendorstock2.put(rs.getLong("TOTQT"), rs.getLong("AC_NO"));
	       			vendors2.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
	       			
		       	}
	       		if(rs.getString("MAT_CODE").equals("1010101084")&& rs.getString("TRNTYPE").equals("0"))
		       	{
	       			vendorstock3.put(rs.getLong("TOTQT"), rs.getLong("AC_NO"));
	       			vendors3.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
	       			
		       	}
	       		if(rs.getString("MAT_CODE").equals("1010101075")&& rs.getString("TRNTYPE").equals("0"))
		       	{
	       			vendorstock4.put(rs.getLong("TOTQT"), rs.getLong("AC_NO"));
	       			vendors4.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
	       			
		       	}
	       		if(rs.getString("MAT_CODE").equals("1010100646")&& rs.getString("TRNTYPE").equals("0"))
		       	{
	       			vendorstock5.put(rs.getLong("TOTQT"), rs.getLong("AC_NO"));
	       			vendors5.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
	       			
		       	}
	       		if(rs.getString("MAT_CODE").equals("1010100633")&& rs.getString("TRNTYPE").equals("0"))
		       	{
	       			vendorstock6.put(rs.getLong("TOTQT"), rs.getLong("AC_NO"));
	       			vendors6.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
	       			
		       	}
	       		if(rs.getString("MAT_CODE").equals("1010101365")&& rs.getString("TRNTYPE").equals("0"))
		       	{
	       			vendorstock7.put(rs.getLong("TOTQT"), rs.getLong("AC_NO"));
	       			vendors7.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
	       			
		       	}
	       		if(rs.getString("MAT_CODE").equals("1010101370")&& rs.getString("TRNTYPE").equals("0"))
		       	{
	       			vendorstock8.put(rs.getLong("TOTQT"), rs.getLong("AC_NO"));
	       			vendors8.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
	       			
		       	}
	       		if(rs.getString("MAT_CODE").equals("1010101371")&& rs.getString("TRNTYPE").equals("0"))
		       	{
	       			vendorstock9.put(rs.getLong("TOTQT"), rs.getLong("AC_NO"));
	       			vendors9.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
	       			
		       	}
	       		if(rs.getString("MAT_CODE").equals("1010101557")&& rs.getString("TRNTYPE").equals("0"))
		       	{
	       			vendorstock10.put(rs.getLong("TOTQT"), rs.getLong("AC_NO"));
	       			vendors10.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
	       			
		       	}
	       		if(rs.getString("MAT_CODE").equals("1010101210")&& rs.getString("TRNTYPE").equals("0"))
		       	{
	       			vendorstock11.put(rs.getLong("TOTQT"), rs.getLong("AC_NO"));
	       			vendors11.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
	       			
		       	}
	       		if(rs.getString("MAT_CODE").equals("1010101209")&& rs.getString("TRNTYPE").equals("0"))
		       	{
	       			vendorstock12.put(rs.getLong("TOTQT"), rs.getLong("AC_NO"));
	       			vendors12.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
	       			
		       	}
	       	}
	       	vo.setVendorstock1(vendorstock1);
	       	vo.setVendorstock2(vendorstock2);
	       	vo.setVendorstock3(vendorstock3);
	       	vo.setVendorstock4(vendorstock4);
	       	vo.setVendorstock5(vendorstock5);
	       	vo.setVendorstock6(vendorstock6);
	       	vo.setVendorstock7(vendorstock7);
	       	vo.setVendorstock8(vendorstock8);
	       	vo.setVendorstock9(vendorstock9);
	       	vo.setVendorstock10(vendorstock10);
	       	vo.setVendorstock11(vendorstock11);
	       	vo.setVendorstock12(vendorstock12);
	    
	       		cs = con.prepareCall("{call Sel_DayWiseSubContractStockLedgerFinal('103','0','"+yestdate+"','"+yestdate+"')}");
		       	rs = cs.executeQuery();
	       		while(rs.next()){
	       			if(rs.getString("MAT_CODE").equals("1010100280") && rs.getString("TRNTYPE").equals("1"))
			       	{
	       				castrec1.put(rs.getLong("Trn_Desp"), rs.getLong("AC_NO"));
	       				vendors1.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
	       				
			       	}
		       		if(rs.getString("MAT_CODE").equals("1010101165")&& rs.getString("TRNTYPE").equals("1"))
			       	{
		       			castrec2.put(rs.getLong("Trn_Desp"), rs.getLong("AC_NO"));
		       			vendors2.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
		       			
			       	}
		       		if(rs.getString("MAT_CODE").equals("1010101084")&& rs.getString("TRNTYPE").equals("1"))
			       	{
		       			castrec3.put(rs.getLong("Trn_Desp"), rs.getLong("AC_NO"));
		       			vendors3.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
		       			
			       	}
		       		if(rs.getString("MAT_CODE").equals("1010101075")&& rs.getString("TRNTYPE").equals("1"))
			       	{
		       			castrec4.put(rs.getLong("Trn_Desp"), rs.getLong("AC_NO"));
		       			vendors4.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
		       			
			       	}
		       		if(rs.getString("MAT_CODE").equals("1010100646")&& rs.getString("TRNTYPE").equals("1"))
			       	{
		       			castrec5.put(rs.getLong("Trn_Desp"), rs.getLong("AC_NO"));
		       			vendors5.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
		       			
			       	}
		       		if(rs.getString("MAT_CODE").equals("1010100633")&& rs.getString("TRNTYPE").equals("1"))
			       	{
		       			castrec6.put(rs.getLong("Trn_Desp"), rs.getLong("AC_NO"));
		       			vendors6.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
		       			
			       	}
		       		if(rs.getString("MAT_CODE").equals("1010101365")&& rs.getString("TRNTYPE").equals("1"))
			       	{
		       			castrec7.put(rs.getLong("Trn_Desp"), rs.getLong("AC_NO"));
		       			vendors7.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
		       			
			       	}
		       		if(rs.getString("MAT_CODE").equals("1010101370")&& rs.getString("TRNTYPE").equals("1"))
			       	{
		       			castrec8.put(rs.getLong("Trn_Desp"), rs.getLong("AC_NO"));
		       			vendors8.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
		       			
			       	}
		       		if(rs.getString("MAT_CODE").equals("1010101371")&& rs.getString("TRNTYPE").equals("1"))
			       	{
		       			castrec9.put(rs.getLong("Trn_Desp"), rs.getLong("AC_NO"));
		       			vendors9.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
		       			
			       	}
		       		if(rs.getString("MAT_CODE").equals("1010101557")&& rs.getString("TRNTYPE").equals("1"))
			       	{
		       			castrec10.put(rs.getLong("Trn_Desp"), rs.getLong("AC_NO"));
		       			vendors10.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
		       			
			       	}
		       		if(rs.getString("MAT_CODE").equals("1010101210")&& rs.getString("TRNTYPE").equals("1"))
			       	{
		       			castrec11.put(rs.getLong("Trn_Desp"), rs.getLong("AC_NO"));
		       			vendors11.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
		       			
			       	}
		       		if(rs.getString("MAT_CODE").equals("1010101209")&& rs.getString("TRNTYPE").equals("1"))
			       	{
		       			castrec12.put(rs.getLong("Trn_Desp"), rs.getLong("AC_NO"));
		       			vendors12.put(rs.getLong("AC_NO"), rs.getString("AC_NAME"));
		       			
			       	}
	       		}
	       	
  	       	
	       		
	       	 vo.setCastrec1(castrec1);
	       	 vo.setCastrec2(castrec2);
	       	 vo.setCastrec3(castrec3);
	       	 vo.setCastrec4(castrec4);
	       	 vo.setCastrec5(castrec5);
	       	 vo.setCastrec6(castrec6);
	       	 vo.setCastrec7(castrec7);
	       	 vo.setCastrec8(castrec8);
	       	 vo.setCastrec9(castrec9);
	       	 vo.setCastrec10(castrec10);
	       	 vo.setCastrec11(castrec11);
	       	 vo.setCastrec12(castrec12);
	       	 
	       	 vo.setVendors1(vendors1);
	       	 vo.setVendors2(vendors2);
	       	 vo.setVendors3(vendors3);
	       	 vo.setVendors4(vendors4);
	       	 vo.setVendors5(vendors5);
	       	 vo.setVendors6(vendors6);
	       	 vo.setVendors7(vendors7);
	       	 vo.setVendors8(vendors8);
	       	 vo.setVendors9(vendors9);
	       	 vo.setVendors10(vendors10);
	       	 vo.setVendors11(vendors11);
	       	 vo.setVendors12(vendors12);
	       	 
	       	 
	       	sql = "select SUB_GLACNO,SUBGL_LONGNAME from MSTACCTGLSUB where SUB_GLACNO in(101110189,101110377,101110190,101110414,101110237,101110238,101110492)ORDER BY CHARINDEX(CAST(SUB_GLACNO AS VARCHAR),'101110189,101110377,101110190,101110414,101110237,101110238,101110492') ";
	       	ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next())
	         {
	         customer1.put(Long.parseLong(rs.getString(1)),rs.getString(2));
	         cs = con.prepareCall("{call Sel_RptDespatchPlanSale('103', '0', '"+rs.getString(1)+"', '"+date+"')}");
	         rs1 = cs.executeQuery();
	         boolean flag=false;
	         while(rs1.next())
	         {
	        	 if(rs1.getString("MAT_CODE").equals("1010100280"))
	        	 {
	        	schedule1.add(rs1.getLong("NOS_QTY"));
	        	dispatch1.add(rs1.getLong("SALE_QTY"));
	        	flag=true;
	        	 }
	         }
	         if(flag==false)
	         {
	        	 schedule1.add((long) 0);
	        	 dispatch1.add((long) 0);
	         }
	         vo.setSchedule1(schedule1);
	         vo.setDispatch1(dispatch1);
	         }
	       		vo.setCustomer1(customer1);
	       		sql = "select SUB_GLACNO,SUBGL_LONGNAME from MSTACCTGLSUB where SUB_GLACNO in(101110004,101110005,101110081,101110082)ORDER BY CHARINDEX(CAST(SUB_GLACNO AS VARCHAR),'101110004,101110005,101110081,101110082') ";
	       		ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next())
		         {
		         customer2.put(Long.parseLong(rs.getString(1)),rs.getString(2));  
		         cs = con.prepareCall("{call Sel_RptDespatchPlanSale('103', '0', '"+rs.getString(1)+"', '"+date+"')}");
		         rs1 = cs.executeQuery();
		         boolean flag=false;
		         while(rs1.next())
		         {
		        	 if(rs1.getString("MAT_CODE").equals("1010101165"))
		        	 {
		        	schedule2.add(rs1.getLong("NOS_QTY")); 
		        	dispatch2.add(rs1.getLong("SALE_QTY"));
		        	flag=true;
		        	 }
		         }
		         if(flag==false)
		         {
		        	 schedule2.add((long) 0);
		        	 dispatch2.add((long) 0);
		         }
		         vo.setSchedule2(schedule2);
		         vo.setDispatch2(dispatch2);
		         }
		       vo.setCustomer2(customer2);
		       sql = "select SUB_GLACNO,SUBGL_LONGNAME from MSTACCTGLSUB where SUB_GLACNO in(101110189,101110377,101110190,101110414,101110237,101110238,101110492) ORDER BY CHARINDEX(CAST(SUB_GLACNO AS VARCHAR),'101110189,101110377,101110190,101110414,101110237,101110238,101110492')  ";
			   ps = con.prepareStatement(sql);
			   rs = ps.executeQuery();
			   while(rs.next()){
			   customer3.put(Long.parseLong(rs.getString(1)),rs.getString(2));  
		         cs = con.prepareCall("{call Sel_RptDespatchPlanSale('103', '0', '"+rs.getString(1)+"', '"+date+"')}");
		         rs1 = cs.executeQuery();
		         boolean flag=false;
		         while(rs1.next())
		         {
		        	 if(rs1.getString("MAT_CODE").equals("1010101084"))
		        	 {
		        	schedule3.add(rs1.getLong("NOS_QTY"));
		        	dispatch3.add(rs1.getLong("SALE_QTY"));
		        	flag=true;
		        	 }
		        	
		         }
		         if(flag==false)
		         {
		        	 schedule3.add((long) 0);
		        	 dispatch3.add((long) 0);
		         }
		         vo.setSchedule3(schedule3);
		         vo.setDispatch3(dispatch3);
			   }
			   vo.setCustomer3(customer3);
			   sql = "select SUB_GLACNO,SUBGL_LONGNAME from MSTACCTGLSUB where SUB_GLACNO in(101110189,101110377,101110190,101110414,101110237,101110238,101110492) ORDER BY CHARINDEX(CAST(SUB_GLACNO AS VARCHAR),'101110189,101110377,101110190,101110414,101110237,101110238,101110492') ";
			   ps = con.prepareStatement(sql);
			   rs = ps.executeQuery();
			   while(rs.next()){
			   customer4.put(Long.parseLong(rs.getString(1)),rs.getString(2));
		         cs = con.prepareCall("{call Sel_RptDespatchPlanSale('103', '0', '"+rs.getString(1)+"', '"+date+"')}");
		         rs1 = cs.executeQuery();
		         boolean flag=false;
		         while(rs1.next())
		         {
		        	 if(rs1.getString("MAT_CODE").equals("1010101075"))
		        	 {
		        	schedule4.add(rs1.getLong("NOS_QTY")); 
		        	dispatch4.add(rs1.getLong("SALE_QTY"));
		        	flag=true;
		        	 }
		         }
		         if(flag==false)
		         {
		        	 schedule4.add((long) 0);
		        	 dispatch4.add((long) 0);
		         }
		         vo.setSchedule4(schedule4);
		         vo.setDispatch4(dispatch4);
			   }
			   vo.setCustomer4(customer4);
			   sql = "select SUB_GLACNO,SUBGL_LONGNAME from MSTACCTGLSUB where SUB_GLACNO in(101110048,101110566) ORDER BY CHARINDEX(CAST(SUB_GLACNO AS VARCHAR),'101110048,101110566')";
			   ps = con.prepareStatement(sql);
			   rs = ps.executeQuery();
			   while(rs.next()){
			   customer5.put(Long.parseLong(rs.getString(1)),rs.getString(2)); 
		         cs = con.prepareCall("{call Sel_RptDespatchPlanSale('103', '0', '"+rs.getString(1)+"', '"+date+"')}");
		         rs1 = cs.executeQuery();
		         boolean flag=false;
		         while(rs1.next())
		         {
		        	 if(rs1.getString("MAT_CODE").equals("1010100646"))
		        	 {
		        	schedule5.add(rs1.getLong("NOS_QTY"));
		        	dispatch5.add(rs1.getLong("SALE_QTY"));
		        	flag=true;
		        	 }
		         }
		         if(flag==false)
		         {
		        	 schedule5.add((long) 0);
		        	 dispatch5.add((long) 0);
		         }
		         vo.setSchedule5(schedule5);
		         vo.setDispatch5(dispatch5);
			   }
			   vo.setCustomer5(customer5);
			   
			   sql = "select SUB_GLACNO,SUBGL_LONGNAME from MSTACCTGLSUB where SUB_GLACNO in(101110004,101110005,101110081,101110082) ORDER BY CHARINDEX(CAST(SUB_GLACNO AS VARCHAR),'101110004,101110005,101110081,101110082') ";
			   ps = con.prepareStatement(sql);
			   rs = ps.executeQuery();
			   while(rs.next()){
			   customer6.put(Long.parseLong(rs.getString(1)),rs.getString(2));  
		         cs = con.prepareCall("{call Sel_RptDespatchPlanSale('103', '0', '"+rs.getString(1)+"', '"+date+"')}");
		         rs1 = cs.executeQuery();
		         boolean flag=false;
		         while(rs1.next())
		         {
		        	 if(rs1.getString("MAT_CODE").equals("1010100633"))
		        	 {
		        	schedule6.add(rs1.getLong("NOS_QTY")); 
		        	dispatch6.add(rs1.getLong("SALE_QTY"));
		        	flag=true;
		        	 }
		         }
		         if(flag==false)
		         {
		        	 schedule6.add((long) 0);
		        	 dispatch6.add((long) 0);
		         }
		         vo.setSchedule6(schedule6);
		         vo.setDispatch6(dispatch6);
			   }
			   vo.setCustomer6(customer6);
			   
			   
			   sql = "select SUB_GLACNO,SUBGL_LONGNAME from MSTACCTGLSUB where SUB_GLACNO in(101110004,101110005,101110081,101110082) ORDER BY CHARINDEX(CAST(SUB_GLACNO AS VARCHAR),'101110004,101110005,101110081,101110082') ";
			   ps = con.prepareStatement(sql);
			   rs = ps.executeQuery();
			   while(rs.next()){
			   customer7.put(Long.parseLong(rs.getString(1)),rs.getString(2));  
		         cs = con.prepareCall("{call Sel_RptDespatchPlanSale('103', '0', '"+rs.getString(1)+"', '"+date+"')}");
		         rs1 = cs.executeQuery();
		         boolean flag=false;
		         while(rs1.next())
		         {
		        	 if(rs1.getString("MAT_CODE").equals("1010101365"))
		        	 {
		        	schedule7.add(rs1.getLong("NOS_QTY")); 
		        	dispatch7.add(rs1.getLong("SALE_QTY"));
		        	flag=true;
		        	 }
		         }
		         if(flag==false)
		         {
		        	 schedule7.add((long) 0);
		        	 dispatch7.add((long) 0);
		         }
		         vo.setSchedule7(schedule7);
		         vo.setDispatch7(dispatch7);
			   }
			   vo.setCustomer7(customer7);
			   
			   
			   sql = "select SUB_GLACNO,SUBGL_LONGNAME from MSTACCTGLSUB where SUB_GLACNO in(101110048,101110566) ORDER BY CHARINDEX(CAST(SUB_GLACNO AS VARCHAR),'101110048,101110566')";
			   ps = con.prepareStatement(sql);
			   rs = ps.executeQuery();
			   while(rs.next()){
			   customer8.put(Long.parseLong(rs.getString(1)),rs.getString(2));  
		         cs = con.prepareCall("{call Sel_RptDespatchPlanSale('103', '0', '"+rs.getString(1)+"', '"+date+"')}");
		         rs1 = cs.executeQuery();
		         boolean flag=false;
		         while(rs1.next())
		         {
		        	 if(rs1.getString("MAT_CODE").equals("1010101370"))
		        	 {
		        	schedule8.add(rs1.getLong("NOS_QTY")); 
		        	dispatch8.add(rs1.getLong("SALE_QTY"));
		        	flag=true;
		        	 }
		         }
		         if(flag==false)
		         {
		        	 schedule8.add((long) 0);
		        	 dispatch8.add((long) 0);
		         }
		         vo.setSchedule8(schedule8);
		         vo.setDispatch8(dispatch8);
			   }
			   vo.setCustomer8(customer8);
			   
			   sql = "select SUB_GLACNO,SUBGL_LONGNAME from MSTACCTGLSUB where SUB_GLACNO in(101110048) ORDER BY CHARINDEX(CAST(SUB_GLACNO AS VARCHAR),'101110048')";
			   ps = con.prepareStatement(sql);
			   rs = ps.executeQuery();
			   while(rs.next()){
			   customer9.put(Long.parseLong(rs.getString(1)),rs.getString(2)); 
		         cs = con.prepareCall("{call Sel_RptDespatchPlanSale('103', '0', '"+rs.getString(1)+"', '"+date+"')}");
		         rs1 = cs.executeQuery();
		         boolean flag=false;
		         while(rs1.next())
		         {
		        	 if(rs1.getString("MAT_CODE").equals("1010101371"))
		        	 {
		        	schedule9.add(rs1.getLong("NOS_QTY")); 
		        	dispatch9.add(rs1.getLong("SALE_QTY"));
		        	flag=true;
		        	 }
		         }
		         if(flag==false)
		         {
		        	 schedule9.add((long) 0);
		        	 dispatch9.add((long) 0);
		         }
		         vo.setSchedule9(schedule9);
		         vo.setDispatch9(dispatch9);
			   }
			   vo.setCustomer9(customer9);
			   
			   sql = "select SUB_GLACNO,SUBGL_LONGNAME from MSTACCTGLSUB where SUB_GLACNO in(101110048,101110566)  ORDER BY CHARINDEX(CAST(SUB_GLACNO AS VARCHAR),'101110048,101110566')";
			   ps = con.prepareStatement(sql);
			   rs = ps.executeQuery();
			   while(rs.next()){
			   customer10.put(Long.parseLong(rs.getString(1)),rs.getString(2));  
		         cs = con.prepareCall("{call Sel_RptDespatchPlanSale('103', '0', '"+rs.getString(1)+"', '"+date+"')}");
		         rs1 = cs.executeQuery();
		         boolean flag=false;
		         while(rs1.next())
		         {
		        	 if(rs1.getString("MAT_CODE").equals("1010101557"))
		        	 {
		        	schedule10.add(rs1.getLong("NOS_QTY")); 
		        	dispatch10.add(rs1.getLong("SALE_QTY"));
		        	flag=true;
		        	 }
		         }
		         if(flag==false)
		         {
		        	 schedule10.add((long) 0);
		        	 dispatch10.add((long) 0);
		         }
		         vo.setSchedule10(schedule10);
		         vo.setDispatch10(dispatch10);
			   }
			   vo.setCustomer10(customer10);
			   
			   sql = "select SUB_GLACNO,SUBGL_LONGNAME from MSTACCTGLSUB where SUB_GLACNO in(101110048,101110566)  ORDER BY CHARINDEX(CAST(SUB_GLACNO AS VARCHAR),'101110048,101110566') ";
			   ps = con.prepareStatement(sql);
			   rs = ps.executeQuery();
			   while(rs.next()){
			   customer11.put(Long.parseLong(rs.getString(1)),rs.getString(2));  
		         cs = con.prepareCall("{call Sel_RptDespatchPlanSale('103', '0', '"+rs.getString(1)+"', '"+date+"')}");
		         rs1 = cs.executeQuery();
		         boolean flag=false;
		         while(rs1.next())
		         {
		        	 if(rs1.getString("MAT_CODE").equals("1010101210"))
		        	 {
		        	schedule11.add(rs1.getLong("NOS_QTY")); 
		        	dispatch11.add(rs1.getLong("SALE_QTY"));
		        	flag=true;
		        	 }
		         }
		         if(flag==false)
		         {
		        	 schedule11.add((long) 0);
		        	 dispatch11.add((long) 0);
		         }
		         vo.setSchedule11(schedule11);
		         vo.setDispatch11(dispatch11);
			   }
			   vo.setCustomer11(customer11);
			   
			   sql = "select SUB_GLACNO,SUBGL_LONGNAME from MSTACCTGLSUB where SUB_GLACNO in(101110048,101110566) ORDER BY CHARINDEX(CAST(SUB_GLACNO AS VARCHAR),'101110048,101110566' )";
			   ps = con.prepareStatement(sql);
			   rs = ps.executeQuery();
			   while(rs.next()){
			   customer12.put(Long.parseLong(rs.getString(1)),rs.getString(2));
		         cs = con.prepareCall("{call Sel_RptDespatchPlanSale('103', '0', '"+rs.getString(1)+"', '"+date+"')}");
		         rs1 = cs.executeQuery();
		         boolean flag=false;
		         while(rs1.next())
		         {
		        	 if(rs1.getString("MAT_CODE").equals("1010101209"))
		        	 {
		        	schedule12.add(rs1.getLong("NOS_QTY")); 
		        	dispatch12.add(rs1.getLong("SALE_QTY"));
		        	flag=true;
		        	 }
		         }
		         if(flag==false)
		         {
		        	 schedule12.add((long) 0);
		        	 dispatch12.add((long) 0);
		         }
		         vo.setSchedule12(schedule12);
		         vo.setDispatch12(dispatch12);
			   }
			   vo.setCustomer12(customer12);
			   
			   
			   
			   
		} catch (Exception e) {
	   			e.printStackTrace();
	   			System.out.println("Connection Failed");
	   		}
		return true;
	}
        
}

