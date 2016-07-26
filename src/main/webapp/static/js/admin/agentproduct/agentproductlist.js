function searchAgentProducts(f){
	var url = basePath+"/admin/agentproduct/search";
	var loadData = "";
	if(f){
		loadData = null;
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

function fnGotoPageAgentProducts(num){
	searchAgentProducts(false);
}

function editAgentProduct(num){
	var url = basePath+"/admin/agentproduct/edit";
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
	searchAgentProducts(false);
}
function saveAgentProduct(){
	var f = $('#agentproductForm').form('enableValidation').form('validate');
	if(f){
		$('#agentproductForm').form('submit',{
			  success : function(data){
				  var result = eval("("+data+")");
				  if(result.code==1){
					  $.messager.alert('消息提醒','代理产品保存成功。');
					  returnList();
					  refreshList();
				  }else{
					  $.messager.alert('消息提醒','代理产品保存失败!');
				  }
			  }
			 });
	};
}

function delAgentProduct(id){
	$.messager.confirm('消息提醒', '确定要删掉该代理产品吗?', function(r){
		if (r){
			var url = basePath+"/admin/agentproduct/delete";
			var loadData={"id":id};
			$.post(url,loadData,delAgentProductCallback,"text");
		}
	});
}

function delAgentProductCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','代理产品删除成功。');
		refreshList();
	}else{
	  $.messager.alert('消息提醒','代理产品删除失败!');
	}
}