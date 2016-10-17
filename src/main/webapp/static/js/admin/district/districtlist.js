function searchDistricts(f){
	var url = basePath+"/admin/district/search";
	var loadData = "";
	if(f){
		loadData = $("#searchform").serializeArray();
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
	var loadData = $("#searchform").serializeArray();
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
	var loadData={"id":id};
	$("#rightlist").hide();
	$("#rightform").show().loading().load(url,loadData);
}

function returnList(){
	$("#rightlist").show();
	$("#rightform").hide();
	refreshList();
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
		returnList();
	}else{
	  $.messager.alert('消息提醒','地区更新失败!');
	}
}

function refreshList(){
	searchDistricts(true);
}


function getDistricts(_settings){
	var settings = {obj:"",num:0,level:1,total:1,callback:""};
	settings=$.extend(settings,_settings);
	var url = basePath+"/admin/district/regionlongselect";
	var loadData = "";
	var callback = settings.callback;
	if(settings.num==0){
		loadData = {'upid':0,'level':1,'totalLevel':settings.total,'callback':''+settings.callback+''};
		$("#provincespn").loading().load(url,loadData);
		$("#cityspn").html("");
		$("#regionspn").html("");
		$("#townspn").html("");
		$("#villagespn").html("");
		$("#upid").val("0");
	}else if(settings.num==1){
		var upid = $(settings.obj).val();
		if(upid!==""){
			loadData = {'upid':upid,'provinceId':upid,'level':2,'totalLevel':settings.total,'callback':''+settings.callback+''};
			$("#cityspn").loading().load(url,loadData);
			$("#regionspn").html("");
			$("#townspn").html("");
			$("#villagespn").html("");
			$("#upid").val(upid);
			if(callback!=""){
				searchDistricts(true);
			}
		}else{
			$("#cityspn").html("");
			$("#regionspn").html("");
			$("#townspn").html("");
			$("#villagespn").html("");
		}
	}else if(settings.num==2){
		var upid = $(settings.obj).val();
		if(upid!==""){
			var provinceid = $("#provinceId").val();
			loadData = {'upid':upid,'provinceId':provinceid,'level':3,'totalLevel':settings.total,'callback':''+settings.callback+''};
			$("#regionspn").loading().load(url,loadData);
			$("#townspn").html("");
			$("#villagespn").html("");
			$("#upid").val(upid);
			if(callback!=""){
				searchDistricts(true);
			}
		}else{
			$("#regionspn").html("");
			$("#townspn").html("");
			$("#villagespn").html("");
		}
	}else if(settings.num==3){
		var upid = $(settings.obj).val();
		if(upid!==""){
			var provinceid = $("#provinceId").val();
			loadData = {'upid':upid,'provinceId':provinceid,'level':4,'totalLevel':settings.total,'callback':''+settings.callback+''};
			$("#townspn").loading().load(url,loadData);
			$("#villagespn").html("");
			$("#upid").val(upid);
			if(callback!=""){
				searchDistricts(true);
			}
		}else{
			$("#townspn").html("");
			$("#villagespn").html("");
		}
	}else if(settings.num==4){
		var upid = $(settings.obj).val();
		if(upid!==""){
			var provinceid = $("#provinceId").val();
			loadData = {'upid':upid,'provinceId':provinceid,'level':5,'totalLevel':settings.total,'callback':''+settings.callback+''};
			$("#villagespn").loading().load(url,loadData);
			$("#upid").val(upid);
			if(callback!=""){
				searchDistricts(true);
			}
		}else{
			$("#villagespn").html("");
		}
	}
}

