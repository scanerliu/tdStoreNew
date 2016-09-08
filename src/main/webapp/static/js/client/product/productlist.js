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


//查询零元购商品列表
function searchZeroProducts(flag){
	var url = basePath + "/product/zerosearch";
	var loadData = "";
	if(flag){
		loadData = $("#searchform").serializeArray();
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

//零元购分页函数
function fnGotoPageZeroProducts(num){
	searchZeroProducts(false);
}
//查询新品商品列表
function searchNewProducts(flag){
	var url = basePath + "/product/newsearch";
	var loadData = "";
	if(flag){
		loadData = $("#searchform").serializeArray();
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

//零元购分页函数
function fnGotoPageNewProducts(num){
	searchNewProducts(false);
}
//查询秒杀商品列表
function searchKillProducts(flag){
	var url = basePath + "/product/killsearch";
	var loadData = "";
	if(flag){
		loadData = $("#searchform").serializeArray();
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

//查询秒杀分页函数
function fnGotoPageKillProducts(num){
	searchKillProducts(false);
}

/**
 * 查询秒杀 tab选择项
 */
function selectkillTab(id){
	$("#"+id).on("click","a",function(){
		var oid = $(this).attr("aid");
		$(this).addClass("active").siblings().removeClass("active");
		$("#sc_fliterType").val(oid);
		searchKillProducts(true);
	});
}