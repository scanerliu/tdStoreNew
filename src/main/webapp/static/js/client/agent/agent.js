function buyNow(){
	var agentProductId = $("#agentProductId").val();
	var regionId = $("#regionId").val();
	var productTypeId = $("#productTypeId").val();
	var isAgentProductUsePackage = $("#isAgentProductUsePackage").val();
	var productType = $("#productType").val();
	var price = $("#agentprice").val();
	if(agentProductId>0){
		if(isAgentProductUsePackage=="true"){
			var agent = '{"agentProductId":"'+agentProductId+'","regionId":"'+regionId+'","productTypeId":"'+productTypeId+'","productType":"'+productType+'"}';
			setCookie("agentpackage", agent, 30);
			url = basePath+"/package/list?acpe="+price;
			window.location.href=url;
		}else{
			var url = basePath+"/shoppingcart/singleorder";
			$("#agentform").attr("action",url);
			$("#agentform").submit();
		}
	}else{
		alert("数据有误，请重新操作！");
	}
}

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
		if(settings.total==1){
			$("#regionId").val(upid);
			if(callback!=""){
				refreshtypelist();
			}
		}else{
			loadData = {'upid':upid,'provinceId':upid,'totalLevel':settings.total,'callback':''+settings.callback+''};
			$("#cityspn").loading().load(url,loadData);
			$("#regionspn").html("");
			$("#regionId").val("");
		}
	}else if(settings.num==2){
		var upid = $(settings.obj).val();
		if(settings.total==2){
			$("#regionId").val(upid);
			if(callback!=""){
				refreshtypelist();
			}
		}else{
			var provinceid = $("#provinceId").val();
			loadData = {'upid':upid,'provinceId':provinceid,'totalLevel':settings.total,'callback':''+settings.callback+''};
			$("#regionspn").loading().load(url,loadData);
			$("#regionId").val("");
		}
	}else if(settings.num==3){
		var upid = $(settings.obj).val();
		$("#regionId").val(upid);
		if(callback!=""){
			refreshtypelist();
		}
	}
}

function refreshtypelist(){
	getTypes({'obj':null,'num':0});
}

function getTypes(_settings){
	var regionid = $("#regionId").val();
	if(regionid==""){
		alert("请先选择代理地区！");
		return false;
	}
	var settings = {obj:"",num:0};
	settings=$.extend(settings,_settings);
	var url = basePath+"/agent/producttypeselect";
	var loadData = "";
	if(settings.num==0){
		loadData = {'parentId':0};
		$("#onetypespn").loading().load(url,loadData);
		$("#twotypespn").html("");
		$("#typespn").html("");
		$("#productTypeId").val("");
	}else if(settings.num==1){
		var upid = $(settings.obj).val();
		loadData = {'parentId':upid};
		$("#twotypespn").loading().load(url,loadData);
		$("#typespn").html("");
		$("#productTypeId").val("");
	}else if(settings.num==2){
		var upid = $(settings.obj).val();
		loadData = {'parentId':upid,"regionId":regionid};
		$("#typespn").loading().load(url,loadData);
		$("#productTypeId").val("");
	}else if(settings.num==3){
		var upid = $(settings.obj).val();
		$("#productTypeId").val(upid);
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
		$("#productTypeId").val("");
	}else if(settings.num==1){
		var upid = $(settings.obj).val();
		loadData = {'parentId':upid};
		$("#twotypespn").loading().load(url,loadData);
		$("#typespn").html("");
		$("#productTypeId").val("");
	}else if(settings.num==2){
		var upid = $(settings.obj).val();
		loadData = {'parentId':upid};
		$("#typespn").loading().load(url,loadData);
		$("#productTypeId").val("");
	}else if(settings.num==3){
		var upid = $(settings.obj).val();
		$("#productTypeId").val(upid);
	}
}

function addAgent(id){
	var url = basePath+"/agent/addagent";
	var loadData = $("#form").serializeArray();
	$.post(url,loadData,saveCallback,"text");
}

function saveCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$("#pay_warn").css("display","block")
	}else{
		alert(result.msg);
	}
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
			}
		}else{
			$("#villagespn").html("");
		}
	}
}