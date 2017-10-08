package com.muthagroup.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Base64;
import com.muthagroup.connection.ConnectionModel;
import com.muthagroup.vo.MuthaGroupVO;

public class MuthaGroupDAO {
	Connection con=null;
	String sql =null;
	PreparedStatement ps=null;
	PreparedStatement ps1=null;
	PreparedStatement ps2=null;
	ResultSet rs =null;
	ResultSet rs1 =null;
	ResultSet rs2 =null;
	Calendar c = Calendar.getInstance();
	public boolean validateUser(MuthaGroupVO vo){
		boolean validationStatus=false;
		try {
		validationStatus=false;	
		con=ConnectionModel.getMIDIServerConnection();	
		sql = "select * from user_tbl where Login_Name='"+vo.getUserName()+"' and Login_Password='"+vo.getPassword()+"'";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs.next())
		{	
			validationStatus=true;
		}
		else
		{
			validationStatus=false;
		}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return validationStatus;
	}
	public void displayEntry(MuthaGroupVO vo){
		try{
			ArrayList<ArrayList<String>> funaceRecords = new ArrayList<ArrayList<String>>(); 
		con=ConnectionModel.getLinuxServerConnection();
		sql = "select _MARKED_AS_COMPLETE_DATE,MY_SELECT1,MY_SELECT2,_URI from FSPNSTMFPL_CORE WHERE DATE(_MARKED_AS_COMPLETE_DATE)='"+vo.getDate()+"'";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next())
		{
			ArrayList<String> records = new ArrayList<String>();
			for(int i=1;i<=3;i++)
			{
			records.add(rs.getString(i));	
			}
			funaceRecords.add(records);
			//logic for Shift
			Timestamp tStamp=rs.getTimestamp("_MARKED_AS_COMPLETE_DATE");
            final long time = tStamp.getTime();
            c.setTimeInMillis(time);
            int hours_of_day=c.get(Calendar.HOUR_OF_DAY);
			String shift=null;
		     if(hours_of_day>=8&&hours_of_day<16)
		     {
		    	 shift="First Shift";
		     }
		     if(hours_of_day>=16&&hours_of_day<=23)
		     {
		    	 shift="Second Shift";
		     }
		     if(hours_of_day>=0&&hours_of_day<8)
		     {
		    	 shift="Third Shift";
		     }
		     records.add(shift);
		     //Images
		     sql="select value from FSPNSTMFPL_MY_IMAGE1_BLB WHERE _TOP_LEVEL_AURI='" + rs.getString("_URI") + "'";
		     ps1 = con.prepareStatement(sql);
			 rs1 = ps1.executeQuery();
		     if(rs1.next())
		     {
		    	 records.add(Base64.getEncoder().encodeToString(rs1.getBytes("value"))); 
		     }
		     else 
		     {
		    	 records.add(null);				
		     }
		     sql="select value from FSPNSTMFPL_MY_IMAGE2_BLB WHERE _TOP_LEVEL_AURI='" + rs.getString("_URI") + "'";
		     ps2 = con.prepareStatement(sql);
			 rs2 = ps2.executeQuery();
		     if(rs2.next())
		     {
		    	 records.add(Base64.getEncoder().encodeToString(rs2.getBytes("value"))); 
		     }
		     else 
		     {
				records.add(null);				
		     }    
		}
		vo.setFunaceRecords(funaceRecords);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
