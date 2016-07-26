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
	var url = basePath+"/mobile/user/saveInfo";
	var loadData = $('#userInfoForm').serialize();
	$.post(url, loadData, commonCallback, "text");
}

function verifyApply(id, status){
	var url = basePath+"/mobile/user/verifyExperienceStoreApply";
	var loadData = {'id':id, 'status': status};
	$.post(url, loadData, verifyApplyCallback, "text");
}

function verifyApplyCallback(data){
	var result = eval("("+data+")");
	alert('消息提醒'+result.msg);
	if(result.code == 1){
		window.location.reload();
	}
}

function commonCallback(data){
	var result = eval("("+data+")");
	alert('消息提醒'+result.msg);
}

//验证码
var wait=60;
function waitSeconds(o) {
    if (wait == 0) {
    	o.removeAttr("disabled");
    	o.text("获取验证码");
        wait = 60;
    } else {
    	o.attr("disabled", true);
        o.text("重新发送(" + wait + ")");
        wait--;
        setTimeout(function() {
        	waitSeconds(o)
        }, 1000);
    }
}
