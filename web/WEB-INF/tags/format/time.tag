<%@ tag body-content="scriptless"%>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="value" type="java.lang.Integer" rtexprvalue="true" required="true"%>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<c:choose>
	<c:when test="${value>=10}">${value}</c:when>
	<c:otherwise>0${value}</c:otherwise>
</c:choose>