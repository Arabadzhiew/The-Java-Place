<%@ tag body-content="scriptless" %>
<%@ attribute name ="title" type="java.lang.String" rtexprvalue="true" required="true" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>

<!DOCTYPE html>
<html>
	<head>
		<title><c:out value="${title }"/></title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<script src="<c:url value="/resources/js/forms.js"/>"></script>
		<script src="<c:url value="/resources/js/utility.js"/>"></script>
        	
	</head>
	<body class="bg-light bg-gradient" onload="setServerTime();">
		<div class="d-flex p-2 justify-content-between align-items-baseline">
			<div class="d-flex flex-column">
				<div class="d-flex justify-content-between">
					<div class="text-muted" id="serverTime"></div>
					<div class="text-muted">Online: ${usersOnline }/${userCount }</div>
				</div>
				<a href="<c:url value="/"/>">
					<img alt="The Java Place" src="<c:url value="/resources/images/tjpLogo.png"/>"
					width="200" height="110"/>
				</a>
			</div>
			<a href="javascript:void 0;" class="link-danger" onclick="postInvisibleForm('<c:url value="/logout"/>', '${_csrf.token }');">
			Log Out
			</a>
		</div>
		<jsp:doBody/>
	</body>
</html>
