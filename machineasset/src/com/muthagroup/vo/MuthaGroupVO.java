package com.muthagroup.vo;

import java.util.ArrayList;

public class MuthaGroupVO
{
String userName,password,date,cmp;
ArrayList<ArrayList<String>> list;
String machineName,machineCategory,machineMake,purchaseDate,purchaseCost,location,condtion;
public String getMachineName() {
	return machineName;
}

public void setMachineName(String machineName) {
	this.machineName = machineName;
}

public String getMachineCategory() {
	return machineCategory;
}

public void setMachineCategory(String machineCategory) {
	this.machineCategory = machineCategory;
}

public String getMachineMake() {
	return machineMake;
}

public void setMachineMake(String machineMake) {
	this.machineMake = machineMake;
}

public String getPurchaseDate() {
	return purchaseDate;
}

public void setPurchaseDate(String purchaseDate) {
	this.purchaseDate = purchaseDate;
}

public String getPurchaseCost() {
	return purchaseCost;
}

public void setPurchaseCost(String purchaseCost) {
	this.purchaseCost = purchaseCost;
}

public String getLocation() {
	return location;
}

public void setLocation(String location) {
	this.location = location;
}

public String getCondtion() {
	return condtion;
}

public void setCondtion(String condtion) {
	this.condtion = condtion;
}

public ArrayList<ArrayList<String>> getList() {
	return list;
}

public void setList(ArrayList<ArrayList<String>> list) {
	this.list = list;
}

public String getCmp() {
	return cmp;
}

public void setCmp(String cmp) {
	this.cmp = cmp;
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
}
