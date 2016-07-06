function searchManagers(f){
	var url = basePath+"/admin/manager/search";
	var loadData = "";
	if(f){
		loadData = null;
	}else{
		loadData = $("#managerlistform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

function fnGotoPageManagers(num){
	searchManagers(false);
}

function editManager(num){
	var url = basePath+"/admin/manager/edit";
	var loadData={"id":num};
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
	searchManagers(false);
}
function saveManager(){
	var f = $('#managerForm').form('enableValidation').form('validate');
	if(f){
		$('#managerForm').form('submit',{
			  success : function(data){
				  var result = eval("("+data+")");
				  if(result.code==1){
					  $.messager.alert('消息提醒','管理员保存成功。');
					  returnList();
					  refreshList();
				  }else{
					  $.messager.alert('消息提醒','管理员保存失败!');
				  }
			  }
			 });
	};
}

function delManager(id){
	$.messager.confirm('消息提醒', '确定要删掉该管理员吗?', function(r){
		if (r){
			var url = basePath+"/admin/manager/delete";
			var loadData={"id":id};
			$.post(url,loadData,delManagerCallback,"text");
		}
	});
}

function delManagerCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','管理员删除成功。');
		refreshList();
	}else{
	  $.messager.alert('消息提醒','管理员删除失败!');
	}
}

function editRoles(id){
	var url = basePath+"/admin/manager/roles";
	var loadData={"id":id};
	$("#rightform").loading().load(url,loadData);
	showForm();
}

function saveRoles(){
	var url = basePath+"/admin/manager/saverole";
	var nodes = $("#roleForm input[name='subbox']:checked");
	var s = '';
	$.each(nodes, function(){
		var rid = $(this).val();
		if(rid>0){
			if (s != '') {s += ',';}
			s += rid;
		}
	});
	var uId = $("#roleForm #uId").val();
	if(uId==""||uId==undefined){
		$.messager.alert('消息提醒','管理员未选择，请重新操作!');
		return ;
	}
	var postData = {"uid":uId,"roleIds":s};
	$.post(url,postData,saveRolesCallback,"text");
}

function saveRolesCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','角色保存成功。');
	}else{
	  $.messager.alert('消息提醒','角色保存失败!');
	}
}

function changePassword(id){
	var url = basePath+"/admin/manager/changepassword";
	var loadData={"id":id};
	$("#rightform").loading().load(url,loadData);
	showForm();
}

function savePassword(id){
	var f = $('#passwordForm').form('enableValidation').form('validate');
	if(f){
		$('#passwordForm').form('submit',{
			  success : function(data){
				  var result = eval("("+data+")");
				  if(result.code==1){
					  $.messager.alert('消息提醒','密码修改成功。');
					  returnList();
				  }else{
					  $.messager.alert('消息提醒','密码修改失败!');
				  }
			  }
			 });
	};
}

/**
 * 启用账号
 */
function activeManager(id){
	var url = basePath+"/admin/manager/updatestatus";
	if(id==""||id==undefined){
		$.messager.alert('消息提醒','管理员未选择，请重新操作!');
		return ;
	}
	var postData = {"uid":id,"ustatus":1};
	$.post(url,postData,updateCallback,"text");
}
/**
 * 禁用账号
 */
function forbiddenManager(id){
	var url = basePath+"/admin/manager/updatestatus";
	if(id==""||id==undefined){
		$.messager.alert('消息提醒','管理员未选择，请重新操作!');
		return ;
	}
	var postData = {"uid":id,"ustatus":2};
	$.post(url,postData,updateCallback,"text");
}

function updateCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','更新成功。');
		refreshList();
	}else{
	  $.messager.alert('消息提醒','更新失败!');
	}
}

