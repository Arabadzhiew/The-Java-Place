<template:main title="Create Thread">
	<div class="px-5">
		<a class="link-primary text-decoration-none" 
		href="<c:url value="/sub/${sub.url }"/>"><c:out value="${sub.name }"/></a><br/><br/>
		<c:choose>
			<c:when test="${action == 'create' }">
				<h2>Create Thread</h2>
			</c:when>
			<c:otherwise>
				<h2>Edit Thread</h2>
			</c:otherwise>
		</c:choose>
		
		<form:form metohd="post" modelAttribute="threadForm"><br/>
			<form:input class="form-control" path="title" placeholder="Title"/><br/>
			<form:errors path="title" cssClass="errors"/><br/><br/>
			<form:textarea class="form-control" path="body" placeholder="Text (optional)" rows="10"/><br/>
			<form:errors path="body" cssClass="errors"/><br/><br/>
			<c:choose>
				<c:when test="${action == 'create' }">
				<input class="btn btn-outline-primary" type="submit" value="Create"/><br/>
				</c:when>
					<c:otherwise>
						<input class="btn btn-outline-primary" type="submit" value="Edit"/><br/>
					</c:otherwise>
			</c:choose>
			</form:form><br/>
	</div>
</template:main>