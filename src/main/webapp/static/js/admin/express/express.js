function searchExpresses(f){
	var url = basePath+"/admin/express/search";
	var loadData = "";
	if(f){
		loadData = null;
	}else{
		loadData = $("#expresslistform").serializeArray();
	}
	$("#results").load(url,loadData);
}

function fnGotoPageExpresses(num){
	searchExpresses(false);
}

function editExpress(num){
	var url = basePath+"/admin/express/edit";
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
	searchExpresses(false);
}
function saveExpress(){
	var f = $('#expressForm').form('enableValidation').form('validate');
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

function delExpress(id){
	$.messager.confirm('消息提醒', '确定要删掉该快递公司吗?', function(r){
		if (r){
			var url = basePath+"/admin/express/delete";
			var loadData={"id":id};
			$.post(url,loadData,expressCallback,"text");
		}
	});
}

function expressCallback(data){
	var result = eval("("+data+")");
	var msg = result.msg;
	if(result.code==1){
		$.messager.alert('消息提醒',msg);
		refreshList();
	}else{
	  $.messager.alert('消息提醒',msg);
	}
}

function batchDelete(){
	var ids = $("input[name='subbox']:checked");
	if(ids.length == 0){
		$.messager.alert('消息提醒','请选择要删除的快递公司。');
		return;
	}else{
		var idStr = "";
		for(var i = 0; i < ids.length; i ++){
			idStr += $(ids[i]).val() + ",";
		}
		// 判断是否有下级
		var url = basePath+"/admin/express/batchDelete";
		var loadData = {"idStr":idStr};
		$.post(url,loadData,expressCallback,"text");
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

