function searchOrders(f){
	var url = basePath+"/order/search";
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

/**
 * 删除图片
 */
function deleteImg(obj){
	$(obj).parent().remove();
}

/**
 * 申请退款
 */
function applyRefund(){
	var amount = $("#returnAmount").val();
	if(amount==""||amount==0){
		alert("请填写退款金额！");
		return ;
	}
	$("#refundForm").submit();
}
/**
 * 建议投诉
 */
function applyComplaint(){
	var complaint = $("#complaint").val();
	if(complaint==""||complaint.length<10){
		alert("请正确填写投诉内容：10至100个文字！");
		return ;
	}
	$("#complaintForm").submit();
}
/**
 * 查询退货单
 * @param f
 */
function searchRefunds(f){
	var url = basePath+"/order/searchreturn";
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
 * 录入物流单号
 */
function refundtract(){
	var trackingNo = $("#trackingNo").val();
	if(trackingNo==""||trackingNo.length<3){
		alert("请正确填写物流单号！");
		return ;
	}
	var url = basePath+"/order/savetract";
	var postData = $("#tractForm").serializeArray();
	$.post(url,postData,refundtractCallback,'text');
	
}
/**
 * 录入物流单号返回处理函数
 */
function refundtractCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		alert("物流单号录入成功。");
		window.history.go(-1);
	}else{
		alert(result.msg);
	}
}
/**
 * 确认收货
 */
function receiptOrder(orderid){
	if(orderid!=""){
		if(confirm("确定要确认收货？")){
			var url = basePath+"/order/receiptorder";
			var postData = {"orderId":orderid};
			$.post(url,postData,receiptOrderCallback,'text');
		}
	}
}
/**
 * 确认收货返回函数
 */
function receiptOrderCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		alert("操作成功。");
		searchOrders(true);
	}else{
		alert(result.msg);
	}
}

/**
 * 确认收货
 */
function receiptOrder2(orderid){
	if(orderid!=""){
		if(confirm("确定要确认收货？")){
			var url = basePath+"/order/receiptorder";
			var postData = {"orderId":orderid};
			$.post(url,postData,receiptOrder2Callback,'text');
		}
	}
}
/**
 * 确认收货返回函数
 */
function receiptOrder2Callback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		alert("操作成功。");
		location.reload();
	}else{
		alert(result.msg);
	}
}