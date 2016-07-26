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
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/js/huploadify/Huploadify.css">
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script src="${app.basePath}/static/js/huploadify/jquery.Huploadify.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/core.js"></script>
    <script src="${app.basePath}/static/js/mobile/order/orderlist.js" type="text/javascript"></script>
</head>
<body class="body_bg">
  <!-- header_top -->
  <div class="top_heater">
    <a class="hleft hback" href="javascript:;" onclick="window.history.go(-1)" title=""></a>
    <span>投诉</span>
  </div>
  <!-- header_top end -->
 <!-- Center Start -->
<form id="complaintForm" method="post" action="${app.basePath}/mobile/order/complaintorder">
<input type="hidden" name="orderId" value="${order.orderId!'0'}"/>
<section class="container zizhirenzheng">
    <article>
        <span>投诉说明</span>
        <textarea placeholder="请在这里输入文字，少于100文字" name="complaint" id="complaint"></textarea>
    </article>
</section>
<!-- Center End -->

<!-- Footer Start -->
<div style="width:100%;height:0.2rem"></div>
<footer onclick="applyComplaint()">
   <div class="addnew" style="background:#f23030">
       <a href="javascript:;" title="">提交</a>
   </div>
   <span class="footclear" onclick="applyComplaint()"></span>
</footer>
<!-- Footer End -->
</form>
<script>
	$(function(){
	});
</script>
</body>
</html>