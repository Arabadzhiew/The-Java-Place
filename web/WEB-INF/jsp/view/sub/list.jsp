<template:main title="Subs">
	<div align="center">
			<security:authorize access="isAuthenticated()">
				<h2 class="text-muted">Welcome, <security:authentication property="principal.username"/>!</h2>
			</security:authorize>
			<h1 class="display-1">Subs</h1>
			<div class="d-flex px-5">
				<table class="table">
					<tr class="table-dark">
						<th>Forum Sub</th>
						<th>Threads</th>
						<th>Comments</th>
						<th>Last Active</th>
					</tr>
					<c:forEach items="${subs.content }" var = "s">
						<tr>
							<td>
								<a href = "<c:url value="/sub/${s.url }"/>" class="text-decoration-none"><c:out value="${s.name }"/></a>
							</td>
							<td><c:out value="${s.totalThreads }"></c:out></td>
							<td><c:out value="${s.totalComments }"></c:out></td>
							<td>
								<c:choose>
									<c:when test="${s.lastActiveThread != null}">
										<a href="<c:url value="/sub/${s.url }/thread/view?id=
										${s.lastActiveThread.id}"/>" class="text-decoration-none"><c:out value="${s.lastActiveThread.title }"/></a>
									</c:when>
									<c:otherwise>
										<a href="<c:url value="/sub/${s.url}/thread/create"/>" class="link-success text-decoration-none">New Sub :: Create the first thread</a>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="pt-3" align="center">
			<template:pageButtons url="/" page="${subs }"/>
		</div>
</template:main>