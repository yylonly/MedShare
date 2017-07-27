package com.um.ehrprivacy.model;

/**
 *  Patient information model.
 *  
 * @author peterliu
 *
 */
public class PatientInfo {
	
	
	private String IDCardNO;
	private String HospitalID;
	private String PatientName;
	private String HomeAddress;
	private String Sex;
	private String DateOfBirth;
	private String Nationality;
	private String BloodType;
	private String BloodTypeRH;
	
	public String getIDCardNO() {
		return IDCardNO;
	}
	public void setIDCardNO(String iDCardNO) {
		IDCardNO = iDCardNO;
	}
	public String getHospitalID() {
		return HospitalID;
	}
	public void setHospitalID(String hospitalID) {
		HospitalID = hospitalID;
	}
	public String getPatientName() {
		return PatientName;
	}
	public void setPatientName(String patientName) {
		PatientName = patientName;
	}
	public String getHomeAddress() {
		return HomeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		HomeAddress = homeAddress;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	public String getNationality() {
		return Nationality;
	}
	public void setNationality(String nationality) {
		Nationality = nationality;
	}
	public String getBloodType() {
		return BloodType;
	}
	public void setBloodType(String bloodType) {
		BloodType = bloodType;
	}
	public String getBloodTypeRH() {
		return BloodTypeRH;
	}
	public void setBloodTypeRH(String bloodTypeRH) {
		BloodTypeRH = bloodTypeRH;
	}
	
}
