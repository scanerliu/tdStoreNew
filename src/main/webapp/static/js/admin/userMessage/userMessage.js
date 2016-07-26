function searchUserMessage(f){
	var url = basePath+"/admin/message/search";
	var loadData = "";
	if(f){
		loadData = $("#searchform").serializeArray();
	}else{
		loadData = $("#userMessageForm").serializeArray();
	}
	$("#results").load(url,loadData);
}

function fnGotoPageUserMessage(num){
	searchUserMessage(false);
}

function refreshList(){
	searchUserMessage(false);
}

function returnList(){
	$("#rightlist").show();
	$("#rightform").html("").hide();
}

function showForm(){
	$("#rightlist").hide();
	$("#rightform").show();
}
 

function delUserMessage(id){
	$.messager.confirm('消息提醒', '确定要删掉消息吗?', function(r){
		if (r){
			var url = basePath+"/admin/message/delete";
			var loadData={"idStr":id};
			$.post(url,loadData,commonCallback,"text");
		}
	});
}

function batchDelete(){
	var ids = $("input[name='subbox']:checked");
	if(ids.length == 0){
		$.messager.alert('消息提醒','请选择要删除的消息。');
		return;
	}else{
		var idStr = "";
		for(var i = 0; i < ids.length; i ++){
			idStr += $(ids[i]).val() + ",";
		}
		delUserMessage(idStr);
	}
}

function editUserMessage(num){
	var url = basePath+"/admin/message/edit";
	var loadData={"id":num};
	$("#rightform").load(url,loadData);
	showForm();
}

function saveCampaign(){
	// 设置百度编辑器的内容到#cc中
	var content = UE.getEditor('campaignContent').getContent();
	$("#cc").html(content);
	var f = $('#campaignEditForm').form('enableValidation').form('validate');
	if(f){
		$('#campaignEditForm').form('submit',{
			  success : function(data){
				  var result = eval("("+data+")");
				  if(result.code==1){
					  $.messager.alert('消息提醒',result.msg);
					  returnList();
					  refreshList();
				  }else{
					  $.messager.alert('消息提醒',result.msg);
				  }
			  }
		});
	};
}

function commonCallback(data){
	var result = eval("("+data+")");
	$.messager.alert('消息提醒',result.msg);
	if(result.code==1){
		refreshList();
	}
}