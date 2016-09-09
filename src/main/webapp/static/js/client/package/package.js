var __agentpackage = "agentpackage";
function searchPackage(f){
	var url = basePath + "/package/search";
	var loadData = null;
	if(f){
		loadData = $("#searchform").serializeArray();
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}
//零元购分页函数
function fnGotoPagePackages(num){
	searchPackage(false);
}
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
		var url = basePath+"/shoppingcart/buynow";
		$("#agentform").attr("action",url);
		$("#agentform").submit();
//		var postData = {"productId":productId,"quantity":quantity,"productType":itemType};
//		window.location.href= url+"?productId="+productId+"&productType="+itemType+"&quantity="+quantity;
	}else{
		alert("请先选择商品！");
	}
}



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
	var url = basePath + "/package/describe/"+pid;
	$("#detailcontent").loading().load(url,null);
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

