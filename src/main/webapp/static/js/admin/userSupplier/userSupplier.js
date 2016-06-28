function searchUserSupplier(f){
	var url = basePath+"/admin/supplier/search";
	var loadData = "";
	if(f){
		loadData = null;
	}else{
		loadData = $("#userSupplierForm").serializeArray();
	}
	$("#results").load(url,loadData);
}

function fnGotoPageUserSupplier(num){
	searchUserSupplier(false);
}

function editUserSupplier(num){
	var url = basePath+"/admin/supplier/edit";
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
	searchUserSupplier(false);
}

function goCheck(id, isPass){
	var replyContent = $("#reply").val();
	if(!isPass && replyContent==""){
		$.messager.alert('消息提醒',"拒绝需要填写回复内容。");
		return;
	}else{
		var url = basePath+"/admin/supplier/goCheck";
		var loadData={"id":id,"replyContent":replyContent,"isPass":isPass};
		$.post(url,loadData,commonCallback,"text");
	}
}


function commonCallback(data){
	var result = eval("("+data+")");
	$.messager.alert('消息提醒',result.msg);
	if(result.code==1){
		refreshList();
		returnList();
	}
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

