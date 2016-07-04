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


