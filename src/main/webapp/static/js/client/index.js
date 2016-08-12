//关键字搜索
function searchItems(){
	$("#keywordform").submit();
}
//热门词搜索
function searchhotword(word){
	$("#keywords").val(word);
	$("#keywordform").submit();
}
//获取商品类型  m 获取类型：1-普通商品，2-商品包，3-零元购，4-10元购，5-预售，6-秒杀商品7-全积分兑换，8-部分积分兑换
function searchProductType(m){
	var url = basePath + "/product/searchproducttype";
	var loadData = {"sctype":m};
	$("#productTypeList").loading().load(url,loadData);
}
//获取购物车商品个数
function getshoppingcartcount(){
	var url = basePath + "/shoppingcart/cartcount";
	$.post(url,null,function(data){
		var result = eval("("+data+")");
		if(result.code==1){
			$("#shoppingcartcount").html(result.cartcount);
		}
	},'text');
	
}
//获取系统消息个数
function getsystemmessagecount(){
	var url = basePath + "/user/messagecount";
	$.post(url,null,function(data){
		var result = eval("("+data+")");
		if(result.code==1){
			$("#smessagecount").html(result.count);
		}
	},'text');
	
}
//猜你喜欢商品查询
function getenjoyproducts(){
	$("#enjoybtn").off("click");
	var url = basePath + "/product/enjoysearch";
	var loadData = $("#enjoyForm").serializeArray();
	$("#enjoyList").loading().load(url,loadData);
}
