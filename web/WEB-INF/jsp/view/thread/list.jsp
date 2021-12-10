<template:main title="${subName } :: Threads">
	<div align="center">
			<h2 class="display-2"><c:out value="${subName }"/></h2><br/>
			<div class="px-5">
				<table class="table">
					<tr class="table-dark">
						<th>Threads</th>
						<th>Comments</th>
						<security:authorize access="hasAuthority('ADMIN')">
							<th>Actions</th>
						</security:authorize>
					</tr>
					<c:forEach items="${threads }" var="t">
						<tr>
							<td>
								<a class="text-decoration-none" href="<c:url value="/sub/${subUrl }/thread/view?id=${t.id }"/>"><c:out value="${t.title }"/></a>
							</td>
							<td><c:out value="${t.commentCount }"/></td>
							<security:authorize access="hasAuthority('ADMIN')">
								<td>
									<a class="link text-decoration-none" href="<c:url value="/sub/${subUrl }/thread/edit?id=${t.id }"/>">Edit</a>&nbsp;
									<a class="link-danger text-decoration-none" href="javascript:void 0;" onclick="deleteThread('<c:url value="/sub/${t.sub.url }/thread/delete?id=${t.id }"/>', ${t.id }, '${_csrf.token }');">Delete</a>
								</td>
							</security:authorize>
						</tr>
					</c:forEach>
				</table>
				<a class="btn btn-outline-secondary w-100" href="<c:url value="/sub/${subUrl }/thread/create"/>">Create Thread</a><br/><br/>
			</div><br/>
		</div>
</template:main>