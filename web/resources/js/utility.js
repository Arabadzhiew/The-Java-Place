function editForm(id, csrfToken){
	
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
		comment.setAttribute("class", "form-control");
		comment.setAttribute("id", "body");
		comment.setAttribute("name", "body");
		comment.setAttribute("rows", 10);
		comment.setAttribute("cols", 50);
		comment.append(document.getElementById("comment-"+id).innerText);
		var submit = document.createElement("input");
		submit.setAttribute("type", "submit");
		submit.setAttribute("value", "Edit");
		submit.setAttribute("class", "btn btn-outline-primary");
		var csrf = document.createElement("input");
		csrf.setAttribute("type", "hidden");
		csrf.setAttribute("name", "_csrf");
		csrf.setAttribute("value", csrfToken);
	
		form.appendChild(comment);
		form.appendChild(br.cloneNode(false));
		form.appendChild(submit);
		form.appendChild(csrf);
		
		document.getElementById("cmntDiv-"+id).appendChild(form);
		document.getElementById("cmntDiv-"+id).appendChild(form.appendChild(br.cloneNode(false)));
		document.getElementById("editHref-"+id).innerHTML = "Back";
	}
}

function commentForm(id, csrfToken){
	
	if(document.getElementById("commentForm") != null){
		var form = document.getElementById("commentForm");
		form.parentElement.removeChild(form);
		document.getElementById("commentHref").innerHTML = "Comment";
	}else{
		var form = document.createElement("form");
		form.setAttribute("id", "commentForm");
		form.setAttribute("method", "post");
		form.setAttribute("action", "/tjp/sub/faq/thread/view?id="+id);
		
		var br = document.createElement("br");
		var comment = document.createElement("textarea");
		comment.setAttribute("class", "form-control");
		comment.setAttribute("id", "body");
		comment.setAttribute("name", "body");
		comment.setAttribute("rows", 10);
		comment.setAttribute("cols", 50);
		var submit = document.createElement("input");
		submit.setAttribute("type", "submit");
		submit.setAttribute("value", "Comment");
		submit.setAttribute("class", "btn btn-outline-primary");
		var csrf = document.createElement("input");
		csrf.setAttribute("type", "hidden");
		csrf.setAttribute("name", "_csrf");
		csrf.setAttribute("value", csrfToken);
	
		form.appendChild(comment);
		form.appendChild(br.cloneNode(false));
		form.appendChild(submit);
		form.appendChild(csrf);
		
		document.getElementById("commentDiv").appendChild(form);
		document.getElementById("commentHref").innerHTML = "Back";
	}
}

function profileImgForm(username, csrfToken){
	if(document.getElementById("profileImgForm") != null){
		var form = document.getElementById("profileImgForm");
		form.parentNode.removeChild(form);
		document.getElementById("profileImgHref").innerHTML = "Change profile image";
		
	}else{
		var form = 	document.createElement("form");
		form.setAttribute("id", "profileImgForm");
		form.setAttribute("method","post");
		form.setAttribute("action","/tjp/user/"+username);
		form.setAttribute("enctype", "multipart/form-data");
		
		var br = document.createElement("br");
		
		var label = document.createElement("label");
		label.setAttribute("for", "image");
		label.setAttribute("class", "form-label");
		label.innerHTML = "Change profile image"
	
		var image = document.createElement("input");
		image.setAttribute("class", "form-control form-control-sm");
		image.setAttribute("type", "file");
		image.setAttribute("name", "image");
		image.setAttribute("id", "image");
		image.setAttribute("accept",".jpeg, .jpg, .png");
	
		var submit = document.createElement("input");
		submit.setAttribute("class", "btn btn-outline-success btn-sm");
		submit.setAttribute("type", "submit");
		submit.setAttribute("value", "Upload");
	
		var csrf = document.createElement("input");
		csrf.setAttribute("type", "hidden");
		csrf.setAttribute("name", "_csrf");
		csrf.setAttribute("value", csrfToken);
		
		form.appendChild(label);
		form.appendChild(image);
		form.appendChild(br.cloneNode(false));
		form.appendChild(submit);
		form.appendChild(csrf);
		form.appendChild(br.cloneNode(false));
		form.appendChild(br.cloneNode(false));
		form.appendChild(br.cloneNode(false));
	
		document.getElementById("changeImageDiv").appendChild(form);
		document.getElementById("profileImgHref").innerHTML = "Hide";
	}
}

function getTime(){
	var date = new Date();
	
	return format(date.getHours())+":"+format(date.getMinutes())+":"+format(date.getSeconds());
}

function setServerTime(){
	document.getElementById("serverTime").innerText = getTime();
	setTimeout(setServerTime, 1000);
}

function format(timeValue){
	if(timeValue < 10){
		return "0"+timeValue;
	}else{
		return ""+timeValue;
	}
}

function lastActive(lastActive){
	if(lastActive == 0){
		document.getElementById("lastActive").innerHTML = "Last active: today";
	}else if(lastActive == 1){
		document.getElementById("lastActive").innerHTML = "Last active: 1 day ago";
	}else{
		document.getElementById("lastActive").innerHTML = "Last active: " + lastActive +  " days ago";
	}
}
