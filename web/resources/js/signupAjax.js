var xmlHttp = createXmlHttpRequestObject();
var timeout = 100;

function createXmlHttpRequestObject(){
	var xmlHttp;
	
	if(window.ActiveXObject){
		try{
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}catch(e){
			xmlHttp = false;
		}
	}else{
		try{
			xmlHttp = new XMLHttpRequest();
		}catch(e){
			xmlHttp = false;
		}
	}
	
	if(!xmlHttp){
		alert("Something went wrong while instantiating XHR.");
	}else{
		return xmlHttp;
	}
}

function process(){
	if(xmlHttp.readyState == 0 || xmlHttp.readyState == 4){
		username = document.getElementById("username").value;
		if(username.length < 3 || !username.trim()){
			if(document.getElementById("userExists").innerHTML.length != 0){
				document.getElementById("submit").disabled = true;
				document.getElementById("userExists").innerHTML = "";
			}
			setTimeout("process()", timeout);
		}else if(username.includes(" ")){
			document.getElementById("userExists").setAttribute("class", "text-danger");
			document.getElementById("submit").disabled = true;
			document.getElementById("userExists").innerHTML = "The username cannot contain spaces";
			setTimeout("process()", timeout);
		}else{
			username = document.getElementById("username").value;
			xmlHttp.open("GET", "/tjp/user/exists?username="+username, true);
			xmlHttp.onreadystatechange = handleServerResponse();
			xmlHttp.send(null);
		}	
	}else{
		setTimeout("process()", timeout);
	}
}

function handleServerResponse(){
	if(xmlHttp.readyState == 4){
		if(xmlHttp.status == 200){
			xmlResponse = xmlHttp.responseText;
			if(xmlResponse == "false"){
				document.getElementById("submit").disabled = false;
				document.getElementById("userExists").setAttribute("class", "text-success");
				document.getElementById("userExists").innerHTML = "This username is free";
			}else if(xmlResponse == "true"){
				document.getElementById("submit").disabled = true;
				document.getElementById("userExists").setAttribute("class", "text-danger");
				document.getElementById("userExists").innerHTML = "This username is already taken";
			}
			setTimeout("process()", timeout);
		}else{
			alert(xmlHttp.status);
		}
	}else{
		setTimeout("handleServerResponse()", timeout);
	}
}