function searchOrders(f){
	var url = basePath+"/mobile/order/search";
	var loadData = "";
	$(window).off("scroll", scrollHandler);
	var fliter = $("#sc_fliterType").val();
	if(fliter==5){
		url = basePath+"/mobile/order/searchreturn";
	}
	if(f){
		loadData = $("#searchform").serializeArray();
		$("#results").loading().load(url,loadData);
	}else{
		loadData = $("#listform").serializeArray();
		$.get(url, loadData, function(html){
	  		  $(html).appendTo("#results");
	  	});
	}
}

var scrollHandler = function(){
    if ($(document).height() - $(this).scrollTop() - $(this).height()<100){
    	searchOrders(false);
    } 
};

function applyRefund(){
	var amount = $("#returnAmount").val();
	if(amount==""||amount==0){
		alert("请填写退款金额！");
		return ;
	}
	$("#refundForm").submit();
}

function applyComplaint(){
	var complaint = $("#complaint").val();
	if(complaint==""||complaint.length<10){
		alert("请正确填写投诉内容：10至100个文字！");
		return ;
	}
	$("#complaintForm").submit();
}

function refundtract(){
	var trackingNo = $("#trackingNo").val();
	if(trackingNo==""||trackingNo.length<3){
		alert("请正确填写物流单号！");
		return ;
	}
	var url = basePath+"/mobile/order/savetract";
	var postData = $("#tractForm").serializeArray();
	$.post(url,postData,refundtractCallback,'text');
	
}

function refundtractCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		alert("物流单号录入成功。");
		window.history.go(-1);
	}else{
		alert(result.msg);
	}
}