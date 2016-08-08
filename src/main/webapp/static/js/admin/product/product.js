function searchProduct(t){
	var url = basePath + "/admin/product/search";
	var loadData = null;
	
	if(t){
		loadData = $("#searchform").serializeArray();;
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").load(url,loadData);
}

function fnGotoPageproducts(num){
	searchProduct(false);
}

function editProduct(id){
	var url = basePath + "/admin/product/edit";
	var loadData = {"id" : id};
	$("#rightform").load(url,loadData);
	showForm();
}

function showForm(){
	$("#rightlist").hide();
	$("#rightform").show();
}
function returnList(){
	$("#rightlist").show();
	$("#rightform").html("").hide();
}

function refreshList(){
	searchProduct(false);
}



// 提交新增、修改内容
function saveProduct(){
	var tableData = getTableData("skuAssemble");
	var tableDataJson = eval('('+tableData+')');
	console.log(tableDataJson);
	if(tableDataJson.tableHead=="" || tableDataJson.tableContent.length == 0){
		$.messager.alert('消息提醒','货品数据不能为空!');
		return;
	}
	var tablecontent = tableDataJson.tableContent;
	for(var i = 0; i < tablecontent.length; i ++){
		var trdata =  tablecontent[i].trData;
		console.log(trdata);
		var trid = tablecontent[i].trId;
		var specnum = trid.split('_').length;
		console.log("specnum:" + specnum);
		for(var j = specnum; j < trdata.length; j ++){
			if(trdata[j] == "" || trdata[j] == null){
				$.messager.alert('消息提醒','货品数据不能留空!');
				return;
			}
		}
		var hph = trdata[specnum];
		if(hph.indexOf("_") > -1 || hph.indexOf(",") > -1){
			alert("货品号不能含有英文逗号或下划线");
			return;
		}
		
		for(var j = specnum + 1; j < trdata.length-1; j ++){
			if(!price_partten.test(trdata[j])){
				$.messager.alert('消息提醒','货品价格格式错误!');
				return;
			}
		}
		if(!num_partten.test(trdata[trdata.length-1])){
			$.messager.alert('消息提醒','货品库存格式错误!');
			return;
		}
	}
	
	$("#formTableData").val(tableData);
	var f = $('#productForm').form('enableValidation').form('validate');
	if(f){
		$("#productForm").form("submit",{
			success : function(data){
				 var result = eval("("+data+")");
				  if(result.code==1){
					  $.messager.alert('消息提醒','商品保存成功。');
					  returnList();
					  refreshList();
				  }else{
					  $.messager.alert('消息提醒','商品保存失败!');
				  }
			}
		});
	}
}

function deleteProduct(id){
	$.messager.confirm('消息提醒', '删除后不可恢复，确定要继续删除吗?', function(r){
		if (r){
			var url = basePath+"/admin/product/delete";
			var loadData={"id":id};
			$.post(url,loadData,delProductTypeCallback,"text");
		}
	});
}

function delProductTypeCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','删除成功。');
		refreshList();
	}else{
		$.messager.alert('消息提醒','删除失败!');
	}
}

//有两位小数的正实数
var price_partten = /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
// 数字
var  num_partten =  /^[0-9]*$/;
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
 * @param optype 1-上架，2-下架，3-热门推荐，4-取消热门推荐，5-新品推荐，6-取消热门推荐，7-精品推荐，8-取消精品推荐，9-分类推荐，10-取消分类推荐
 */
function bacthOperProducts(optype){
	var oparr = [1,2,3,4,5,6,7,8,9,10];
	var ids = getCheckedProductIds();
	if(ids==""){
		$.messager.alert('消息提醒','请先勾选要操作的商品!');
		return ;
	}
	if($.inArray(optype, oparr)<0){
		$.messager.alert('消息提醒','操作不合规范!');
		return ;
	}
	var url = basePath+"/admin/product/batchoperproducts";
	var postData = {"type":optype,"productIds":ids};
	$.post(url,postData,batchOperCallback,"text");
}

/**
 * 批量操作返回
 * @param data
 */
function batchOperCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','操作成功。');
		refreshList();
	}else{
	  $.messager.alert('消息提醒','操作失败!');
	}
}


/**
 * 选择商品类型
 * 
 */
function changekind(k){
	
	switch (k) {
		case "1":
			hideSeckill()
			break;
		case "2":
			hideSeckill()
			break;
		case "3":
			hideSeckill()
			break;
		case "4":
			hideSeckill()
			break;
		case "5":
			showSeckill();
			break;
		case "6":
			showSeckill();
			break;
		case "7":
			hideSeckill()
			break;
		case "8":
			hideSeckill()
			break;
	default:
		break;
	}
}

// 显示开始、结束时间
function showSeckill(){
	$(".seckill").show();
}
// 隐藏开始、结束时间
function hideSeckill(){
	$(".seckill").hide();
}
