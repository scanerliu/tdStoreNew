function searchPmenus(t){
	var url = basePath + "/admin/pmenu/search";
	var loadData = null;
	if(t){
		loadData = null;
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").load(url,loadData);
}

function editPmenu(id){
	var url = basePath + "/admin/pmenu/edit";
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
	searchPmenus(false);
}

//分页函数
function fnGotoPagePmenus(num){
	searchPmenus(false);
}


// 提交新增、修改广告为内容
function savePmenu(){
	var f = $('#pmenuForm').form('enableValidation').form('validate');
	if(f){
		$('#select2 option').prop('selected',true);
		$("#pmenuForm").form("submit",{
			success : function(data){
				 var result = eval("("+data+")");
				  if(result.code==1){
					  $.messager.alert('消息提醒','保存成功。');
					  returnList();
					  refreshList();
				  }else{
					  $.messager.alert('消息提醒','保存失败!');
				  }
			}
		});
		
	}
}

function delPmenu(id){
	$.messager.confirm('消息提醒', '确定要删掉?', function(r){
		if (r){
			var url = basePath+"/admin/pmenu/delete";
			var loadData={"id":id};
			$.post(url,loadData,delPmenuCallback,"text");
		}
	});
}

function delPmenuCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','删除成功。');
		refreshList();
	}else{
		$.messager.alert('消息提醒','删除失败!');
	}
}
