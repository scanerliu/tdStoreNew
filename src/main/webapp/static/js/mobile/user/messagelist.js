function searchMessages(f){
	var url = basePath+"/mobile/user/searchmessages";
	var loadData = "";
	$(window).off("scroll", scrollHandler);
	var fliter = $("#sc_fliterType").val();
	if(fliter==5){
		url = basePath+"/mobile/order/searchreturn";
	}
	if(f){
		loadData = $("#searchform").serializeArray();
		$("#results").loading().load(url,loadData);
	}else{
		loadData = $("#listform").serializeArray();
		$.get(url, loadData, function(html){
	  		  $(html).appendTo("#results");
	  	});
	}
}

var scrollHandler = function(){
    if ($(document).height() - $(this).scrollTop() - $(this).height()<100){
    	searchOrders(false);
    } 
};

