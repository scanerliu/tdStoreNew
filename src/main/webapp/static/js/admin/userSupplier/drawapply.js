function searchDrawApplys(t){
	var url = basePath + "/admin/supplier/searchdrawapply";
	var loadData = null;
	
	if(t){
		loadData = $("#searchform").serializeArray();;
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").load(url,loadData);
}

function fnGotoPageDrawApplys(num){
	searchDrawApplys(false);
}

function editDrawApply(id){
	var url = basePath + "/admin/supplier/editdrawapply";
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
	searchDrawApplys(false);
}



// 提交新增、修改内容
function saveDrawApply(){
	var f = $('#drawapplyForm').form('enableValidation').form('validate');
	if(f){
		openwaiting();
		$("#drawapplyForm").form("submit",{
			success : function(data){
				closewaiting();
				 var result = eval("("+data+")");
				  if(result.code==1){
					  $.messager.alert('消息提醒','更新成功。');
					  returnList();
					  refreshList();
				  }else{
					  $.messager.alert('消息提醒','更新失败!');
				  }
			}
		});
	}
}