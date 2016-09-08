function searchMessages(f){
	var url = basePath+"/user/searchmessages";
	var loadData = "";
	if(f){
		loadData = $("#searchform").serializeArray();
		$("#results").loading().load(url,loadData);
	}else{
		loadData = $("#searchform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

//分页函数
function fnGotoPageMessages(num){
	searchMessages(false);
}

/**
 * tab选择项
 */
function selectTab(id){
	$("#"+id).on("click","a",function(){
		var oid = $(this).attr("aid");
		$(this).addClass("active").siblings().removeClass("active");
		$("#sc_status").val(oid);
		searchMessages(true);
	});
}
/**
 * menu选择项
 */
function selectMenu(id){
	$("#"+id).on("click","a",function(){
		var oid = $(this).attr("aid");
		$(this).parent().addClass("active").siblings().removeClass("active");
		$("#sc_fliterType").val(oid);
		searchMessages(true);
	});
}
