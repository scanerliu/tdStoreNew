var __integralexchangerate = 1000;

/**
 * 获取推荐项目
 * dvid:load id
 * type: 查询类型 1-热门推荐，2-分类推荐
 * size: 查询数量
 */
function getrecommendproducts(dvid,type,size){
	var url = basePath + "/product/recommendsearch";
	var loadData = "";
	if(type==1){//热门推荐
		loadData = {"hotRecommend":1,"pageSize":size};		
	}else if(type==2){//相关推荐
		loadData = {"typeRecommend":1,"pageSize":size};
	}
	$("#"+dvid).loading().load(url,loadData);
}
//获取商品详情
function getdescribe(pid){
	var url = basePath + "/product/describe/"+pid;
	$("#detailcontent").loading().load(url,null);
}

//获取商品详情
function searchComments(flag){
	var url = basePath + "/product/searchcomments";
	var loadData = "";
	if(flag){//热门推荐
		loadData = $("#searchcommentform").serializeArray();;		
	}else{//相关推荐
		loadData = $("#commentlistform").serializeArray();
	}
	$("#commentList").loading().load(url,loadData);
}


//分页函数
function fnGotoPageComments(num){
	searchComments(false);
}

/**
 * 规格选择货品操作
 * @param type
 */
function changeProductSku(skus){
	var selectUL = $("#slect .slect");
	var idkey = "";
	var selected = true;
	selectUL.each(function(){
		var sected = $(this).find("span[class='checked']");
		if(sected.length==0){
			selected = false;
			return false;
		}else{
			idkey = idkey+"_"+sected.text();
		}
	});
	if(idkey.length>0){
		idkey = idkey.substring(1);
	}
	if(!selected){
		return;
	}
	var idkeys = idkey.split("_");
	idkeys.sort();
	var skuList = skus.skuList;
	var exist = false;
	var price = 0;
	var stock = 0;
	var skuId = 0;
	var marketprice = 0;
	var kind = $("#productKind").val();
	$.each(skuList,function(i,o){
		var specifica = o.specificationids.split("_");
		specifica.sort();
		if(specifica.toString()==idkeys.toString()){
			exist = true;
			if(kind==7){
				price = o.salesPrice * __integralexchangerate;
			}else{
				price = o.salesPrice;
			}
			stock = o.stock;
			marketprice = o.marketPrice;
			skuId = o.id;
			return false;
		}
	});
	if(exist){
		$("#skustock").val(stock);
		$("#s_skustock").html(stock);
		$("#skuId").val(skuId);
		$("#skuPrice").val(price);
		$("#prodprice").html(price);
		$("#marketprice").html("￥"+marketprice);
	}else{
		alert("货品已下架！");
		window.location.reload();
	}
	initprodchecked();
}

function initprodchecked(){
//	var selectUL = $("#slect .slect");
//	var idkey = "";
//	var selected = true;
//	selectUL.each(function(){
//		var sected = $(this).find("span[class='checked']");
//		if(sected.length==0){
//		}else{
//			idkey = idkey+"<span>"+sected.text()+"</span>";
//		}
//	});
//	var num = $("#prodquantity").val();
//	idkey = idkey + "<span>"+num+"件</span>";
//	$("#prodchecked").html(idkey);
}

/**
 * 购买商品数量增减操作
 * @param type
 */
function additem(type){
	var selectUL = $("#slect .slect");
	var selected = true;
	selectUL.each(function(){
		var sected = $(this).find("span[class='checked']");
		if(sected.length==0){
			selected = false;
			return;
		}
	});
	if(!selected){
		alert("请先选择商品规格！");
		return;
	}
	var num = $("#prodquantity").val();
	var numint = parseInt(num);
	var stock = $("#skustock").val();
	var stockint = parseInt(stock);
	if(type==1){//add
		numint = numint+1;
		if(numint>stockint){
			alert("商品库存不足，添加失败！");
			return ;
		}
	}else if(type==2){//sub
		numint = numint-1;
		if(numint<1){
			numint=1;
		}
	}
	$("#prodquantity").val(numint);
	initprodchecked();
}

/**
 * 格式输入
 * @param obj
 */
function formatInputSkuNum(obj){
	var num = obj.value.replace(/\D/g,'');
	obj.value = num;
	var selectUL = $("#slect .slect");
	var selected = true;
	selectUL.each(function(){
		var sected = $(this).find("span[class='checked']");
		if(sected.length==0){
			selected = false;
			return;
		}
	});
	if(!selected){
		obj.value = 1;
		alert("请先选择商品规格！");
		return;
	}
	var stock = $("#skustock").val();
	var stockint = parseInt(stock);
	var num = obj.value.replace(/\D/g,'');
	if(num<1){
		obj.value=min;
	}else if(num>stockint){
		obj.value=stockint;
	}else{
		obj.value = num;
	}
	initprodchecked();
}

/**
 * 加入购物车操作
 */
function addToShoppingcart(){
	var skuId = $("#skuId").val();
	var skuPrice = $("#skuPrice").val();
	var productId = $("#productId").val();
	var postage = $("#propostage").val();
	var quantity = $("#prodquantity").val();
	var itemType = $("#productKind").val();
	var skustock = $("#skustock").val();
	if(Number(skuId)>0){
		if(Number(skustock)>=Number(quantity)){
			var url = basePath+"/shoppingcart/addsku";
			var postData = {"productId":productId,"productSkuId":skuId,"price":skuPrice,"postage":postage,"quantity":quantity,"itemType":itemType};
			openwaiting();
			$.post(url,postData,addToShoppingcartCallback,"text");
		}else{
			alert("商品库存不足！");
		}
	}else{
		alert("请先选择商品规格！");
	}
	
}

function addToShoppingcartCallback(data){
	closewaiting();
	var result = eval("("+data+")");
	if(result.code==1){
		alert('加入购物车成功。');
	}else{
		alert('加入购物车失败!');
	}
}
//立即购买
function buyNow(){
	var skuId = $("#skuId").val();
	var skuPrice = $("#skuPrice").val();
	var productId = $("#productId").val();
	var postage = $("#propostage").val();
	var quantity = $("#prodquantity").val();
	var itemType = $("#productKind").val();
	var skustock = $("#skustock").val();
	if(skuId>0){
		if(Number(skustock)>=Number(quantity)){
			var url = basePath+"/shoppingcart/buynow";
			var postData = {"productId":productId,"productSkuId":skuId,"quantity":quantity,"productType":itemType};
			window.location.href= url+"?productId="+productId+"&productSkuId="+skuId+"&quantity="+quantity;
		}else{
			alert("商品库存不足！");
		}
	}else{
		alert("请先选择商品规格！");
	}
}


/**
 * 点击收藏
 */
function addCollect(productId)
{
    if (undefined == productId)
    {
        return;
    }
    openwaiting();
    $.ajax({
        type:"post",
        url:basePath+"/product/collect",
        data:{"productId": productId},
        dataType: "json",
        success:function(data){
            // 需登录
        	closewaiting();
            if (data.code==0)
            {
                alert(data.msg);
            }
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
			// 关闭等待图标
			closewaiting();
			alert("收藏失败");
		},
    });
}

