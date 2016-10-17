function searchExper(t){
	var url = basePath + "/experience/search";
	var loadData = null;
	if(t){
		loadData = $("#searchform").serializeArray();;
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").load(url,loadData);
}

function getLongDistricts(_settings){
	var settings = {obj:"",num:0,level:1,total:1,callback:""};
	settings=$.extend(settings,_settings);
	var url = basePath+"/region/regionlongselect";
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
				searchExper(true);
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
				searchExper(true);
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
				searchExper(true);
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
				searchExper(true);
			}
		}else{
			$("#villagespn").html("");
		}
	}
}