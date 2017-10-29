package com.muthagroup.vo;

public class FurnaceStatusVO {
String userID,user,furnaceId,shift,date,time,imageId1,imageId2,StringImage1,StringImage2;
byte[] byteImage1,byteImage2;
public String getStringImage1() {
	return StringImage1;
}
public void setStringImage1(String stringImage1) {
	StringImage1 = stringImage1;
}
public String getStringImage2() {
	return StringImage2;
}
public void setStringImage2(String stringImage2) {
	StringImage2 = stringImage2;
}
public String getImageId1() {
	return imageId1;
}
public void setImageId1(String imageId1) {
	this.imageId1 = imageId1;
}
public String getImageId2() {
	return imageId2;
}
public void setImageId2(String imageId2) {
	this.imageId2 = imageId2;
}
public String getUserID() {
	return userID;
}
public void setUserID(String userID) {
	this.userID = userID;
}
public String getUser() {
	return user;
}
public void setUser(String user) {
	this.user = user;
}
public String getFurnaceId() {
	return furnaceId;
}
public void setFurnaceId(String furnaceId) {
	this.furnaceId = furnaceId;
}
public String getShift() {
	return shift;
}
public void setShift(String shift) {
	this.shift = shift;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public byte[] getByteImage1() {
	return byteImage1;
}
public void setByteImage1(byte[] byteImage1) {
	this.byteImage1 = byteImage1;
}
public byte[] getByteImage2() {
	return byteImage2;
}
public void setByteImage2(byte[] byteImage2) {
	this.byteImage2 = byteImage2;
}
}
