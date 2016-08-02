function searchAcountLog(f){
	var url = basePath+"/admin/report/user/acountLog/search";
	var loadData = "";
	if(f){
		loadData = $("#searchform").serializeArray();
	}else{
		loadData = $("#acountLogListForm").serializeArray();
	}
	$("#results").load(url,loadData);
}

function fnGotoPageAcountLog(num){
	searchAcountLog(false);
}
