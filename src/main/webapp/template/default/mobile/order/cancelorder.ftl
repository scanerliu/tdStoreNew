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
    <title>取消订单</title>
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
    <a class="hleft hback" href="${app.basePath}/mobile/order/list" title=""></a>
    <span>取消订单</span>
  </div>
  <!-- header_top end -->
 <!-- Center Start -->
<form id="cancelForm">
<input type="hidden" name="orderId" value="${order.orderId!'0'}"/>
<section class="container zizhirenzheng">
    <article>
        <span>申请服务</span>
        <span>取消订单</span>
    </article>
    <article>
        <span>取消原因</span>
        <select name="cancelReason">
            <option value="现在不想购买">现在不想购买</option>
            <option value="商品价格较贵">商品价格较贵</option>
            <option value="价格波动">价格波动</option>
            <option value="商品缺货">商品缺货</option>
            <option value="重复下单">重复下单</option>
            <option value="添加或删除商品">添加或删除商品</option>
            <option value="收货人信息有误">收货人信息有误</option>
            <option value="送货时间过长">送货时间过长</option>
            <option value="无法支付订单">无法支付订单</option>
        </select>
    </article>
    <article>
        <span>温馨提示：</span>
		<span>订单成功取消后无法恢复。 该订单已付金额将返还个人钱包账号中。</span>
    </article>
</section>
<!-- Center End -->

<!-- Footer Start -->
<div style="width:100%;height:0.2rem"></div>
<footer>
   <div class="addnew" style="background:#f23030" onclick="cancelOrder()">
       <a href="javascript:;" title="">提交</a>
   </div>
    <span class="footclear"></span>
</footer>
<!-- Footer End -->
</form>
<#-- 引入警告提示样式 -->
<#include "/common/common_warn.ftl">
<#-- 引入等待提示样式 -->
<#include "/common/common_wait.ftl"> 
<script>
	$(function(){
	});
</script>
</body>
</html>