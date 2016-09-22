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
    <title>提示信息</title>
    <#include "/common/common.ftl" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/common.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/main.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/index.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/x_pc.css"/>
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/core.js"></script>
    <script src="${app.basePath}/static/js/mobile/index.js" type="text/javascript"></script>
    <script src="${app.basePath}/static/js/mobile/shoppingcart/shoppingcartlist.js" type="text/javascript"></script>
</head>
<script>
  window.onload=function(){
    value_over();
  }
</script>
<body class="body_gray">
  <!-- header_top -->
  <div class="top_heater">
    <a href="${app.basePath}/mobile/index" title="" class="hleft hback"></a>
    <span>账户未验证</span>
  </div>
  <!-- header_top end -->

  <!-- Center Start -->
  <section class="container evaluate-success">
    <div class="div2">用户未验证，请先到个人信息中绑定手机号码和设置地区完成验证才能购买商品！</div>
	<div class="div1"><a href='${app.basePath}/mobile/user/info' class="btn-share">立即验证</a></div>
    <a class="btn-share" href="${app.basePath}/mobile/index" title="">点击返回首页</a>
  </section>
  <!-- Center End -->
<script>
$(function(){
	
});
</script>
</body>  
</html>