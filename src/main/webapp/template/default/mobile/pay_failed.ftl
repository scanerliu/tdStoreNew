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
<title>购买失败提示</title>
<!-- css -->
<link rel="stylesheet" href="${app.basePath}/static/touch/css/common.css" type="text/css" />
<link href="${app.basePath}/static/touch/css/main.css" rel="stylesheet" type="text/css" />
<link href="${app.basePath}/static/touch/css/x_pc.css" rel="stylesheet" type="text/css" />
<!-- js -->
<script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>
</head>
<body>

  <!-- header_top -->
  <div class="top_heater">
    <a href="${app.basePath}/mobile/order/list" title="返回" class="hleft hback"></a>
    <span>购买失败</span>
  </div>
  <!-- header_top end -->

  <!-- Center Start -->
  <section class="container evaluate-success">
    <img class="icon-success" src="${app.basePath}/static/touch/images/f-fail.png" alt="">
    <div class="div1">对不起！付款失败</div>
    <div class="div2"></div>
    <a class="btn-share" href="${app.basePath}/mobile/order/list" title="点击返回">点击返回</a>
  </section>
  <!-- Center End -->

</body>
</html>