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
		<c:choose>
			<c:when test="${threads.totalElements >  0 }">
				<div class="card bg-light">
					<table class="table">
						<tbody>
							<c:forEach items="${threads.content }" var="t">
								<tr>
									<td align="left">
										<a class="text-decoration-none" href="<c:url value="/sub/${t.sub.url }/thread/view?id=${t.id }"/>">${t.title }</a>
									</td>
									<td align ="right">
										<small>${t.dateCreated.dayOfMonth }&nbsp;<format:month value="${t.dateCreated.monthValue }"/>&nbsp;${t.dateCreated.year }</small>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div align="center">
						<template:pageButtons url="/user/${user.username }/threads" page="${threads }"/>
					</div><br/>
				</div>
			</c:when>
			<c:otherwise>
				<div align="center">
					<i class="text-muted">This user hasn't made any threads yet.</i>
				</div>
			</c:otherwise>
		</c:choose>
	</div><br/>
</template:user>