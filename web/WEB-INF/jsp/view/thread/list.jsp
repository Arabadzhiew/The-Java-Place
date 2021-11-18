<!DOCTYPE html>
<html>
	<head>
		<title>The Java Place</title>
	</head>
	<body>
		<table>
			<tr><th>Forum Threads</th></tr>
			<c:forEach items="${threads }" var="t">
				<tr>
					<td>
						<a href="<c:url value="/thread/view?id=${t.id }"/>"><c:out value="${t.title }"/>
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<footer>
			<a href="<c:url value="/thread/create"/>">Create Thread</a>
		</footer>
	</body>
</html>