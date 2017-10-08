package com.muthagroup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.muthagroup.connection.ConnectionModel;
import com.muthagroup.vo.MuthaGroupVO;

public class MuthaGroupDAO {
	Connection con=null;
	String sql =null;
	PreparedStatement ps=null;
	ResultSet rs =null;
	
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
	public void newEntry(MuthaGroupVO vo) {
	try{
		con=ConnectionModel.getLinuxServerConnection();	
		sql="insert into visitor_entry(first_name,middle_name,last_name,address,mob,emp_to_meet,purpose,date,in_time,out_time) values(?,?,?,?,?,?,?,?,?,?)";
		ps = con.prepareStatement(sql);
		ps.setString(1,vo.getFirstName());
		ps.setString(2,vo.getMiddleName());
		ps.setString(3,vo.getLastName());
		ps.setString(4,vo.getAddress());
		ps.setString(5,vo.getMobileNo());
		ps.setString(6,vo.getEmpToMeet());
		ps.setString(7,vo.getPurpose());
		ps.setString(8,vo.getDate());
		ps.setString(9,vo.getInTime());
		ps.setString(10,"-");
		ps.executeUpdate();
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	public void displayEntry(MuthaGroupVO vo){
		try{
			ArrayList<ArrayList<String>> visitorRecords = new ArrayList<ArrayList<String>>(); 
		con=ConnectionModel.getLinuxServerConnection();
		sql = "select * from visitor_entry where date='"+vo.getDate()+"'";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next())
		{
			ArrayList<String> records = new ArrayList<String>();
			for(int i=1;i<=11;i++)
			{
			records.add(rs.getString(i));	
			}
			visitorRecords.add(records);
		}
		vo.setVisitorRecords(visitorRecords);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void outVisitor(MuthaGroupVO vo, String id) {
		try {
			con=ConnectionModel.getLinuxServerConnection();	
			sql="update visitor_entry set out_time='"+vo.getOutTime()+"' where sr_no="+id;
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void uploadEmp(MuthaGroupVO vo) {
		try {
			ArrayList<String> empUser=new ArrayList<String>(); 
			con=ConnectionModel.getMIDIServerConnection();	
			sql = "select * from user_tbl where Enable_id=1";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next())
			{	
				empUser.add(rs.getString("U_Name"));
			}
			vo.setEmpUser(empUser);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		
	}
	public void uploadMob(MuthaGroupVO vo){
		try {
			ArrayList<String> visitorMob=new ArrayList<String>(); 
			con=ConnectionModel.getLinuxServerConnection();	
			sql = "select distinct(mob) from visitor_entry";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next())
			{	
				visitorMob.add(rs.getString("mob"));
			}
			vo.setVisitorMob(visitorMob);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	}
}
