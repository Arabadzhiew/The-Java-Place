<%@ tag body-content="scriptless" %>
<%@ attribute  name="page" type="org.springframework.data.domain.Page" rtexprvalue="true" required="true" %>
<%@ attribute  name="url" type="java.lang.String" rtexprvalue="true" required="true" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>

<c:if test="${page.totalPages > 1 }">
	<c:forEach begin="1" end="${page.totalPages }" var="p">
		<c:choose>
			<c:when test="${page.pageable.pageNumber + 1 != p }">
				<a class="btn btn-outline-primary btn-sm" 
				href="<c:url value="${url }">
					<c:param name="paging.page" value="${p }"/>
					<c:param name="paging.size" value="${page.size }"/>
				</c:url>">
					${p }
				</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-primary btn-sm" href="javascript:void 0;">${p }</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</c:if>