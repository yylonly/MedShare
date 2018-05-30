package com.um.ehrprivacy.model;

import com.google.gson.Gson;

/**
 *  This is the structure of the public patient records.
 *  	
 * @author peterliu
 *
 */
public class PatientRecord {
	
	private PatientInfo patientInfo;
	private String PatientRecordID;
	private String HospitalID;
	private String IDCardNO;
	private String Date;
	private HemodialysisChartRecordSet hemodialysisChartRecordSet;
	private HemodialysisRecordSet hemodialysisRecordSet;
	private HBVaccineInjectionRecordSet hbVaccineInjectionRecordSet;
	private LaboratoryReportRecordSet laboratoryReportRecordSet;
	
	
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
	public HemodialysisChartRecordSet getHemodialysisChartRecordSet() {
		return hemodialysisChartRecordSet;
	}
	public void setHemodialysisChartRecordSet(HemodialysisChartRecordSet hemodialysisChartRecordSet) {
		this.hemodialysisChartRecordSet = hemodialysisChartRecordSet;
	}
	public HemodialysisRecordSet getHemodialysisRecordSet() {
		return hemodialysisRecordSet;
	}
	public void setHemodialysisRecordSet(HemodialysisRecordSet hemodialysisRecordSet) {
		this.hemodialysisRecordSet = hemodialysisRecordSet;
	}
	public HBVaccineInjectionRecordSet getHbVaccineInjectionRecordSet() {
		return hbVaccineInjectionRecordSet;
	}
	public void setHbVaccineInjectionRecordSet(HBVaccineInjectionRecordSet hbVaccineInjectionRecordSet) {
		this.hbVaccineInjectionRecordSet = hbVaccineInjectionRecordSet;
	}
	public LaboratoryReportRecordSet getLaboratoryReportRecordSet() {
		return laboratoryReportRecordSet;
	}
	public void setLaboratoryReportRecordSet(LaboratoryReportRecordSet laboratoryReportRecordSet) {
		this.laboratoryReportRecordSet = laboratoryReportRecordSet;
	}
	public PatientInfo getPatientInfo() {
		return patientInfo;
	}
	public void setPatientInfo(PatientInfo patientInfo) {
		this.patientInfo = patientInfo;
	}
	public String getPatientRecordID() {
		return PatientRecordID;
	}
	public void setPatientRecordID(String patientRecordID) {
		PatientRecordID = patientRecordID;
	}
	
	
	/**
	 *  object to json string
	 */
	public String toString(){
		
		// 1.Init json 
		Gson gson = new Gson();
		
		String json = gson.toJson(this);
		System.out.println(json);
		return json;
	}
	
	public PatientRecord toObject(String json){
		return null;
	}
	
}
