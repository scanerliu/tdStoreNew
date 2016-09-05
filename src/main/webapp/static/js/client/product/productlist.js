//查询商品列表
function searchProducts(flag){
	var url = basePath + "/product/search";
	var loadData = "";
	if(flag){
		loadData = $("#searchform").serializeArray();
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

//分页函数
function fnGotoPageProducts(num){
	searchProducts(false);
}


//查询积分兑换
function searchPointProducts(f){
	var url = basePath+"/product/point/search";
	var loadData = "";
	if(f){
		loadData = $("#searchform").serializeArray();
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

//分页函数
function fnGotoPagePointProducts(num){
	searchPointProducts(false);
}