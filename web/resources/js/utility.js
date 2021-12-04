function editForm(id, text, csrfToken){
	
	if(document.getElementById("comment-"+id).style.display == "none"){
		var form = document.getElementById("commentEditForm");
		form.parentElement.removeChild(form)
		document.getElementById("comment-"+id).style.display = "block";
		document.getElementById("editHref-"+id).innerHTML = "Edit";
	
	}else{
		document.getElementById("comment-"+id).style.display = "none";
		var form = document.createElement("form");
		form.setAttribute("id", "commentEditForm");
		form.setAttribute("method", "post");
		form.setAttribute("action", "/tjp/sub/faq/thread/comment/edit?id="+id);
		
		var br = document.createElement("br");
		var comment = document.createElement("textarea");
		comment.setAttribute("id", "body");
		comment.setAttribute("name", "body");
		comment.setAttribute("rows", 10);
		comment.setAttribute("cols", 50);
		comment.append(text);
		var submit = document.createElement("input");
		submit.setAttribute("type", "submit");
		submit.setAttribute("value", "Edit");
		var csrf = document.createElement("input");
		csrf.setAttribute("type", "hidden");
		csrf.setAttribute("name", "_csrf");
		csrf.setAttribute("value", csrfToken);
	
		form.appendChild(comment);
		form.appendChild(br.cloneNode(false));
		form.appendChild(submit);
		form.appendChild(csrf);
		
		document.getElementById("cmntDiv-"+id).appendChild(form);
		document.getElementById("editHref-"+id).innerHTML = "Back";
	}
	
}