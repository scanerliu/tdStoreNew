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
    <form class="findin2" id="repasswordForm" action="${app.basePath}/mobile/repassword">
        <div>
            <label>设置密码</label>
            <input type="password" placeholder="请输入新密码" name="upassword" id="upassword" datatype="*6-16" nullmsg="请设置密码！" errormsg="密码范围在6~16位之间！"></div>
        <div>
            <label>确认密码</label>
            <input type="password" placeholder="再次输入密码" name="repassword" id="repassword" datatype="*" recheck="upassword" nullmsg="请再输入一次密码！" errormsg="您两次输入的账号密码不一致！" >
        </div>
        <div>
        <input type="submit" value=保存并重新登录></div>
        <input type="hidden" name="uid" value="${user.uid}" datatype="n1-11" nullmsg="用户不存在！" errormsg="用户不存在！"/>
        <input type="hidden" name="sign" value="${sign}" datatype="*10-64" nullmsg="签名错误！" errormsg="签名错误！"/>
    </form>
</section>
<!-- Center End -->
<script>
$(function(){
$(document).ready(function(){
		// 初始化ValidForm插件
		$("#repasswordForm").Validform({
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
			ajaxPost:true, 
			callback:function(data){
				//返回数据data是json对象，{"info":"demo info","status":"1"}
				//info: 输出提示信息;
				//status: 返回提交数据的状态,是否提交成功。如可以用"y"表示提交成功，"n"表示提交失败，在ajax_post.php文件返回数据里自定字符，主要用在callback函数里根据该值执行相应的回调操作;
				if(data.status == '1'){
					window.location.href = basePath+"/mobile/login";
				}
			}
		});
	});
});
</script>
</body>  
</html>