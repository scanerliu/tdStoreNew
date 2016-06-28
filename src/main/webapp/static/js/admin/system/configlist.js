function searchSystemConfigs(f){
	var url = basePath+"/admin/systemconfig/search";
	var loadData = "";
	if(f){
		loadData = null;
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

function showForm(){
	$("#rightlist").hide();
	$("#rightform").show();
}
function returnList(){
	$("#rightlist").show();
	$("#rightform").html("").hide();
}
function saveConfigs(){
	var tab = $('#dg').tabs('getSelected');
	var index = $('#dg').tabs('getTabIndex',tab);
	var postData = $(tab).find(":input[name]").serializeArray();
	if(postData.length>0){
		var url = basePath+"/admin/systemconfig/save.htm";
		$.post(url,postData,function (data){
			var da = eval('('+data+')');
			if(da.code==1){
				$.messager.alert('消息提醒', "保存成功。");
				var surl = basePath+"/admin/systemconfig/search.htm";
				$.ajaxSetup ({
					cache: false //close AJAX cache
				});
			    $("#results").load(surl, null,function(){
			    	$('#dg').tabs('select',index);
			    });
			}else{
				$.messager.alert('消息提醒', "保存失败！");
			}
		});
	}else{
		$.messager.alert('消息提醒', "没有需要保存的参数项！");
		return false;
	}
}