//查询商品列表
function searchProducts(flag){
	var url = basePath + "/user/searchmyproduct";
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
	var url = basePath + "/user/editproduct";
	var loadData = {"id":id};
	$("#formdiv").loading().load(url,loadData);
	showForm();
}

function saveProduct(){
	
}
function showForm(){
	$("#formdiv").show();
	$("#listdiv").hide();
}
function returnList(){
	$("#formdiv").hide();
	$("#listdiv").show();
}
