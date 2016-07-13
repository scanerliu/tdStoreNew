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
    <script src="${app.basePath}/static/js/mobile/login.js" type="text/javascript"></script>
</head>
<body class="body_bg1">
<section class="container">
    <div class="signin_logo"><img src="${app.basePath}/static/default/mobile/images/logo1.png" alt="logo"></div>
    <form class="sign_form" id="loginForm" action="${app.basePath}/mobile/login" method="post">
        <div class="div1"><input type="text" name="username" placeholder="ID号或手机号登录" id="username" value="${loginForm.username!''}"></div>
        <div class="div2">
            <input type="password" name="password" placeholder="密码" id="password">
            <a href="${app.basePath}/mobile/forgetpassword" title="找回密码"></a>
        </div>
        <div class="div3"><input type="button" value="立即登录" onclick="login()"></div>
    </form>
    <div class="other_signin">
        <section class="sec3">还没有账号？<a href="${app.basePath}/mobile/register" title="">立即注册 ></a></section>
    </div>
</section>
<script>
$(function(){
});
</script>
</body>  
</html>