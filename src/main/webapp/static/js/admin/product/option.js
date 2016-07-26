function searchOption(id)
{
	var url = basePath+"/admin/attribute/option/search";
	var loadData = {"attriId":id};

	$("#results").load(url,loadData);
	returnList();
}

function editOption(attriId,id){
	var url = basePath + "/admin/attribute/option/edit";
	var loadData = {"attriId":attriId,"id" : id};
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

function optionList(attriId){
	searchOption(attriId);
}

// 提交新增、修改广告为内容
function saveOption(){
	var f = $('#attributeForm').form('enableValidation').form('validate');
	if(f){
		$("#attributeForm").form("submit",{
			success : function(data){
				 var result = eval("("+data+")");
				  if(result.code==1){
					  $.messager.alert('消息提醒','属性内容保存成功。');
					  returnList();
					  optionList(result.attriId);
				  }else{
					  $.messager.alert('消息提醒','属性内容保存失败!');
				  }
			}
		});
		
	}
}

function delOption(id){
	$.messager.confirm('消息提醒', '删除后将不可恢复，确定要继续删除吗?', function(r){
		if (r){
			var url = basePath+"/admin/attribute/option/delete";
			var loadData={"id":id};
			$.post(url,loadData,delProductTypeCallback,"text");
		}
	});
}

function delProductTypeCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','删除成功。');
		optionList(result.attriId);
	}else{
		$.messager.alert('消息提醒','删除失败!');
	}
}
