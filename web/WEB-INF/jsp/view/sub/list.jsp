<!DOCTYPE html>
<html>
	<head>
		<title>Home Page</title>
		<style type="text/css">
			table,
			tr{
				border: 1px solid black;
				border-collapse: collapse;
			}
			td, th{
				border: 1px solid black;
				padding: 10px;
			}
		</style>
		<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
		<script type="text/javascript" lang="javascript">
        	var logout = function(url) {
            	var form = $('<form method="post"></form>')
            		.attr({action: url});
            	form.append($('<input type="hidden">').attr({
            		name: '${_csrf.parameterName}', value: '${_csrf.token}'
            	}));
            	$('body').append(form);
            	form.submit();
        	};
		</script>
	</head>
	
	<body>
		<div align="center">
			<h1>Subs</h1>
			<table>
				<tr>
					<th>Forum Sub</th>
					<th>Threads</th>
					<th>Comments</th>
					<th>Last Active</th>
				</tr>
				<c:forEach items="${subs }" var = "s">
					<tr>
						<td>
							<a href = "<c:url value="/sub/${s.url }"/>"><c:out value="${s.name }"/></a>
						</td>
						<td><c:out value="${s.totalThreads }"></c:out></td>
						<td><c:out value="${s.totalComments }"></c:out></td>
						<td>
							<c:choose>
								<c:when test="${s.lastActiveThread != null}">
									<a href="<c:url value="/sub/${s.url }/thread/view?id=
									${s.lastActiveThread.id}"/>"><c:out value="${s.lastActiveThread.title }"/></a>
								</c:when>
								<c:otherwise>
									<a href="<c:url value="/sub/${s.url}/thread/create"/>">New Sub :: Create the first thread</a>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
			<a href="javascript:void 0;" onclick="logout('<c:url value="/logout"/>');">Log out</a>
		</div>
	</body>
</html>