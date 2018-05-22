<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link href="css/bootstrap.css" rel="stylesheet" />
	<link href="css/signin.css" rel="stylesheet" />
	<script type="text/javascript" src = "js/jquery-1.11.3.min.js" ></script>
</head>
<body class="container">
	<form action="trackerlogin" method="post" >
				<div class="form-signin">
				<h2 class="form-signin-heading">MANAGER SIGN IN</h2>
				<label for="inputEmail" class="sr-only">User Name</label>
        		<input id="inputUserName" class="form-control" name="trackerid" placeholder="User Name" required autofocus>
        		<br>
       			<label for="inputPassword" class="sr-only">Password</label>
        		<input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
				<input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign in">
				<br>
				<a href="home">User Login</a>
			</div>
	</form>
</body>
</html>