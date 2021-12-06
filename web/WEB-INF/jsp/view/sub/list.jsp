<template:main title="Subs">
	<div align="center">
			<h1>Welcome, <security:authentication property="principal.username"/>!</h1>
			<h1>Subs</h1>
			<table>
				<tr>
					<th>Forum Sub</th>
					<th>Threads</th>
					<th>Comments</th>
					<th>Last Active</th>
				</tr>
				<c:forEach items="${subs }" var = "s">
					<tr>
						<td>
							<a href = "<c:url value="/sub/${s.url }"/>"><c:out value="${s.name }"/></a>
						</td>
						<td><c:out value="${s.totalThreads }"></c:out></td>
						<td><c:out value="${s.totalComments }"></c:out></td>
						<td>
							<c:choose>
								<c:when test="${s.lastActiveThread != null}">
									<a href="<c:url value="/sub/${s.url }/thread/view?id=
									${s.lastActiveThread.id}"/>"><c:out value="${s.lastActiveThread.title }"/></a>
								</c:when>
								<c:otherwise>
									<a href="<c:url value="/sub/${s.url}/thread/create"/>">New Sub :: Create the first thread</a>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
</template:main>