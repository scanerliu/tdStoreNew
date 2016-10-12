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


function searchProfits(f){
	var url = basePath + "/mobile/user/searchprofit";
	var loadData = $("#searchForm").serializeArray();
	$(window).off("scroll", scrollProfitHandler);
	if(f){
		$("#results").loading().load(url,loadData);
	}else{
		$.get(url, loadData, function(html){
	  		  $(html).appendTo("#results");
	  	});
	}
}

var scrollProfitHandler = function(){
    if ($(document).height() - $(this).scrollTop() - $(this).height()<100){
    	searchProfits(false);
    } 
};

function searchDrawApplys(f){
	var url = basePath + "/mobile/user/searchdrawapply";
	var loadData = $("#searchForm").serializeArray();
	$(window).off("scroll", scrollDrawApplyHandler);
	if(f){
		$("#results").loading().load(url,loadData);
	}else{
		$.get(url, loadData, function(html){
			$(html).appendTo("#results");
		});
	}
}

var scrollDrawApplyHandler = function(){
	if ($(document).height() - $(this).scrollTop() - $(this).height()<100){
		searchDrawApplys(false);
	} 
};


