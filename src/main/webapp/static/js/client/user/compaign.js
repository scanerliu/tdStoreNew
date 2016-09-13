function getDistricts(_settings){
	var settings = {obj:"",num:0,total:1,callback:""};
	settings=$.extend(settings,_settings);
	var url = basePath+"/region/regionselect";
	var loadData = "";
	var callback = settings.callback;
	if(settings.num==0){
		loadData = {'upid':0,'totalLevel':settings.total,'callback':''+settings.callback+''};
		$("#provincespn").loading().load(url,loadData);
		$("#cityspn").html("");
		$("#regionspn").html("");
		$("#regionId").val("");
	}else if(settings.num==1){
		var upid = $(settings.obj).val();
		$("#regionId").val(upid);
		flushDescription();
		loadData = {'upid':upid,'provinceId':upid,'totalLevel':settings.total,'callback':''+settings.callback+''};
		$("#cityspn").loading().load(url,loadData);
		$("#regionspn").html("");
	}else if(settings.num==2){
		var upid = $(settings.obj).val();
		$("#regionId").val(upid);
		var provinceid = $("#provinceId").val();
		flushDescription();
		loadData = {'upid':upid,'provinceId':provinceid,'totalLevel':settings.total,'callback':''+settings.callback+''};
		$("#regionspn").loading().load(url,loadData);
	}else if(settings.num==3){
		var upid = $(settings.obj).val();
		$("#regionId").val(upid);
		flushDescription();
	}
}

function flushDescription(){
	/*$("#campaignDescription").html("");
	var selectedDistrictId = $("#regionId").val();
	if(selectedDistrictId != ""){
		var url = basePath+"/user/flushDescription";
		var loadData = {"selectedDistrictId": selectedDistrictId};
		$.post(url, loadData, function(data){
			var jsonData = eval('('+data+')');
			if(jsonData.code == "1"){
    			$("#campaignDescription").html(jsonData.note);					
    			$("#cId").val(jsonData.cid);					
			}else{
				alert(jsonData.msg);
			}
    	}, "text");
	}*/
	
	var selectedDistrictId = $("#regionId").val();
	if(selectedDistrictId != ""){
		var url = basePath+"/user/loadcelectiondes";
		var loadData = {"selectedDistrictId": selectedDistrictId};
		$("#camdiv").loading().load(url, loadData);
	}
	
}