function searchCustomers(f){
	var url = basePath+"/admin/customer/search";
	var loadData = "";
	if(f){
		loadData = $("#searchform").serializeArray();;
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

function fnGotoPageCustomers(num){
	searchCustomers(false);
}

function editCustomer(num){
	var url = basePath+"/admin/customer/edit";
	var loadData={"id":num};
	$("#rightform").loading().load(url,loadData);
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
	searchCustomers(false);
}
function saveCustomer(){
	var f = $('#customerForm').form('enableValidation').form('validate');
	if(f){
		$('#customerForm').form('submit',{
			  success : function(data){
				  var result = eval("("+data+")");
				  if(result.code==1){
					  $.messager.alert('消息提醒','会员保存成功。');
					  returnList();
					  refreshList();
				  }else{
					  $.messager.alert('消息提醒','会员保存失败!');
				  }
			  }
			 });
	};
}

function delCustomer(id){
	$.messager.confirm('消息提醒', '确定要删掉该会员吗?', function(r){
		if (r){
			var url = basePath+"/admin/customer/delete";
			var loadData={"id":id};
			$.post(url,loadData,delCustomerCallback,"text");
		}
	});
}

function delCustomerCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','会员删除成功。');
		refreshList();
	}else{
	  $.messager.alert('消息提醒','会员删除失败!');
	}
}

function editRoles(id){
	var url = basePath+"/admin/customer/roles";
	var loadData={"id":id};
	$("#rightform").loading().load(url,loadData);
	showForm();
}

function saveRoles(){
	var url = basePath+"/admin/customer/saverole";
	var nodes = $("#roleForm input[name='subbox']:checked");
	var s = '';
	$.each(nodes, function(){
		var rid = $(this).val();
		if(rid>0){
			if (s != '') {s += ',';}
			s += rid;
		}
	});
	var uId = $("#roleForm #uId").val();
	if(uId==""||uId==undefined){
		$.messager.alert('消息提醒','会员未选择，请重新操作!');
		return ;
	}
	var postData = {"uid":uId,"roleIds":s};
	$.post(url,postData,saveRolesCallback,"text");
}

function saveRolesCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','角色保存成功。');
	}else{
	  $.messager.alert('消息提醒','角色保存失败!');
	}
}

function changePassword(id){
	var url = basePath+"/admin/customer/changepassword";
	var loadData={"id":id};
	$("#rightform").loading().load(url,loadData);
	showForm();
}

function savePassword(id){
	var f = $('#passwordForm').form('enableValidation').form('validate');
	if(f){
		$('#passwordForm').form('submit',{
			  success : function(data){
				  var result = eval("("+data+")");
				  if(result.code==1){
					  $.messager.alert('消息提醒','密码修改成功。');
					  returnList();
				  }else{
					  $.messager.alert('消息提醒','密码修改失败!');
				  }
			  }
			 });
	};
}

/**
 * 启用账号
 */
function activeCustomer(id){
	var url = basePath+"/admin/customer/updatestatus";
	if(id==""||id==undefined){
		$.messager.alert('消息提醒','会员未选择，请重新操作!');
		return ;
	}
	var postData = {"uid":id,"ustatus":1};
	$.post(url,postData,updateCallback,"text");
}
/**
 * 禁用账号
 */
function forbiddenCustomer(id){
	var url = basePath+"/admin/customer/updatestatus";
	if(id==""||id==undefined){
		$.messager.alert('消息提醒','会员未选择，请重新操作!');
		return ;
	}
	var postData = {"uid":id,"ustatus":2};
	$.post(url,postData,updateCallback,"text");
}

function updateCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','更新成功。');
		refreshList();
	}else{
	  $.messager.alert('消息提醒','更新失败!');
	}
}

function customerDetail(id){
	if(id==""||id==undefined){
		$.messager.alert('消息提醒','会员未选择，请重新操作!');
		return ;
	}
	var url = basePath+"/admin/customer/details";
	var loadData={"id":id};
	$("#rightform").loading().load(url,loadData);
	showForm();
}

