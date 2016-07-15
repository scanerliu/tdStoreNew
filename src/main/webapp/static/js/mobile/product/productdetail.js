

/**
 * 购物车商品数量增减操作
 * @param id
 * @param type
 */
function additem(id,type){
	var num = $("#quantity_"+id).val();
	var numint = parseInt(num);
	if(type==1){//add
		numint = numint+1;
		if(numint>9999){
			$("#quantity_"+id).val(numint);
			return ;
		}
	}else if(type==2){//sub
		numint = numint-1;
		if(numint<1){
			$("#quantity_"+id).val(1);
			return;
		}
	}
	$("#quantity_"+id).val(numint);
	var url = basePath+"/mobile/shoppingcart/add";
	var postData = {"id":id,"optype":type};
	$.post(url,postData,additemCallback,"text");
}

function additemCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$("#totalAmount").html(result.totalAmount);
		$("#totalPostage").html(result.totalPostage);
		
	}else{
		alert('商品操作失败!');
	}
}

/**
 * 购物车商品数量更改操作
 * @param id
 * @param type
 */
function changeitem(obj){
	var num = $(obj).val();
	var numint = parseInt(num);
	if(numint>9999){
		numint = 9999;
	}else if(numint<1){
		numint = 1;
	}
	$(obj).val(numint);
	var itemid = $(obj).attr("itemid");
	var url = basePath+"/mobile/shoppingcart/change";
	var postData = {"id":itemid,"quantity":numint};
	$.post(url,postData,changeitemCallback,"text");
}

function changeitemCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$("#totalAmount").html(result.totalAmount);
		$("#totalPostage").html(result.totalPostage);
		
	}else{
		alert('商品操作失败!');
	}
}

/**
 * 购物车商品移除操作
 */
function removeItems(){
	var elements = $("#product_goods input[name='ids']:checked");
	var ids = "";
	elements.each(function(i,o){
		if(i==0){
			ids = $(this).val();
		}else{
			ids = ids +","+$(this).val();
		}
	});
	if(ids==""){
		alert("请选择要移除的商品！");
		return ;
	}
	if(confirm("确定要移除商品？")){
		var url = basePath+"/mobile/shoppingcart/remove?ids="+ids;
		var postData = {"ids":ids};
		$.post(url,postData,removeItemsCallback,"text");
	}
	
}

function refreshList(){
	window.location.reload();
}

function removeItemsCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		alert('商品删除成功。');
		refreshList();
	}else{
		alert('商品删除失败!');
	}
}
//去下单
function nextOrder(){
	var url = basePath+"/mobile/shoppingcart/confirmorder";
	alert("稍等一下，功能开发中...");
}
