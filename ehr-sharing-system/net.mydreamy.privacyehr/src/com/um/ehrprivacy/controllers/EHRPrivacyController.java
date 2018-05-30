package com.um.ehrprivacy.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.um.ehrprivacy.model.EHRModel;
import com.um.ehrprivacy.model.TrackerLog;
import com.um.ehrprivacy.utils.HandleLog;
import com.um.ehrprivacy.utils.HandlePatientRecordOperation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EHRPrivacyController {
	
	/* Query the records based on the conditions */
	@RequestMapping(value = "queryrecord", method = RequestMethod.GET)
	public String handleQuery( HttpServletRequest request, Model model, HttpSession session){
		
		// 1. Get the query conditions: patient id , date, hospital id
		String patientid = request.getParameter("patientid");
//		String userid = (String) session.getAttribute("userId"); // userid privacy method
//		System.out.println(userid);
		
		String startDate = request.getParameter("querystartdate");
		
		String endDate   = request.getParameter("queryenddate");
		
		String hospitalID = request.getParameter("hospitalid");
		
		System.out.println("patiendid:"+patientid);
		System.out.println("startDate:"+startDate);
		System.out.println("endDate:"+endDate);
		System.out.println("hospitalID:"+hospitalID);
		
		// 2. Search patient node information in the Patient Index collections.
		List<HashMap<String, String>> patientNodeInfos = HandlePatientRecordOperation.getPatientNodeRecordList(patientid);
		
		Map<String, String> infoNodes = HandlePatientRecordOperation.getPatientNodeInfoMap(patientid); // Patient informations node 
		
		// 3. Request the patient records to the nodes got from the Patient Index collections.
//		List<EHRModel> result = HandlePatientRecordOperation.getPatientRecords(patientNodeInfos, infoNodes ,patientid, userid, startDate, endDate, hospitalID);
		List<EHRModel> result = HandlePatientRecordOperation.getPatientRecords(patientNodeInfos, infoNodes ,patientid, "1", startDate, endDate, hospitalID);
		
		// 4. Format the query results and return.
//		model.addAttribute("username", userid);
		model.addAttribute("ehealthrecrods", result);
		return "success";
	}
	
	/**
	 *  Get detail of records.
	 * @param patientid
	 * @param recordid
	 * @param hospitalid
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="detail", method = RequestMethod.GET)
	public String handleDetail(Locale locale, String patientid,String recordid,String hospitalid, Model model, HttpSession session){
		
//		String userid = (String) session.getAttribute("userId");
		// 2. Search patient node information in the Patient Index collections.
		List<HashMap<String, String>> patientNodeInfos = HandlePatientRecordOperation.getPatientNodeRecordList(patientid);
		Map<String, String> infoNodes = HandlePatientRecordOperation.getPatientNodeInfoMap(patientid);
		
		// 3. Request the patient records to the nodes got from the Patient Index collections.
//		List<EHRModel> result = HandlePatientRecordOperation.getPatientRecords(patientNodeInfos, infoNodes ,patientid, userid, "", "", hospitalid);
		List<EHRModel> result = HandlePatientRecordOperation.getPatientRecords(patientNodeInfos, infoNodes ,patientid, "1", "", "", hospitalid);
		
		for(EHRModel p : result){
			if(recordid.trim().equals(p.getPatientRecordID().trim())){
				model.addAttribute("record", p);
			}
		}
//		model.addAttribute("username", userid);
		
		// Log operation
		// 1. Attributions
		String clientip = (String) session.getAttribute("clientip");
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		
		String formattedDate = dateFormat.format(date);
		
		// 2. Record log.
		Map<String, String> logMap = new HashMap<String, String>();
//		logMap.put("UserID", userid);
		logMap.put("UserType", "Doctor");
		logMap.put("IPAddress", clientip);
		logMap.put("Operation", "Read");
		logMap.put("TimeStamp", formattedDate);
		logMap.put("Location", hospitalid);
		logMap.put("EHRID", recordid);
		logMap.put("EHRType", infoNodes.get("collection"));
		
		HandleLog.recordLog(logMap);
		
		
		return "newDetail";
	}
	
	
	
	/* Query the records based on the conditions */
	@RequestMapping(value = "querylog", method = RequestMethod.POST)
	public String handleLogQuery( HttpServletRequest request, Model model, HttpSession session){
		
		// 1. Get the query conditions: patient id , date, hospital id
		String ehrid = request.getParameter("ehrid");
		String userid = (String) session.getAttribute("trackername"); // userid privacy method
		
		String startDate = request.getParameter("querystartdate");
		
		String endDate   = request.getParameter("queryenddate");
		
		List<TrackerLog> list = HandleLog.getTrackerLogList(ehrid, startDate, endDate);
		System.out.println("ehrid" + ehrid);
		
		// 4. Format the query results and return.
		model.addAttribute("trackerid", userid);
		model.addAttribute("trackerlogs", list);
		return "trackerlog";
	}
	
}