function getDistricts(obj,num){
	var url = basePath+"/admin/district/regionselect";
	var loadData = "";
	if(num==0){
		loadData = {'upid':0};
		$("#provincespn").loading().load(url,loadData);
		$("#cityspn").html("");
		$("#regionspn").html("");
		$("#uregionId").val("");
	}else if(num==1){
		var upid = $(obj).val();
		loadData = {'upid':upid,'provinceId':upid};
		$("#cityspn").loading().load(url,loadData);
		$("#regionspn").html("");
		$("#uregionId").val("");
	}else if(num==2){
		var upid = $(obj).val();
		var provinceid = $("#provinceId").val();
		loadData = {'upid':upid,'provinceId':provinceid};
		$("#regionspn").loading().load(url,loadData);
		$("#uregionId").val("");
	}else if(num==3){
		var upid = $(obj).val();
		$("#uregionId").val(upid);
	}
}

function searchCustomerPointLogs(f){
	var url = basePath+"/admin/customer/customerpointslog";
	var loadData = "";
	if(f){
		loadData = null;
	}else{
		loadData = $("#integrallist_form").serializeArray();
	}
	alert(loadData);
	$("#integrallog_div").loading().load(url,loadData);
}

function fnGotoPageCustomerPointLogs(num){
	searchCustomerPointLogs(false);
}

function changeUserIntegral(){
	var uid = $("#customeruid").val();
	var changetype = $("#changetype").val();
	var integral = $("#changeintegral").val();
	var preintegral = $("#preintegral").val();
	var changenote = $("#changenote").val();
	if(integral==""){
		$.messager.alert('消息提醒','请填写积分变更数量!');
		return;
	}
	if(changetype==2){
		if(preintegral < integral){
			$.messager.alert('消息提醒','减少数量不能大于会员已有数量!');
			return;
		}
		integral = 0-integral;
	}
	$.messager.confirm('消息提醒', '确定要变更积分吗?', function(r){
		if (r){
			var url = basePath+"/admin/customer/addintegral";
			var postData = {"uid":uid,"integral":integral,"note":changenote};
			$.post(url,postData,changeUserIntegralCallback,"text");
		}
	});
}

function changeUserIntegralCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','更新成功。');
		$("#customer_integral").html(result.integral);
		searchCustomerPointLogs(true);
	}else{
	  $.messager.alert('消息提醒','更新失败!');
	}
}
function searchCustomerAccountLogs(f){
	var url = basePath+"/admin/customer/customeraccountlog";
	var loadData = "";
	if(f){
		loadData = null;
	}else{
		loadData = $("#accountlist_form").serializeArray();
	}
	$("#accountlog_div").loading().load(url,loadData);
}

function fnGotoPageCustomerAccountLogs(num){
	searchCustomerAccountLogs(false);
}

function changeUserAccount(){
	var uid = $("#customeruid").val();
	var changetype = $("#changetype").val();
	var upamount = $("#changeamount").val();
	var preamount = $("#preamount").val();
	var changenote = $("#changenote").val();
	if(upamount==""){
		$.messager.alert('消息提醒','请填写变更金额!');
		return;
	}
	preamount = parseFloat(preamount);
	upamount = parseFloat(upamount);
	if(changetype==2){
		if(preamount < upamount){
			$.messager.alert('消息提醒','减少金额不能大于会员已有金额!');
			return;
		}
		upamount = 0-upamount;
	}
	$.messager.confirm('消息提醒', '确定要变更金额吗?', function(r){
		if (r){
			var url = basePath+"/admin/customer/addamount";
			var postData = {"uid":uid,"upamount":upamount,"note":changenote};
			$.post(url,postData,changeUserAccountCallback,"text");
		}
	});
}

function changeUserAccountCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','更新成功。');
		$("#customer_amount").html(result.amount);
		searchCustomerAccountLogs(false);
	}else{
		$.messager.alert('消息提醒','更新失败!');
	}
}