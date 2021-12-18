<%@ tag body-content="scriptless"%>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="value" type="java.lang.Integer" rtexprvalue="true" required="true"%>
<%@ include file="/WEB-INF/jsp/base.jspf" %>


<c:choose>
	<c:when test="${value == 1 }">January</c:when>
	<c:when test="${value == 2 }">February</c:when>
	<c:when test="${value == 3 }">March</c:when>
	<c:when test="${value == 4 }">April</c:when>
	<c:when test="${value == 5 }">May</c:when>
	<c:when test="${value == 6 }">June</c:when>
	<c:when test="${value == 7 }">July</c:when>
	<c:when test="${value == 8 }">August</c:when>
	<c:when test="${value == 9 }">September</c:when>
	<c:when test="${value == 10 }">October</c:when>
	<c:when test="${value == 11 }">November</c:when>
	<c:when test="${value == 12 }">December</c:when>
</c:choose>
