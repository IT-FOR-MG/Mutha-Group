package com.muthagroup.vo;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MuthaGroupVO {
	String date=null;
	LinkedHashMap<Long,String> allItem=new LinkedHashMap<Long,String>(); 
	LinkedHashMap<Long,String> allCustomersAndVendors=new LinkedHashMap<Long,String>(); 
	LinkedHashMap<Long,String> item=new LinkedHashMap<Long,String>();
	LinkedHashMap<Long,String> customer=new LinkedHashMap<Long,String>();
	LinkedHashMap<Long,String> vendor=new LinkedHashMap<Long,String>();
	ArrayList<ArrayList<String>> customerRecords=new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> vendorRecords=new ArrayList<ArrayList<String>>(); 
	ArrayList<ArrayList<String>> internalRecords=new ArrayList<ArrayList<String>>(); 
	
	//Get Set Date
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	//Get Set Item,Customer/Vendor Master
	public LinkedHashMap<Long, String> getAllItem() {
		return allItem;
	}
	public void setAllItem(LinkedHashMap<Long, String> allItem) {
		this.allItem = allItem;
	}
	public LinkedHashMap<Long, String> getAllCustomersAndVendors() {
		return allCustomersAndVendors;
	}
	public void setAllCustomersAndVendors(LinkedHashMap<Long, String> allCustomersAndVendors) {
		this.allCustomersAndVendors = allCustomersAndVendors;
	}
	
	//Get Set Configured Items,Customers,Vendors 
	public LinkedHashMap<Long, String> getItem() {
		return item;
	}
	public void setItem(LinkedHashMap<Long, String> item) {
		this.item = item;
	}
	public LinkedHashMap<Long, String> getCustomer() {
		return customer;
	}
	public void setCustomer(LinkedHashMap<Long, String> customer) {
		this.customer = customer;
	}
	public LinkedHashMap<Long, String> getVendor() {
		return vendor;
	}
	public void setVendor(LinkedHashMap<Long, String> vendor) {
		this.vendor = vendor;
	}
	
	//Get Set Records 
	public ArrayList<ArrayList<String>> getCustomerRecords() {
		return customerRecords;
	}
	public void setCustomerRecords(ArrayList<ArrayList<String>> customerRecords) {
		this.customerRecords = customerRecords;
	}
	public ArrayList<ArrayList<String>> getVendorRecords() {
		return vendorRecords;
	}
	public void setVendorRecords(ArrayList<ArrayList<String>> vendorRecords) {
		this.vendorRecords = vendorRecords;
	}
	public ArrayList<ArrayList<String>> getInternalRecords() {
		return internalRecords;
	}
	public void setInternalRecords(ArrayList<ArrayList<String>> internalRecords) {
		this.internalRecords = internalRecords;
	}
}
