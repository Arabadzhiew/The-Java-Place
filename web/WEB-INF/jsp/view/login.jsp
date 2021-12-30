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
			<a class="text-decoration-none" data-bs-toggle="modal" data-bs-target="#forgottenPassword" 
			href="javascript:void 0;">Forgot your password?</a><br/><br/>
		</div><br/>
		<div align="center">
			<a class="btn btn-success" href="<c:url value="/signup"/>">Create New Account</a>
		</div>
		<div class="modal fade" id="forgottenPassword" tabindex="-1" role="dialog" aria-labelledby="forgottenPasswordLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h2 class="modal-title" id="forgottenPasswordLabel">Account recovery</h2>
						<button class="btn-close" data-bs-dismiss="modal"></button>
					</div>
					<form:form class="form-control" modelAttribute="recoveryForm" method="post"
					action="/tjp/recovery">
						<div class="modal-body">
							<h5>Please enter the email address you are registered with,
							 so we can send you a recovery message.
							</h5><br/>
							<form:input class="form-control" path="email" placeholder="E-mail"/>
							<c:choose>
								<c:when test="${recoverySuccess != null }">
									<span class="text-success">A message with instructions was sent to your email</span>
								</c:when>
								<c:when test="${recoveryError != null}">
									<span class="text-danger">${recoveryError }</span>
								</c:when>
							</c:choose>
						</div>
						<div class="modal-footer" align="center">
							<input class="btn btn-outline-success" type="submit" value="Send"/>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<c:if test="${recoveryError != null}">
			<script type="text/javascript">
				var modal = new bootstrap.Modal(document.getElementById('forgottenPassword'), {
					keyboard: false
				})
			 	modal.show();
			</script>
		</c:if>
	</div>
</template:unauthenticated>
