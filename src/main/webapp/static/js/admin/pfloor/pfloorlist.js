function searchPfloors(t){
	var url = basePath + "/admin/pfloor/search";
	var loadData = null;
	if(t){
		loadData = null;
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").load(url,loadData);
}

function editPfloor(id){
	var url = basePath + "/admin/pfloor/edit";
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
	searchPfloors(false);
}

//分页函数
function fnGotoPagePfloors(num){
	searchPfloors(false);
}


// 提交新增、修改广告为内容
function savePmenu(){
	var f = $('#pfloorForm').form('enableValidation').form('validate');
	if(f){
		$('#select2 option').prop('selected',true);
		$("#pfloorForm").form("submit",{
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

function delPfloor(id){
	$.messager.confirm('消息提醒', '确定要删掉?', function(r){
		if (r){
			var url = basePath+"/admin/pfloor/delete";
			var loadData={"id":id};
			$.post(url,loadData,delPfloorCallback,"text");
		}
	});
}

function delPfloorCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','删除成功。');
		refreshList();
	}else{
		$.messager.alert('消息提醒','删除失败!');
	}
}

function getAllTypes(_settings){
	var settings = {obj:"",num:0};
	settings=$.extend(settings,_settings);
	var url = basePath+"/agent/producttypeallselect";
	var loadData = "";
	if(settings.num==0){
		loadData = {'parentId':0};
		$("#onetypespn").loading().load(url,loadData);
		$("#twotypespn").html("");
		$("#typespn").html("");
		//$("#productTypeId").val("");
	}else if(settings.num==1){
		var upid = $(settings.obj).val();
		loadData = {'parentId':upid};
		$("#twotypespn").loading().load(url,loadData);
		$("#typespn").html("");
		//$("#productTypeId").val("");
	}else if(settings.num==2){
		var upid = $(settings.obj).val();
		loadData = {'parentId':upid};
		$("#typespn").loading().load(url,loadData);
		//$("#productTypeId").val("");
	}else if(settings.num==3){
		var upid = $(settings.obj).val();
		var item = $("option:selected",$(settings.obj));
		item.appendTo('#select2');
	}
}
