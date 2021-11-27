<template:main title="${subName } :: Threads">
	<div align="center">
			<table>
				<tr>
					<th><c:out value="${subName }"/> :: Threads</th>
					<th>Comments</th>
					<security:authorize access="hasAuthority('ADMIN')">
						<th>Actions</th>
					</security:authorize>
				</tr>
				<c:forEach items="${threads }" var="t">
					<tr>
						<td>
							<a href="<c:url value="/sub/${subUrl }/thread/view?id=${t.id }"/>"><c:out value="${t.title }"/></a>
						</td>
						<td><c:out value="${t.commentCount }"/></td>
						<security:authorize access="hasAuthority('ADMIN')">
							<td>
								<a href="<c:url value="/sub/${subUrl }/thread/edit?id=${t.id }"/>">Edit</a>&nbsp;|
								<a href="javascript:void 0;" onclick="deleteThread('<c:url value="/sub/${t.sub.url }/thread/delete?id=${t.id }"/>', ${t.id }, '${_csrf.token }');">Delete</a>
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
</template:main>