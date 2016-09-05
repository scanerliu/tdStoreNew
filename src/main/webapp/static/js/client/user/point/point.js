function searchPointLogs(f){
	var url = basePath + "/user/point/search";
	var loadData = null;
	if(f){
		loadData = $("#searchForm").serializeArray();
	}else{
		loadData = $("#listForm").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

//分页函数
function fnGotoPagePointLogs(num){
	searchPointLogs(false);
}

/**
 * tab选择项
 */
function selectTab(id){
	$("#"+id).on("click","a",function(){
		var oid = $(this).attr("aid");
		$(this).addClass("active").siblings().removeClass("active");
		if(oid==0){
			$("#sc_incomeType").val("");
		}else if(oid==1){
			$("#sc_incomeType").val("1");
		}else if(oid==2){
			$("#sc_incomeType").val("2");
		}
		searchPointLogs(true);
	});
}