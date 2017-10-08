package com.muthagroup.vo;

import java.util.ArrayList;

public class MuthaGroupVO {
	String userName,password,date;
	ArrayList<ArrayList<String>> FunaceRecords = new ArrayList<ArrayList<String>>();
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public ArrayList<ArrayList<String>> getFunaceRecords() {
		return FunaceRecords;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setFunaceRecords(ArrayList<ArrayList<String>> funaceRecords) {
		FunaceRecords = funaceRecords;
	} 
}
