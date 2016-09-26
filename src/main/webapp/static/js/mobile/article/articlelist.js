function searchArticles(f){
	var url = basePath+"/mobile/article/search";
	var loadData = "";
	$(window).off("scroll", scrollHandler);
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
    	searchArticles(false);
    } 
};

