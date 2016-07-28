<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
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
    <title>我的订单</title>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/common.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/main.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/x_pc.css"/>
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
    <script src="${app.basePath}/static/js/mobile/supply/supply.js" type="text/javascript"></script>
</head>
<body class="body_gray">
  <!-- header_top -->
  <div class="top_heater">
    <a class="hleft hback" href="${app.basePath}/mobile/user/center" title="返回"></a>
    <span>发货列表</span>
  </div>
  <!-- header_top end -->
  <div id="results"></div>
  <script type="text/javascript">
	$(function(){
	    searchOrder();
	});
	
</script>
</body>
</html>