<!DOCTYPE html>
<html>
	<head>
		<title>Home Page</title>
	</head>
	
	<body>
		<table>
			<tr><th>Forum Subs:</th></tr>
			<c:forEach items="${subs }" var = "s">
				<tr>
					<td>
						<a href = "<c:url value="/sub/${s.url }"/>"><c:out value="${s.name }"/></a>
					</td>
				</tr>
			</c:forEach>
		</table>
		
	</body>
</html>