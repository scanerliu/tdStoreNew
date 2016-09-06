function searchDownUsers(f){
	var url = basePath + "/user/getDownUsers";
	var loadData = null;
	if(f){
		loadData = $("#searchForm").serializeArray();
	}else{
		loadData = $("#listForm").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

/**
 * tab选择项
 */
function selectTab(id){
	$("#"+id).on("click","a",function(){
		var oid = $(this).attr("aid");
		$(this).addClass("active").siblings().removeClass("active");
		$("#sc_clevelType").val(oid);
		searchDownUsers(true);
	});
}

//分页函数
function fnGotoPageDownUsers(num){
	searchDownUsers(false);
}
