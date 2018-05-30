package com.um.ehrprivacy.model;

import java.util.List;

public class EHRModel {
	
	private String PatientRecordID;
	private String HospitalID;
	
	private PatientInfo patientInfo;

	private String IDCardNO;

	private String Date;
	
	private HemodialysisChartRecordSetRecord hemodialysisChartRecordSetRecord;
	
	private List<HemodialysisBeforeRecord> hemodialysisBeforeRecords;
	
	private String LMWH;
	private String Erythropoietin;
	private String Zempler;
	private String Venofar;
	
	private List<BloodPressRecord> bloodPressRecords;
	
	private String ResponseNurse;
	private String PreTem;
	private String Comments;
	private String StartNurse;
	private String CheckNurse;
	private String CloseNurse;
	private String WithdrawalNurse;
	private String Doctor;
	
	
	public String getPatientRecordID() {
		return PatientRecordID;
	}
	public void setPatientRecordID(String patientRecordID) {
		PatientRecordID = patientRecordID;
	}
	public String getHospitalID() {
		return HospitalID;
	}
	public void setHospitalID(String hospitalID) {
		HospitalID = hospitalID;
	}
	public String getIDCardNO() {
		return IDCardNO;
	}
	public void setIDCardNO(String iDCardNO) {
		IDCardNO = iDCardNO;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public HemodialysisChartRecordSetRecord getHemodialysisChartRecordSetRecord() {
		return hemodialysisChartRecordSetRecord;
	}
	public void setHemodialysisChartRecordSetRecord(HemodialysisChartRecordSetRecord hemodialysisChartRecordSetRecord) {
		this.hemodialysisChartRecordSetRecord = hemodialysisChartRecordSetRecord;
	}
	public List<HemodialysisBeforeRecord> getHemodialysisBeforeRecords() {
		return hemodialysisBeforeRecords;
	}
	public void setHemodialysisBeforeRecords(List<HemodialysisBeforeRecord> hemodialysisBeforeRecords) {
		this.hemodialysisBeforeRecords = hemodialysisBeforeRecords;
	}
	public String getLMWH() {
		return LMWH;
	}
	public void setLMWH(String lMWH) {
		LMWH = lMWH;
	}
	public String getErythropoietin() {
		return Erythropoietin;
	}
	public void setErythropoietin(String erythropoietin) {
		Erythropoietin = erythropoietin;
	}
	public String getZempler() {
		return Zempler;
	}
	public void setZempler(String zempler) {
		Zempler = zempler;
	}
	public String getVenofar() {
		return Venofar;
	}
	public void setVenofar(String venofar) {
		Venofar = venofar;
	}
	public List<BloodPressRecord> getBloodPressRecords() {
		return bloodPressRecords;
	}
	public void setBloodPressRecords(List<BloodPressRecord> bloodPressRecords) {
		this.bloodPressRecords = bloodPressRecords;
	}
	public String getResponseNurse() {
		return ResponseNurse;
	}
	public void setResponseNurse(String responseNurse) {
		ResponseNurse = responseNurse;
	}
	public String getPreTem() {
		return PreTem;
	}
	public void setPreTem(String preTem) {
		PreTem = preTem;
	}
	public String getComments() {
		return Comments;
	}
	public void setComments(String comments) {
		Comments = comments;
	}
	public String getStartNurse() {
		return StartNurse;
	}
	public void setStartNurse(String startNurse) {
		StartNurse = startNurse;
	}
	public String getCheckNurse() {
		return CheckNurse;
	}
	public void setCheckNurse(String checkNurse) {
		CheckNurse = checkNurse;
	}
	public String getCloseNurse() {
		return CloseNurse;
	}
	public void setCloseNurse(String closeNurse) {
		CloseNurse = closeNurse;
	}
	public String getWithdrawalNurse() {
		return WithdrawalNurse;
	}
	public void setWithdrawalNurse(String withdrawalNurse) {
		WithdrawalNurse = withdrawalNurse;
	}
	public String getDoctor() {
		return Doctor;
	}
	public void setDoctor(String doctor) {
		Doctor = doctor;
	}
	public PatientInfo getPatientInfo() {
		return patientInfo;
	}
	public void setPatientInfo(PatientInfo patientInfo) {
		this.patientInfo = patientInfo;
	}
	
	

	
	
}
