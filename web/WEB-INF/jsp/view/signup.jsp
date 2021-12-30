<!DOCTYPE html>
<html>
	<head>
		<title>Sign Up</title>
		<link rel="shortcut icon" href="<c:url value="/favicon.ico"/>"/>
		<link rel="stylesheet" href="<c:url value="/resources/css/login.css"/>">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<script src="<c:url value="/resources/js/signupAjax.js"/>"></script>
		
	</head>
	<body class="bg-light bg-gradient" onload="process();">
		<div class="container p-5">
			<div class="row px-5">
				<div class="col-lg p-5">
					<img alt="The Java Place" src="<c:url value="/resources/images/tjpLogo.png"/>"
					width="200" height="110"/>
					<h2 class="display-4">The place for everything Java related</h2>
				</div>
				<div class="col-lg p-5">
					<div class="p-5">
						<h2>Sign Up</h2><br/>
						<c:if test="${passwordsDontMatch != null}">
							<p class="text-danger">The passwords do not match</p>
						</c:if>
						<form:form method="post" modelAttribute="signupForm">
							<form:input class="form-control input-lg" path="username" placeholder="Username"/>
							<span id="userExists"></span>
							<form:errors path="username" cssClass="errors"/><br/><br/>
							<form:input class="form-control input-lg" path="email" placeholder="E-mail"/>
							<form:errors path="email" cssClass="errors"/><br/><br/>
							<form:password class="form-control input-lg" path="password" placeholder="Password"/>
							<form:errors path="password" cssClass="errors"/><br/><br/>
							<form:password class="form-control input-lg" path="confirmPassword" placeholder="Confirm password"/>
							<form:errors path="confirmPassword" cssClass="errors"/><br/><br/>
							<input class="btn btn-success w-100" id="submit" disabled type="submit" value="Sign Up"/>
						</form:form><br/>
						Already registered?&nbsp;<a class="text-decoration-none" href="<c:url value="/login"/>">Log In</a>
					</div>
				</div>
			</div>
		</div>
		<template:footer/>
	</body>
</html>