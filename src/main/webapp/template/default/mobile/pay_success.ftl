<#import "/common/app.ftl" as app>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Language" content="zh-CN">
<meta name="keywords" content="${system.webkeywords!''}">
<meta name="description" content="${system.webdescription!''}">
<meta name="copyright" content="${system.webcopyright!''}" />
<link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
<meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<title>购买成功提示</title>
<!-- css -->
<link href="${app.basePath}/static/touch/css/common.css" rel="stylesheet" type="text/css" />
<link href="${app.basePath}/static/touch/css/main.css" rel="stylesheet" type="text/css" />
<link href="${app.basePath}/static/touch/css/x_pc.css" rel="stylesheet" type="text/css" />
<link href="${app.basePath}/static/touch/css/index.css" rel="stylesheet" type="text/css" />
<!-- js -->
<script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>
<script type="text/javascript" src="${app.basePath}/static/touch/js/index.js"></script>
</head>

<script>
  window.onload=function(){
    value_over();
  }
</script>

<body>

  <!-- header_top -->
  <div class="top_heater">
    <a href="javascript:history.go(-1);" title="返回" class="hleft hback"></a>
    <span>购买成功</span>
  </div>
  <!-- header_top end -->

  <!-- Center Start -->
  <section class="container evaluate-success">
    <img class="icon-success" src="images/f-success.png" alt="">
    <div class="div1">您已成功付款<#if order??>${order.payAmount?string('0.00')}</#if>元</div>
    <div class="div2">点击查看<a href="${app.basePath}/mobile/order/detail${order.orderId!'0'}" title="订单详情">订单详情</a></div>
    <#--
    <a class="btn-share" id="avalue_btn" href="javascript:;" title="">分享<i class="icon-share"></i></a>
    -->
    <a class="btn-share" href="javascript:history.go(-1);" title="点击返回">点击返回</a>
  </section>
  <!-- Center End -->
  
  <#--
  <div style="width:100%;height:2.7rem"></div>
  <div class="value_over" id="value_over">
    <section class="sec1">
      <a href="#" title=""><p>QQ好友</p></a>
      <a href="#" title=""><p>QQ空间</p></a>
      <a href="#" title=""><p>微信好友</p></a>
      <a href="#" title=""><p>朋友圈</p></a>
      <a href="#" title=""><p>新浪微博</p></a>
    </section>
    <menu>取消分享</menu>
  </div>
  -->
</body>
</html>