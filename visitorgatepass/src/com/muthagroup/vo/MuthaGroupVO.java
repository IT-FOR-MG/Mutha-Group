package com.muthagroup.vo;

import java.util.ArrayList;

public class MuthaGroupVO {
	String userName,password,firstName,middleName,lastName,Address,mobileNo,empToMeet,purpose,inTime,outTime,date;
	ArrayList<ArrayList<String>> visitorRecords = new ArrayList<ArrayList<String>>(); 
	ArrayList<String> empUser=new ArrayList<String>();
	ArrayList<String> visitorMob=new ArrayList<String>();
	public ArrayList<String> getVisitorMob() {
		return visitorMob;
	}

	public void setVisitorMob(ArrayList<String> visitorMob) {
		this.visitorMob = visitorMob;
	}

	public ArrayList<String> getEmpUser() {
		return empUser;
	}

	public void setEmpUser(ArrayList<String> empUser) {
		this.empUser = empUser;
	}

	public ArrayList<ArrayList<String>> getVisitorRecords() {
		return visitorRecords;
	}

	public void setVisitorRecords(ArrayList<ArrayList<String>> visitorRecords) {
		this.visitorRecords = visitorRecords;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmpToMeet() {
		return empToMeet;
	}

	public void setEmpToMeet(String empToMeet) {
		this.empToMeet = empToMeet;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
