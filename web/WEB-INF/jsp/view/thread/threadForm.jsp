<!DOCTYPE html>
<html>
	<head>
		<title>Home</title>
		<style type="text/css">
			.errors{
				color: #d11313;
			}
		</style>
	</head>
	<body>
		<c:choose>
			<c:when test="${action == 'create' }">
				<h2>Create Thread</h2>
			</c:when>
			<c:otherwise>
				<h2>Edit Thread</h2>
			</c:otherwise>
		</c:choose>
		
		<form:form metohd="post" modelAttribute="threadForm"><br/>
			<form:label path="title">Title:</form:label><br/>
			<form:input path="title"/><br/>
			<form:errors path="title" cssClass="errors"/><br/><br/>
			<form:label path="body">Body:</form:label><br/>
			<form:textarea path="body" rows="10" cols = "50"/><br/>
			<form:errors path="body" cssClass="errors"/><br/><br/>
			<c:choose>
			<c:when test="${action == 'create' }">
				<input type="submit" value="Create"/><br/>
			</c:when>
			<c:otherwise>
				<input type="submit" value="Edit"/><br/>
			</c:otherwise>
		</c:choose>
		</form:form><br/>
		<a href="<c:url value="/sub/${sub.url }"/>">Return to the <c:out value="${sub.name }"/> Sub</a>
	</body>
</html>