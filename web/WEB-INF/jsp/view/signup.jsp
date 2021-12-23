<!DOCTYPE html>
<html>
	<head>
		<title>Log In</title>
		<link rel="shortcut icon" href="<c:url value="/favicon.ico"/>"/>
		<link rel="stylesheet" href="<c:url value="/resources/css/login.css"/>">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<script src="<c:url value="/resources/js/signupAjax.js"/>"></script>
		
	</head>
	<body class="container bg-light bg-gradient p-5" onload="process();">
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
						<p style="color: #d11313;">The two passwords do not match</p>
					</c:if>
					<form:form method="post" modelAttribute="signupForm">
						<form:input class="form-control input-lg" path="username" placeholder="Username"/><br/>
						<div id="userExists"></div>
						<form:errors path="username" cssClass="errors"/><br/>
						<form:input class="form-control input-lg" path="email" placeholder="E-mail"/><br/>
						<form:errors path="email" cssClass="errors"/><br/>
						<form:password class="form-control input-lg" path="password" placeholder="Password"/><br/>
						<form:errors path="password" cssClass="errors"/><br/>
						<form:password class="form-control input-lg" path="confirmPassword" placeholder="Confirm password"/><br/>
						<form:errors path="confirmPassword" cssClass="errors"/><br/>
						<input class="btn btn-success btn-disabled w-100" id="submit" disabled type="submit" value="Sign Up"/>
					</form:form><br/>
					Already registered?&nbsp;<a class="text-decoration-none" href="<c:url value="/login"/>">Log In</a>
				</div>
			</div>
		</div>
	</body>
</html>