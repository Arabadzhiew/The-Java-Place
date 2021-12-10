<template:unauthenticated title="Log In">
	<div class="p-5">
		<h2>Log In</h2><br/>
		<form:form method="post" modelAttribute="loginForm">
			<form:input class="form-control input-lg" path="username" placeholder="Username"/><br/>
			<form:password class="form-control input-lg" path="password" placeholder="Password"/><br/>
			<input class="btn btn-primary w-100" type="submit" value="Log In"/>
			<c:if test="${error != null }">
				<div align="center">
					<br/><b class="errors">The username and password you entered do not match.</b>
				</div>
			</c:if>
		</form:form><br/>
		<div class="border-bottom" align="center">
			<a class="text-decoration-none">Forgot your password?</a><br/><br/>
		</div><br/>
		<div align="center">
			<a class="btn btn-success" href="<c:url value="/signup"/>">Create New Account</a>
		</div>
	</div>
</template:unauthenticated>
