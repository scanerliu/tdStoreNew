function searchList(typeId,o){
	var url = basePath + "/mobile/product/list/search";
	var loadData = {"typeId":typeId,"orderby":o};
	
	$("#results").load(url,loadData);
}

function searchNew(o){
	var url = basePath + "/mobile/product/new/search";
	var loadData = {"orderby":o};
	
	$("#results").load(url,loadData);
}

//查询积分兑换
function searchPointProducts(f){
	var url = basePath+"/mobile/product/point/search";
	var loadData = "";
	$(window).off("scroll", scrollHandler);
	var fliter = $("#sc_fliterType").val();
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
//查询积分兑换 分页
var scrollHandler = function(){
    if ($(document).height() - $(this).scrollTop() - $(this).height()<100){
    	searchPointProducts(false);
    } 
};