function searchMembership(f){
	var url = basePath+"/admin/membership/search";
	var loadData = "";
	if(f){
		loadData = null;
	}else{
		loadData = $("#membershipListForm").serializeArray();
	}
	$("#results").load(url,loadData);
}

function fnGotoPageMembership(num){
	searchMembership(false);
}

function editMembership(num){
	var url = basePath+"/admin/membership/edit";
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
	searchMembership(false);
}

function saveMembership(){
	var f = $('#membershipForm').form('enableValidation').form('validate');
	if(f){
		$('#membershipForm').form('submit',{
			  success : function(data){
				  var result = eval("("+data+")");
				  $.messager.alert('消息提醒',result.msg);
				  if(result.code==1){
					  returnList();
					  refreshList();
				  }
			  }
		});
	};
}

function delMembership(id){
	$.messager.confirm('消息提醒', '确定要删掉该会员等级吗?', function(r){
		if (r){
			var url = basePath+"/admin/membership/delete";
			var loadData={"idStr":id};
			$.post(url,loadData,commonCallback,"text");
		}
	});
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
		$.messager.alert('消息提醒','请选择要删除的会员等级。');
		return;
	}else{
		var idStr = "";
		for(var i = 0; i < ids.length; i ++){
			idStr += $(ids[i]).val() + ",";
		}
		delMembership(idStr);
	}
}








