package com.um.ehrprivacy.utils;

import com.um.ehrprivacy.dao.ConnectionDB;

/**
 *  Handle doctor or patient login verify operation.
 *  
 * @author peterliu
 *
 */
public class HandleLoginOperation {
	
	
	/**
	 *  
	 * @param doctorid
	 * @param password
	 * @return
	 */
	public static boolean verifyLegitimacyOfTracker(String trackerid, String password){
		if(trackerid.equals("") || password.equals("")){
			return false;
		}
		
		// "10.119.180.42"   27017   Log Trackers
		String trackerNodeHost = DaoConfig.trackernodehost;
		int trackerNodePort = Integer.valueOf(DaoConfig.trackernodeport);
		
		String trackerNodeDatabase = DaoConfig.trackernodedatabase;
		String trackerNodeCollection = DaoConfig.trackernodecollection;
		
		// 3. Return verify result.
		return ConnectionDB.verifyDoctorLoginInfo(trackerNodeHost, trackerNodePort, trackerNodeDatabase, trackerNodeCollection, trackerid, password);
	}
}
