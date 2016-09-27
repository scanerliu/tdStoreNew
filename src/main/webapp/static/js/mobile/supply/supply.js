function searchOrders(f){
	var url = basePath+"/mobile/supply/order/search";
	var loadData = "";
	$(window).off("scroll", scrollHandler);
	var fliter = $("#sc_fliterType").val();
	if(fliter==1){
		url = basePath+"/mobile/supply/order/refundlist";
	}
	if(f){
		loadData = $("#searchform").serializeArray();
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}


function refuseReturn(){
	var remark = $("#remark").val();
	if(remark==""||remark.length<5 || remark.length > 60){
		alert("请正确填写拒绝说明：5至60个文字！");
		return ;
	}
	$("#form").submit();
}

function agreeReturn(shipId){
	var s = confirm("确认同意此次退款申请？");
		 if (s) {
			 $.ajax({
				 url : basePath+"/mobile/supply/agree",
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

var scrollHandler = function(){
    if ($(document).height() - $(this).scrollTop() - $(this).height()<100){
    	searchOrders(false);
    } 
};

function shippmentOrder(){
	var url = basePath+"/mobile/supply/shiporder";
	var loadData = $("#shipmentform").serializeArray();
	
	$.post(url,loadData,saveCallback,"text");
}

function saveCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		  alert('订单发货成功。');
		  window.location.href=basePath+"/mobile/supply/order";
	  }else{
		  alert(result.msg);
	  }
}


function delCollect(id){
	var url = basePath+"/mobile/user/collect/delete";
	var loadData={"id":id};
	$.post(url,loadData,delCollectCallback,"text");
}



function delCollectCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		searchCollect();
	}
}

function completeReturn(shipId){
	var s = confirm("确认完成退款？");
		 if (s) {
			 $.ajax({
				 url : basePath+"/mobile/supply/refundorder",
				 type : "post",
				 data : {"shipId":shipId},
				 success:function(result){
					 var data = eval("("+result+")");
					 if(data.code==1){
						 alert(data.msg);
						 searchOrders(false);
					 }else{
						 alert(data.msg);
					 }
				 }
			 })
		 }
}
