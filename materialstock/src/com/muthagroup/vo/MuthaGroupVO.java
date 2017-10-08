package com.muthagroup.vo;

import java.util.ArrayList;

public class MuthaGroupVO
{
String userName,password,date,cmp;
String[] records;
ArrayList<ArrayList<String>> list1,list2;
ArrayList<String> MATCODE;
ArrayList<String> MATNAME;
ArrayList<String> MATTYPE;
ArrayList<String> MATGROUPCODE;
ArrayList<String> MATGROUPNAME;

public ArrayList<String> getMATTYPE() {
	return MATTYPE;
}

public void setMATTYPE(ArrayList<String> mATTYPE) {
	MATTYPE = mATTYPE;
}

public ArrayList<String> getMATGROUPCODE() {
	return MATGROUPCODE;
}

public void setMATGROUPCODE(ArrayList<String> mATGROUPCODE) {
	MATGROUPCODE = mATGROUPCODE;
}

public ArrayList<String> getMATGROUPNAME() {
	return MATGROUPNAME;
}

public void setMATGROUPNAME(ArrayList<String> mATGROUPNAME) {
	MATGROUPNAME = mATGROUPNAME;
}




public ArrayList<String> getMATCODE() {
	return MATCODE;
}

public void setMATCODE(ArrayList<String> mATCODE) {
	MATCODE = mATCODE;
}

public ArrayList<String> getMATNAME() {
	return MATNAME;
}

public void setMATNAME(ArrayList<String> mATNAME) {
	MATNAME = mATNAME;
}




public String getCmp() {
	return cmp;
}

public void setCmp(String cmp) {
	this.cmp = cmp;
}

public String[] getRecords() {
	return records;
}

public void setRecords(String[] records) {
	this.records = records;
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

public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}

public ArrayList<ArrayList<String>> getList1() {
	return list1;
}

public void setList1(ArrayList<ArrayList<String>> list1) {
	this.list1 = list1;
}
public ArrayList<ArrayList<String>> getList2() {
	return list2;
}

public void setList2(ArrayList<ArrayList<String>> list2) {
	this.list2 = list2;
}

}
