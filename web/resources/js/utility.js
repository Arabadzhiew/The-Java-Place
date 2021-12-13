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
		document.getElementById("editHref-"+id).innerHTML = "Back";
	}
}

function commentForm(id, csrfToken){
	
	if(document.getElementById("commentForm") != null){
		var form = document.getElementById("commentForm");
		form.parentElement.removeChild(form)
		document.getElementById("commentHref").innerHTML = "Comment";
	}else{
		var form = document.createElement("form");
		form.setAttribute("id", "commentForm");
		form.setAttribute("method", "post");
		form.setAttribute("action", "/tjp/sub/faq/thread/view?id="+id);
		
		var br = document.createElement("br");
		var comment = document.createElement("textarea");
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
