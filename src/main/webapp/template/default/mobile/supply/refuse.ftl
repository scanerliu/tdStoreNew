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
    <title>商家取消发货、退货</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/common.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/main.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/index.css">
	<script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
    <script src="${app.basePath}/static/js/mobile/supply/supply.js" type="text/javascript"></script>
</head>

<script>
    window.onload=function(){
        
    }
</script>

<body class="body_bg">

<!-- header_top -->
<div class="top_heater">
    <a href="javascript:history.go(-1);" title="返回" class="hleft hback"></a>
    <span>拒绝退货</span>
</div>
<!-- header_top end -->

<!-- Center Start -->
<form action="${app.basePath}/mobile/supply/refuse/reply", id="form" method="post">
<input type="hidden" value="${ship.id}" name="shipId">
<section class="container zizhirenzheng">
    <article>
        <span>申请服务</span>
        <span>退货/退款</span>
    </article>
    <article>
        <span>订单状态</span>
        <span>
        <#if ship.order??>
        	  <#if ship.order.payStatus==1 && ship.order.shipmentStatus==2>
	      		待发货
		      <#elseif ship.order.payStatus==1 && ship.order.shipmentStatus==1>
		      		待收货
		      <#elseif ship.order.payStatus==2>
		      		待支付
		      </#if>
        </#if></span>
    </article>
    <article>
        <span>退款原因</span>
        <span>	
        	<#if ship.returnCause??>
        	<#switch ship.returnCause>
        		<#case 1>七天无理由退换货<#break>
        		<#case 2>退运费<#break>
        		<#case 3>收到商品破损<#break>
        		<#case 4>商品错发/漏发<#break>
        		<#case 5>商品需要维修<#break>
        		<#case 6>收到商品与描述不符<#break>
        		<#case 7>商品质量问题<#break>
        		<#case 8>假冒品牌<#break>
        		<#case 9>未收到货<#break>
        		<#default>
        	</#switch>
        	</#if>
       </span>
    </article>
    <article>
        <span>拒绝说明</span>
        <textarea name="remark" id="remark" placeholder="请在这里输入说明"></textarea>
    </article>
</section>
</form>
<!-- Center End -->
<!-- Footer Start -->
<div style="width:100%;height:0.2rem"></div>
<footer>
   <div class="addnew" style="background:#f23030">
       <a href="javascript:;" onclick="refuseReturn()" title="回复">回复</a>
   </div>
    <span class="footclear" onclick="refuseReturn()"></span>
</footer>

<!-- Footer End -->
</body>
</html>