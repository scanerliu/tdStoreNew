//查询商品列表
function showitem(id){
	var url = basePath + "/site/item"+id;
	$("#results").loading().load(url,null);
}

function showitems(id){
	$("#sc_cid").val(id);
	searchArticles(true);
}

function searchArticles(flag){
	var url = basePath + "/site/search";
	var loadData = "";
	if(flag){
		loadData = $("#searchform").serializeArray();
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

//分页函数
function fnGotoPageArticles(num){
	searchArticles(false);
}