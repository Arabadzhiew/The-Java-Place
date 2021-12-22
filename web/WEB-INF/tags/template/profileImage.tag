<%@ tag body-content="scriptless" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<%@ attribute  name="user" type="com.arabadzhiev.site.entity.User" rtexprvalue="true" required="true" %>
<%@ attribute  name="width" type="java.lang.String" rtexprvalue="true" required="true" %>
<%@ attribute  name="height" type="java.lang.String" rtexprvalue="true" required="true" %>

<c:choose>
	<c:when test="${user.profileImage != null }">
		<img class="rounded-circle img img-thumbnail full-width" width="${width }" height="${height }"
		src="<c:url value="/images/profile/${user.username }"/>"/>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${user.role == 'User' }">
				<img class="rounded-circle img-thumbnail" width="${width }" height="${height }" 
				src="<c:url value="/resources/images/userAvatar.png"/>"/>
			</c:when>
			<c:when test="${user.role == 'Admin' }">
				<img class="rounded-circle img-thumbnail" width="${width }" height="${height }" 
				src="<c:url value="/resources/images/adminAvatar.png"/>"/>
			</c:when>
		</c:choose>
	</c:otherwise>
</c:choose>