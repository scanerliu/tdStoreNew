function searchProduct(t){
	var url = basePath + "/admin/product/search";
	var loadData = null;
	
	if(t){
		loadData = null;
	}else{
		loadData = $("#productTypeform").serializeArray();
	}
	$("#results").load(url,loadData);
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
	$.messager.confirm('消息提醒', '删除后讲不可恢复，确定要继续删除吗?', function(r){
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
var price_partten = /^[0-9]+(.[0-9]{2})?$/;
// 数字
var  num_partten =  /^[0-9]*$/;
