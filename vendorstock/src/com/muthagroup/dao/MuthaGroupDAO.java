package com.muthagroup.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import com.muthagroup.connection.ConnectionModel;
import com.muthagroup.vo.MuthaGroupVO;
public class MuthaGroupDAO {

	Connection con1=ConnectionModel.getConnection1();
	Connection con2=ConnectionModel.getConnection2();
	String sql=null;
	Calendar c = Calendar.getInstance();
	int  year = c.get(Calendar.YEAR);
	int  month = c.get(Calendar.MONTH)+1;
	int  day=c.get(Calendar.DAY_OF_MONTH);
	String date=String.valueOf(year)+String.format("%02d", month)+String.format("%02d", day);
public void setDate(MuthaGroupVO vo)
{
	vo.setDate(day+"/"+month+"/"+year);
}
public void setAllItems(MuthaGroupVO vo)
{
	LinkedHashMap<Long,String> allItem=new LinkedHashMap<Long,String>();
	try
	{
	sql = "select CODE,NAME from MSTMATERIALS where MATERIAL_TYPE='101' and NAME not like 'Z CLOSED%' order by NAME";
	PreparedStatement ps = con1.prepareStatement(sql);
	ResultSet rs = ps.executeQuery();
	 while(rs.next())
	{
		 allItem.put(Long.parseLong(rs.getString("CODE")),rs.getString("NAME"));
	}
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	vo.setAllItem(allItem);
}
public void setallCustomersAndVendors(MuthaGroupVO vo)
{
	LinkedHashMap<Long,String> allCustomersAndVendors=new LinkedHashMap<Long,String>();
	try
	{
	sql = "select SUB_GLACNO,SUBGL_LONGNAME from MSTACCTGLSUB ";
	PreparedStatement ps = con1.prepareStatement(sql);
	ResultSet rs = ps.executeQuery();
	 while(rs.next())
	{
		 allCustomersAndVendors.put(Long.parseLong(rs.getString("SUB_GLACNO")),rs.getString("SUBGL_LONGNAME"));
	}
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	vo.setAllCustomersAndVendors(allCustomersAndVendors);
}
public void setItems(MuthaGroupVO vo)
{
	LinkedHashMap<Long,String> item=new LinkedHashMap<Long,String>();
	try{
		sql="select * from stock_mat";
		PreparedStatement  ps=con2.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			item.put(Long.parseLong(rs.getString("mat_code")),rs.getString("comm_name"));	
		}
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	vo.setItem(item);
}
public void setCutomers(MuthaGroupVO vo)
{
	LinkedHashMap<Long,String> customer=new LinkedHashMap<Long,String>();
	try{
		sql="select * from stock_cust";
		PreparedStatement  ps=con2.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			customer.put(Long.parseLong(rs.getString("mat_code")),rs.getString("cust_code"));	
		}
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	vo.setCustomer(customer);
}
public void setVendors(MuthaGroupVO vo)
{
	LinkedHashMap<Long,String> vendor=new LinkedHashMap<Long,String>();
	try{
		sql="select * from stock_vendor";
		PreparedStatement  ps=con2.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			vendor.put(Long.parseLong(rs.getString("mat_code")),rs.getString("vendor_code"));	
		}
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	vo.setCustomer(vendor);
}
public void setCustomerRecords(MuthaGroupVO vo)
{
	ArrayList<ArrayList<String>> customerRecordes=new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> dispPlanSale=getDispPlanSale();
	try{
		sql="select * from stock_cust";
		PreparedStatement  ps=con2.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			ArrayList<String> customerstock =new ArrayList<String>();
			boolean flag=false; 
			customerstock.add(rs.getString("mat_code"));
			customerstock.add(getNameByCode(rs.getString("cust_code")));
			for(int i=0;i<dispPlanSale.size();i++)
			{
			if(dispPlanSale.get(i).get(0).equals(rs.getString("mat_code"))&&dispPlanSale.get(i).get(1).equals(rs.getString("cust_code")))
			{
			flag=true;
			customerstock.add(dispPlanSale.get(i).get(2));  
			customerstock.add(dispPlanSale.get(i).get(3)); 
			}
			}
			if(!flag)
			{
			customerstock.add("0");  
			customerstock.add("0");
			}
			customerRecordes.add(customerstock);	
		}
		}catch(Exception e)
	{
		e.printStackTrace();
	}
	vo.setCustomerRecords(customerRecordes); 
}
public void setVendorRecords(MuthaGroupVO vo)
{
	ArrayList<ArrayList<String>> vendorRecords=new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> vendorERPStock= getVendorERPStock();
	ArrayList<ArrayList<String>> vendorCastingRecieved= getVendorCastingRecieved();
	ArrayList<ArrayList<String>> odkProductionDataRecords= getODKProductionData();
	try
	{
		sql="select * from stock_vendor";
		PreparedStatement  ps=con2.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			ArrayList<String> vendorstock =new ArrayList<String>();
			boolean flag1=false;
			boolean flag2=false;
			boolean flag3=false;
			vendorstock.add(rs.getString("mat_code"));
			vendorstock.add(rs.getString("vendor_code"));
			vendorstock.add(getNameByCode(rs.getString("vendor_code")));
			for(int i=0;i<vendorERPStock.size();i++)
			{
			if(vendorERPStock.get(i).get(0).equals(getRef_codeByMat_code(rs.getString("mat_code")))&&vendorERPStock.get(i).get(1).equals(rs.getString("vendor_code")))
			{
			flag1=true;
			vendorstock.add(vendorERPStock.get(i).get(2));  
			}
			}
			if(!flag1)
			{
			vendorstock.add("0");  
			}
			for(int i=0;i<vendorCastingRecieved.size();i++)
			{
			if(vendorCastingRecieved.get(i).get(0).equals(getRef_codeByMat_code(rs.getString("mat_code")))&&vendorCastingRecieved.get(i).get(1).equals(rs.getString("vendor_code")))
			{
			flag2=true;
			vendorstock.add(vendorCastingRecieved.get(i).get(2));  
			}
			}
			if(!flag2)
			{
			vendorstock.add("0");  
			}
			for(int i=0;i<odkProductionDataRecords.size();i++)
			{
			if(odkProductionDataRecords.get(i).get(0).equals(getRef_codeByMat_code(rs.getString("mat_code")))&&odkProductionDataRecords.get(i).get(1).equals(rs.getString("vendor_code")))
			{
			flag2=true;
			vendorstock.add(odkProductionDataRecords.get(i).get(2));  
			vendorstock.add(odkProductionDataRecords.get(i).get(3));  
			vendorstock.add(odkProductionDataRecords.get(i).get(4));  
			vendorstock.add(odkProductionDataRecords.get(i).get(5));  
			vendorstock.add(odkProductionDataRecords.get(i).get(6));  
			}
			}
			if(!flag3)
			{
			vendorstock.add("0");  
			vendorstock.add("0");  
			vendorstock.add("0");  
			vendorstock.add("0");  
			vendorstock.add("0");  
			}
			vendorRecords.add(vendorstock);	
		}
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	vo.setVendorRecords(vendorRecords);
}
public void setInternalRecords(MuthaGroupVO vo)
{
	ArrayList<ArrayList<String>> odkInternalRecords=new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> odkInternalData=getODKInternalData();
	ArrayList<ArrayList<String>> odkPouringData=getODKPouringData();
	try{
		sql="select * from stock_mat";
		PreparedStatement  ps=con2.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			ArrayList<String> odkData=new ArrayList<String>();
			odkData.add(rs.getString("mat_code"));
			boolean flag1=false;
			boolean flag2=false;
			for(int i=0;i<odkInternalData.size();i++)
			{
				if(odkInternalData.get(i).get(0).equals(getRef_codeByMat_code(rs.getString("mat_code"))))
				{
					flag1=true;
					odkData.add(odkInternalData.get(i).get(1));
					odkData.add(odkInternalData.get(i).get(2));
					odkData.add(odkInternalData.get(i).get(3));
					odkData.add(odkInternalData.get(i).get(4));		
				}
			}
			if(!flag1)
			{
				odkData.add("0");
				odkData.add("0");
				odkData.add("0");
				odkData.add("0");
			}
			for(int i=0;i<odkPouringData.size();i++)
			{
				if(odkPouringData.get(i).get(0).equals(getRef_codeByMat_code(rs.getString("mat_code"))))
				{
					flag2=true;
					odkData.add(odkPouringData.get(i).get(1));
					odkData.add(odkPouringData.get(i).get(2));
				}
			}
			if(!flag2)
			{
				odkData.add("0");
				odkData.add("0");
			}
			odkInternalRecords.add(odkData);
		}
		}catch(Exception e)
	   {
			e.printStackTrace();
	   }
	vo.setInternalRecords(odkInternalRecords);
}
public String getdistcustomers()
{
	String distcust="";
	try{
		sql="select distinct cust_code from stock_cust";
		PreparedStatement  ps=con2.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			distcust=distcust+rs.getString("cust_code");	
		}
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	return distcust;
}
public String getNameByCode(String code)
{
	String name="";
	try
	{
	sql = "select SUBGL_LONGNAME from MSTACCTGLSUB where SUB_GLACNO="+code;
	PreparedStatement ps = con1.prepareStatement(sql);
	ResultSet rs = ps.executeQuery();
	 while(rs.next())
	{
		 name=rs.getString("SUBGL_LONGNAME");
	}
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	return name;
}
public ArrayList<ArrayList<String>> getDispPlanSale()
{
	ArrayList<ArrayList<String>> dispPlanSale= new ArrayList<ArrayList<String>> ();
	try{
	sql="{call Sel_RptDespatchPlanSale('103','0','"+getdistcustomers()+"','"+date+"')}";
	CallableStatement cs = con1.prepareCall(sql);
	ResultSet rs = cs.executeQuery();
	while(rs.next())
	{
		ArrayList<String> dispPlanSaleRecord= new ArrayList<String>();
		dispPlanSaleRecord.add(rs.getString("mat_code"));
		dispPlanSaleRecord.add(rs.getString("cust_code"));
		dispPlanSaleRecord.add(Integer.toString(rs.getInt("nos_qty")));
		dispPlanSaleRecord.add(Integer.toString(rs.getInt("sale_qty")));
		dispPlanSale.add(dispPlanSaleRecord);
	}
    }catch(Exception e)
	{
	e.printStackTrace();
	}
	return dispPlanSale;
}
public ArrayList<ArrayList<String>> getVendorERPStock()
{
	ArrayList<ArrayList<String>> vendorERPStock= new ArrayList<ArrayList<String>>();
	try{
		sql="{call Sel_DayWiseSubContractStockLedgerFinal('103','0','"+date+"','"+date+"')} ";
		CallableStatement cs = con1.prepareCall(sql);
		ResultSet rs = cs.executeQuery();
		while(rs.next())
		{
			if(rs.getString("TRNTYPE").equals("0"))
		 	{
			ArrayList<String> vendorERPStockRecord= new ArrayList<String>();
			vendorERPStockRecord.add(rs.getString("mat_code"));
			vendorERPStockRecord.add(rs.getString("ac_no"));
			vendorERPStockRecord.add(Integer.toString(rs.getInt("totqt")));
			vendorERPStock.add(vendorERPStockRecord);
		 	}
	    }
	   }catch(Exception e)
	    {
		e.printStackTrace();
		}
	return vendorERPStock;
}
private String getRef_codeByMat_code(String mat_code) {
	String ref_code=null;
	try{
		 sql = "select REF_MATCODE from MSTMATBOM where MAT_CODE='"+mat_code+"'";
		 PreparedStatement ps=con1.prepareStatement(sql);
		 ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
				ref_code=rs.getString("REF_MATCODE");	
		}
		else
		{
				ref_code=mat_code;
		}
		}catch(Exception e){}
	return ref_code;
}
public ArrayList<ArrayList<String>> getVendorCastingRecieved()
{
	ArrayList<ArrayList<String>> vendorCastingRecieved= new ArrayList<ArrayList<String>>();
try{
	//Get Yesterday 
	c.add(Calendar.DATE, -1);
	int  year = c.get(Calendar.YEAR);
	int  month = c.get(Calendar.MONTH)+1;
	int  day=c.get(Calendar.DAY_OF_MONTH);
	String yestdate=String.valueOf(year)+String.format("%02d", month)+String.format("%02d", day);
	
	sql="{call Sel_DayWiseSubContractStockLedgerFinal('103','0','"+yestdate+"','"+yestdate+"')} ";
	CallableStatement cs = con1.prepareCall(sql);
	ResultSet rs = cs.executeQuery();
	while(rs.next())
	{
		if(rs.getString("TRNTYPE").equals("1"))
	 	{
		ArrayList<String> vendorcastingRecievedRecord= new ArrayList<String>();
		vendorcastingRecievedRecord.add(rs.getString("mat_code"));
		vendorcastingRecievedRecord.add(rs.getString("ac_no"));
		vendorcastingRecievedRecord.add(Integer.toString(rs.getInt("totqt")));
		vendorCastingRecieved.add(vendorcastingRecievedRecord);
	 	}
    }
   }catch(Exception e)
    {
	e.printStackTrace();
	}
return vendorCastingRecieved;
	
}
public ArrayList<ArrayList<String>> getODKProductionData()
{
	ArrayList<ArrayList<String>> odkProductionData=new ArrayList<ArrayList<String>>();
	try{
		sql = "select * from odk_stock where DATE='"+date+"'";
		PreparedStatement  ps=con2.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			ArrayList<String> odkProductionDataRecords=new ArrayList<String>();
			odkProductionDataRecords.add(rs.getString("mat_code"));
			odkProductionDataRecords.add(rs.getString("vendor_code"));
			odkProductionDataRecords.add(rs.getString("prod"));
			odkProductionDataRecords.add(rs.getString("pack"));
			odkProductionDataRecords.add(rs.getString("for_packing"));
			odkProductionDataRecords.add(rs.getString("for_insp"));
			String total=""+Integer.parseInt(rs.getString("pack"))+Integer.parseInt(rs.getString("for_packing"))+Integer.parseInt(rs.getString("for_insp"));
			odkProductionDataRecords.add(total);
			odkProductionData.add(odkProductionDataRecords);
		}
		}catch(Exception e)
	   {
			e.printStackTrace();
	   }
	return odkProductionData;
}
public ArrayList<ArrayList<String>> getODKInternalData()
{
	ArrayList<ArrayList<String>> odkInternalData=new ArrayList<ArrayList<String>>();
	try{
		sql = "select * from odk_int_stock where DATE='"+date+"'";
		PreparedStatement  ps=con2.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			ArrayList<String> odkinternalRecords=new ArrayList<String>();
			odkinternalRecords.add(rs.getString("mat_code"));
			odkinternalRecords.add(rs.getString("yest_invoice"));
			odkinternalRecords.add(rs.getString("yest_disp"));
			odkinternalRecords.add(rs.getString("pend_invoice"));
			odkinternalRecords.add(rs.getString("today_plan"));
			odkInternalData.add(odkinternalRecords); 
		}
		}catch(Exception e)
	   {
			e.printStackTrace();
	   }
	return odkInternalData;
	
}
public ArrayList<ArrayList<String>> getODKPouringData()
{
	ArrayList<ArrayList<String>> odkPouringData=new ArrayList<ArrayList<String>>();
	try{
		sql = "select * from odk_pouring_stock where DATE='"+date+"'";
		PreparedStatement  ps=con2.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			ArrayList<String> odkpouringRecords=new ArrayList<String>();
			odkpouringRecords.add(rs.getString("mat_code"));
			odkpouringRecords.add(rs.getString("yest_pouring"));
			odkpouringRecords.add(rs.getString("cumm"));
			odkPouringData.add(odkpouringRecords); 
		}   
	}catch(Exception e)
	   {
			e.printStackTrace();
	   }
	return odkPouringData;
}
}
