<%@ tag body-content="scriptless" %>
<%@ attribute name ="title" type="java.lang.String" rtexprvalue="true" required="true" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>

<!DOCTYPE html>
<html>
	<head>
		<title><c:out value="${title }"/></title>
		<link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>
		<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
		<script src="<c:url value="/resources/js/forms.js"/>"></script>
        	
	</head>
	<body>
		<a href="javascript:void 0;" onclick="postInvisibleForm('<c:url value="/logout"/>', '${_csrf.token }');">
			Log Out
		</a>
		<jsp:doBody/>
	</body>
</html>
