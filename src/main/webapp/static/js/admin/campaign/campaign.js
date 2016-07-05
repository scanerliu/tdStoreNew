function searchCampaign(f){
	var url = basePath+"/admin/campaign/search";
	var loadData = "";
	if(f){
		loadData = $("#searchConditionForm").serializeArray();
	}else{
		loadData = $("#campaignForm").serializeArray();
	}
	$("#results").load(url,loadData);
}

function fnGotoPageCampaign(num){
	searchCampaign(false);
}

function refreshList(){
	searchCampaign(false);
}

function returnList(){
	$("#rightlist").show();
	$("#rightform").html("").hide();
}

function showForm(){
	$("#rightlist").hide();
	$("#rightform").show();
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

function delcampaign(id){
	$.messager.confirm('消息提醒', '确定要删掉活动吗?', function(r){
		if (r){
			var url = basePath+"/admin/campaign/delete";
			var loadData={"idStr":id};
			$.post(url,loadData,commonCallback,"text");
		}
	});
}

function batchDelete(){
	var ids = $("input[name='subbox']:checked");
	if(ids.length == 0){
		$.messager.alert('消息提醒','请选择要删除的活动。');
		return;
	}else{
		var idStr = "";
		for(var i = 0; i < ids.length; i ++){
			idStr += $(ids[i]).val() + ",";
		}
		delcampaign(idStr);
	}
}

function editcampaign(num){
	var url = basePath+"/admin/campaign/edit";
	var loadData={"id":num};
	$("#rightform").load(url,loadData);
	showForm();
}

function saveCampaign(){
	// 设置百度编辑器的内容到#cc中
	var content = UE.getEditor('campaignContent').getContent();
	$("#cc").html(content);
	var f = $('#campaignEditForm').form('enableValidation').form('validate');
	if(f){
		$('#campaignEditForm').form('submit',{
			  success : function(data){
				  var result = eval("("+data+")");
				  if(result.code==1){
					  $.messager.alert('消息提醒',result.msg);
					  returnList();
					  refreshList();
				  }else{
					  $.messager.alert('消息提醒',result.msg);
				  }
			  }
		});
	};
}



function commonCallback(data){
	var result = eval("("+data+")");
	$.messager.alert('消息提醒',result.msg);
	if(result.code==1){
		refreshList();
	}
}
























/*

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
*/