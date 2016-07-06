function searchAgent(f){
	var url = basePath+"/admin/agent/search";
	var loadData = "";
	if(f){
		loadData = $("#searchConditionForm").serializeArray();
	}else{
		loadData = $("#agentForm").serializeArray();
	}
	$("#results").load(url,loadData);
}

function fnGotoPageAgent(num){
	searchAgent(false);
}

function refreshList(){
	searchAgent(false);
}
 
function getProductTypeSelections(level, selectedProductTypeId){
	// 选择第三级时，无需异步加载
	if(level != 3){
		var url = basePath+"/admin/producttype/getProductTypeSelections";
		var loadData = {"level":level, "selectedProductTypeId":selectedProductTypeId};
		// 根据产品类别级别确定加载区域, level=0为首次进入列表页，未选择下拉框的情况。level!=0的值代表选择了第level级下拉框
		if(level == 0){
			$("#firstProductType").load(url,loadData);
			$("#secondProductType").html("");
			$("#thirdProductType").html("");
		}else if(level == 1){
			$("#product_type_id").val(selectedProductTypeId);
			if(selectedProductTypeId > 0){
				$("#secondProductType").load(url,loadData);
				$("#thirdProductType").html("");
			}else{
				$("#secondProductType").html("");
				$("#thirdProductType").html("");
			}
		}else if(level == 2){
			if(selectedProductTypeId > 0){
				$("#product_type_id").val(selectedProductTypeId);
				$("#thirdProductType").load(url,loadData);				
			}else{
				var parent_id = $("#product_type_" + (level-1)).val();
				$("#product_type_id").val(parent_id);
				$("#thirdProductType").html("");
			}
		}		
	}
	if(level == 3){
		if(selectedProductTypeId > 0){
			$("#product_type_id").val(selectedProductTypeId);
		}else{
			var parent_id = $("#product_type_" + (level-1)).val();
			$("#product_type_id").val(parent_id);				
		}
	}
}

function getDistrictSelections(level, selectedDistrictId, prefix, callBackFunction){
	// 选择第三级时，无需异步加载
	if(level != 3){
		var url = basePath+"/admin/district/getDistrictSelections";
		var loadData = {"level":level, "selectedDistrictId":selectedDistrictId, "prefix":prefix};
		// 根据地区级别确定加载区域, level=0为首次进入列表页，未选择下拉框的情况。level!=0的值代表选择了第level级下拉框
		if(level == 0){
			$("#"+prefix+"_firstDistrictLevel").load(url,loadData, callBackFunction);
			$("#"+prefix+"_secondDistrictLevel").html("");
			$("#"+prefix+"_thirdDistrictLevel").html("");
		}else if(level == 1){
			$("#"+prefix+"_region_id").val(selectedDistrictId);
			if(selectedDistrictId > 0){
				$("#"+prefix+"_secondDistrictLevel").load(url,loadData, callBackFunction);
				$("#"+prefix+"_thirdDistrictLevel").html("");
			}else{
				$("#"+prefix+"_secondDistrictLevel").html("");
				$("#"+prefix+"_thirdDistrictLevel").html("");
			}
		}else if(level == 2){
			if(selectedDistrictId > 0){
				$("#"+prefix+"_region_id").val(selectedDistrictId);
				$("#"+prefix+"_thirdDistrictLevel").load(url,loadData, callBackFunction);				
			}else{
				var parent_id = $("#"+prefix+"_district_id_" + (level-1)).val();
				$("#"+prefix+"_region_id").val(parent_id);
				$("#"+prefix+"_thirdDistrictLevel").html("");
			}
		}		
	}
	if(level == 3){
		if(selectedDistrictId > 0){
			$("#"+prefix+"_region_id").val(selectedDistrictId);	
		}else{
			var parent_id = $("#"+prefix+"_district_id_" + (level-1)).val();
			$("#"+prefix+"_region_id").val(parent_id);								
		}
	}
}

function delAgent(id){
	$.messager.confirm('消息提醒', '确定要删掉代理吗?', function(r){
		if (r){
			var url = basePath+"/admin/agent/delete";
			var loadData={"idStr":id};
			$.post(url,loadData,commonCallback,"text");
		}
	});
}

function batchDelete(){
	var ids = $("input[name='subbox']:checked");
	if(ids.length == 0){
		$.messager.alert('消息提醒','请选择要删除的代理。');
		return;
	}else{
		var idStr = "";
		for(var i = 0; i < ids.length; i ++){
			idStr += $(ids[i]).val() + ",";
		}
		delAgent(idStr);
	}
}

function commonCallback(data){
	var result = eval("("+data+")");
	$.messager.alert('消息提醒',result.msg);
	if(result.code==1){
		refreshList();
	}
}


























function editAgent(num){
	var url = basePath+"/admin/agent/edit";
	var loadData={"id":num};
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
	refreshList();
}

function refreshList(){
	searchAgent(false);
}

function goCheck(id, isPass){
	var url = basePath+"/admin/agent/goCheck";
	var loadData={"id":id, "isPass":isPass};
	$.post(url,loadData,commonCallback,"text");
}

function commonCallback(data){
	var result = eval("("+data+")");
	$.messager.alert('消息提醒',result.msg);
	if(result.code==1){
		refreshList();
		returnList();
	}
}