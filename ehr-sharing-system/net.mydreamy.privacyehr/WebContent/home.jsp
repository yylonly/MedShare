<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
	
	<!-- Bootstrap core css  -->
	<link href="css/bootstrap.css" rel="stylesheet" />
	<link href="css/signin.css" rel="stylesheet" />
	<script type="text/javascript" src = "js/jquery-1.11.3.min.js" ></script>
	<script>
	
		function btnLoginClicked() {
			
			var url = 'http://' + $('#authServer').val() + ':8080/authdoctor?username=' + $('#inputUserName').val() + '&password=' + $('#inputPassword').val();
			
			$.get(url,function(data){
				$.post('login',
						{
							tokens: data.token
						},
						function(response){
							
							$('#subViewDiv').html(response);
						});
			});
		}
	</script>
	
</head>
	<body class="container">
			<!-- <form action="login" method="post" > -->
			<div id="subViewDiv">
				<div class="form-signin">
					<h2 class="form-signin-heading">USER SIGN IN</h2>
					<label for="authServer" class="sr-only">Auth Server</label>
					<input id="authServer" class="form-control" name="authserver" placeholder="Auth server" required>
					<br>
					<label for="inputEmail" class="sr-only">User Name</label>
        				<input id="inputUserName" class="form-control" name="username" placeholder="User Name" required autofocus>
        				<br>
       				<label for="inputPassword" class="sr-only">Password</label>
        				<input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
					<button class="btn btn-lg btn-primary btn-block" onClick="btnLoginClicked()" >Sign in</button>
					<br>
					<a href="tracker">Manager Login</a>
					<br>
					<br>
				<div>
					${errormsg }
				</div>
			</div>
			</div>
			<!-- </form> -->
	</body>
</html>
