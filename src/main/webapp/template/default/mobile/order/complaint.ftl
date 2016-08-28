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
    <title>投诉</title>
    <#include "/common/common.ftl" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/common.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/main.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/index.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/x_pc.css"/>
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/core.js"></script>
</head>
<body class="body_bg">
  <!-- header_top -->
  <div class="top_heater">
    <a class="hleft hback" href="${app.basePath}/mobile/order/list" title=""></a>
    <span>投诉</span>
  </div>
  <!-- header_top end -->
	<!-- Center Start -->
	<#if result.flag>
	  <section class="container evaluate-success">
	    <img class="icon-success" src="${app.basePath}/static/default/mobile/images/f-success.png" alt="">
	    <div class="div1">操作成功</div>
	    <div class="div2"><h3>${result.failMsg!''}</h3></div>
	    <a class="btn-share" href="${app.basePath}/mobile/order/list" title="">点击返回</a>
	  </section>
	 <#else>
	 	<section class="container evaluate-success">
		    <img class="icon-success" src="${app.basePath}/static/default/mobile/images/f-fail.png" alt="">
		    <div class="div1">操作失败</div>
		    <div class="div2"><h3>${result.failMsg!''}</h3></div>
		    <a class="btn-share" href="${app.basePath}/mobile/order/list" title="">点击返回</a>
		  </section>
	 </#if>
  <!-- Center End -->
</body>
</html>