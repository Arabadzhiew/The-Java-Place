<template:main title="${thread.title }">
	<div class="px-5">
		<div>
				<div class="container pb-3 border-bottom border-primary">
					<a class="text-decoration-none" href="<c:url value="/sub/${sub.url }"/>"> <c:out value="${sub.name }"/></a><br/><br/>
					<div class="row">
						<div class="col-10">
							<div class="d-flex justify-content-between border-bottom border-muted">
								<div class="d-flex flex-column">
									<h2>${thread.title }</h2>
								</div>	
								<div class="d-flex flex-column">
									<small class="text-secondary">
										${thread.dateCreated.dayOfMonth }/${thread.dateCreated.monthValue }/${thread.dateCreated.year }&nbsp;${thread.dateCreated.hour }:${thread.dateCreated.minute }
									</small>
								</div>						
							</div>
							<p style="white-space: pre-line;">${thread.body }</p><br/>
						</div>
						<div class="col-2 pb-3 border rounded bg-light" align="center">
							<security:authorize access="#thread.user.username == authentication.name" var="ownComment"/>
								<a class="link-dark text-decoration-none" href="<c:url value="/user/${thread.user.username }"/>">${thread.user.username }
									<c:if test="${ownComment }">(you)</c:if>
								</a>
							<c:choose>
								<c:when test="${thread.user.role.equals('User')}">
									<p class="text-secondary">User</p>
									<img class="rounded-circle img-thumbnail" width="66" height="66" alt="User" src="<c:url value="/resources/images/userAvatar.png"/>">
								</c:when>
								<c:when test="${thread.user.role.equals('Admin') }">
									<p class="text-danger">Admin</p>
									<img class="rounded-circle img-thumbnail" width="66" height="66" alt="Admin" src="<c:url value="/resources/images/adminAvatar.png"/>">
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${usersOnlineList.contains(thread.user) }">
									<br/><br/><div class="rounded-circle" style="width: 15px; height: 15px; background-color: #198754;"></div>
									Online
								</c:when>
								<c:otherwise>
									<br/><br/><div class="rounded-circle" style="width: 15px; height: 15px; background-color: #dc3545;"></div>
									Offline
								</c:otherwise>
							</c:choose>
						</div>
					</div>
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
		
			<c:forEach items="${comments.content }" var="c">
				<div class="container pb-3 border-bottom border-dark">
					<div class="row">
						<div class="col-10">
							<c:if test="${c.dateCreated != null }">
								<div class="border-bottom" align="right">
									<c:choose>
										<c:when test="${c.dateCreated.minute > 10 }">
											<small class="text-muted">${c.dateCreated.dayOfMonth }/${c.dateCreated.monthValue }/${c.dateCreated.year }&nbsp;${c.dateCreated.hour }:${c.dateCreated.minute }</small>
										</c:when>
										<c:otherwise>
											<small class="text-muted">${c.dateCreated.dayOfMonth }/${c.dateCreated.monthValue }/${c.dateCreated.year }&nbsp;${c.dateCreated.hour }:0${c.dateCreated.minute }</small>
										</c:otherwise>
									</c:choose>
								</div><br/><br/>
							</c:if>
							<p id="comment-${c.id }" style="white-space: pre-line;">${c.body }</p>
							<div id="cmntDiv-${c.id }" ></div>
						</div>
						<div class="col-2 pb-3 border rounded bg-light" align="center">
							<security:authorize access="#c.user.username == authentication.name" var="ownComment"/>
							<a class="link-dark text-decoration-none" href="<c:url value="/user/${c.user.username }"/>">${c.user.username }
								<c:if test="${ownComment }">(you)</c:if>
							</a>
							<c:choose>
								<c:when test="${c.user.role.equals('User')}">
									<p class="text-secondary">User</p>
									<img class="rounded-circle img-thumbnail" width="66" height="66" alt="User" src="<c:url value="/resources/images/userAvatar.png"/>">
								</c:when>
								<c:when test="${c.user.role.equals('Admin') }">
									<p class="text-danger">Admin</p>
									<img class="rounded-circle img-thumbnail" width="66" height="66" alt="Admin" src="<c:url value="/resources/images/adminAvatar.png"/>">
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${usersOnlineList.contains(c.user) }">
									<br/><br/><div class="rounded-circle" style="width: 15px; height: 15px; background-color: #198754;"></div>
									Online
								</c:when>
								<c:otherwise>
									<br/><br/><div class="rounded-circle" style="width: 15px; height: 15px; background-color: #dc3545;"></div>
									Offline
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<security:authorize access="#c.user.username == authentication.name or hasAuthority('ADMIN')">
						<a class="link-danger text-decoration-none" href="javascript:void 0;" 
						onclick="deleteComment('<c:url value="/sub/${thread.sub.url }/thread/comment/delete?id=${thread.id }&commentId=${c.id }"/>', '${_csrf.token }')">
						Delete
						</a>&nbsp;
						<a class="link text-decoration-none" id="editHref-${c.id }" href="javascript:void 0;" 
						onclick="editForm('${c.id}', '${_csrf.token }')">Edit</a>
					</security:authorize>
					</div><br/>
			</c:forEach>
		</div>
		<div class="pb-3" align="center">
			<c:if test="${comments.totalPages > 1}">
				<c:forEach begin="1" end="${comments.totalPages }" var="p">
					<c:choose>
						<c:when test="${comments.pageable.pageNumber + 1 != p }">
							<a class="btn btn-sm btn-outline-primary" href="<c:url value="/sub/${sub.url }/thread/view?id=${thread .id}&paging.page=${p }&paging.size=${comments.size }"/>">${p }</a>
						</c:when>
						<c:otherwise>
							<a class="btn btn-sm btn-primary" href="javascript:void 0;">${p }</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
		</div>
	</div>
</template:main>