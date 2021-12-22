<%@ tag body-content="scriptless" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>

<template:main title="${user.username }">
	<div class="container">
		<div class="row pb-3">
			<div class="col-3">
				<div align="center">
					<template:profileImage user="${user }" width="130" height="130"></template:profileImage>
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
				<small>Registered on: ${user.dateCreated.dayOfMonth }&nbsp;<format:month value="${user.dateCreated.monthValue }"/>&nbsp;${user.dateCreated.year }</small><br/>
				<small id="lastActive">Last active: </small><br/><br/>
				<script type="text/javascript">lastActive(${lastActive});</script>
				<security:authorize access="#user.username == authentication.name OR hasAuthority('ADMIN')">
					<div id="changeImageDiv">
					</div>
					<a class="text-decoration-none" id="profileImgHref" onclick="profileImgForm('${user.username }', '${_csrf.token }')" href="javascript:void 0;">Change profile image</a>
					<br/>
					<c:if test="${user.profileImage != null }">
					<security:authorize access="#user.username == authentication.name" var="ownProfile"/>
						<c:choose>
							<c:when test="${ownProfile }">
								<a class="text-decoration-none text-danger" onclick="deleteProfileImage('<c:url value="/user/${user.username }/delete/profileImage"/>', 'your', '${_csrf.token }')" href="javascript:void 0;">Delete profile image</a>
							</c:when>
							<c:otherwise>
								<a class="text-decoration-none text-danger" onclick="deleteProfileImage('<c:url value="/user/${user.username }/delete/profileImage"/>', '${user.username.concat('&rsquo;s') }', '${_csrf.token }')" href="javascript:void 0;">Delete profile image</a>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${error != null }">
						<br/>
						<br/>
						<b class="text-danger">An error occurred while trying to upload the image.</b>
					</c:if>
				</security:authorize>
			</div>
		</div>
		<jsp:doBody/>
	</div>
</template:main>