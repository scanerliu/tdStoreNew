function searchProductSku(f){
	var url = basePath+"/admin/productPackage/skuSearch";
	var loadData = "";
	if(f){
		loadData = null;
	}else{
		loadData = $("#pageForm").serializeArray();
	}
	$("#skuListForm").load(url,loadData);
}

function fnGotoPageProductSku(num){
	searchProductSku(false);
}

function searchSkuByProductName(){
	var productName = $("#searchProductName").val();
	$("#productName").val(productName);
	searchProductSku(false);
	
}

function selectedSkus(){
	var skuIdStr = "";
	var idLabels = $("#selectedSkuDiv").find("label");
	for(var i = 0; i < idLabels.length; i ++){
		// 获取数据
		var skuId = $(idLabels[i]).attr("theId");
		var productName = $(idLabels[i]).attr("productName");
		var productImage = $(idLabels[i]).attr("productImage");
		var price = $(idLabels[i]).attr("price");
		var specStr = $(idLabels[i]).attr("specStr");
		var imgStr = 
		"<div style='float:left;margin-left:10px;' id='ppiDiv' onclick='removeSkuId(this)'>" +
			"<div id='productImage'>" + 
				"<img width='100px' height='100px' src='"+productImage+"'>" + 
			"</div>" + 
			"<div>" + 
				"商品名称：<label id='productName'>"+productName+"</label><br>" + 
				"销售价：<label id='price'>"+price+"</label><br>" + 
				"<input type='hidden' id='specifications' value='"+specStr+"'>" + 
			"</div>" + 
		"</div>";
		$("#skusTr").append(imgStr);
		console.log(imgStr);
	}
	$('#pskuswindow').window('close');
}

// 展示商品包的货品
function flushSkuShow(){
	removeSkuIdStrBlank();
	var skuIdStr = $("#skuIdStrInput").val();
	var loadData = {'skuIdStr': skuIdStr};
	var url = basePath+"/admin/productPackage/skuShow";
	$("#skusTr").load(url,loadData);
}

// 点击图片删除skuid
function removeSkuId(theSkuImg){
	var skuId = $(theSkuImg).attr("skuId");
	$(theSkuImg).remove();
	var skuIdStr = $("#skuIdStrInput").val();
	skuIdStr = skuIdStr.replace(skuId ,"");
	removeSkuIdStrBlank();
	$("#skuIdStrInput").val(skuIdStr);
}

// 去掉skuIdStrInput中的空字符串
function removeSkuIdStrBlank(){
	var skuIdStr = $("#skuIdStrInput").val();
	var skuIdArrayWithBlank =  skuIdStr.split(",");
	var str = "";
	for(var i = 0; i < skuIdArrayWithBlank.length; i ++){
		if(skuIdArrayWithBlank[i] != ""){
			str = str + skuIdArrayWithBlank[i];
			if(i < skuIdArrayWithBlank.length - 1){
				str = str + ",";
			}
		}
	}
	$("#skuIdStrInput").val(str);
}


