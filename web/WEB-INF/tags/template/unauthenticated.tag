<%@ tag body-content="scriptless" %>
<%@ attribute name ="title" type="java.lang.String" rtexprvalue="true" required="true" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Log In</title>
		<link rel="shortcut icon" href="<c:url value="/favicon.ico"/>"/>
		<link rel="stylesheet" href="<c:url value="/resources/css/login.css"/>">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<script src="<c:url value="/resources/js/signupAjax.js"/>"></script>
		
	</head>
	<body class="container bg-light bg-gradient p-5">
		<div class="row px-5">
			<div class="col-lg p-5">
				<img alt="The Java Place" src="<c:url value="/resources/images/tjpLogo.png"/>"
				width="200" height="110"/>
				<h2 class="display-4">The place for everything Java related</h2>
			</div>
			<div class="col-lg p-5">
				<jsp:doBody/>
			</div>
		</div>
	</body>
</html>