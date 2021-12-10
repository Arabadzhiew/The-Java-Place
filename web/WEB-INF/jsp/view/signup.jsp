<template:unauthenticated title="Sign Up">
	<div class="p-5">
		<h2>Sign Up</h2><br/>
		<c:if test="${passwordsDontMatch != null}">
			<p style="color: #d11313;">The two passwords do not match</p>
		</c:if>
		<form:form method="post" modelAttribute="signupForm">
			<form:input class="form-control input-lg" path="username" placeholder="Username"/><br/>
			<form:errors path="username" cssClass="errors"/><br/>
			<form:input class="form-control input-lg" path="email" placeholder="E-mail"/><br/>
			<form:errors path="email" cssClass="errors"/><br/>
			<form:password class="form-control input-lg" path="password" placeholder="Password"/><br/>
			<form:errors path="password" cssClass="errors"/><br/>
			<form:password class="form-control input-lg" path="confirmPassword" placeholder="Confirm password"/><br/>
			<form:errors path="confirmPassword" cssClass="errors"/><br/>
			<input class="btn btn-success w-100" type="submit" value="Sign Up"/>
		</form:form><br/>
		Already registered?&nbsp;<a class="text-decoration-none" href="<c:url value="/login"/>">Log In</a>
	</div>
</template:unauthenticated>