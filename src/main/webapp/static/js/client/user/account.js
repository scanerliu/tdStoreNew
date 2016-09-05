function searchAccountLogs(f){
	var url = basePath + "/user/searchaccount";
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
		if(oid==0){
			$("#sc_incomeType").val("");
		}else if(oid==1){
			$("#sc_incomeType").val("1");
		}else if(oid==2){
			$("#sc_incomeType").val("2");
		}
		searchAccountLogs(true);
	});
}

//分页函数
function fnGotoPageAccountLogs(num){
	searchAccountLogs(false);
}

function searchProfits(f){
	var url = basePath + "/user/searchprofit";
	var loadData = $("#searchForm").serializeArray();
	if(f){
		loadData = $("#searchForm").serializeArray();
	}else{
		loadData = $("#listForm").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

//分页函数
function fnGotoPageProfits(num){
	searchProfits(false);
}
/**
 * tab选择项
 */
function selectProfitTab(id){
	$("#"+id).on("click","a",function(){
		var oid = $(this).attr("aid");
		$(this).addClass("active").siblings().removeClass("active");
		if(oid==0){
			$("#sc_fliterType").val("");
		}else if(oid==1){
			$("#sc_fliterType").val("1");
		}else if(oid==2){
			$("#sc_fliterType").val("2");
		}
		searchProfits(true);
	});
}