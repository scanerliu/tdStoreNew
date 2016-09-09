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

function getDistricts(settings){
	settings = {obj:"",num:0,total:1,callback:""};
	var url = basePath+"/district/regionselect";
	var loadData = "";
	if(settings.num==0){
		loadData = {'upid':0};
		$("#provincespn").loading().load(url,loadData);
		$("#cityspn").html("");
		$("#regionspn").html("");
		$("#uregionId").val("");
	}else if(settings.num==1){
		var upid = $(obj).val();
		loadData = {'upid':upid,'provinceId':upid};
		$("#cityspn").loading().load(url,loadData);
		$("#regionspn").html("");
		$("#uregionId").val("");
	}else if(settings.num==2){
		var upid = $(obj).val();
		var provinceid = $("#provinceId").val();
		loadData = {'upid':upid,'provinceId':provinceid};
		$("#regionspn").loading().load(url,loadData);
		$("#uregionId").val("");
	}else if(settings.num==3){
		var upid = $(obj).val();
		$("#uregionId").val(upid);
	}
}