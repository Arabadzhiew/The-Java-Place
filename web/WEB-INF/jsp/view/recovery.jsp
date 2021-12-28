<template:unauthenticated title="Password resset">
	<div class="p-5">
		<h2>Password reset</h2><br/>
		<c:if test="${passwordsDontMatch == true}">
			<p class="text-danger">The passwords do not match</p>
		</c:if>
		<form:form modelAttribute="passwordChangeForm" method="post">
			<form:password class="form-control" path="password" placeholder="Password"/>
			<form:errors class="text-danger" path="password"/><br/><br/>
			<form:password class="form-control" path="confirmPassword" placeholder="Confirm password"/>
			<form:errors class="text-danger" path="confirmPassword"/><br/><br/>
			<input class="btn btn-success w-100" type="submit" value="Change password" 
			onclick="return confirm('Are you sure you want to change your passoword? This action cannot be undone.');"/>
		</form:form>
	</div>
</template:unauthenticated>