function searchcomment(t){
	var url = basePath + "/admin/productcomment/search";
	var loadData = null;
	
	if(t){
		loadData = null;
	}else{
		loadData = $("#commentform").serializeArray();
	}
	$("#results").load(url,loadData);
}

function editComment(id){
	var url = basePath + "/admin/productcomment/edit";
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
	searchcomment(false);
}

// 提交新增、修改内容
function saveComment(){
	var f = $('#commentForm').form('enableValidation').form('validate');
	if(f){
		$("#commentForm").form("submit",{
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
			var url = basePath+"/admin/productcomment/delete";
			var loadData={"id":id};
			$.post(url,loadData,delCommentCallback,"text");
		}
	});
}

function delCommentCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','删除成功。');
		refreshList();
	}else{
		$.messager.alert('消息提醒','删除失败!');
	}
}
