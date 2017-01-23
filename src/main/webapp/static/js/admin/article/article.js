function searchArticle(f){
	var url = basePath+"/admin/article/search";
	var loadData = "";
	if(f){
		loadData = $("#searchform").serializeArray();
	}else{
		loadData = $("#articlelistform").serializeArray();
	}
	$("#results").load(url,loadData);
}

function fnGotoPageArticle(num){
	searchArticle(false);
}

function editArticle(num){
	var url = basePath+"/admin/article/edit";
	var loadData={"aid":num};
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
	searchArticle(false);
}

function saveArticle(){
	// 设置百度编辑器的内容到#cd中
	var content = UE.getEditor('articleContent').getContent();
	$("#ac").html(content);
	var f = $('#articleForm').form('enableValidation').form('validate');
	if(f){
		$('#articleForm').form('submit',{
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

function delArticle(aid){
	$.messager.confirm('消息提醒', '确定要删掉资讯吗?', function(r){
		if (r){
			var url = basePath+"/admin/article/delete";
			var loadData={"aidStr":aid};
			$.post(url,loadData,commonCallback,"text");
		}
	});
}

function batchDelete(){
	var ids = $("input[name='subbox']:checked");
	if(ids.length == 0){
		$.messager.alert('消息提醒','请选择要删除的资讯。');
		return;
	}else{
		var aidStr = "";
		for(var i = 0; i < ids.length; i ++){
			aidStr += $(ids[i]).val() + ",";
		}
		delArticle(aidStr);
	}
}

function commonCallback(data){
	var result = eval("("+data+")");
	$.messager.alert('消息提醒',result.msg);
	if(result.code==1){
		refreshList();
	}
}