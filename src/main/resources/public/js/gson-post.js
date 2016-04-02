$(function() {
    $.ajax({
        url : "/assets",
        type : "get",
        success : function(result) {
            var assets = JSON.parse(result);
            console.log(result);
            for ( var i = 0; i < assets.length; i++) {
                $("#all-assets").prepend(

  "<tr>" +
    "<td>" + assets[i].assetName + "</td>"
   + "<td>" + assets[i].type + "</td>" 
   + "<td>" + assets[i].serialNumber + "</td>" 
   +"</tr>" +
  "<tr>"

                );
            }
        }
    });
});