package com.um.ehrprivacy.model;

public class HemodialysisBeforeRecord {
	
	private String Time;
	private String PumpSpeed;
	private String VenPress;
	private String TMP;
	private String TEMP;
	private String COND;
	private String UF;
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public String getPumpSpeed() {
		return PumpSpeed;
	}
	public void setPumpSpeed(String pumpSpeed) {
		PumpSpeed = pumpSpeed;
	}
	public String getVenPress() {
		return VenPress;
	}
	public void setVenPress(String venPress) {
		VenPress = venPress;
	}
	public String getTMP() {
		return TMP;
	}
	public void setTMP(String tMP) {
		TMP = tMP;
	}
	public String getTEMP() {
		return TEMP;
	}
	public void setTEMP(String tEMP) {
		TEMP = tEMP;
	}
	public String getCOND() {
		return COND;
	}
	public void setCOND(String cOND) {
		COND = cOND;
	}
	public String getUF() {
		return UF;
	}
	public void setUF(String uF) {
		UF = uF;
	}
	
}
