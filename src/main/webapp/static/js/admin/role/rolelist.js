function searchRoles(f){
	var url = basePath+"/admin/role/search";
	var loadData = "";
	if(f){
		loadData = null;
	}else{
		loadData = $("#rolelistform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

function fnGotoPageRoles(num){
	searchRoles(false);
}

function editRole(num){
	var url = basePath+"/admin/role/edit";
	var loadData={"roleId":num};
	$("#rightform").loading().load(url,loadData);
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
	searchRoles(false);
}
function saveRole(){
	var f = $('#roleForm').form('enableValidation').form('validate');
	if(f){
		$('#roleForm').form('submit',{
			  success : function(data){
				  var result = eval("("+data+")");
				  if(result.code==1){
					  $.messager.alert('消息提醒','角色保存成功。');
					  returnList();
					  refreshList();
				  }else{
					  $.messager.alert('消息提醒','角色保存失败!');
				  }
			  }
			 });
	};
}

function delRole(id){
	$.messager.confirm('消息提醒', '确定要删掉该角色吗?', function(r){
		if (r){
			var url = basePath+"/admin/role/delete";
			var loadData={"id":id};
			$.post(url,loadData,delRoleCallback,"text");
		}
	});
}

function delRoleCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','角色删除成功。');
		refreshList();
	}else{
	  $.messager.alert('消息提醒','角色删除失败!');
	}
}

function editPermissions(id){
	var url = basePath+"/admin/role/permissions";
	var loadData={"roleId":id};
	$("#rightform").loading().load(url,loadData);
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

