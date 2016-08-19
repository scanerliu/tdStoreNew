//查询商品列表
var __skuinex = 1;
function searchProducts(flag){
	var url = basePath + "/user/searchsupplierproduct";
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
/**
 * tab选择项
 */
function selectTab(id){
	$("#"+id).on("click","a",function(){
		var oid = $(this).attr("aid");
		$(this).addClass("active").siblings().removeClass("active");
		if(oid==0){
			$("#sc_onshelf").val("");
		}else if(oid==1){
			$("#sc_onshelf").val("true");
		}else if(oid==2){
			$("#sc_onshelf").val("false");
		}
		searchProducts(true);
	});
}
/**
 * 商品编辑
 */
function editProduct(id){
	var url = basePath + "/user/editproductprice";
	var loadData = {"id":id};
	$("#formdiv").loading().load(url,loadData);
	showForm();
}

function showForm(){
	$("#formdiv").show();
	$("#listdiv").hide();
}
function returnList(){
	$("#formdiv").hide();
	$("#listdiv").show();
}
function refreshList(){
	searchProducts(false);
}
/**
 * 获取选择的商品id字符串，如：1,2,3,5
 */
function getCheckedProductIds(){
	var nodes = $("#listform input[name='subbox']:checked");
	var s = '';
	$.each(nodes, function(){
		var rid = $(this).val();
		if(rid>0){
			if (s != '') {s += ',';}
			s += rid;
		}
	});
	return s;
}
/**
 * 批量操作商品
 * @param optype 1-上架，2-下架
 */
function bacthOperProducts(optype){
	var oparr = [1,2];
	var ids = getCheckedProductIds();
	if(ids==""){
		alert('请先勾选要操作的商品!');
		return ;
	}
	if($.inArray(optype, oparr)<0){
		alert('操作不合规范!');
		return ;
	}
	var op = "";
	if(optype==1){
		op = "上架";
	}else if(optype==2){
		op = "下架";
	}
	if(confirm("确定要"+op+"选择的商品？")){
		var url = basePath+"/user/batchoperproducts";
		var postData = {"type":optype,"ids":ids};
		openwaiting();
		$.post(url,postData,batchOperCallback,"text");
	}
}

/**
 * 批量操作返回
 * @param data
 */
function batchOperCallback(data){
	closewaiting();
	var result = eval("("+data+")");
	if(result.code==1){
		alert('操作成功。');
		refreshList();
	}else{
	  alert('操作失败!');
	}
}
