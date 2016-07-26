function searchBenefits(f){
	var url = basePath+"/admin/benefit/search";
	var loadData = "";
	if(f){
		loadData = null;
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

function fnGotoPageBenefits(num){
	searchBenefits(false);
}

function editBenefit(num){
	var url = basePath+"/admin/benefit/edit";
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
	searchBenefits(false);
}
function saveBenefit(){
	var f = $('#benefitForm').form('enableValidation').form('validate');
	if(f){
		$('#benefitForm').form('submit',{
			  success : function(data){
				  var result = eval("("+data+")");
				  if(result.code==1){
					  $.messager.alert('消息提醒','分润设置保存成功。');
					  returnList();
					  refreshList();
				  }else{
					  $.messager.alert('消息提醒','分润设置保存失败!');
				  }
			  }
			 });
	};
}

function delBenefit(id){
	/*$.messager.confirm('消息提醒', '确定要删掉该设置吗?', function(r){
		if (r){
			var url = basePath+"/admin/benefit/delete";
			var loadData={"id":id};
			$.post(url,loadData,delBenefitCallback,"text");
		}
	});*/
}

function delBenefitCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','分润设置删除成功。');
		refreshList();
	}else{
	  $.messager.alert('消息提醒','分润设置删除失败!');
	}
}