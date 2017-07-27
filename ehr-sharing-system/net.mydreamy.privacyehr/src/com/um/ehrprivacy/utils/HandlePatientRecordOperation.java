package com.um.ehrprivacy.utils;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.um.ehrprivacy.model.BloodPressRecord;
import com.um.ehrprivacy.model.EHRModel;
import com.um.ehrprivacy.model.HBVaccineInjectionRecordSet;
import com.um.ehrprivacy.model.HemodialysisBeforeRecord;
import com.um.ehrprivacy.model.HemodialysisChartRecordSet;
import com.um.ehrprivacy.model.HemodialysisChartRecordSetRecord;
import com.um.ehrprivacy.model.HemodialysisRecordSet;
import com.um.ehrprivacy.model.LaboratoryReportRecordSet;
import com.um.ehrprivacy.model.PatientInfo;
import com.um.ehrprivacy.model.PatientRecord;

/**
 *  Patient records operation.
 *  	
 *  	main process:
 *  		1. Get the node information of patient record from Patient Index Table.
 *  		2. Search records from those nodes.		
 *  
 * @author peterliu
 *
 */
public class HandlePatientRecordOperation {
	
	/**
	 *  Get all node informations of patient from Patient Index Table.
	 *  Each patient may be have many nodes.
	 * @param patientid
	 * @return
	 */
	public static List<HashMap<String, String>> getPatientNodeRecordList(String patientid){
		if(patientid.equals("")){
			return null;
		}
		final List<HashMap<String, String>> nodeList = new ArrayList<HashMap<String,String>>();
		
		// The patient index table node information.
		// 10.119.180.42  27017 EhrPrivacy PatientIndex 
		String patientIndexHost = DaoConfig.patientindexhost;
		int patientIndexPort = Integer.valueOf(DaoConfig.patientindexport);
		String patientIndexDatabase = DaoConfig.patientindexdatabase;
		String patientIndexCollection = DaoConfig.patientindexcollection;
		
		// Get the database connection.
		MongoClient client = new MongoClient(patientIndexHost , patientIndexPort);	
				
		try {
			
			MongoDatabase database = client.getDatabase(patientIndexDatabase);
	        
			MongoCollection<Document> resultCollection = database.getCollection(patientIndexCollection);
				        
			// Query the collection to get doctor information.
			FindIterable<Document> iterable = resultCollection.find(new Document("patient.Patient_ID", patientid));
			
			iterable.forEach(new Block<Document>() {

				@Override
				public void apply(Document document) {
					// TODO Auto-generated method stub
					HashMap<String, String> patientNodeMap = new HashMap<String, String>();
					
					Document doc = (Document) document.get("patient");
					
					patientNodeMap.put("patientid", doc.getString("Patient_ID"));
					patientNodeMap.put("recordid", doc.getString("Record_ID"));
					patientNodeMap.put("host", doc.getString("host"));
					patientNodeMap.put("port", doc.getString("port"));
					patientNodeMap.put("database", doc.getString("database"));
					patientNodeMap.put("collection", doc.getString("collection")); // Patient informations
					nodeList.add(patientNodeMap);
				}
			});
			
		} finally {
			// TODO: handle finally clause
			client.close();
		}
		System.out.println("node list len:" + nodeList.size());
		return nodeList;
	}
	
