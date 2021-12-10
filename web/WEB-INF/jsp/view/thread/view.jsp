<template:main title="${thread.title }">
	<div class="px-5">
		<div>
			<a class="text-decoration-none" href="<c:url value="/sub/${sub.url }"/>"> <c:out value="${sub.name }"/></a><br/><br/>
				<div class="border-bottom border-primary">
					<i>Posted by : ${thread.user.username }</i><br/>
					<h2>${thread.title }</h2>
					<p>${thread.body }</p><br/>
					<security:authorize access="#thread.user.username == authentication.name or hasAuthority('ADMIN')">
						<a class="link-danger text-decoration-none" 
						href="javascript:void 0;" onclick="deleteThread('<c:url value="/sub/${thread.sub.url }/thread/delete?id=${thread.id }"/>', ${thread.id }, '${_csrf.token }');">Delete</a>
						&nbsp;
						<a class="link text-decoration-none" 
						href="<c:url value="/sub/${thread.sub.url }/thread/edit?id=${thread.id }"/>">Edit</a>&nbsp;
					</security:authorize>
					<a class="link-primary text-decoration-none" id="commentHref" href="javascript:void 0;" onclick="commentForm('${thread.id }', '${_csrf.token }')">Comment</a>
				<div id="commentDiv"></div><br/>
			</div><br/><br/>
		
			<c:forEach items="${comments }" var="c">
				<div class="border-bottom">
					<h3 id="comment-${c.id }">${c.body }</h3>
					<div id="cmntDiv-${c.id }"></div>
					<security:authorize access="#c.user.username == authentication.name" var="ownComment"/>
					<c:choose>
						<c:when test="${ownComment}">
								<i>Commented by: you</i>&nbsp;
						</c:when>
						<c:otherwise>
							<i>Commented by: <b>${c.user.username }</b></i>&nbsp;
						</c:otherwise>
					</c:choose>
					<c:if test="${c.dateCreated != null }">
						<i>At: </i><b>${c.dateCreated }</b>
					</c:if><br/>
					<security:authorize access="#c.user.username == authentication.name or hasAuthority('ADMIN')">
						<a class="link-danger text-decoration-none" href="javascript:void 0;" 
							onclick="deleteComment('<c:url value="/sub/${thread.sub.url }/thread/comment/delete?id=${thread.id }&commentId=${c.id }"/>', '${_csrf.token }')">
							Delete
						</a>&nbsp;
						<a class="link text-decoration-none" id="editHref-${c.id }" href="javascript:void 0;" 
						onclick="editForm('${c.id}', '${c.body }', '${_csrf.token }')">Edit</a>
						
					</security:authorize>
				</div><br/>
			</c:forEach>
		</div>
	</div>
</template:main>