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
	<div>
		<div class="container">
		<!-- Doctor Informations -->
		<div class="doctorinfo">
			<h2>welcome, ${ trackerid }</h2>
			 <a href="trackerlogout">Logout</a>  
		</div>
		<!-- Query Conditions -->
		<div class="conditions">
			<!-- log conditions -->
			<form action="querylog" method="post">
				<div>
					Record ID. 
					<input id="ehrid" name="ehrid" placeholder="EHR ID" required autofocus>
					Date.
					<input id="querydate" name="querystartdate" type="date" autofocus/>
					-
					<input id="querydate" name="queryenddate" type="date" autofocus/>
					
					<input type="submit" value="Query" />
				</div>
			</form>
		</div>
		<!-- Query Results -->
			<table class="table table-striped">
				<tr>
	                <th>NO.</th>
	                <th>User ID</th>
	                <th>User Type</th>
	                <th>IP Address</th>
	                <!-- <th>Operation</th> -->
	                <th>Time Stamp</th>
	                <th>Location</th>
	                <th>EHR ID</th>
	                <th>EHR Type</th>
            	</tr>
            	<c:forEach var="log" items="${trackerlogs }" varStatus="status">
            		<tr>
	            		<td>${status.index+1 }</td>
	            		<td> ${log.getUserID()}</td>
	            		<td> ${log.getUserType()}</td>
	            		<td> ${log.getIPAddress()}</td>
	            		<%-- <td> ${log.getOperation()}</td> --%>
	            		<td> ${log.getTimeStamp()}</td>
	            		<td> ${log.getLocation()}</td>
	            		<td> ${log.getEHRID()}</td>
	            		<td> ${log.getEHRType()}</td>
            	 	</tr>
            	</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>