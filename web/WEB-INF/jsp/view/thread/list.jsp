<!DOCTYPE html>
<html>
	<head>
		<title><c:out value="${subName }"/> :: Threads</title>
		<style type="text/css">
			table, tr{
				border: 1px solid #000000;
				border-collapse: collapse;
			}
			td, th{
				border: 1px solid #000000;
				padding: 10px;
			}
			form{
				display: inline;
			}
		</style>
	</head>
	<body>
		<div align="center">
			<table>
				<tr>
					<th><c:out value="${subName }"/> :: Threads</th>
					<th>Comments</th>
					<security:authorize url="/sub/${subUrl }/thread/edit">
						<th>Actions</th>
					</security:authorize>
				</tr>
				<c:forEach items="${threads }" var="t">
					<tr>
						<td>
							<a href="<c:url value="/sub/${subUrl }/thread/view?id=${t.id }"/>"><c:out value="${t.title }"/></a>
						</td>
						<td><c:out value="${t.commentCount }"/></td>
						<security:authorize url="/sub/${subUrl }/thread/edit">
							<td>
								<a href="<c:url value="/sub/${subUrl }/thread/edit?id=${t.id }"/>">Edit</a>&nbsp;|
								<form action="<c:url value="/sub/${subUrl }/thread/delete?id=${t.id }"/>" method="post">
									<input type="submit" value="Delete" 
									onclick="return confirm('Are you sure you want to delete thread with Id = ${t.id }?')"/>
								</form>
							</td>
						</security:authorize>
					</tr>
				</c:forEach>
			</table><br/>
			<footer>
				<a href="<c:url value="/sub/${subUrl }/thread/create"/>">Create Thread</a><br/><br/>
				<a href="<c:url value="/"/>">Back to home page</a>
			</footer>
		</div>
	</body>
</html>