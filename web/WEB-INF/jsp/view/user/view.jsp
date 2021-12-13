<template:user>
	<div class="row pt-3">
		<ul class="nav nav-tabs">
			<li class="nav-item">
				<a class="nav-link" href="<c:url value="/user/${user.username }/threads"/>">Threads</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="<c:url value="/user/${user.username }/comments"/>">Comments</a>
			</li>
		</ul>
	</div>
</template:user>