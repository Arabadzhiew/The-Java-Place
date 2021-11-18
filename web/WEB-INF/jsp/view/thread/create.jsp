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
		<h2>Create Thread</h2>
		
		<form:form metohd="post" modelAttribute="threadForm"><br/>
			<form:label path="title">Title:</form:label><br/>
			<form:input path="title"/><br/>
			<form:errors path="title" cssClass="errors"/><br/><br/>
			<form:label path="body">Body:</form:label><br/>
			<form:textarea path="body" rows="10" cols = "50"/><br/>
			<form:errors path="body" cssClass="errors"/><br/><br/>
			<input type="submit" value="Submit"/><br/><br/>
			
			<a href="<c:url value="/"/>">Back to home page</a>
		</form:form>
	</body>
</html>