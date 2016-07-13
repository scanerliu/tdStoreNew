function searchBrands(f){
	var url = basePath+"/admin/brand/search";
	var loadData = "";
	if(f){
		loadData = null;
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

function fnGotoPageBrands(num){
	searchBrands(false);
}

function editBrand(num){
	var url = basePath+"/admin/brand/edit";
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
	searchBrands(false);
}
function saveBrand(){
	var f = $('#brandForm').form('enableValidation').form('validate');
	if(f){
		$('#brandForm').form('submit',{
			  success : function(data){
				  var result = eval("("+data+")");
				  if(result.code==1){
					  $.messager.alert('消息提醒','品牌保存成功。');
					  returnList();
					  refreshList();
				  }else{
					  $.messager.alert('消息提醒','品牌保存失败!');
				  }
			  }
			 });
	};
}

function delBrand(id){
	$.messager.confirm('消息提醒', '确定要删掉该品牌吗?', function(r){
		if (r){
			var url = basePath+"/admin/brand/delete";
			var loadData={"id":id};
			$.post(url,loadData,delBrandCallback,"text");
		}
	});
}

function delBrandCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','品牌删除成功。');
		refreshList();
	}else{
	  $.messager.alert('消息提醒',result.msg);
	}
}