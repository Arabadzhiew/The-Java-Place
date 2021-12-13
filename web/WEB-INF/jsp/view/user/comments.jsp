<template:user>
	<div class="row pt-3">
		<ul class="nav nav-tabs">
			<li class="nav-item">
				<a class="nav-link" href="<c:url value="/user/${user.username }/threads"/>">Threads</a>
			</li>
			<li class="nav-item">
				<a class="nav-link active" href="<c:url value="/user/${user.username }/comments"/>">Comments</a>
			</li>
		</ul>
	</div><br/>
	<div class="row">
		<div class="card bg-light">
			<table class="table">
				<tbody>
					<c:forEach items="${comments.content }" var="c">
						<tr>
							<td>
								<a class="text-decoration-none" href="<c:url value="/sub/${c.thread.sub.url }/thread/view?id=${c.thread.id }"/>">${c.thread.title }</a>
							</td>
							<td align ="right">
								<small>${c.dateCreated.dayOfMonth }/${c.dateCreated.monthValue }/${c.dateCreated.year }</small>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div align="center">
				<c:forEach begin="1" end="${comments.totalPages }" var="p">
					<c:choose>
						<c:when test="${comments.pageable.pageNumber + 1 != p }">
							<a class="btn btn-sm btn-outline-primary" href="<c:url value="/user/${user.username }/comments?paging.page=${p }&paging.size=${comments.size }"/>">${p }</a>
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