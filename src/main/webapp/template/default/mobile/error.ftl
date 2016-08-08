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
    <title>下单失败</title>
    <#include "/common/common.ftl" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/common.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/main.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/index.css"/>
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
    <a href="${app.basePath}/mobile/shoppingcart/list" title="" class="hleft hback"></a>
    <span>操作失败</span>
  </div>
  <!-- header_top end -->

  <!-- Center Start -->
  <section class="container evaluate-success">
    <img class="icon-success" src="${app.basePath}/static/default/mobile/images/f-fail.png" alt="">
    <div class="div1">对不起！操作失败</div>
    <div class="div2">
    <#if errmsg??>
    <h3>${errmsg}</h3>
	</#if>
	</div>
    <a class="btn-share" href="${app.basePath}/mobile/index" title="">点击返回首页</a>
  </section>
  <!-- Center End -->
<script>
$(function(){
	
});
</script>
</body>  
</html>