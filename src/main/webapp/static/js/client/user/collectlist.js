function searchCollects(f){
	var url = basePath+"/user/collect/search";
	var loadData = "";
	if(f){
		loadData = $("#searchform").serializeArray();
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

//分页函数
function fnGotoPageCollects(num){
	searchCollects(false);
}