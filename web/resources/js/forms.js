var postInvisibleForm = function(url, csrfToken) {
	var form = $('<form method="post"></form>')
		.attr({action: url});
	form.append($('<input type="hidden">').attr({
		name: '_csrf', value: csrfToken
    }));
	$('body').append(form);
    form.submit();
};
        	
var deleteThread = function(url, id, csrfToken){
	if(confirm("Are you sure you want to delete thread with Id = " + id)){
		postInvisibleForm(url, csrfToken);
     }
};