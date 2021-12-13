<%@ tag body-content="scriptless" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>

<template:main title="${user.username }">
	<div class="container">
		<div class="row pb-3">
			<div class="col-3">
				<div align="center">
					<c:choose>
						<c:when test="${user.role.equals('User') }">
							<img class="rounded-circle img-thumbnail" width="120" height="120" alt="profilePicture"
							src="<c:url value="/resources/images/userAvatar.png"/>"/>
						</c:when>
						<c:when test="${user.role.equals('Admin') }">
							<img class="rounded-circle img-thumbnail" width="120" height="120" alt="profilePicture"
							src="<c:url value="/resources/images/adminAvatar.png"/>"/>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${usersOnlineList.contains(user) }">
							<br/><br/><div class="rounded-circle" style="width: 15px; height: 15px; background-color: #198754;"></div>
							<b>Online</b>
						</c:when>
						<c:otherwise>
							<br/><br/><div class="rounded-circle" style="width: 15px; height: 15px; background-color: #dc3545;"></div>
							<b>Offline</b>
							</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="col-3">
				<h2>${user.username }</h2>
				<small>Registered on: ${user.dateCreated.dayOfMonth }/${user.dateCreated.monthValue }/${user.dateCreated.year }</small><br/>
				<small id="lastActive">Last active: </small>
				<script type="text/javascript">lastActive(${lastActive});</script>
			</div>
		</div>
		<jsp:doBody/>
	</div>
</template:main>