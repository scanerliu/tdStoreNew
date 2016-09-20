//查询商品列表
function showitem(id){
	$("#hmenu a").removeClass("on");
	$("#hmenu a[aid="+id+"]").addClass("on");
	var url = basePath + "/help/item"+id;
	$("#results").loading().load(url,null);
}
