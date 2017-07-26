<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
	<body>
		<h1>
			Login 
		</h1>
			<div>
				<form action="login" method="get">
					<div>
						<input type="text" name="username" />
					</div>
					<div>
						<input type="text" name="password" />
					</div>
					<div>
						<input type="submit" value="Login" />
					</div>
				</form>
			</div>
	</body>
</html>
