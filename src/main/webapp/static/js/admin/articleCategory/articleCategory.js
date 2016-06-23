function searchArticleCategory(f){
	var url = basePath+"/admin/articlecategory/search";
	var loadData = "";
	if(f){
		loadData = null;
	}else{
		loadData = $("#articleCategorylistform").serializeArray();
	}
	$("#results").load(url,loadData);
}

function fnGotoPageArticleCategory(num){
	searchArticleCategory(false);
}

function editArticleCategory(num){
	var url = basePath+"/admin/articlecategory/edit";
	var loadData={"cid":num};
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
	searchArticleCategory(false);
}

function saveArticleCategory(){
	var f = $('#articleCategoryForm').form('enableValidation').form('validate');
	if(f){
		$('#articleCategoryForm').form('submit',{
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

function delArticleCategory(cid){
	// 判断是否有下级
	var url = basePath+"/admin/articlecategory/hasChrildrenOrExistArticle";
	var loadData = {"cidStr":cid};
	$.post(url,loadData,hasChrildrenOrExistArticleCallback,"text");
}

function hasChrildrenOrExistArticleCallback(data){
	var result = eval("("+data+")");
	var cidStr = result.cidStr;
	if(result.code==0){
		$.messager.alert('消息提醒',result.msg);
		return;
	}else{
		$.messager.confirm('消息提醒', '确定要删掉该资讯目录吗?', function(r){
			if (r){
				var url = basePath+"/admin/articlecategory/delete";
				var loadData={"cidStr":cidStr};
				$.post(url,loadData,commonCallback,"text");
			}
		});
	}
}

function commonCallback(data){
	var result = eval("("+data+")");
	$.messager.alert('消息提醒',result.msg);
	if(result.code==1){
		refreshList();
	}
}

function batchDelete(){
	var ids = $("input[name='subbox']:checked");
	if(ids.length == 0){
		$.messager.alert('消息提醒','请选择要删除的资讯目录。');
		return;
	}else{
		var idStr = "";
		for(var i = 0; i < ids.length; i ++){
			idStr += $(ids[i]).val() + ",";
		}
		delArticleCategory(idStr);
	}
}








