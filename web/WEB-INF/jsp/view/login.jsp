<!DOCTYPE html>
<html>
	<head>
		<title>Log In</title>
		<link rel="stylesheet" href="<c:url value="/resources/css/login.css"/>">
	</head>
	<body>
		<h2>Log In</h2>
		<form:form method="post" modelAttribute="loginForm">
			<form:label path="username">Username: </form:label><br/>
			<form:input path="username"/><br/>
			<form:errors path="username" cssClass="errors"/><br/>
			<form:label path="password">Password: </form:label><br/>
			<form:password path="password"/><br/>
			<form:errors path="password" cssClass="errors"/><br/>
			<input type="submit" value="Log In"/>
			<c:if test="${error != null }">
				<br/><br/><b class="errors">The username and password you entered do not match.</b>
			</c:if>
		</form:form><br/>
		Not registered yet?&nbsp;<a href="<c:url value="/signup"/>">Sign Up</a>
	</body>
</html>