	/**
	 *  Get the patient information node index information.
	 *  
	 * @param patientid
	 * @return
	 */
	public static Map<String, String> getPatientNodeInfoMap(String patientid){
		if(patientid.equals("")){
			return null;
		}
		
		Map<String, String> infoNodeMap = new HashMap<String, String>();
		// The patient index table node information.
		
		// 10.119.180.42  27017 EhrPrivacy PatientIndex 
		String patientIndexHost = DaoConfig.patientinfoindexhost;
		int patientIndexPort = Integer.valueOf(DaoConfig.patientinfoindexport);
		String patientIndexDatabase = DaoConfig.patientinfoindexdatabase;
		String patientIndexCollection = DaoConfig.patientinfoindexcollection;
				
		// Get the database connection.
		MongoClient client = new MongoClient(patientIndexHost , patientIndexPort);
		try {
			MongoDatabase database = client.getDatabase(patientIndexDatabase);
	        
			MongoCollection<Document> resultCollection = database.getCollection(patientIndexCollection);
				        
			// Query the collection to get doctor information.
			FindIterable<Document> iterable = resultCollection.find(new Document("patient.Patient_ID", patientid));
			
			Document result = iterable.first();
			
			if (result == null) {
				return infoNodeMap;
			}
			
			Document patientInfo = (Document) result.get("patient");
			
			infoNodeMap.put("patientid", patientInfo.getString("Patient_ID"));
			infoNodeMap.put("host", patientInfo.getString("host"));
			infoNodeMap.put("port", patientInfo.getString("port"));
			infoNodeMap.put("database", patientInfo.getString("database"));
			infoNodeMap.put("collection", patientInfo.getString("collection"));
			
		} finally {
			// TODO: handle finally clause
			client.close();
		}
		System.out.println(infoNodeMap.get("host") + "---" + infoNodeMap.get("database") + "----" + infoNodeMap.get("collection"));
		return infoNodeMap;
	}
	
	/**
	 *   Get all patient records 
	 *   
	 * @param patientNodeInfos
	 * @param userid --- privacy ,control which informations can show to doctor.
	 * @return
	 */
	public static List<PatientRecord> getPatientRecords1(List<HashMap<String, String>> patientNodeInfos, Map<String, String> patientInfos, String patientid , String userid, String startDate, String endDate, String hospitalID){
		if(patientNodeInfos == null || patientNodeInfos.size() == 0 || userid.equals("") || patientInfos == null || patientInfos.size() == 0) {
			return null;
		}
		
		final List<PatientRecord> patientRecords = new ArrayList<PatientRecord>();
		
		/*
		 *   Query patient's information
		 */
		
		String infonodeHost = patientInfos.get("host"); // host
		int infonodePort = Integer.valueOf(patientInfos.get("port")); // port
		String infonodeDB = patientInfos.get("database"); // database
		String infonodeCollection = patientInfos.get("collection"); // collection
		
		MongoClient infoclient = new MongoClient(infonodeHost,infonodePort);
		MongoDatabase infodb = infoclient.getDatabase(infonodeDB);
		MongoCollection<Document> infocollection = infodb.getCollection(infonodeCollection);
		
		FindIterable<Document> infoiterable = infocollection.find(new Document("patient.IDCardNO", patientid));
		Document infoDocumet = infoiterable.first();
		
		final PatientInfo patientInfo = new PatientInfo(); // Patient information
		
		Document patientInfoDoc = (Document) infoDocumet.get("patient");
		patientInfo.setIDCardNO(patientInfoDoc.getString("IDCardNO"));
		patientInfo.setHospitalID(patientInfoDoc.getString("HospitalID"));
		patientInfo.setPatientName(patientInfoDoc.getString("PatientName"));
		patientInfo.setHomeAddress(patientInfoDoc.getString("HomeAddress"));
		patientInfo.setSex(patientInfoDoc.getString("Sex"));
		patientInfo.setDateOfBirth(patientInfoDoc.getString("DateOfBirth"));
		patientInfo.setNationality(patientInfoDoc.getString("Nationality"));
		patientInfo.setBloodType(patientInfoDoc.getString("BloodType"));
		patientInfo.setBloodTypeRH(patientInfoDoc.getString("BloodTypeRH"));
		
		infoclient.close();
		
		/*
		 *  Query patient's records
		 */
		// 1. Based on the nodes informations, query all nodes.
		int nodeLength = patientNodeInfos.size();
		for(int i = 0; i < nodeLength; i++){
			
			// Node route information
			String nodeHost = patientNodeInfos.get(i).get("host");
			int nodePort = Integer.valueOf(patientNodeInfos.get(i).get("port"));
			String nodeDB = patientNodeInfos.get(i).get("database");
			String nodeCollection = patientNodeInfos.get(i).get("collection");
			String nodeRecordId = patientNodeInfos.get(i).get("recordid");
			
			MongoClient client = new MongoClient(nodeHost, nodePort);
			
			MongoDatabase db = client.getDatabase(nodeDB);
			
			MongoCollection<Document> collection = db.getCollection(nodeCollection);
			
			// Find document------
			Document conditions = new Document("patientRecord.IDCardNO", patientid).append("patientRecord.PatientRecordID", nodeRecordId); // patient id condition
			// Date condition
			if( !startDate.equals("") && !endDate.equals("")){
				conditions.append("patientRecord.Date", new Document("$gt", startDate).append("$lte", endDate));
			}
			// Hospital id condition
			if(!hospitalID.equals("")){
				conditions.append("patientRecord.HospitalID", hospitalID.trim());
			}
			
			FindIterable<Document> iterable = collection.find(conditions);
			
			iterable.forEach(new Block<Document>() {

				@Override
				public void apply(Document document) {
					// TODO Auto-generated method stub
					// Each document of patient record need to convert to PatientRecord.
					
					// Convert method!!!!-----   Document --> PatientRecord
					PatientRecord patientRecord = convertDocumentToPatientRecord(document);
					patientRecord.setPatientInfo(patientInfo);
					patientRecords.add(patientRecord);
					
				}
			});
			
			client.close();
		}
		
		// Return results
		return patientRecords;
	}
	
