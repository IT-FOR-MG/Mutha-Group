

import java.sql.*;
import java.util.Calendar;

class Example
{ 
       public static void main(String args[])
       { 
    	   Calendar c = Calendar.getInstance();
    	   int  year = c.get(Calendar.YEAR);
    	   int  month = c.get(Calendar.MONTH)+1;
    	   int  day=c.get(Calendar.DAY_OF_MONTH);
    	   //int  hour=c.get(Calendar.HOUR_OF_DAY);
    	   //int  minute=c.get(Calendar.MINUTE);
    	   String date=String.valueOf(year)+String.format("%02d", month)+String.format("%02d", day);
    	   //String yestdate=String.valueOf(year)+String.format("%02d", month)+String.format("%02d", day-1);
    	   //String time=String.format("%02d", hour)+":"+String.format("%02d", minute);
    	   try {
    	 Connection con = null;
    	 Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://192.168.0.7:3306/erp_database", "root","root"); 
         System.out.println("Connected.");
         //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");       
         //con = DriverManager.getConnection("jdbc:sqlserver://192.168.0.6:1433;databaseName=FOUNDRYERP;user=UDAY;password=12345 ");
         //System.out.println("Connection Occurred to FOUNDRYERP");
         //String sql="{call Sel_RptDespatchPlanSale('103','0','101110189101110190','20170724')}";
         //CallableStatement cs = con.prepareCall(sql);
         //CallableStatement cs = con.prepareCall("{ call Sel_DayWiseSubContractStockLedgerFinal('103','0','"+date+"','"+date+"') }");
         //ResultSet rs = cs.executeQuery();
        //String SQL = "select * from  "; 
        //  String SQL = "select * from MSTMATERIALS";
        //  String SQL = "select * from MSTMATBOM";
        String SQL="select * from new_item_creation  ";
        //  String SQL = "select CODE,NAME from MSTMATERIALS where CODE='1010100582' ";
        //  String SQL = "select SUB_GLACNO,MAT_CODE from MSTMATERIALGLSUB ";
        //  String SQL = "select SUB_GLACNO,SUBGL_LONGNAME from MSTACCTGLSUB  where SUBGL_LONGNAME not like 'ZZZ %' order by SUBGL_LONGNAME  ";
      	//String SQL="SELECT MSTACCTGLSUB.SUBGL_LONGNAME,MAX(MSTMATERIALGLSUB.SUB_GLACNO) FROM MSTMATERIALGLSUB LEFT OUTER JOIN  MSTACCTGLSUB ON MSTACCTGLSUB.SUB_GLACNO = MSTMATERIALGLSUB.SUB_GLACNO GROUP BY MSTACCTGLSUB.SUBGL_LONGNAME,MSTACCTGLSUB.SUB_GLACNO   ORDER BY MSTACCTGLSUB.SUB_GLACNO";
        //String SQL = "select * from MSTMATERIALGLSUB ";
       //String SQL= "select a.SUB_GLACNO,a.SUBGL_LONGNAME  from MSTACCTGLSUB as a  LEFT OUTER JOIN  MSTACCTGLSUB as b ON a.SUBGL_LONGNAME=b.SUBGL_LONGNAME where a.SUB_GLCODE='11' ";
       // String SQL= "select * from MSTACCTGLSUB where SUBGL_LONGNAME like 'MUTHA%' ";
        //String SQL = "select SUB_GLACNO,SUBGL_LONGNAME from MSTACCTGLSUB  where SUB_GLACNO='101110081' and SUB_GLCODE='11'  and SUBGL_LONGNAME not like 'ZZZ %' order by SUBGL_LONGNAME  ";
        //String SQL = "select DISTINCT CODE,NAME from MSTMATERIALS where MATERIAL_TYPE='101' and NAME not like 'Z CLOSED%' order by NAME";
      	Statement stmt = con.createStatement(); 
     	ResultSet rs = stmt.executeQuery(SQL);
     	ResultSetMetaData rsmd = rs.getMetaData(); 
     	int length=rsmd.getColumnCount();
     	int i=1;
     	while(i<=length)
     	{
     		 System.out.print(rsmd.getColumnName(i));
     		System.out.print("	");
        	i++;
     		
     	}
       /*System.out.print("CUST_NAME");
 	   System.out.print("	");
 	   System.out.print("SALE_QTY");
 	   System.out.print("	");
 	   System.out.print("NOS_QTY");*/
     	 System.out.print("\n");
     	i=1;   
       while(rs.next())
         {
    	   i=1;
        	while(i<=length)
        	{	 
        	 System.out.print(rs.getString(i));
        	 System.out.print("	");
        	 i++;
        	}
        	
        	System.out.print("\n");
         }
   		} catch (Exception e) {
   			e.printStackTrace();
   			System.out.println("Connection Failed");
   		}
    }
}
