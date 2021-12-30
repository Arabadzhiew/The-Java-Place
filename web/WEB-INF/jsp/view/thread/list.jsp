<template:main title="${subName } :: Threads">
	<security:authorize var="authenticated" access="isAuthenticated()"/>
	<c:choose>
		<c:when test="${threads != null }">
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
						<c:forEach items="${threads.content }" var="t">
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
					<c:if test="${authenticated }">
						<a class="btn btn-outline-secondary w-100" href="<c:url value="/sub/${subUrl }/thread/create"/>">Create thread</a><br/><br/>
					</c:if>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div align="center">
				<h2 class="display-2"><c:out value="${subName }"/></h2><br/>
				<div class="p-5">
					<c:choose>
						<c:when test="${authenticated }">
							<a class="btn btn-outline-success w-100" href="<c:url value="/sub/${subUrl }/thread/create"/>">Create the first thread</a><br/><br/>
						</c:when>
						<c:otherwise>
							<i class="text-muted">This sub is new and currently doesn't have any threads. Sign in and create the first!</i>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	<div class="pt-3" align="center">
		<template:pageButtons page="${threads }" url="/sub/${subUrl }"></template:pageButtons>
	</div>
</template:main>