<!DOCTYPE html>
<html>
	<head>
		<title>${thread.title }</title>
		<style type="text/css">
			form{
				display: inline;
			}
		</style>
	</head>
	
	<body>
		<h2>${thread.title }</h2>
		<i>By : ${thread.user.username }</i><br/>
		<p>${thread.body }</p><br/><br/>
		<form method="post"
			action="<c:url value="/sub/${thread.subUrl }/thread/delete?id=${thread.id }"/>">
			<input type="submit" value="Delete" 
				onclick="return confirm('Are you sure you want to delete thread with Id = ${thread.id}');"/>
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
		</form>
		<a href="<c:url value="/sub/${thread.subUrl }/thread/edit?id=${thread.id }"/>">Edit</a><br/><br/>
		
		<form:form method="post" modelAttribute="commentForm">
			<form:label path="body">Comment:</form:label><br/><br/>
			<form:textarea path="body" rows="10" cols="50"/><br/><br/>
			<form:errors path="body" cssStyle="color: #d11313;"/><br/>
			<input type="submit" value="Submit"/><br/><br/>
		</form:form>
		
		<c:forEach items="${comments }" var="c">
			<h3>${c.body }</h3>
			<c:if test="${c.dateCreated != null }">
				<i>Commented at: </i><b>${c.dateCreated }</b><br/>
			</c:if>
		</c:forEach><br/>
		
		<footer><a href="<c:url value="/sub/${sub.url }"/>">Return to the <c:out value="${sub.name }"/> Sub</a></footer>
	</body>
</html>