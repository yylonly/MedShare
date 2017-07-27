var mongo = require('mongoskin');
var localdb = mongo.db("mongodb://10.119.180.43:27017/EhrPrivacy", {native_parser:true});
var centerdb = mongo.db("mongodb://10.119.180.42:27017/EhrPrivacy", {native_parser:true});

console.log("prepare finished");

var patientid = {};
var recordid = {};

//check that records in center or not
var checkandinsert = function(pid, rid) {
    
             var query =  {
                 'patient.Patient_ID' : pid,
                 'patient.Record_ID' : rid
             };
          
             centerdb.collection('PatientIndex').find(query).toArray(function(err, result) { 
             
             // console.log("sync result" + JSON.stringify(result));
              
                 if (result.length != 0)
                 {
                    console.log("center exist index " + rid + ", next one");
                    
                 }  
                 else
                 {
                    //insert it, if it not exist
                    console.log("index " + recordid + " not exist in cetner");
                    var ehr = {
                         "patient" : {
                             "Patient_ID" : pid,
                             "Record_ID" : rid,
                             "Hospital_ID" : "kw", "host" : "10.119.180.43", "port" : "27017", 
                             "database" : "EhrPrivacy", "collection" : "PatientRecords" 
                         }
                    }
                    
                    centerdb.collection('PatientIndex').insert(ehr);
                    console.log("new index updated");
                 }

       });
};

//find all records in kw hospital
localdb.collection('PatientRecords').find({}, {'patientRecord.IDCardNO': 1, 'patientRecord.PatientRecordID' : 1, _id:0}).toArray(function(err, result) {
    
        
      if (result.length != 0)
      {
          for (var i = 0; i < result.length; i++)
          {
             console.log("i is: " + i);         
             console.log(result[i].patientRecord);
              
             patientid = result[i].patientRecord.IDCardNO;
             recordid = result[i].patientRecord.PatientRecordID;
              
             console.log("testing patient id: " + patientid);
             console.log("testing record id: " +recordid);
              
             checkandinsert(patientid, recordid);
              
             console.log("\n\n");
          }
                 
      }
      else
      {
          console.log("result empty");
      }
    
});
