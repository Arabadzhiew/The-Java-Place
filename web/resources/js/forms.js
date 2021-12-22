var postInvisibleForm = function(url, csrfToken) {
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", url);
	
	var csrf = document.createElement("input");
	csrf.setAttribute("type", "hidden");
	csrf.setAttribute("name", "_csrf");
	csrf.setAttribute("value", csrfToken)
	
	form.appendChild(csrf);
	document.body.appendChild(form);
	form.submit();
};
        	
var deleteThread = function(url, id, csrfToken){
	if(confirm("Are you sure you want to delete thread with Id = " + id)){
		postInvisibleForm(url, csrfToken);
     }
};

var deleteComment = function(url, csrfToken){
	if(confirm("Are you sure you want to delete this comment")){
		postInvisibleForm(url, csrfToken);
	}
}

function deleteProfileImage(url, username, csrfToken){
	if(confirm("Are you sure you want to delete " + username + " profile image?")){
		postInvisibleForm(url, csrfToken);
	}
}