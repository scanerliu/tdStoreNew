//验证码
var pwait=30;
var pisWait = false;
function waitSecondsForPhoneNum(o) {
    if (pwait == 0) {
    	// 为获取验证按钮绑定获取验证码事件
		$("#getChangePhoneNumValidCode").bind("click", function(){
			var phone = $("#utel").val();
			if(phone == ""){
				alert("请输入手机号码。");
				return;
			}else if(!(/^1[3|4|5|7|8]\d{9}$/.test(phone))){ 
				alert("手机号码有误，请重填");
				return;
			}else{
				pisWait = true;
				waitSecondsForPhoneNum($("#getChangePhoneNumValidCode"));
				var url = basePath+"/mobile/getChangePhoneNumValidCode";
				var loadData = {'phone': phone};
				$.post(url, loadData, function(pdata){
					var result = eval("("+pdata+")");
					alert('消息提醒'+result.msg);
					pisWait = false;
				}, "text");			
			}
		});
		// 为获取验证按钮绑定等待60秒事件
		$("#getChangePhoneNumValidCode").bind("click", function(){
			if(pisWait){
				waitSecondsForPhoneNum($("#getChangePhoneNumValidCode"));
			}
		});
    	o.text("获取验证码");
        pwait = 30;
    } else {
    	$('#getChangePhoneNumValidCode').unbind("click");
        o.text("重新发送(" + pwait + ")");
        pwait--;
        setTimeout(function() {
        	waitSecondsForPhoneNum(o)
        }, 1000);
    }
}