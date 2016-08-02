var __agentpackage = "agentpackage";
function searchPackage(f){
	var url = basePath + "/mobile/package/search";
	var loadData = $("#searchForm").serializeArray();
	$(window).off("scroll", scrollHandler);
	if(f){
		$("#results").loading().load(url,loadData);
	}else{
		$.get(url, loadData, function(html){
	  		  $(html).appendTo("#results");
	  	});
	}
}

var scrollHandler = function(){
    if ($(document).height() - $(this).scrollTop() - $(this).height()<100){
    	searchPackage(false);
    } 
};

//立即购买
function buyNow(){
	var productId = $("#productId").val();
	var quantity = $("#prodquantity").val();
	var itemType = $("#productKind").val();
	var agentform = getCookie("agentpackage");
	var agent = decodeURIComponent(agentform);
	var orderform = eval("("+agent+")");
	
	if(agentform==""||agentform.length<10 || orderform.agentProductId==undefined){
		alert("请先选择代理产品！");
		return false;
	}
	
	if(productId>0){
		//赋值
		$("#agentProductId").val(orderform.agentProductId);
		$("#productTypeId").val(orderform.productTypeId);
		$("#regionId").val(orderform.regionId);
		var url = basePath+"/mobile/shoppingcart/buynow";
		$("#agentform").attr("action",url);
		$("#agentform").submit();
//		var postData = {"productId":productId,"quantity":quantity,"productType":itemType};
//		window.location.href= url+"?productId="+productId+"&productType="+itemType+"&quantity="+quantity;
	}else{
		alert("请先选择商品！");
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
    
    $.ajax({
        type:"post",
        url:basePath+"/mobile/product/collect",
        data:{"productId": productId},
        dataType: "json",
        success:function(data){
            // 需登录
            if (data.code==0)
            {
                alert(data.msg);
                setTimeout(function(){
                    window.location.href = basePath+"/mobile/login";
                }, 1000); 
            }
        }
    });
}

