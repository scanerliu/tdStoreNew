<#import "/common/app.ftl" as app> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="keywords" content="${system.webkeywords!''}">
    <meta name="description" content="${system.webdescription!''}">
    <meta name="copyright" content="${system.webcopyright!''}" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <title>商品列表 - ${system.webkeywords!''}</title>
    <#include "/common/common.ftl" />
    <link rel="stylesheet" href="${app.basePath}/static/default/client/style/login.css" />
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>
    <script src="${app.basePath}/static/js/client/login.js" type="text/javascript"></script>
</head>
<body class="body_bg1">
<!-- 顶部 -->
<div class="login-top">
    <h1></h1>
    <a href="index.html" title="">
        <!-- LOGO尺寸 559*71 -->
        <img src="${app.basePath}/static/default/client/images/zj_logo_login.png" alt=""/>
    </a>
</div>
<!-- 顶部 END -->

<!-- 登录 -->
<div class="login-banner">
    <div class="login">
        <img src="${app.basePath}/static/default/client/images/zj_banner_login.jpg" alt=""/>
        <div class="login-form">
            <form action="">
                <div class="form_div1">
                    <p class="p1">会员登录</p>
                </div>
                <input type="text" class="ipt1" name="username" id="username" value="" placeholder="ID号或手机号"/>
                <input type="password" class="ipt2" name="password" id="password" value="" placeholder="密码"/>

                <div class="form_div2">
                    <p><input type="checkbox" name="rememberMe" value="1">&nbsp;自动登录</p>
                    <a href="javascript:void(0);" id="a_click" title="">忘记密码？</a>
                </div>
                <input type="button" value="登录" class="form_btn" onclick="login()"/>
                <div class="login_way">
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Center End -->
<!-- Footer Start -->
<footer class="login_footer">
<#include "./common/foot.ftl">
</footer>
<!-- Footer End -->
</body>  
</html>