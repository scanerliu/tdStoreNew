/**
 * 购物车商品数量增减操作
 * @param id
 * @param type
 */
function additem(id,type){
	var num = $("#quantity_"+id).val();
	var stock = $("#stock_"+id).val();
	var stockint = parseInt(stock);
	var numint = parseInt(num);
	if(type==1){//add
		if(numint==stockint){
			return;
		}else if(numint>stockint){
			numint = stockint;
		}else{
			numint = numint+1;
		}
	}else if(type==2){//sub
		if(numint==1){
			return;
		}
		numint = numint-1;
	}
	$("#quantity_"+id).val(numint);
	var url = basePath+"/shoppingcart/add";
	var postData = {"id":id,"optype":type};
	openwaiting();
	$.post(url,postData,additemCallback,"text");
}

function additemCallback(data){
	closewaiting();
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
	var itemid = $(obj).attr("itemid");
	var numint = parseInt(num);
	var stock = $("#stock_"+itemid).val();
	var stockint = parseInt(stock);
	if(numint>stockint){
		numint = stockint;
	}else if(numint<1){
		numint = 1;
	}
	$(obj).val(numint);
	var url = basePath+"/shoppingcart/change";
	var postData = {"id":itemid,"quantity":numint};
	openwaiting();
	$.post(url,postData,changeitemCallback,"text");
}

function changeitemCallback(data){
	closewaiting();
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
function removeItems(ids){
	/*var elements = $("#product_goods input[name='ids']:checked");
	var ids = "";
	elements.each(function(i,o){
		if(i==0){
			ids = $(this).val();
		}else{
			ids = ids +","+$(this).val();
		}
	});*/
	if(ids==""||ids==0){
		alert("请选择要移除的商品！");
		return ;
	}
	if(confirm("确定要移除商品？")){
		var url = basePath+"/shoppingcart/remove?ids="+ids;
		var postData = {"ids":ids};
		openwaiting();
		$.post(url,postData,removeItemsCallback,"text");
	}
	
}

function refreshList(){
	window.location.reload();
}

function removeItemsCallback(data){
	closewaiting();
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
	var url = basePath+"/shoppingcart/confirmorder";
	window.location.href=url;
}
//下订单
function genernateOrder(){
	var addressId = $("#addressId").val();
	var needShipment = $("#needShipment").val();
	if(addressId==""&& needShipment=="true"){
		alert("请填写收货地址！");
		return ;
	}
	openwaiting();
	var url = basePath+"/shoppingcart/order";
	$("#confirmorder").attr("action",url);
	$("#confirmorder").submit();
}
//立即下单->下订单
function genernateOrder2(){
	var url = basePath+"/shoppingcart/singleorder";
	var addressId = $("#addressId").val();
	var needShipment = $("#needShipment").val();
	if(addressId==""&& needShipment=="true"){
		alert("请填写收货地址！");
		return ;
	}
	openwaiting();
	$("#confirmorder").attr("action",url);
	$("#confirmorder").submit();
}
