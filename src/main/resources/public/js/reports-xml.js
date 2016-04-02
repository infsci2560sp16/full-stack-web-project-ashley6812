function selectReport () {
	$.ajax({
		url : "/reports/:report" + $( "#reportType" ).val(),
		type : "get",
		success : function(result) {
			$("#typeOfReport").html("");
			console.log(result);
			reports = result.getElementsByTagName("report");
			for ( var i = 0; i < reports.length; i++) {
				$("#typeOfReport").prepend(
						'<div class="row" id="' + reports[i].childNodes[0].firstChild.nodeValue + '">' +
							'<div class="col-md-7">' +
									'<reports[i].childNodes[6].firstChild.nodeValue>' +
							'</div>' +
							'<div class="col-md-5">' +
								'<h3>' + reports[i].childNodes[1].firstChild.nodeValue + ' ' + reports[i].childNodes[2].firstChild.nodeValue + ' ' + reports[i].childNodes[3].firstChild.nodeValue + '</h3>' +
								'<h4>$' + reports[i].childNodes[5].firstChild.nodeValue + '</h4>' +
								'<h4>' + reports[i].childNodes[4].firstChild.nodeValue + '</h4>' +
							'</div>' +
						'</div>' +
						'<hr>'
				);
			}
		}
	});
}