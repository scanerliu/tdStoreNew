function login(){
	var username=$("#username").val();
	var password=$("#password").val();
	//用户名
	var nameReg = /^[0-9a-zA-Z]{11,26}$/;
	var passReg = /^[0-9a-zA-Z]{6,20}$/;
	var fname = nameReg.test(username);
	if(fname){}else{
		alert("请正确输入用户名:11位至26位数字或字母！");
		$("#username").focus();
		return;
	}
	var fpass = passReg.test(password);
	if(fpass){}else{
		alert("请正确输入密码:6位至20位数字或字母！");
		$("#password").focus();
		return;
	}
	
	$("#loginForm").submit();
}

function formreset(form){
	$("#"+form).find("input[type='text'],input[type='password']").each(function(){
	    $(this).val("");
	});
}

var __vtwait = 0;
var __vt;
function waitSeconds(){
	__vtwait--;
	if(__vtwait<=0){
		clearInterval(__vt);
		$("#getChangePhoneNumValidCode").text("重新发送").addClass("ipt_yzmac");;
		$("#getChangePhoneNumValidCode").on("click",function(){
        	getvalidcode(60);
        });
	}else{
		$("#getChangePhoneNumValidCode").text("重新发送(" + __vtwait + ")");
	}
}
function getvalidcode(time){
	var phone = $("#utel").val();
	if(phone == ""){
		alert("请输入手机号码。");
		return;
	}else if(!(/^1[3|4|5|7|8]\d{9}$/.test(phone))){ 
		alert("手机号码有误，请重填");
		return;
	}
	$("#getChangePhoneNumValidCode").off("click").removeClass("ipt_yzmac");
	__vtwait = time;
	waitSeconds();
	__vt = setInterval("waitSeconds()", 1000);
	var url = basePath+"/mobile/getChangePhoneNumValidCode";
	var loadData = {'phone': phone};
	$.post(url, loadData, function(pdata){
		var result = eval("("+pdata+")");
		alert('消息提醒'+result.msg);
	}, "text");	
}

