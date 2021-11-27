<!DOCTYPE html>
<html>
	<head>
		<title>Sign Up</title>
		<link rel="stylesheet" href="<c:url value="/resources/css/login.css"/>">
	</head>
	<body>
		<h2>Sing Up</h2>
		<c:if test="${passwordsDontMatch != null}">
			<p style="color: #d11313;">The two passwords do not match</p>
		</c:if>
		<form:form method="post" modelAttribute="signupForm">
			<form:label path="username">Username:</form:label><br/>
			<form:input path="username"/><br/>
			<form:errors path="username" cssClass="errors"/><br/>
			<form:label path="email">Email:</form:label><br/>
			<form:input path="email"/><br/>
			<form:errors path="email" cssClass="errors"/><br/>
			<form:label path="password">Password: </form:label><br/>
			<form:password path="password"/><br/>
			<form:errors path="password" cssClass="errors"/><br/>
			<form:label path="confirmPassword">Confirm password:</form:label><br/>
			<form:password path="confirmPassword"/><br/>
			<form:errors path="confirmPassword" cssClass="errors"/><br/>
			<input type="submit" value="Sign Up"/>
		</form:form><br/>
		Already registered?&nbsp;<a href="<c:url value="/login"/>">Log In</a>
	</body>
</html>