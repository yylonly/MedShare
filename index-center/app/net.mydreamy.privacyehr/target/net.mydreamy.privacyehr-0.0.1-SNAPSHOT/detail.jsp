<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link href="css/bootstrap.css" rel="stylesheet" />
	<link rel="stylesheet" href="css/bootstrap-table.css">
	<link href="css/query.css" rel="stylesheet" />
	<script type="text/javascript" src = "js/jquery-1.11.3.min.js" ></script>

</head>
<body>
	<div class="container">
		<!-- Doctor Informations -->
		<div class="doctorinfo">
			<h2>welcome, ${username }</h2>
			 <a href="logout">Logout</a>  
		</div>
		<div class="back">
			<a href="javascript:history.back()">Back</a>
			<br>
			<br>
		</div>
		<!-- Detail Table -->
		<%-- ${record } --%>
		<table class="table table-bordered">
			<tr class="info">
				<th>RECORDID</th>
				<td>${record.getPatientRecordID() }</td>
			</tr>
			<tr>
				<th>DATE/TIME</th>
				<td>${record.getDate() }</td>
			</tr>
			<tr class="info">
				<th>HOSPITAL</th>
				<td>${record.getHospitalID() }</td>
			</tr>
		</table>
		<br>
		<br>
		<table class="table table-bordered">
			<tr class="info">
				<th>PATIENT NAME</th>
				<td>${record.getPatientInfo().getPatientName() }</td>
			</tr>
			<tr>
				<th>ADDRESS</th>
				<td>${record.getPatientInfo().getHomeAddress() }</td>
			</tr>
			<tr class="info">
				<th>SEX</th>
				<td>${record.getPatientInfo().getSex() }</td>
			</tr>
			<tr>
				<th>DoB</th>
				<td>${record.getPatientInfo().getDateOfBirth() }</td>
			</tr>
			<tr class="info">
				<th>BLOODTYPE</th>
				<td>${record.getPatientInfo().getBloodType() }</td>
			</tr>
			<tr >
				<th>BLOODTYPERH</th>
				<td>${record.getPatientInfo().getBloodTypeRH() }</td>
			</tr>
		</table>
		<br>
		<br>
		<table class="table table-bordered">
			<!-- HemodialysisRecordSet -->
			<tr class="success">
				<th rowspan="10">HemodialysisRecordSet</th>
				<th>Date</th>
				<th>Times</th>
				<th>Machine</th>
				<th>Dialyzer</th>
				<th>BloodAccess</th>
				<th>WeightPreD</th>
				<th>DryWeight</th>
			</tr>
			
			<tr>
				<td>${record.getHemodialysisRecordSet().getDate() }</td>
				<td>${record.getHemodialysisRecordSet().getTimes() }</td>
				<td>${record.getHemodialysisRecordSet().getMachine() }</td>
				<td>${record.getHemodialysisRecordSet().getDialyzer() }</td>
				<td>${record.getHemodialysisRecordSet().getBloodAccess() }</td>
				<td>${record.getHemodialysisRecordSet().getWeightPreD() }</td>
				<td>${record.getHemodialysisRecordSet().getDryWeight() }</td>
			</tr>
			
			<tr class="success">
				<th>DurationM</th>
				<th>Programme</th>
				<th>KCI</th>
				<th>NetLoss</th>
				<th>DialysisBlockage</th>
				<th>AntiCoagulation</th>
				<th>AntiCoDosage</th>
			</tr>
			
			<tr>
				<td>${record.getHemodialysisRecordSet().getDurationM() }</td>
				<td>${record.getHemodialysisRecordSet().getProgramme() }</td>
				<td>${record.getHemodialysisRecordSet().getKCI() }</td>
				<td>${record.getHemodialysisRecordSet().getNetLoss() }</td>
				<td>${record.getHemodialysisRecordSet().getDialysisBlockage() }</td>
				<td>${record.getHemodialysisRecordSet().getAntiCoagulation() }</td>
				<td>${record.getHemodialysisRecordSet().getAntiCoDosage() }</td>
			</tr>
			
			<tr class="success">
				<th>DurationH</th>
				<th>Vomiting</th>
				<th>Cramp</th>
				<th>Comments</th>
				<th>NSFlushVolume</th>
				<th>RecommonDosage</th>
				<th>VenoferDosage</th>
			</tr>
			
			<tr>
				<td>${record.getHemodialysisRecordSet().getDurationH() }</td>
				<td>${record.getHemodialysisRecordSet().getVomiting() }</td>
				<td>${record.getHemodialysisRecordSet().getCramp() }</td>
				<td>${record.getHemodialysisRecordSet().getComments() }</td>
				<td>${record.getHemodialysisRecordSet().getNSFlushVolume() }</td>
				<td>${record.getHemodialysisRecordSet().getRecommonDosage() }</td>
				<td>${record.getHemodialysisRecordSet().getVenoferDosage() }</td>
			</tr>
			
			<tr class="success">
				<th>DarbepoetinDosage</th>
				<th>D3ZemplarDosage</th>
				<th>GlucoseDosage</th>
				<th>NSDosage</th>
				<th>Hypotension</th>
				<th>Hypertension</th>
				<th>Arhythmia</th>
				
				
			</tr>
			
			<tr>
				<td>${record.getHemodialysisRecordSet().getDarbepoetinDosage() }</td>
				<td>${record.getHemodialysisRecordSet().getD3ZemplarDosage() }</td>
				<td>${record.getHemodialysisRecordSet().getGlucoseDosage() }</td>
				<td>${record.getHemodialysisRecordSet().getNSDosage() }</td>
				<td>${record.getHemodialysisRecordSet().getHypotension() }</td>
				<td>${record.getHemodialysisRecordSet().getHypotension() }</td>
				<td>${record.getHemodialysisRecordSet().getArhythmia() }</td>
			</tr>
			
			<tr class="success">
				<th>TargetLoss</th>
				<th>WeightPostD</th>
				<th>StartNurse</th>
				<th>CheckNurse</th>
				<th>CloseNurse</th>
				<th>WithdrawalNurse</th>
				<th>Doctor</th>
			</tr>
			
			<tr>
				<td>${record.getHemodialysisRecordSet().getTargetLoss() }</td>
				<td>${record.getHemodialysisRecordSet().getWeightPostD() }</td>
				<td>${record.getHemodialysisRecordSet().getStartNurse() }</td>
				<td>${record.getHemodialysisRecordSet().getCheckNurse() }</td>
				<td>${record.getHemodialysisRecordSet().getCloseNurse() }</td>
				<td>${record.getHemodialysisRecordSet().getWithdrawalNurse() }</td>
				<td>${record.getHemodialysisRecordSet().getDoctor() }</td>
			</tr>
			
			
			<!-- HemodialysisChartRecordSet -->
			<tr class="info">
				<th rowspan="4">HemodialysisChartRecordSet</th>
				<th>Time</th>
				<th>SystolicPressure</th>
				<th>DiastolicPressure</th>
				<th>Pulse</th>
				<th>UF</th>
				<th>Na</th>
				<th>Temp</th>
			</tr>
			
			<tr >
				<td>${record.getHemodialysisChartRecordSet().getTime() }</td>
				<td>${record.getHemodialysisChartRecordSet().getSystolicPressure() }</td>
				<td>${record.getHemodialysisChartRecordSet().getDiastolicPressure() }</td>
				<td>${record.getHemodialysisChartRecordSet().getPulse() }</td>
				<td>${record.getHemodialysisChartRecordSet().getUF() }</td>
				<td>${record.getHemodialysisChartRecordSet().getNa() }</td>
				<td>${record.getHemodialysisChartRecordSet().getTemp() }</td>
			</tr>
			
			<tr class="info">
				<th>TMP</th>
				<th>Ven</th>
				<th>BloodFlow</th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
			
			<tr >
				<td>${record.getHemodialysisChartRecordSet().getTMP() }</td>
				<td>${record.getHemodialysisChartRecordSet().getVen() }</td>
				<td>${record.getHemodialysisChartRecordSet().getBloodFlow() }</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			
			
			<!-- HBVaccineInjectionRecordSet -->
			<tr class="success">
				<th rowspan="4">HBVaccineInjectionRecordSet</th>
				<th>DateMonth0</th>
				<th>DateMonth1</th>
				<th>DateMonth2</th>
				<th>DateMonth3</th>
				<th>DateMonth4</th>
				<th>DateMonth5</th>
				<th>DateMonth6</th>
			</tr>
			
			<tr>
				<td>${record.getHbVaccineInjectionRecordSet().getDateMonth0() }</td>
				<td>${record.getHbVaccineInjectionRecordSet().getDateMonth1() }</td>
				<td>${record.getHbVaccineInjectionRecordSet().getDateMonth2() }</td>
				<td>${record.getHbVaccineInjectionRecordSet().getDateMonth3() }</td>
				<td>${record.getHbVaccineInjectionRecordSet().getDateMonth4() }</td>
				<td>${record.getHbVaccineInjectionRecordSet().getDateMonth5() }</td>
				<td>${record.getHbVaccineInjectionRecordSet().getDateMonth6() }</td>
			</tr>
			<tr class="success">
				<th>DateMonth7</th>
				<th>DateMonth8</th>
				<th>DateMonth9</th>
				<th>DateMonth10</th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
			
			<tr>
				<td>${record.getHbVaccineInjectionRecordSet().getDateMonth7() }</td>
				<td>${record.getHbVaccineInjectionRecordSet().getDateMonth8() }</td>
				<td>${record.getHbVaccineInjectionRecordSet().getDateMonth9() }</td>
				<td>${record.getHbVaccineInjectionRecordSet().getDateMonth10() }</td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			
			
			<!-- LaboratoryReportRecordSet -->
			<tr class="info">
				<th rowspan="2">LaboratoryReportRecordSet</th>
				<th>HB</th>
				<th>HBsAg</th>
				<th>AntiHBs</th>
				<th>HBeAg</th>
				<th>AntiHBe</th>
				<th>AntiHCV</th>
				<th>VDRL</th>
			</tr>
			
			<tr>
				<td>${record.getLaboratoryReportRecordSet().getHB() }</td>
				<td>${record.getLaboratoryReportRecordSet().getHBsAg() }</td>
				<td>${record.getLaboratoryReportRecordSet().getAntiHBs() }</td>
				<td>${record.getLaboratoryReportRecordSet().getHBeAg() }</td>
				<td>${record.getLaboratoryReportRecordSet().getAntiHBe() }</td>
				<td>${record.getLaboratoryReportRecordSet().getAntiHCV() }</td>
				<td>${record.getLaboratoryReportRecordSet().getVDRL() }</td>
			</tr>
		</table>
		
	</div>
</body>
</html>