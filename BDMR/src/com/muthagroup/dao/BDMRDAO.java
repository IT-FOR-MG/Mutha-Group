package com.muthagroup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import com.muthagroup.connection.ConnetionModel;
import com.muthagroup.vo.BDMRVO;

public class BDMRDAO {
   Connection con=null;
	public void insertIntoERP(BDMRVO vo) {
		Calendar c = Calendar.getInstance();
		int TRID=0;
		int year=0;
		int month=0;
		int day=0;
		int hour=0;
		int minute=0;
		String date=null;
		String time=null;
		String start=null;		
		String end=null;
		
		String TRNO=null;
		String SQL=null;
		Statement stmt=null;
		ResultSet rs=null;
		String MAXTRNO=null;
		
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH)+1;
		day=c.get(Calendar.DAY_OF_MONTH);
		hour=c.get(Calendar.HOUR_OF_DAY);
		minute=c.get(Calendar.MINUTE);
		date=String.format("%04d", year)+String.format("%02d", month)+String.format("%02d", day);
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
		con=ConnetionModel.getMSSqlConnection();	
		SQL = "SELECT MAX(CAST(TRAN_NO AS BIGINT)) FROM TRNMAINTENANCEREQH WHERE ISNUMERIC(TRAN_NO)=1 "; 
		stmt = con.createStatement(); 
        rs = stmt.executeQuery(SQL);
        if (rs.next()){ 
       	if(rs.getString(1)!=null){
       	MAXTRNO=rs.getString(1).substring(14); 
        TRID=Integer.parseInt(MAXTRNO)+1; 
       	}
       	else {
       	TRID=0001;
	    }
        }
        TRNO="101"+start+end+"92011"+String.format("%02d", month)+String.format("%04d", TRID);
        PreparedStatement prstmt = con.prepareStatement("insert into TRNMAINTENANCEREQH (TRAN_SUBTYPE,SHORT_NAME,TRAN_DATE,TAG_CODE,DEPT_CODE,TRAN_AMT,EMP_CODE,ESTIMITED_DATE,PRIORITY,IS_SHUTDOWNREQUIRED,IS_SYSSHUTDOWNREQUIRED,IS_SAFETYPERMITREQUIRED,SAFETY_PERMITDATE,BREAKDOWN_DATE,BREAKDOWN_TIME,SHORT_NARRTN,LONG_NARRTN,SYSADD_DATETIME,SYSADD_LOGIN,SYSCHNG_DATETIME,SYSCHNG_LOGIN,SYSAPR_DATETIME,SYSAPR_LOGIN,SYSCHNG_REMARK,STATUS_CODE,SYS_DATE,IS_PREDICTIVE,USER_NAME,TRAN_NO) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        prstmt.setString(1,"1");
        //System.out.println("1:TRAN_SUBTYPE: "+"1");
        prstmt.setString(2,"MNTREQ");
        //System.out.println("2:SHORT_NAME: "+"MNTREQ");
        prstmt.setString(3,date);
        //System.out.println("3:TRAN_DATE: "+date);
        prstmt.setString(4,vo.getMachine_name());
        //System.out.println("4:TAG_CODE: "+"1010000001");
        prstmt.setNull(5, java.sql.Types.INTEGER);
        //System.out.println("5:DEPT_CODE: "+srsuserrs.getString("user_dept_code"));
        prstmt.setString(6,"0");
        //System.out.println("6:TRAN_AMT: "+"0");
        prstmt.setString(7,"1010274");
        //System.out.println("7:EMP_CODE: "+srsuserrs.getString("srs_user_code"));
        prstmt.setString(8,"");
        //System.out.println("8:ESTIMITED_DATE: "+date);
        prstmt.setString(9,"0");
        //System.out.println("9:PRIORITY: "+"0");
        prstmt.setString(10,"0");
        //System.out.println("10:IS_SHUTDOWNREQUIRED: "+"0");
        prstmt.setString(11,"0");
        //System.out.println("11:IS_SYSSHUTDOWNREQUIRED: "+"0");
        prstmt.setString(12,"0");
        //System.out.println("12:IS_SAFETYPERMITREQUIRED: "+"0");
        prstmt.setNull(13, java.sql.Types.INTEGER);
        //System.out.println("13:SAFETY_PERMITDATE: "+"");
        prstmt.setString(14,date);
        //System.out.println("14:BREAKDOWN_DATE: "+date);
        prstmt.setString(15,time);
        //System.out.println("15:BREAKDOWN_TIME: "+time);
        prstmt.setString(16,"Breakdown Maintainance Reqisition");
        //System.out.println("16:SHORT_NARRTN: "+"Breakdown Main.Req.");
        prstmt.setString(17,vo.getMachine_desc());	
        //System.out.println("17:LONG_NARRTN: "+"Machine Type: "+machine_type+"\nMachine Name: "+machine_name+"\nDetails"+description);
        prstmt.setString(18,date+time+":01"); 
        //System.out.println("18:SYSADD_DATETIME: "+date+":"+time);
        prstmt.setString(19,"ADMIN");		
        //System.out.println("19:SYSADD_LOGIN: "+"ADMIN");
        prstmt.setString(20,date+time+":01");
        //System.out.println("20:SYSCHNG_DATETIME: "+date+":"+time);
        prstmt.setString(21,"ADMIN");
        //System.out.println("21:SYSCHNG_LOGIN: "+"ADMIN");
        prstmt.setNull(22, java.sql.Types.INTEGER);
        //System.out.println("22: SYSAPR_DATETIME: "+"");
        prstmt.setNull(23, java.sql.Types.INTEGER);
        //System.out.println("23:SYSAPR_LOGIN: "+"");
        prstmt.setNull(24, java.sql.Types.INTEGER);
        //System.out.println("24:SYSCHNG_REMARK: "+"");
        prstmt.setString(25,"0");	
        //System.out.println("25:STATUS_CODE: "+"0");
        prstmt.setString(26,date+time+":01");	
        //System.out.println("26:SYS_DATE: "+date+":"+time);
        prstmt.setString(27,"0");
        //System.out.println("27:IS_PREDICTIVE: "+"0");
        prstmt.setString(28,"BWAYS");
        //System.out.println("28:USER_NAME: "+"BWAYS");
        prstmt.setString(29,TRNO);
        //System.out.println("29:TRAN_NO: "+TRID);
       /* prstmt.setString(29,"");
        prstmt.setString(30,"0");*/
        prstmt.executeUpdate();
        con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
