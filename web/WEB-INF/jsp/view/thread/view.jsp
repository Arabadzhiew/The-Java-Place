<template:main title="${thread.title }">
	<h2>${thread.title }</h2>
	<i>By : ${thread.user.username }</i><br/>
	<p>${thread.body }</p><br/><br/>
	<security:authorize access="#thread.user.username == authentication.name or hasAuthority('ADMIN')">
		<a href="javascript:void 0;" onclick="deleteThread('<c:url value="/sub/${thread.sub.url }/thread/delete?id=${thread.id }"/>', ${thread.id }, '${_csrf.token }');">Delete</a>
		&nbsp;
		<a href="<c:url value="/sub/${thread.sub.url }/thread/edit?id=${thread.id }"/>">Edit</a><br/><br/>
	</security:authorize>
		
	<form:form method="post" modelAttribute="commentForm">
		<form:label path="body">Comment:</form:label><br/><br/>
		<form:textarea path="body" rows="10" cols="50"/><br/><br/>
		<form:errors path="body" cssStyle="color: #d11313;"/><br/>
		<input type="submit" value="Submit"/><br/><br/>
	</form:form>
		
	<c:forEach items="${comments }" var="c">
		<h3>${c.body }</h3>
		<i>Commented by: ${c.user.username }</i><br/>
		<c:if test="${c.dateCreated != null }">
			<i>At: </i><b>${c.dateCreated }</b><br/>
		</c:if>
		<security:authorize access="#c.user.username == authentication.name or hasAuthority('ADMIN')">
			<a href="javascript:void 0;" 
				onclick="deleteComment('<c:url value="/sub/${thread.sub.url }/thread/comment/delete?id=${thread.id }&commentId=${c.id }"/>', '${_csrf.token }')">
				Delete
			</a>
		</security:authorize>
	</c:forEach><br/><br/>
		
	<footer><a href="<c:url value="/sub/${sub.url }"/>">Return to the <c:out value="${sub.name }"/> Sub</a></footer>
</template:main>