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
								<small>${c.dateCreated.dayOfMonth }&nbsp;<format:month value="${c.dateCreated.monthValue }"/>&nbsp;${c.dateCreated.year }</small>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div align="center">
				<template:pageButtons url="/user/${user.username }/comments" page="${comments }"/>
			</div><br/>
		</div>
	</div><br/>
</template:user>