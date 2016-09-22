function searchAdsense(t){
	var url = basePath + "/admin/advertposition/search";
	var loadData = null;
	
	if(t){
		loadData = null;
	}else{
		loadData = $("#adsenseform").serializeArray();
	}
	$("#results").load(url,loadData);
}

function editAdsense(id){
	var url = basePath + "/admin/advertposition/edit";
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
	searchAdsense(false);
}

//分页函数
function fnGotoPageAdType(num){
	searchAdsense(false);
}


// 提交新增、修改广告为内容
function saveAdsense(){
	var f = $('#adsenseForm').form('enableValidation').form('validate');
	if(f){
		$("#adsenseForm").form("submit",{
			success : function(data){
				 var result = eval("("+data+")");
				  if(result.code==1){
					  $.messager.alert('消息提醒','广告位保存成功。');
					  returnList();
					  refreshList();
				  }else{
					  $.messager.alert('消息提醒','广告位保存失败!');
				  }
			}
		});
		
	}
}

function delAdsense(id){
	$.messager.confirm('消息提醒', '确定要删掉该广告位?', function(r){
		if (r){
			var url = basePath+"/admin/advertposition/delete";
			var loadData={"id":id};
			$.post(url,loadData,delAdseneceCallback,"text");
		}
	});
}

function delAdseneceCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','删除成功。');
		refreshList();
	}else{
		$.messager.alert('消息提醒','删除失败!');
	}
}


//分页函数
function fnGotoPageAdvertisement(num){
	searchAd(false);
}

/**
 * 
 * 广告
 * 
 */
function searchAd(t){
	var url = basePath + "/admin/advert/search";
	var loadData = null;
	
	if(t){
		loadData = null;
	}else{
		loadData = $("#adform").serializeArray();
	}
	$("#results").load(url,loadData);
}

function editAd(id){
	var url = basePath + "/admin/advert/edit";
	var loadData = {"id" : id};
	$("#rightform").load(url,loadData);
	showForm();
}

function saveAd(){
	var f = $('#adForm').form('enableValidation').form('validate');
	if(f){
		var adsId = $("#adsId").val();
		if(adsId==""){
			$.messager.alert('消息提醒','请选择广告位。');
			return false;
		}
		$("#adForm").form("submit",{
			success : function(data){
				 var result = eval("("+data+")");
				  if(result.code==1){
					  $.messager.alert('消息提醒','广告位保存成功。');
					  returnList();
					  refreshListAd();
				  }else{
					  $.messager.alert('消息提醒','广告位保存失败!');
				  }
			}
		});
		
	}
}

function refreshListAd(){
	searchAd(false);
}

function delAd(id){
	$.messager.confirm('消息提醒', '确定要删掉该广告?', function(r){
		if (r){
			var url = basePath+"/admin/advert/delete";
			var loadData={"id":id};
			$.post(url,loadData,delAdCallback,"text");
		}
	});
}

function delAdCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','删除成功。');
		refreshListAd();
	}else{
		$.messager.alert('消息提醒','删除失败!');
	}
}
var __flooraids;
function changeAds(obj){
	var asid = $(obj).val();
	if(asid==6){
		$("#typeIdTr").show();
		$("#floorIdTr").hide();
		$("#floorIdSelect").find("option[value='']").prop("selected",true);
	}else if($.inArray(asid, __flooraids)){
		$("#floorIdTr").show();
		$("#typeIdTr").hide();
		$("#typeIdSelect").find("option[value='']").prop("selected",true);
	}else{
		$("#typeIdTr").hide();
		$("#typeIdSelect").find("option[value='']").prop("selected",true);
		$("#floorIdTr").hide();
		$("#floorIdSelect").find("option[value='']").prop("selected",true);
	}
}
