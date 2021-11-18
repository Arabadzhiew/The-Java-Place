<!DOCTYPE html>
<html>
	<head>
		<title>${thread.title }</title>
	</head>
	
	<body>
		<h2>${thread.title }</h2>
		<p>${thread.body }</p><br/><br/>
		
		<form:form method="post" modelAttribute="commentForm">
			<form:label path="body">Comment:</form:label><br/><br/>
			<form:textarea path="body" rows="10" cols="50"/><br/><br/>
			<form:errors path="body" cssStyle="color: #d11313;"/><br/>
			<input type="submit" value="Submit"/><br/><br/>
		</form:form>
		
		<c:forEach items="${comments }" var="c">
			<h3>${c.body }</h3>
			<c:if test="${c.dateCreated != nul }">
				<i>Commented at: </i><b>${c.dateCreated }</b><br/>
			</c:if>
		</c:forEach><br/>
		
		<footer><a href="<c:url value="/"/>">Back to home page</a></footer>
	</body>
</html>