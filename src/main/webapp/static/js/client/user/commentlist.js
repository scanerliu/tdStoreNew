function searchComments(f){
	var url = basePath+"/user/comment/search";
	var loadData = "";
	if(f){
		loadData = $("#searchform").serializeArray();
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

//分页函数
function fnGotoPageComments(num){
	searchComments(false);
}