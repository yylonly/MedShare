<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.css" rel="stylesheet" />
	<link rel="stylesheet" href="css/bootstrap-table.css">
	<link href="css/query.css" rel="stylesheet" />
	<script type="text/javascript" src = "js/jquery-1.11.3.min.js" ></script>
	<style>
		hr {
		 -moz-border-bottom-colors: none;
		  -moz-border-image: none;
		  -moz-border-left-colors: none;
		  -moz-border-right-colors: none;
		  -moz-border-top-colors: none;
		  border-color: #EEEEEE -moz-use-text-color #FFFFFF;
		  border-style: solid none;
		  border-width: 1px 0;
		  margin: 18px 0;
		}
	</style>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<!-- Doctor Informations -->
		<div class="doctorinfo">
			<%-- <h2>welcome, ${username }</h2> --%>
			 <a href="logout">Logout</a>  
		</div>
		<div class="back">
			<a href="javascript:history.back()">Back</a>
			<br>
			<br>
		</div>
		<hr/>
		<div style="border: 1px solid black;width:250px;text-align:center">
			<h3>透析号：${record.getPatientRecordID() }</h3>
		</div>
		<div style="text-align:center"><h2>HEMODIALYSIS CHART</h2></div>
		<hr/>
		<div>
			<table  class="table table-bordered">
				<tr>
					<td>
						<p>患者姓名:</p>
						<p>NAME: ${record.getPatientInfo().getPatientName() }</p>
					</td>
					<td>
						<p>日期:</p>
						<p>DATE: ${record.getDate() }</p>
					</td>
					<td>
						<p>血透机型:</p>
						<p>UNIT: ${record.getHemodialysisChartRecordSetRecord().getUNIT() }</p>
					</td>
					<td>
						<p>血透前体重:</p>
						<p>PRE－WEIGHT:   ${record.getHemodialysisChartRecordSetRecord().getPreWeight() }KG</p>
					</td>
					<td>
						<p>透析时间:</p>
						<p>DURATION:  ${record.getHemodialysisChartRecordSetRecord().getDuration() }HR></p>
					</td>
				</tr>
				<tr>
					<td>
						<p>门诊号:</p>
						<p>OPD NO.:   ${record.getIDCardNO() }</p>
					</td>
					<td>
						<p>血透次数:</p>
						<p>TIMES: ${record.getHemodialysisChartRecordSetRecord().getTimes() }</p>
					</td>
					<td>
						<p>透析液:</p>
						<p>DIAYSATE: ${record.getHemodialysisChartRecordSetRecord().getDiaysate() }</p>
					</td>
					<td>
						<p>理想体重:</p>
						<p>DRY-WEIGHT:   ${record.getHemodialysisChartRecordSetRecord().getDryWeight() }KG</p>
					</td>
					<td>
						<p>透析程式:</p>
						<p>PROGRAM:  ${record.getHemodialysisChartRecordSetRecord().getProgram() }</p>
					</td>
				</tr>
				<tr>
					<td>
						<p>年龄:</p>
						<p>AGE:  65 </p>
					</td>
					<td>
						<p>有否塞肾:</p>
						<p>Dialyser blockage: ${record.getHemodialysisChartRecordSetRecord().getDialyserBlockage() }</p>
					</td>
					<td>
						<p>透析器型号:</p>
						<p>DIALYSER: ${record.getHemodialysisChartRecordSetRecord().getDialyser() }</p>
					</td>
					<td>
						<p>目标脱水量:</p>
						<p>TARGET-LOSS:   ${record.getHemodialysisChartRecordSetRecord().getTargetLoss() }L</p>
					</td>
					<td>
						<p>15%KCL:  ${record.getHemodialysisChartRecordSetRecord().getKCI() }(ml)</p>
						<p>加入5（L）A液中</p>
					</td>
				</tr>
				<tr>
					<td>
						<p>性别:</p>
						<p>SEX: ${record.getPatientInfo().getSex() }</p>
					</td>
					<td>
						<p></p>
						<p></p>
					</td>
					<td>
						<p>血管通路:</p>
						<p>Vascular access: ${record.getHemodialysisChartRecordSetRecord().getVascularAccess() }</p>
					</td>
					<td>
						<p>血透后体重:</p>
						<p>POST－WEIGHT:   ${record.getHemodialysisChartRecordSetRecord().getPostWeight() }KG</p>
					</td>
					<td>
						<p>实际脱水量:</p>
						<p>NET-LOSS:  ${record.getHemodialysisChartRecordSetRecord().getNetLoss() }L</p>
					</td>
				</tr>
				
			</table>
			
		</div> 
			<!-- left -->
			<hr/>
			<div style="width:55%;float:left;">
				<!-- before -->
				<div>
					<table class="table table-bordered">
						<tr class="info">
							<th>时间 TIME</th>
							<th>泵速 PUMP SPEED</th>
							<th>静脉压 VEN. PRESS</th>
							<th>跨膜压 T.M.P</th>
							<th>透析液温度 TEMP.</th>
							<th>钠浓度 COND. (ms/cm)</th>
							<th>超滤量 UF(L)</th>
						</tr>
						<c:forEach var="before" items="${record.getHemodialysisBeforeRecords() }">
							<tr>
								<td>${before.getTime() }</td>
								<td>${before.getPumpSpeed() }</td>
								<td>${before.getVenPress() }</td>
								<td>${before.getTMP() }</td>
								<td>${before.getTEMP() }</td>
								<td>${before.getCOND() }</td>
								<td>${before.getUF() }</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<!-- ezv -->
				<hr/>
				<div>
					<table class="table table-bordered">
						<tr>
							<th class="info">低分子肝素L.M.W.H.</th>
							<td>${record.getLMWH() }</td>
						</tr>
						<tr>
							<th class="info">Erythropoietin</th>
							<td>${record.getErythropoietin() }</td>
						</tr>
						<tr>
							<th class="info">Zemplar</th>
							<td>${record.getZempler() }</td>
						</tr>
						<tr>
							<th class="info">Venofer</th>
							<td>${record.getVenofar() }</td>
						</tr>
					</table>
				</div>
				<!-- bp -->
				<hr/>
				<div>
					<table class="table table-bordered">
						<tr class="info">
							<th>血压（高）mmHg</th>
							<th>血压（低）mmHg</th>
							<th>脉搏（bpm）</th>
							<th>巡视护士</th>
						</tr>
						<c:forEach var="bp" items="${record.getBloodPressRecords() }">
							<tr>
								<td>${bp.getHighPress() }</td>
								<td>${bp.getLowPress() }</td>
								<td>${bp.getPulse() }</td>
								<td>${bp.getTourNurse() }</td>
							</tr>
						</c:forEach>
					</table>
					<hr/>
					<div>
						<p>责任护士:  ${record.getResponseNurse() }</p>
					</div>
					<hr/>
				</div>
			</div>
			<!-- right -->
			<div style="width:40%;float:right;">
				<table class="table table-bordered">
					<tr>
						<td>
							<div>
								<p>记录/医嘱:</p>
								<p>NOTES / MEDICAL ORDER:</p>
								<p>透前体温：   ${record.getPreTem() }</p>
							</div>
						</td>
					</tr>
					<tr style="height:500px">
						<td>
							<div>
								<table class="table table-bordered">
									<tr>
										<td rowspan="4">护士签名 NURSE SIGN</td>
										<td>开机:   ${record.getStartNurse() }</td>
									</tr>
									<tr><td>查机:   ${record.getCheckNurse() }</td></tr>
									<tr><td>收机:   ${record.getCloseNurse() }</td></tr>
									<tr><td>拔针:   ${record.getWithdrawalNurse() }</td></tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div>
								<p>医生签名</p>
								<p>Doctor sign:     ${record.getDoctor() }</p>
							</div>
						</td>
					</tr>
				</table>
			</div>
		<div>
		</div>
	</div>
</body>
</html>