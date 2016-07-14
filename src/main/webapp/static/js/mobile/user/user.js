function sign(uid){
	var url = basePath+"/mobile/user/sign";
	var loadData={"uid":uid};
	$.post(url,loadData,updateIntegralCallback,"text");
	gettedIntegral
}

function updateIntegralCallback(data){
	var result = eval("("+data+")");
	if(result.code==0){
		alert(result.msg);
		return;
	}else if(result.code==1){
		// 修改弹框积分
		$("#gettedIntegral").html(result.gettedIntegral);
		// 显示弹框
		$('.pop-ups').show();
		e.stopPropagation();
	} 
}

function saveUserInfo(){
	
	
}