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
	var nodes = $('#roleForm #roleTree').tree('getChecked');
	var s = '';
	for(var i=0; i<nodes.length; i++){
		if(nodes[i].id>0){
			if (s != '') {s += ',';}
			s += nodes[i].id;
		}
	}
	var uId = $("#roleForm #uId").val();
	if(uId==""||uId==undefined){
		$.messager.alert('消息提醒','管理员未选择，请重新操作!');
		return ;
	}
	var postData = {"id":uId,"roleIds":s};
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

