package com.um.ehrprivacy.model;

public class TrackerLog {
	
	private String 	UserID;           
	private String 	UserType;             
	private String IPAddress;              
	private String Operation;              
	private String TimeStamp;              
	private String 	Location;             
	private String 	EHRID;          
	private String 	EHRType;
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getUserType() {
		return UserType;
	}
	public void setUserType(String userType) {
		UserType = userType;
	}
	public String getIPAddress() {
		return IPAddress;
	}
	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
	public String getOperation() {
		return Operation;
	}
	public void setOperation(String operation) {
		Operation = operation;
	}
	public String getTimeStamp() {
		return TimeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getEHRID() {
		return EHRID;
	}
	public void setEHRID(String eHRID) {
		EHRID = eHRID;
	}
	public String getEHRType() {
		return EHRType;
	}
	public void setEHRType(String eHRType) {
		EHRType = eHRType;
	}            
	
}
