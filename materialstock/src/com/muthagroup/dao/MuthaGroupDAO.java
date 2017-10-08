package com.muthagroup.dao;

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

import com.muthagroup.connection.ConnectionModel;
import com.muthagroup.vo.MuthaGroupVO;
import com.mysql.jdbc.ResultSetMetaData;

public class MuthaGroupDAO {
	Connection con=ConnectionModel.getConnection2();
	String sql =null;
	PreparedStatement ps=null;
	ResultSet rs =null;
	public boolean getMaterialList(MuthaGroupVO vo){
		String cmp=vo.getCmp();
		String db=null;
		switch(cmp)
		{
		case "101":
			db="ENGERP";
			break;
		case "102":
			db="H25ERP";
			break;
		case "103":
			db="FOUNDRYERP";
			break;
		case "105":
			db="DIERP";
			break;
		case "106":
			db="K1ERP";
			break;
		case "108":
			db="PWDERP";
			break;
		}
  try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		    Connection connection = DriverManager.getConnection("jdbc:sqlserver://192.168.0.6:1433;databaseName="+db+";user=BWAYS;password=BWAYSKING321");	
	  		String SQL = "SELECT * FROM MSTMATERIALS";	
	  		Statement stmt2 = connection.createStatement(); 
        	ResultSet rs = stmt2.executeQuery(SQL);
        	ArrayList<String> matcode = new ArrayList<String>();
        	ArrayList<String> matname = new ArrayList<String>();
        	ArrayList<String> mattype = new ArrayList<String>();
        	while (rs.next()) 
				 {
        		     matcode.add(rs.getString("CODE"));
        		     matname.add(rs.getString("NAME"));
        		     mattype.add(rs.getString("MATERIAL_TYPE"));
				 }
				vo.setMATCODE(matcode);
				vo.setMATNAME(matname);
				vo.setMATTYPE(mattype);
			 SQL = "SELECT * FROM MSTMATGROUP";	
		  	 stmt2 = connection.createStatement(); 
	        	rs = stmt2.executeQuery(SQL);
	        	ArrayList<String> matgroupcode = new ArrayList<String>();
	        	ArrayList<String> matgroupname = new ArrayList<String>();
	        	while (rs.next()) 
					 {
	        		matgroupcode.add(rs.getString("CODE"));
	        		matgroupname.add(rs.getString("NAME"));
					 }
					vo.setMATGROUPCODE(matgroupcode);
				    vo.setMATGROUPNAME(matgroupname);
				
				return true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;	
	}
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
	public boolean getStockStatus(MuthaGroupVO vo)
		{
			boolean flag = false;
			try {
			ArrayList<ArrayList<String>>allList=new ArrayList<ArrayList<String>>();
			String cmp=null;
			String site=null;
			String mat_type=null;
			String location=null;
			String qty=null;
			String id=null;	
			String rowdate=vo.getDate();
			String[] date=rowdate.split(" ");
			Date monthofdate = null;
			monthofdate = new SimpleDateFormat("MMMM").parse(date[0]);
			Calendar cal = Calendar.getInstance();
			cal.setTime(monthofdate);
			int monthno=cal.get(Calendar.MONTH)+1;
			if(vo.getUserName().equals("itsupports"))
			{
			sql="select * from barcode_tbl where month(date)='"+monthno+"' and year(date)='"+date[1]+"' and enable='1' order by mat_name ";
			}
			else
			{
			sql="select * from barcode_tbl where month(date)='"+monthno+"' and year(date)='"+date[1]+"' and enable='1' and company="+vo.getCmp()+" order by mat_name";
			}
	
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				flag=false;
				while(rs.next())
				{
				if(rs.getString(1)!=null)
				{
				cmp=rs.getString("company");
				mat_type=rs.getString("mat_type");
				location=rs.getString("location");
				site=rs.getString("stock_site");
				qty=rs.getString("qty");
				id=rs.getString("id");
				ArrayList<String>list=new ArrayList<String>();	
				list.add(rs.getString("date"));                                     //1 Date
				switch(cmp)                                                         //2 Company
				{
				case "0":
					list.add("Un Defined");
					break;
				case "101":
					list.add("Mutha Engg H-21");
					break;
				case "102":
					list.add("Mutha Engg H-25");
					break;	
				case "103":
					list.add("Mutha Founders");
					break;
				case "105":
					list.add("Dhanashee Ind");
					break;
				case "106":
					list.add("Mutha Engg UIII");
					break;	
				case "108":
					list.add("Mutha Powder Coating");
					break;
				}
				list.add(rs.getString("barcode_id"));                               //3 Bar code ID
				list.add(rs.getString("mat_name"));                                 //4 Material Name
				switch(mat_type)                                                    //5 Material Type
				{
				case "0":
					list.add("Un Defined");
					break;
				case "1":
					list.add("As Cast");
					break;
				case "2":
					list.add("Fettled");
					break;
				case "3":
					list.add("Machined");
					break;
				case "4":
					list.add("Rejected");
					break;
				case "5":
					list.add("Rework");
					break;
				case "6":
					list.add("F4 Out");
					break;
				case "7":
					list.add("Other");
					break;
				case "8":
					list.add("Casting Defect");
					break;
				case "9":
					list.add("Machining Defect");
					break;
				case "10":
					list.add("Semi Finish");
					break;
				case "11":
					list.add("Raw");
					break;
				case "12":
					list.add("Inprocess");
					break;
				case "13":
					list.add("WIP2");
					break;
				}
				switch(site)
				{
				case "0":
					list.add("Un Defined");
					break;
				case "1":
					list.add("In House");
					break;
				case "2":
					list.add("Vendor");
					break;
				}
				switch(location)                                                 //6 Location
				{
				case "0":
					list.add("Un Defined");
					break;
				case "1":
					list.add("Knock Out");
					break;
				case "2":
					list.add("Shot Blasting");
					break;
				case "3":
					list.add("Quality");
					break;
				case "4":
					list.add("Dial SPM");
					break;
				case "5":
					list.add("Rework Area");
					break;
				case "6":
					list.add("Rejection Area");
					break;
				case "7":
					list.add("Pattern Shop");
					break;
				case "8":
					list.add("Office Area");
					break;					
				}
				if(qty.equals("0"))                                               //7 Quantity
				{
					list.add("Un Defined");
				}
				else
				{
					list.add(qty);
				}
				
				if(id.equals("0"))
				{
					list.add("Un Defined");
				}
				else
				{
					list.add(id);
				}
				allList.add(list);
				flag=true;
				}
				}
				if(flag)
				{
				vo.setList1(allList);
				}
				else
				{
					vo.setList1(null);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return flag;	
		}
	public boolean getItemwiseStockStatus(MuthaGroupVO vo)
	{
		boolean flag = false;
		try {
			ArrayList<ArrayList<String>>allList=new ArrayList<ArrayList<String>>();	
			String rowdate=vo.getDate();
			String[] date=rowdate.split(" ");
			Date monthofdate = null;
			monthofdate = new SimpleDateFormat("MMMM").parse(date[0]);
			Calendar cal = Calendar.getInstance();
			cal.setTime(monthofdate);
			int monthno=cal.get(Calendar.MONTH)+1;	
			if(vo.getUserName().equals("itsupports")){
		sql="select  mat_name,"+
				"coalesce(sum(case when mat_type = '1' then qty end), 0),"+
				"coalesce(sum(case when mat_type = '2' then qty end), 0),"+
				"coalesce(sum(case when mat_type = '3' then qty end), 0),"+
				"coalesce(sum(case when mat_type = '4' then qty end), 0),"+
				"coalesce(sum(case when mat_type = '5' then qty end), 0),"+
				"coalesce(sum(case when mat_type = '6' then qty end), 0),"+
				"coalesce(sum(case when mat_type = '7' then qty end), 0),"+
				"coalesce(sum(case when mat_type = '8' then qty end), 0),"+
				"coalesce(sum(case when mat_type = '9' then qty end), 0),"+
				"coalesce(sum(case when mat_type = '10' then qty end), 0),"+
				"coalesce(sum(case when mat_type = '11' then qty end), 0),"+
				"coalesce(sum(case when mat_type = '12' then qty end), 0),"+
				"coalesce(sum(case when mat_type = '13' then qty end), 0),"+
		"SUM(QTY)"+
		"FROM odk_prod.barcode_tbl WHERE month(date)='"+monthno+"' and year(date)='"+date[1]+"' and enable='1' GROUP BY mat_name,mat_type order by mat_name ";		
			}
			else
			{
				sql="select  mat_name,"+
						"coalesce(sum(case when mat_type = '1' then qty end), 0),"+
						"coalesce(sum(case when mat_type = '2' then qty end), 0),"+
						"coalesce(sum(case when mat_type = '3' then qty end), 0),"+
						"coalesce(sum(case when mat_type = '4' then qty end), 0),"+
						"coalesce(sum(case when mat_type = '5' then qty end), 0),"+
						"coalesce(sum(case when mat_type = '6' then qty end), 0),"+
						"coalesce(sum(case when mat_type = '7' then qty end), 0),"+
						"coalesce(sum(case when mat_type = '8' then qty end), 0),"+
						"coalesce(sum(case when mat_type = '9' then qty end), 0),"+
						"coalesce(sum(case when mat_type = '10' then qty end), 0),"+
						"coalesce(sum(case when mat_type = '11' then qty end), 0),"+
						"coalesce(sum(case when mat_type = '12' then qty end), 0),"+
						"coalesce(sum(case when mat_type = '13' then qty end), 0),"+
						"SUM(QTY)"+
						"FROM odk_prod.barcode_tbl WHERE month(date)='"+monthno+"' and year(date)='"+date[1]+"' and enable='1' and company="+vo.getCmp()+" GROUP BY mat_name order by mat_name ";			
			} 
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
	       while(rs.next())
	         {
	    	  ArrayList<String> list=new ArrayList<String>();
	    	  for(int i=1;i<=15;i++)
	    	  {
	    	  list.add(rs.getString(i));
	          }
	         allList.add(list);
	         flag=true;
	         }
	       if(flag){
	        vo.setList2(allList);
	       }
	       else{
	    	   vo.setList2(null);
	       }
	       }catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;	
	}
	public boolean uplaodERP(MuthaGroupVO vo) {
			String array[]=vo.getRecords();
			StringBuilder sb = new StringBuilder("Select sum(qty),mat_code,company,stock_site from barcode_tbl where id IN (");
			  boolean added = false;
			  for(String s:array){
			    if (added){
			      sb.append(",");
			    }      
			    sb.append("'");
			    sb.append(s);
			    sb.append("'");
			    added = true;
			  }
			  sb.append(") and enable ='1' group by mat_code,company,stock_site");
			try {
				ps = con.prepareStatement(sb.toString());
				rs = ps.executeQuery();
				if(rs.next())
				{
					Connection con=null;
					Calendar c = Calendar.getInstance();
					String start=null;		
					String end=null;
					String date=null;
					String time=null;
					String TRID=null;
					String chlnqty=null;
					String castqty=null;
					int trid=0;
					int year=0;
					int month=0;
					int day=0;
					int hour=0;
					int minute=0;
					 year = c.get(Calendar.YEAR);
		   			 month = c.get(Calendar.MONTH)+1;
		   			 day=c.get(Calendar.DAY_OF_MONTH);
		   			 hour=c.get(Calendar.HOUR_OF_DAY);
		   			 minute=c.get(Calendar.MINUTE);
		   			 date=String.valueOf(year)+String.format("%02d", month)+String.format("%02d", day);
		   			 time=String.format("%02d", hour)+":"+String.format("%02d", minute);
		   			if(month<=3)
		   			{
		   				start=(String.valueOf(year-1).substring(2));
		   				end=(String.valueOf(year).substring(2));
		   				
		   			}
		   			else
		   			{
		   				start=(String.valueOf(year).substring(2));
		   				end=(String.valueOf(year+1).substring(2));
		   			}
				try{
					String cmp=rs.getString("company");
					String db=null;
					if(cmp.equals("101"))
					{
						db="ENGERP";
					}
					if(cmp.equals("102"))
					{
						db="H25ERP";
					}
					if(cmp.equals("103"))
					{
						db="FOUNDRIERP";
					}
					if(cmp.equals("105"))
					{
						db="DIERP";
					}
					if(cmp.equals("106"))
					{
						db="K1ERP";
					}
					if(cmp.equals("108"))
					{
						db="PWDERP";
					}
					
					 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				     con = DriverManager.getConnection("jdbc:sqlserver://192.168.0.6:1433;databaseName="+db+";user=BWAYS;password=BWAYSKING321 ");	
			         String SQL = "SELECT  MAX(CAST(TRAN_NO AS BIGINT))  FROM TRNACCTMATH WHERE SUBSTRING(CAST(TRAN_NO AS VARCHAR), 8, 5)='21511'"; 
			         Statement stmt2 = con.createStatement(); 
			         ResultSet resultset = stmt2.executeQuery(SQL);
			         if (resultset.next()) 
			         {
			        	 if(resultset.getString(1)!=null)
			        	 {
			        	String strid=resultset.getString(1).substring(14); 
			            trid=Integer.parseInt(strid)+1; 
			        	 }
			        	 else
				         {
				        	 trid=000001;
				         }
			         }
			         
			         if(rs.getString("stock_site").equals("0"))
			         {
			        	 chlnqty=rs.getString("sum(qty)");
			        	 castqty="0";
			         }
			         else
			         {
			        	 chlnqty="0";
			        	 castqty=rs.getString("sum(qty)");
			         }
			         TRID=rs.getString("company")+start+end+"21511"+String.format("%06d", trid);
			        PreparedStatement ps =con.prepareStatement("insert into TRNACCTMATH (TRAN_NO,TRAN_SUBTYPE,SHORT_NAME,TRAN_DATE,COMPUNIT_ID,GUID,SYSADD_DATETIME,SYSADD_LOGIN,SYSCHNG_DATETIME,SYSCHNG_LOGIN,STATUS_CODE,SYS_DATE,USER_NAME,EXC_TRANNO,SR_NO ) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			        ps.setString(1,TRID);
			        ps.setString(2,"1");
			        ps.setString(3,"PHYSTOCK");
			        ps.setString(4,date);
			        ps.setString(5,rs.getString("mat_code"));
			        ps.setString(6,"91C45D81-8A9F-46EB-AFF5-C4ED91C1CD42");
			        ps.setString(7,date+":"+time);
			        ps.setString(8,"ADMIN");
			        ps.setString(9,date+":"+time);
			        ps.setString(10,"ADMIN");
			        ps.setString(11,"21");
			        ps.setString(12,date+":"+time);
			        ps.setString(13,"BWAYS");
			        ps.setString(14,TRID);
			        ps.setString(15,"1");
			        ps.executeUpdate();
			        PreparedStatement ps1 =con.prepareStatement("insert into TRNSTORMATI (TRAN_NO,SR_NO,TRAN_SUBTYPE,TRAN_DATE,MAT_CODE,CHLN_QTY,CAST_QTY,STATUS_CODE) values(?,?,?,?,?,?,?,?)");
			        ps1.setString(1,TRID);
			        ps1.setString(2,"1");
			        ps1.setString(3,"1");
			        ps1.setString(4,date);
			        ps1.setString(5,rs.getString("mat_code"));
			        ps1.setString(6,chlnqty);
			        ps1.setString(7,castqty);
			        ps1.setString(8,"21");
			        ps1.executeUpdate();
			        
				}catch(Exception e){
					e.printStackTrace();
				}
				
					return true;
				}
				else
				{
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// TODO Auto-generated method stub
			return false;
		}
    public boolean updatebarcode_tbl(MuthaGroupVO vo){
        	String array[]=vo.getRecords();
        	StringBuilder sb = new StringBuilder("update barcode_tbl set enable = '0' where id in(");
			  boolean added = false;
			  for(String s:array){
			    if (added){
			      sb.append(",");
			    }      
			    sb.append("'");
			    sb.append(s);
			    sb.append("'");
			    added = true;
			  }
			  sb.append(")");
			try {
				ps = con.prepareStatement(sb.toString());
				int res=ps.executeUpdate();
				if(res>0)
				{
					return true;
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
        	
        	return false;
        }
        	
        
}

