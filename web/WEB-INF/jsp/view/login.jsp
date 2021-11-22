<!DOCTYPE html>
<html>
	<head>
		<title>Log In</title>
	</head>
	<body>
		<h2>Log In</h2>
		<form:form method="post" modelAttribute="loginForm">
			<form:label path="username">Username: </form:label><br/>
			<form:input path="username"/><br/>
			<form:label path="password">Password: </form:label><br/>
			<form:password path="password"/><br/>
			<input type="submit" value="Log In"/>
		</form:form>
	</body>
</html>