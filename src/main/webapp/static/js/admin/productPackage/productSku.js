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
		var skuId = $(idLabels[i]).attr("theId");
		skuIdStr = skuIdStr + skuId;
		if(i < idLabels.length - 1){
			skuIdStr = skuIdStr + ",";
		}
	}
	var str = $("#skuIdStrInput").val();
	str = str + "," + skuIdStr
	$("#skuIdStrInput").val(str);
	$('#pskuswindow').window('close');
	flushSkuShow();
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


