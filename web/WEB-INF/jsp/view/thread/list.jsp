<!DOCTYPE html>
<html>
	<head>
		<title><c:out value="${subName }"/> :: Threads</title>
	</head>
	<body>
		<table>
			<tr><th><c:out value="${subName }"/> :: Threads</th></tr>
			<c:forEach items="${threads }" var="t">
				<tr>
					<td>
						<a href="<c:url value="/sub/${subUrl }/thread/view?id=${t.id }"/>"><c:out value="${t.title }"/>
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<footer>
			<a href="<c:url value="/sub/${subUrl }/thread/create"/>">Create Thread</a><br/><br/>
			<a href="<c:url value="/"/>">Back to home page</a>
		</footer>
	</body>
</html>