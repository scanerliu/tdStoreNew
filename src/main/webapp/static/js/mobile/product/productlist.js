function searchList(o){
	var url = basePath + "/mobile/product/search";
	var loadData = {"orderby":o};
	
	$("#results").load(url,loadData);
}

function searchProducts(f){
	$(window).off("scroll", scrollHandler);
	var url = basePath + "/mobile/product/searchmore";
	if(f){
		loadData = $("#searchform").serializeArray();
		$("#results").loading().load(url,loadData);
	}else{
		loadData = $("#listform").serializeArray();
		$.get(url, loadData, function(html){
	  		  $(html).appendTo("#product_more");
	  	});
	}
}

//查询积分兑换 分页
var scrollHandler = function(){
    if ($(document).height() - $(this).scrollTop()<750){
    	searchProducts(false);
    } 
};