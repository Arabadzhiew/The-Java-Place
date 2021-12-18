<template:main title="${query }">
	<div class="p-5">
		<c:choose>
			<c:when test="${hasResults }">
				<div class="container">
					<c:forEach items="${results.content }" var="t">
						<div class="row py-3 border-bottom">
							<div class="col-10">
								<a class="text-decoration-none" href="<c:url value="/sub/${t.sub.url }/thread/view?id=${t.id }"/>">
									${t.title }
								</a>
								<p style="white-space: pre-line;">
									<c:choose>
										<c:when test="${t.body.length() > 100 }">
											${t.body.substring(0, 96).concat('...') }
										</c:when>
										<c:otherwise>
											${t.body }
										</c:otherwise>
									</c:choose>
								</p>
							</div>
							<div class="col-2 border-left">
								<small>
									Posted by:
									<a 
										<c:choose>
											<c:when test="${t.user.role.equals('User') }">
												class="text-decoration-none text-secondary" 
											</c:when>
											<c:when test="${t.user.role.equals('Admin') }">
												class="text-decoration-none text-danger"
											</c:when>
										</c:choose>
									href="<c:url value="/user/${t.user.username }"/>">
										${t.user.username }
									</a>
								</small><br/>
								<small>
									on ${t.dateCreated.dayOfMonth }&nbsp;<format:month value="${t.dateCreated.monthValue }"/>&nbsp;${t.dateCreated.year }
								</small><br/>
								<small>
									Sub:
									<a class="text-decoration-none" href="<c:url value="/sub/${t.sub.url }"/>">
										${t.sub.name }
									</a>
								</small><br/>
								<small>
									Comments: ${t.comments.size() }
								</small>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:when>
			<c:otherwise>
				<i>There are no results for your search.</i>
			</c:otherwise>
		</c:choose>
		<c:if test="${results.totalPages > 1 }">
			<div class="row pt-3">
				<div align="center">
					<c:forEach begin="1" end="${results.totalPages }" var="p">
						<c:choose>
							<c:when test="${results.pageable.pageNumber + 1 != p }">
								<a class="btn btn-outline-primary btn-sm" href="<c:url value="/search/threads?query=${query }&paging.page=${p }&paging.size=${results.size }"/>">${p }</a>
							</c:when>
							<c:otherwise>
								<a class="btn btn-primary btn-sm" href="javascript:void 0;">${p }</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div>
		</c:if>
	</div>
</template:main>