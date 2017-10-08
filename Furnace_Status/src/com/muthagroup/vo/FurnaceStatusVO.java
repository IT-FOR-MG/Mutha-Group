package com.muthagroup.vo;

public class FurnaceStatusVO {
String userID,user,furnaceId,shift,date,time,imageId,StringImage;
byte[] byteImage;
public String getStringImage() {
	return StringImage;
}
public void setStringImage(String stringImage) {
	StringImage = stringImage;
}
public String getImageId() {
	return imageId;
}
public void setImageId(String imageId) {
	this.imageId = imageId;
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
public byte[] getByteImage() {
	return byteImage;
}
public void setByteImage(byte[] byteImage) {
	this.byteImage = byteImage;
}
}
