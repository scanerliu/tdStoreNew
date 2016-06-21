function searchDistricts(f){
	var url = basePath+"/admin/district/search";
	var loadData = "";
	if(f){
		loadData = null;
	}else{
		loadData = $("#districtlistform").serializeArray();
	}
	$("#results").load(url,loadData);
}
			
function addDistrict(){
	var newDistrict = $("#newDistrict").val();
	if(newDistrict == null || newDistrict == ""){
		$.messager.alert('消息提醒','地区名不能为空。');
		return;
	}
	var url = basePath+"/admin/district/save";
	var loadData = $("#districtlistform").serializeArray();
	$.post(url,loadData,addDistrictCallback,"text");
}

function delDistrict(id){
	// 判断是否有下级
	var url = basePath+"/admin/district/hasChrildren";
	var loadData = {"idStr":id};
	$.post(url,loadData,hasChildrenDistrictCallback,"text");
}

function addDistrictCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','地区添加成功。');
		refreshList();
	}else{
	  $.messager.alert('消息提醒','地区添加失败!');
	}
}

function delDistrictCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','地区删除成功。');
		refreshList();
	}else{
	  $.messager.alert('消息提醒','地区删除失败!');
	}
}

function hasChildrenDistrictCallback(data){
	var result = eval("("+data+")");
	var idStr = result.idStr;
	if(result.code==0){
		$.messager.alert('消息提醒','该地区有下级地区，请先删除下级地区。');
		return;
	}else{
		$.messager.confirm('消息提醒', '确定要删掉该地区吗?', function(r){
			if (r){
				var url = basePath+"/admin/district/delete";
				var loadData={"idStr":idStr};
				$.post(url,loadData,delDistrictCallback,"text");
			}
		});
	}
}

function hasBatchChildrenDistrictCallback(data){
	var result = eval("("+data+")");
	var idStr = result.idStr;
	if(result.code==0){
		$.messager.alert('消息提醒','该地区有下级地区，请先删除下级地区。');
		return;
	}else{
		$.messager.confirm('消息提醒', '确定要删掉该地区吗?', function(r){
			if (r){
				var url = basePath+"/admin/district/batchDelete";
				var loadData={"idStr":idStr};
				$.post(url,loadData,batchDeleteCallback,"text");				
			}
		});
	}
}

function batchDelete(){
	var ids = $("input[name='subbox']:checked");
	if(ids.length == 0){
		$.messager.alert('消息提醒','请选择要删除的地区。');
		return;
	}else{
		var idStr = "";
		for(var i = 0; i < ids.length; i ++){
			idStr += $(ids[i]).val() + ",";
		}
		// 判断是否有下级
		var url = basePath+"/admin/district/hasChrildren";
		var loadData = {"idStr":idStr};
		$.post(url,loadData,hasBatchChildrenDistrictCallback,"text");
	}
}

function batchDeleteCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','地区删除成功。');
		refreshList();
	}else{
	  $.messager.alert('消息提醒','地区删除失败!');
	}
}

function editDistrict(id){
	var url = basePath+"/admin/district/edit";
	var provinceId = $("#provinceId").val();
	var cityId = $("#cityId").val();
	var loadData={"id":id, "provinceId":provinceId, "cityId":cityId};
	$("#results").load(url,loadData);
}

function returnList(provinceId, cityId){
	var url = basePath+"/admin/district/search";
	var loadData = {"provinceId":provinceId, "cityId":cityId};
	$("#results").load(url,loadData);
}

function updateDistrict(){
	var name = $("input[name='name']").val();
	var id = $("input[name='id']").val();
	if(name == null || name == ""){
		$.messager.alert('消息提醒','地区名不能为空!');
		return;
	}
	var url = basePath+"/admin/district/update";
	var loadData={"id":id, "name":name};
	$.post(url,loadData,updateDistrictCallback,"text");
}

function updateDistrictCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','地区更新成功。');
		var url = basePath+"/admin/district/search";
		var provinceId = $("input[name='provinceId']").val();
		var cityId = $("input[name='cityId']").val();
		var loadData = {"provinceId":provinceId, "cityId":cityId};
		$("#results").load(url,loadData);
	}else{
	  $.messager.alert('消息提醒','地区更新失败!');
	}
}

function refreshList(){
	searchDistricts(false);
}


