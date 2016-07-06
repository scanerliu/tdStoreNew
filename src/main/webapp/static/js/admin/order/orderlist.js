function searchOrders(f){
	var url = basePath+"/admin/order/search";
	var loadData = "";
	if(f){
		loadData = $("#searchform").serializeArray();
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

function fnGotoPageOrders(num){
	searchOrders(false);
}

function orderDetail(num){
	var url = basePath+"/admin/order/details";
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
	searchOrders(false);
}
function saveOrder(){
	var f = $('#orderForm').form('enableValidation').form('validate');
	if(f){
		$('#orderForm').form('submit',{
			  success : function(data){
				  var result = eval("("+data+")");
				  if(result.code==1){
					  $.messager.alert('消息提醒','订单保存成功。');
					  returnList();
					  refreshList();
				  }else{
					  $.messager.alert('消息提醒','订单保存失败!');
				  }
			  }
			 });
	};
}

function delOrder(id){
	$.messager.confirm('消息提醒', '确定要删掉该订单吗?', function(r){
		if (r){
			var url = basePath+"/admin/order/delete";
			var loadData={"id":id};
			$.post(url,loadData,delOrderCallback,"text");
		}
	});
}

function delOrderCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','订单删除成功。');
		refreshList();
	}else{
	  $.messager.alert('消息提醒','订单删除失败!');
	}
}