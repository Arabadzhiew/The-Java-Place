<%@ tag body-content="scriptless" %>
<%@ attribute name ="title" type="java.lang.String" rtexprvalue="true" required="true" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>

<!DOCTYPE html>
<html>
	<head>
		<title><c:out value="${title }"/></title>
		<link rel="shortcut icon" href="<c:url value="/favicon.ico"/>"/>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<style type="text/css">
			#userHref{
				color: black;
				text-decoration: none;
			}
			#userHref:hover{
				color: #520011;
				text-decoration: underline;
			}
		</style>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
		<script src="<c:url value="/resources/js/forms.js"/>"></script>
		<script src="<c:url value="/resources/js/utility.js"/>"></script>
        	
	</head>
	<body class="bg-light bg-gradient" onload="setServerTime();">
		<div class="d-flex p-2 justify-content-between align-items-baseline">
			<div class="d-flex flex-column align-self-start">
				<div class="d-flex justify-content-between">
					<div class="text-muted" id="serverTime"></div>
					<div class="text-muted">Online: ${usersOnline }/${userCount }</div>
				</div>
				<a href="<c:url value="/"/>">
					<img alt="The Java Place" src="<c:url value="/resources/images/tjpLogo.png"/>"
					width="200" height="110"/>
				</a>
			</div>
			<div class="d-flex flex-column flex-fill align-self-start px-5">
				<form:form class="form-inline" modelAttribute="searchForm" method="get" action ="/tjp/search/threads">
					<form:input class="form-control" path="query" placeholder="Search for threads"/>
				</form:form>
			</div>
			<security:authorize var="authenticated" access="isAuthenticated()"></security:authorize>
			<c:choose>
				<c:when test="${authenticated}">
					<div class="d-flex flex-column align-self-start px-3">
						<div class="btn-group">
							<a class="btn btn-outline-dark" href="<c:url value="/user/${username }"/>">${username }</a>
							<button class="btn btn-outline-dark dropdown-toggle dropdown-toggle-split"
							data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							</button>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="<c:url value="/user/${username }/threads"/>">Your threads</a>
								<a class="dropdown-item" href="<c:url value="/user/${username }/comments"/>">Your comments</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item text-danger" href="javascript:void 0;" onclick="postInvisibleForm('<c:url value="/logout"/>', '${_csrf.token }');">Log out</a>
							</div>
						</div>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="<c:url value="/user/${username }/threads"/>">Your threads</a>
							<a class="dropdown-item" href="<c:url value="/user/${username }/comments"/>">Your comments</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item text-danger" href="javascript:void 0;" onclick="postInvisibleForm('<c:url value="/logout"/>', '${_csrf.token }');">Log out</a>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="d-flex flex-column align-self-start pl-3" style="margin: 0 15px 0 0;">
						<a class="btn btn-outline-primary" href="<c:url value="/login"/>">Log In</a>
					</div>
					<div class="d-flex flex-column align-self-start pr-3" style="margin: 0 15px 0 0;">
						<a class="btn btn-primary" href="<c:url value="/signup"/>">Sign Up</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div><br/>
		<jsp:doBody/>
		<template:footer/>
	</body>
</html>
