<template:user>
	<div class="row pt-3">
		<ul class="nav nav-tabs">
			<li class="nav-item">
				<a class="nav-link active" href="<c:url value="/user/${user.username }/threads"/>">Threads</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="<c:url value="/user/${user.username }/comments"/>">Comments</a>
			</li>
		</ul>
	</div><br/>
	<div class="row">
		<div class="card bg-light">
			<table class="table">
				<tbody>
					<c:forEach items="${threads.content }" var="t">
						<tr>
							<td align="left">
								<a class="text-decoration-none" href="<c:url value="/sub/${t.sub.url }/thread/view?id=${t.id }"/>">${t.title }</a>
							</td>
							<td align ="right">
								<small>${t.dateCreated.dayOfMonth }/${t.dateCreated.monthValue }/${t.dateCreated.year }</small>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div align="center">
				<c:forEach begin="1" end="${threads.totalPages }" var="p">
					<c:choose>
						<c:when test="${threads.pageable.pageNumber + 1 != p }">
							<a class="btn btn-sm btn-outline-primary" href="<c:url value="/user/${user.username }/threads?paging.page=${p }&paging.size=${comments.size }"/>">${p }</a>
						</c:when>
						<c:otherwise>
							<a class="btn btn-sm btn-primary" href="javascript:void 0;">${p }</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div><br/>
		</div>
	</div><br/>
</template:user>