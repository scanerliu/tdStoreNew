function searchOrders(f)
{
	var url = basePath+"/supply/searchorder";
	var loadData = null;
	$("#results").load(url,loadData);
}

function searchOrders(f){
	var url = basePath+"/supply/searchorder";
	var loadData = "";
	if(f){
		loadData = $("#searchform").serializeArray();
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

//分页函数
function fnGotoPageOrders(num){
	searchOrders(false);
}


function shippmentOrder(){
	var url = basePath+"/supply/shiporder";
	var loadData = $("#shipmentform").serializeArray();
	
	$.post(url,loadData,saveCallback,"text");
}

function saveCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		  alert('订单发货成功。');
		  window.location.href=basePath+"/supply/order";
	  }else{
		  alert(result.msg);
	  }
}


function delCollect(id){
	var url = basePath+"/user/collect/delete";
	var loadData={"id":id};
	$.post(url,loadData,delCollectCallback,"text");
}



function delCollectCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		searchCollect();
	}
}

/**
 * 查询退货单
 * @param f
 */
function searchRefunds(f){
	var url = basePath+"/supply/searchrefund";
	var loadData = "";
	if(f){
		loadData = $("#searchform").serializeArray();
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

//分页函数
function fnGotoPageRefunds(num){
	searchRefunds(false);
}

/**
 * 查询物流信息
 * @param f
 */
function searchpostinfo(com,no){
	var url = basePath+"/order/searchpostinfo";
	var loadData = {"trackingNo":no,"trackingCom":com};
	$("#postinfo").loading().load(url,loadData);
}

function agreeReturn(shipId){
	var s = confirm("确认同意此次退款申请？");
		 if (s) {
			 $.ajax({
				 url : basePath+"/supply/agree",
				 type : "post",
				 data : {"shipId":shipId},
				 success:function(result){
					 var data = eval("("+result+")");
					 if(data.code==1){
						 alert(data.msg);
						 window.location.reload();
					 }else{
						 alert(data.msg);
					 }
				 }
			 })
		 }
}

function refuseReturn(shipId){
	var remark = $("#remark").val();
	if(remark==""||remark.length<5||remark.length>50){
		alert("请正确填写拒绝理由！");
		$("#remark").focus();
		return false;
	}
	var s = confirm("确认拒绝此次退款申请？");
		 if (s) {
			 $.ajax({
				 url : basePath+"/supply/refusereturn",
				 type : "post",
				 data : {"shipId":shipId,"remark":remark},
				 success:function(result){
					 var data = eval("("+result+")");
					 if(data.code==1){
						 alert(data.msg);
						 window.location.reload();
					 }else{
						 alert(data.msg);
					 }
				 }
			 })
		 }
}

function completeReturn(shipId){
	var s = confirm("确认完成退款？");
		 if (s) {
			 $.ajax({
				 url : basePath+"/supply/refundorder",
				 type : "post",
				 data : {"shipId":shipId},
				 success:function(result){
					 var data = eval("("+result+")");
					 if(data.code==1){
						 alert(data.msg);
						 window.location.reload();
					 }else{
						 alert(data.msg);
					 }
				 }
			 })
		 }
}
