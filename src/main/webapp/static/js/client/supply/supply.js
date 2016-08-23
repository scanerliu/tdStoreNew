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
				 url : basePath+"/supply/agree",
				 type : "post",
				 data : {"shipId":shipId},
				 success:function(data){
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
