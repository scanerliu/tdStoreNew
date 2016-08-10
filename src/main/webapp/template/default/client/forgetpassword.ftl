<#import "/common/app.ftl" as app> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="copyright" content="" />
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <title>登录</title>
    <#include "/common/common.ftl" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/common.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/main.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/index.css"/>
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/forgetpassword.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/Validform_v5.3.2_min.js"></script>
</head>
<body>
<!-- header_top -->
<div class="top_heater">
    <a href="${app.basePath}/mobile/login" title=""  class="hleft hback"></a>
    <span>找回密码</span>
</div>
<!-- header_top end -->
<!-- Center Start -->
<section class="container">
    <form class="findin" id="forgetpasswordForm" method="post" action="${app.basePath}/mobile/forgetpassword2">
        <div>
            <label>手机号</label>
            <input type="text" placeholder="请输入手机号码" id="utel" name="utel" placeholder="请输入新手机号" datatype="m" nullmsg="请填写手机号！" errormsg="手机号码格式错误！"></div>
        <div>
            <label>验证码</label>
            <input type="text" name="valideCode" id="valideCode" datatype="*" nullmsg="请填写验证码！" placeholder="请输入验证码">
            <a href="javascript:;" id="getChangePhoneNumValidCode" title="获取验证码">获取验证码</a>
        </div>
        <div><a id="goforgetpassword" href="javascript:;" title="">下一步</a></div>
        <div>
        	手机收不到验证码怎么办？<br>
            1.检查垃圾短信，在设置中，将发件人设置为白名单。<br>
            2.若手机已停用，请拨打我们的服务热线。
        </div>
    </form>
</section>
<!-- Center End -->
<script>
$(function(){
$(document).ready(function(){
		// 初始化ValidForm插件
		$("#forgetpasswordForm").Validform({
			tiptype:function(msg,o,cssctl){
			    //msg：提示信息;
			    //o:{obj:*,type:*,curform:*},
			    //obj指向的是当前验证的表单元素（或表单对象，验证全部验证通过，提交表单时o.obj为该表单对象），
			    //type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, 
			    //curform为当前form对象;
			    //cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
			    if(o.type == 3){
			    	alert(msg);
			    }
			},
			postonce:true, // 开启二次提交防御
			ajaxPost:false
		});
		
		// 为提交按钮绑定事件
		$("#goforgetpassword").bind("click", function(){
		  	$("#forgetpasswordForm").submit();
		});
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
				var url = basePath+"/mobile/user/getChangePhoneNumValidCode";
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
	});
});
</script>
</body>  
</html>