function searchExperienceStore(f){
	var url = basePath+"/admin/experiencestore/search";
	var loadData = "";
	if(f){
		loadData = null;
	}else{
		loadData = $("#experienceStoreForm").serializeArray();
	}
	$("#results").load(url,loadData);
}

function fnGotoPageExperienceStore(num){
	searchExperienceStore(false);
}

function editExperienceStore(num){
	var url = basePath+"/admin/experiencestore/edit";
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
	refreshList();
}

function refreshList(){
	searchExperienceStore(false);
}

function goCheck(id, isPass){
	var url = basePath+"/admin/experiencestore/goCheck";
	var loadData={"id":id, "isPass":isPass};
	$.post(url,loadData,commonCallback,"text");
}

function validateInputValueIsNum(num){  
	var reg = new RegExp("^[0-9]*$");  
	if(!reg.test(num)){  
		return false;  
	}else{
		return true;
	}   
}

function changeSort(id){
	var sort = $("#sort").val();
	if(sort==""){
		$.messager.alert('消息提醒',"排序不能为空");
		return;
	}
	if(!validateInputValueIsNum(sort)){
		$.messager.alert('消息提醒',"请输入数字");
		return;
	}
	if(sort < 0 || sort > 10000){
		$.messager.alert('消息提醒',"排序不能超过10000");
		return;
	}
	var url = basePath+"/admin/experiencestore/changeSort";
	var loadData={"id":id, "sort":sort};
	$.post(url,loadData,changeSortCallback,"text");
}

function changeSortCallback(data){
	var result = eval("("+data+")");
	$.messager.alert('消息提醒',result.msg);
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

