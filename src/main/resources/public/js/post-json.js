function userNames () {
	var obj = JSON.stringify({"Name" : $( "#Name" ).val()});
	$.ajax({
		contentType:'application/json',
		url : "/all-users",
		type : "post",
		dataType: "json",
		data : obj,
		success : function(users) {
			console.log(users);
			$("#all-users").html("");
			for ( var i = 0; i < users.length; i++) {
				$("#all-users").prepend(
					"<p>" + users[i].name + "</p>" + "<p>" + users[i].role + "</p>" + "<p>" + users[i].email + "</p>"
				);
			}
		}
	});
}