	/**
	 *   Get all patient records 
	 *   
	 * @param patientNodeInfos
	 * @param userid --- privacy ,control which informations can show to doctor.
	 * @return
	 */
	public static List<EHRModel> getPatientRecords(List<HashMap<String, String>> patientNodeInfos, Map<String, String> patientInfos, String patientid , String userid, String startDate, String endDate, String hospitalID){
		if(patientNodeInfos == null || patientNodeInfos.size() == 0 || userid.equals("") || patientInfos == null || patientInfos.size() == 0) {
			return null;
		}
		
		final List<EHRModel> patientRecords = new ArrayList<EHRModel>();
		
		/*
		 *   Query patient's information
		 */
		
		String infonodeHost = patientInfos.get("host"); // host
		int infonodePort = Integer.valueOf(patientInfos.get("port")); // port
		String infonodeDB = patientInfos.get("database"); // database
		String infonodeCollection = patientInfos.get("collection"); // collection
		System.out.println("infonodeDB:" + infonodeDB + "---" + infonodeHost + "----" + infonodeCollection);
		MongoClient infoclient = new MongoClient(infonodeHost,infonodePort);
		MongoDatabase infodb = infoclient.getDatabase(infonodeDB);
		MongoCollection<Document> infocollection = infodb.getCollection(infonodeCollection);
		
		FindIterable<Document> infoiterable = infocollection.find(new Document("patient.IDCardNO", patientid));
		Document infoDocumet = infoiterable.first();
		
		final PatientInfo patientInfo = new PatientInfo(); // Patient information
		
		Document patientInfoDoc = (Document) infoDocumet.get("patient");
		patientInfo.setIDCardNO(patientInfoDoc.getString("IDCardNO"));
		patientInfo.setHospitalID(patientInfoDoc.getString("HospitalID"));
		patientInfo.setPatientName(patientInfoDoc.getString("PatientName"));
		patientInfo.setHomeAddress(patientInfoDoc.getString("HomeAddress"));
		patientInfo.setSex(patientInfoDoc.getString("Sex"));
		patientInfo.setDateOfBirth(patientInfoDoc.getString("DateOfBirth"));
		patientInfo.setNationality(patientInfoDoc.getString("Nationality"));
		patientInfo.setBloodType(patientInfoDoc.getString("BloodType"));
		patientInfo.setBloodTypeRH(patientInfoDoc.getString("BloodTypeRH"));
		
		infoclient.close();
		
		System.out.println(patientInfo);
		
		/*
		 *  Query patient's records
		 */
		// 1. Based on the nodes informations, query all nodes.
		int nodeLength = patientNodeInfos.size();
		System.out.println("node len:" + nodeLength);
		for(int i = 0; i < nodeLength; i++){
			
			// Node route information
			String nodeHost = patientNodeInfos.get(i).get("host");
			int nodePort = Integer.valueOf(patientNodeInfos.get(i).get("port"));
			String nodeDB = patientNodeInfos.get(i).get("database");
			String nodeCollection = patientNodeInfos.get(i).get("collection");
			String nodeRecordId = patientNodeInfos.get(i).get("recordid");
			System.out.println(nodeHost + "--- " + nodeDB + "---" + nodeCollection);
			MongoClient client = new MongoClient(nodeHost, nodePort);
			
			MongoDatabase db = client.getDatabase(nodeDB);
			
			MongoCollection<Document> collection = db.getCollection(nodeCollection);
			
			// Find document------
			Document conditions = new Document("patientRecord.IDCardNO", patientid).append("patientRecord.PatientRecordID", nodeRecordId); // patient id condition
			// Date condition
			if( !startDate.equals("") && !endDate.equals("")){
				conditions.append("patientRecord.Date", new Document("$gt", startDate).append("$lte", endDate));
			}
			// Hospital id condition
			if(!hospitalID.equals("")){
				conditions.append("patientRecord.HospitalID", hospitalID.trim());
			}
			
			FindIterable<Document> iterable = collection.find(conditions);
			
			iterable.forEach(new Block<Document>() {

				@Override
				public void apply(Document document) {
					// TODO Auto-generated method stub
					// Each document of patient record need to convert to PatientRecord.
					
					// Convert method!!!!-----   Document --> EHRModel
					EHRModel patientRecord = convertDocumentToEHRModel(document);
					patientRecord.setPatientInfo(patientInfo);
					patientRecords.add(patientRecord);
					
				}
			});
			
			client.close();
		}
		
		// Return results
		return patientRecords;
	}
	
	
	/**
	 *  Convert methods ----:  Document - > PatientRecord
	 * @param document
	 * @return
	 */
	public static EHRModel convertDocumentToEHRModel(Document document){
		if(document == null){
			return null;
		}
		EHRModel ehrModel = new EHRModel();
		
		Document record = (Document) document.get("patientRecord");
		if( record == null ){
			return ehrModel;
		}
		String PatientRecordID = record.getString("PatientRecordID");
		String HospitalID = record.getString("HospitalID");
		String IDCardNO = record.getString("IDCardNO");
		String Date = record.getString("Date");
		ehrModel.setPatientRecordID(PatientRecordID);
		ehrModel.setHospitalID(HospitalID);
		ehrModel.setIDCardNO(IDCardNO);
		ehrModel.setDate(Date);
		
		//HemodialysisChartRecordSetRecord set
		HemodialysisChartRecordSetRecord hemodialysisChartRecordSetRecord = new HemodialysisChartRecordSetRecord();
		Document chartRecordDocument = (Document) record.get("HemodialysisChartRecordSetRecord");
		
		hemodialysisChartRecordSetRecord.setTimes(chartRecordDocument.getString("Times"));
		hemodialysisChartRecordSetRecord.setDialyserBlockage(chartRecordDocument.getString("DialyserBlockage"));
		hemodialysisChartRecordSetRecord.setUNIT(chartRecordDocument.getString("UNIT"));
		hemodialysisChartRecordSetRecord.setDiaysate(chartRecordDocument.getString("Diaysate"));
		hemodialysisChartRecordSetRecord.setDialyser(chartRecordDocument.getString("Dialyser"));
		hemodialysisChartRecordSetRecord.setVascularAccess(chartRecordDocument.getString("VascularAccess"));
		hemodialysisChartRecordSetRecord.setPreWeight(chartRecordDocument.getString("PreWeight"));
		hemodialysisChartRecordSetRecord.setDryWeight(chartRecordDocument.getString("DryWeight"));
		hemodialysisChartRecordSetRecord.setTargetLoss(chartRecordDocument.getString("TargetLoss"));
		hemodialysisChartRecordSetRecord.setPostWeight(chartRecordDocument.getString("PostWeight"));
		hemodialysisChartRecordSetRecord.setDuration(chartRecordDocument.getString("Duration"));;
		hemodialysisChartRecordSetRecord.setProgram(chartRecordDocument.getString("Program"));
		hemodialysisChartRecordSetRecord.setKCI(chartRecordDocument.getString("KCI"));
		hemodialysisChartRecordSetRecord.setNetLoss(chartRecordDocument.getString("NetLoss"));
		
		ehrModel.setHemodialysisChartRecordSetRecord(hemodialysisChartRecordSetRecord);
		
		// HemodialysisBeforeRecord set
		List<HemodialysisBeforeRecord> hemodialysisBeforeRecords = new ArrayList<HemodialysisBeforeRecord>();
		
		ArrayList<Document> beforeRecords = (ArrayList<Document>) record.get("HemodialysisBeforeRecord");
		if(beforeRecords != null && beforeRecords.size() != 0){
			int beforerecordLength = beforeRecords.size();
			for(int i = 0; i < beforerecordLength; i++){
				HemodialysisBeforeRecord hebefore = new HemodialysisBeforeRecord();
				hebefore.setTime(((Document) beforeRecords.get(i)).getString("Time"));
				hebefore.setPumpSpeed(((Document) beforeRecords.get(i)).getString("PumpSpeed"));
				hebefore.setVenPress(((Document) beforeRecords.get(i)).getString("VenPress"));
				hebefore.setTMP(((Document) beforeRecords.get(i)).getString("TMP"));
				hebefore.setTEMP(((Document) beforeRecords.get(i)).getString("TEMP"));;
				hebefore.setCOND(((Document) beforeRecords.get(i)).getString("COND"));
				hebefore.setUF(((Document) beforeRecords.get(i)).getString("UF"));
				
				hemodialysisBeforeRecords.add(hebefore);
			}
		}
		
		ehrModel.setHemodialysisBeforeRecords(hemodialysisBeforeRecords);
		
		// 
		String LMWH = record.getString("LMWH");
		String Erythropoietin = record.getString("Erythropoietin");
		String Zempler = record.getString("Zempler");
		String Venofar = record.getString("Venofar");
		
		ehrModel.setLMWH(LMWH);
		ehrModel.setErythropoietin(Erythropoietin);
		ehrModel.setZempler(Zempler);
		ehrModel.setVenofar(Venofar);
		
		List<BloodPressRecord> bloodPressRecords = new ArrayList<BloodPressRecord>();
		
		ArrayList<Document> bloodpressdoc = (ArrayList<Document>) record.get("BloodPressRecord");
		if(bloodpressdoc != null && bloodpressdoc.size() != 0){
			int bloodlength = bloodpressdoc.size();
			for(int i = 0; i < bloodlength; i++){
				BloodPressRecord blood = new BloodPressRecord();
				
				blood.setHighPress(((Document) bloodpressdoc.get(i)).getString("HighPress"));
				blood.setLowPress(((Document) bloodpressdoc.get(i)).getString("LowPress"));;
				blood.setPulse(((Document) bloodpressdoc.get(i)).getString("Pulse"));
				blood.setTourNurse(((Document) bloodpressdoc.get(i)).getString("TourNurse"));
				
				
				bloodPressRecords.add(blood);
			}
		}
		
		ehrModel.setBloodPressRecords(bloodPressRecords);
		
		
		String ResponseNurse = record.getString("ResponseNurse");
		String PreTem = record.getString("PreTem");
		String Comments = record.getString("Comments");
		String StartNurse = record.getString("StartNurse");
		String CheckNurse = record.getString("CheckNurse");
		String CloseNurse = record.getString("CloseNurse");
		String WithdrawalNurse = record.getString("WithdrawalNurse");
		String Doctor = record.getString("Doctor");
		
		ehrModel.setResponseNurse(ResponseNurse);
		ehrModel.setPreTem(PreTem);
		ehrModel.setComments(Comments);
		ehrModel.setStartNurse(StartNurse);
		ehrModel.setCheckNurse(CheckNurse);
		ehrModel.setCloseNurse(CloseNurse);
		ehrModel.setWithdrawalNurse(WithdrawalNurse);
		ehrModel.setDoctor(Doctor);
		
		return ehrModel;
	}
	
	
	/**
	 *  Convert methods ----:  Document - > PatientRecord
	 * @param document
	 * @return
	 */
	public static PatientRecord convertDocumentToPatientRecord(Document document){
		if(document == null){
			return null;
		}
		PatientRecord patientRecord = new PatientRecord();
		
		//Set attributes
		
		Document record = (Document) document.get("patientRecord");
		if( record == null ){
			return patientRecord;
		}
		String PatientRecordID = record.getString("PatientRecordID");
		String HospitalID = record.getString("HospitalID");
		String IDCardNO = record.getString("IDCardNO");
		String Date = record.getString("Date");
		
		patientRecord.setPatientRecordID(PatientRecordID);
		patientRecord.setHospitalID(HospitalID);
		patientRecord.setIDCardNO(IDCardNO);
		patientRecord.setDate(Date);
		
		HemodialysisChartRecordSet hemodialysisChartRecordSet = new HemodialysisChartRecordSet();
		Document hcrDocument = (Document) record.get("HemodialysisChartRecordSet");
		
		hemodialysisChartRecordSet.setBloodFlow(hcrDocument.getString("BloodFlow"));
		hemodialysisChartRecordSet.setDiastolicPressure(hcrDocument.getString("DiastolicPressure"));
		hemodialysisChartRecordSet.setNa(hcrDocument.getString("Na"));
		hemodialysisChartRecordSet.setPulse(hcrDocument.getString("Pulse"));
		hemodialysisChartRecordSet.setSystolicPressure(hcrDocument.getString("SystolicPressure"));
		hemodialysisChartRecordSet.setTemp(hcrDocument.getString("Temp"));
		hemodialysisChartRecordSet.setTime(hcrDocument.getString("Time"));
		hemodialysisChartRecordSet.setTMP(hcrDocument.getString("TMP"));
		hemodialysisChartRecordSet.setUF(hcrDocument.getString("UF"));
		hemodialysisChartRecordSet.setVen(hcrDocument.getString("Ven"));
		patientRecord.setHemodialysisChartRecordSet(hemodialysisChartRecordSet);
		
		
		HemodialysisRecordSet hRecordSet = new HemodialysisRecordSet();
		Document hrsDocument = (Document) record.get("HemodialysisRecordSet");
		hRecordSet.setAntiCoagulation(hrsDocument.getString("AntiCoagulation"));
		hRecordSet.setAntiCoDosage(hrsDocument.getString("AntiCoDosage"));
		hRecordSet.setArhythmia(hrsDocument.getString("Arhythmia"));
		hRecordSet.setBloodAccess(hrsDocument.getString("BloodAccess"));
		hRecordSet.setCheckNurse(hrsDocument.getString("CheckNurse"));
		hRecordSet.setCloseNurse(hrsDocument.getString("CloseNurse"));
		hRecordSet.setComments(hrsDocument.getString("Comments"));
		hRecordSet.setCramp(hrsDocument.getString("Cramp"));
		hRecordSet.setD3ZemplarDosage(hrsDocument.getString("D3ZemplarDosage"));
		hRecordSet.setDarbepoetinDosage(hrsDocument.getString("DarbepoetinDosage"));
		hRecordSet.setDate(hrsDocument.getString("Date"));
		hRecordSet.setDialysisBlockage(hrsDocument.getString("DialysisBlockage"));
		hRecordSet.setDialyzer(hrsDocument.getString("Dialyzer"));
		hRecordSet.setDoctor(hrsDocument.getString("Doctor"));
		hRecordSet.setDryWeight(hrsDocument.getString("DryWeight"));
		hRecordSet.setDurationH(hrsDocument.getString("DurationH"));
		hRecordSet.setDurationM(hrsDocument.getString("DurationM"));
		hRecordSet.setGlucoseDosage(hrsDocument.getString("GlucoseDosage"));
		hRecordSet.setHypertension(hrsDocument.getString("Hypertension"));
		hRecordSet.setHypotension(hrsDocument.getString("Hypotension"));
		hRecordSet.setKCI(hrsDocument.getString("KCI"));
		hRecordSet.setMachine(hrsDocument.getString("Machine"));
		hRecordSet.setNetLoss(hrsDocument.getString("NetLoss"));
		hRecordSet.setNSDosage(hrsDocument.getString("NSDosage"));
		hRecordSet.setNSFlushVolume(hrsDocument.getString("NSFlushVolume"));
		hRecordSet.setProgramme(hrsDocument.getString("Programme"));
		hRecordSet.setRecommonDosage(hrsDocument.getString("RecommonDosage"));
		hRecordSet.setStartNurse(hrsDocument.getString("StartNurse"));
		hRecordSet.setTargetLoss(hrsDocument.getString("TargetLoss"));
		hRecordSet.setTimes(hrsDocument.getString("Times"));
		hRecordSet.setVenoferDosage(hrsDocument.getString("VenoferDosage"));
		hRecordSet.setVomiting(hrsDocument.getString("Vomiting"));
		hRecordSet.setWeightPostD(hrsDocument.getString("WeightPostD"));
		hRecordSet.setWeightPreD(hrsDocument.getString("WeightPreD"));
		hRecordSet.setWithdrawalNurse(hrsDocument.getString("WithdrawalNurse"));
		patientRecord.setHemodialysisRecordSet(hRecordSet);
		
		HBVaccineInjectionRecordSet hbVaccineInjectionRecordSet = new HBVaccineInjectionRecordSet();
		Document hbvDocument = (Document) record.get("HBVaccineInjectionRecordSet");
		
		hbVaccineInjectionRecordSet.setDateMonth0(hbvDocument.getString("DateMonth0"));
		hbVaccineInjectionRecordSet.setDateMonth1(hbvDocument.getString("DateMonth1"));
		hbVaccineInjectionRecordSet.setDateMonth2(hbvDocument.getString("DateMonth2"));
		hbVaccineInjectionRecordSet.setDateMonth3(hbvDocument.getString("DateMonth3"));
		hbVaccineInjectionRecordSet.setDateMonth4(hbvDocument.getString("DateMonth4"));
		hbVaccineInjectionRecordSet.setDateMonth5(hbvDocument.getString("DateMonth5"));
		hbVaccineInjectionRecordSet.setDateMonth6(hbvDocument.getString("DateMonth6"));
		hbVaccineInjectionRecordSet.setDateMonth7(hbvDocument.getString("DateMonth7"));
		hbVaccineInjectionRecordSet.setDateMonth8(hbvDocument.getString("DateMonth8"));
		hbVaccineInjectionRecordSet.setDateMonth9(hbvDocument.getString("DateMonth9"));
		hbVaccineInjectionRecordSet.setDateMonth10(hbvDocument.getString("DateMonth10"));
		
		patientRecord.setHbVaccineInjectionRecordSet(hbVaccineInjectionRecordSet);
		
		LaboratoryReportRecordSet laboratoryReportRecordSet = new LaboratoryReportRecordSet();
		Document lbrDocument = (Document) record.get("LaboratoryReportRecordSet");
		laboratoryReportRecordSet.setAntiHBe(lbrDocument.getString("AntiHBe"));
		laboratoryReportRecordSet.setAntiHBs(lbrDocument.getString("AntiHBs"));
		laboratoryReportRecordSet.setAntiHCV(lbrDocument.getString("AntiHCV"));
		laboratoryReportRecordSet.setHB(lbrDocument.getString("HB"));
		laboratoryReportRecordSet.setHBeAg(lbrDocument.getString("HBeAg"));
		laboratoryReportRecordSet.setVDRL(lbrDocument.getString("VDRL"));
		
		
		patientRecord.setLaboratoryReportRecordSet(laboratoryReportRecordSet);
		
		
		return patientRecord;
	}
	
}
