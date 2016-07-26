function searchProductPackage(f){
	var url = basePath+"/admin/productPackage/search";
	var loadData = "";
	if(f){
		loadData = null;
	}else{
		loadData = $("#productPacakgeListForm").serializeArray();
	}
	$("#results").load(url,loadData);
}

function fnGotoPageProductPackage(num){
	searchProductPackage(false);
}

function editProductPackage(num){
	var url = basePath+"/admin/productPackage/edit";
	var loadData={"id":num};
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
	searchProductPackage(false);
}
function saveProductPackage(){
	var f = $('#productPackageForm').form('enableValidation').form('validate');
	if(f){
		$('#expressForm').form('submit',{
			  success : function(data){
				  var result = eval("("+data+")");
				  if(result.code==1){
					  $.messager.alert('消息提醒','快递公司保存成功。');
					  returnList();
					  refreshList();
				  }else{
					  $.messager.alert('消息提醒','快递公司保存失败!');
				  }
			  }
			 });
	};
}

function delProductPackage(id){
	$.messager.confirm('消息提醒', '确定要删掉该商品包吗?', function(r){
		if (r){
			var url = basePath+"/admin/productPackage/delete";
			var loadData={"id":id};
			$.post(url,loadData,commonCallback,"text");
		}
	});
}

function commonCallback(data){
	var result = eval("("+data+")");
	var msg = result.msg;
	$.messager.alert('消息提醒',msg);
	if(result.code==1){
		refreshList();
	}
}

function batchDelete(){
	var ids = $("input[name='subbox']:checked");
	if(ids.length == 0){
		$.messager.alert('消息提醒','请选择要删除的商品包。');
		return;
	}else{
		var idStr = "";
		for(var i = 0; i < ids.length; i ++){
			idStr += $(ids[i]).val() + ",";
		}
		var url = basePath+"/admin/productPackage/batchDelete";
		var loadData = {"idStr":idStr};
		$.post(url,loadData,commonCallback,"text");
		refreshList();
	}
}













function editPermissions(id){
	var url = basePath+"/admin/role/permissions";
	var loadData={"roleId":id};
	$("#rightform").load(url,loadData);
	showForm();
}

function savePermissions(){
	var url = basePath+"/admin/role/savepermission";
	var nodes = $('#permissionForm #menuTree').tree('getChecked');
	var s = '';
	for(var i=0; i<nodes.length; i++){
		if(nodes[i].id>0){
			if (s != '') {s += ',';}
			s += nodes[i].id;
		}
	}
	var roleId = $("#permissionForm #roleId").val();
	if(roleId==""||roleId==undefined){
		$.messager.alert('消息提醒','角色未选择，请重新操作!');
		return ;
	}
	var postData = {"id":roleId,"menuIds":s};
	$.post(url,postData,savePermissionsCallback,"text");
}

function savePermissionsCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','权限保存成功。');
	}else{
	  $.messager.alert('消息提醒','权限保存失败!');
	}
}

