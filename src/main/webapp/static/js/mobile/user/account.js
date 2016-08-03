function searchAccountLogs(f){
	var url = basePath + "/mobile/user/searchaccount";
	var loadData = $("#searchForm").serializeArray();
	$(window).off("scroll", scrollHandler);
	if(f){
		$("#results").loading().load(url,loadData);
	}else{
		$.get(url, loadData, function(html){
	  		  $(html).appendTo("#results");
	  	});
	}
}

var scrollHandler = function(){
    if ($(document).height() - $(this).scrollTop() - $(this).height()<100){
    	searchAccountLogs(false);
    } 
};

