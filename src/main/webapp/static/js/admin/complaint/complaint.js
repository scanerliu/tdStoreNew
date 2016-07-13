function searchcomplaint(t){
	var url = basePath + "/admin/complaint/search";
	var loadData = null;
	
	if(t){
		loadData = null;
	}else{
		loadData = $("#complaintform").serializeArray();
	}
	$("#results").load(url,loadData);
}

function editComplaint(id){
	var url = basePath + "/admin/complaint/edit";
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
	searchcomplaint(false);
}

// 提交新增、修改广告为内容
function saveAttribute(){
	var f = $('#attributeForm').form('enableValidation').form('validate');
	if(f){
		$("#attributeForm").form("submit",{
			success : function(data){
				 var result = eval("("+data+")");
				  if(result.code==1){
					  $.messager.alert('消息提醒','修改成功。');
					  returnList();
					  refreshList();
				  }else{
					  $.messager.alert('消息提醒','修改失败!');
				  }
			}
		});
		
	}
}

function delComplaint(id){
	$.messager.confirm('消息提醒', '确定要继续删除吗?', function(r){
		if (r){
			var url = basePath+"/admin/complaint/delete";
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
