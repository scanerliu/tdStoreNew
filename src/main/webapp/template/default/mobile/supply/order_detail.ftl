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
    <title>商家查看订单详情</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/common.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/main.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/index.css">
    <!-- js -->
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
    <span>订单详情</span>
</div>
<!-- header_top end -->

<!-- Center Start -->
<section class="container">
    <div class="sorder1">
        <div class="sorder_title">订单信息</div>
        <ul class="sorder_list">
            <li>
                <label class="fl">订单编号</label>
                <section class="fr">${order.orderNo!''}</section>
            </li>
            <li>
                <label class="fl">下单时间</label>
                <section class="fr">
                    <span class="sp_time1"><#if order.createTime??>${order.createTime?string('yyyy-MM-dd HH:mm')}</#if></span>
                </section>
            </li>
           <!--  <li>
                <label class="fl">付款时间</label>
                <section class="fr">
                    <span class="sp_time1">2016-06-16</span>
                    <span class="sp_time2">14:20</span>
                </section>
            </li>
            <li>
                <label class="fl">退款原因</label>
                <section class="fr">7天无理由退货</section>
            </li>
            <li>
                <label class="fl">退款金额</label>
                <section class="fr">169.00</section>
            </li>
            <li>
                <label class="fl">手续费</label>
                <section class="fr">10.00</section>
            </li> -->
            <li>
                <label class="fl">状态</label>
                <section class="sec_status fr">
                <#if order.payStatus==1 && order.shipmentStatus==2>
	      		待发货
		      <#elseif order.payStatus==1 && order.shipmentStatus==1>
		      		待收货
		      <#elseif order.payStatus==2>
		      		待支付
		      </#if></section>
            </li>
        </ul>
    </div>
    <div class="sorder2">
        <div class="sorder_title">商品信息</div>
        <#if order.skuList??>
	    <#list order.skuList as sku>
        <div class="somiddle">
            <img src="${app.basePath}${sku.productImage!''}" alt="${sku.productName!''}">
            <div class="right-content">
                <h3>${sku.productName!''}</h3>
                <div>
                    <#if sku.specialList??>
	              	<#list sku.specialList as special>
	              	<span><label for="">${special.sname!''}：</label><font>${special.soption!''}</font></span>
	                </#list>
	                </#if>
                </div>
                <p>
                    <label class="lab1">￥${sku.price!'0'}</label>
                    <!-- <label class="lab2">数量：x<span>4</span></label> -->
                </p>
            </div>
        </div>
        </#list>
	    </#if>
        <div class="sobottom">
            共 <span>${order.itemNum!'0'}</span> 件商品  合计<strong class="price">￥<span>${order.totalAmount!'0'}</span></strong>（含运费：<strong>￥<span>${order.postage!'0'}</span></strong>）
        </div>
    </div>
    <div class="sorder1">
        <div class="sorder_title">买家信息</div>
        <ul class="sorder_list">
            <li>
                <label class="fl">姓名</label>
                <section class="fr"><#if order.orderAddress??>${order.orderAddress.customerName!''}</#if></section>
            </li>
            <li>
                <label class="fl">电话</label>
                <section class="fr"><#if order.orderAddress??>${order.orderAddress.telphone!''}</#if></section>
            </li>
            <li class="s_adreess">
                <label class="fl">地址</label>
                <section class="fr"><#if order.orderAddress??>${order.orderAddress.regionFullName!''} ${order.orderAddress.address!''}</#if></section>
            </li>
            <li>
                <label class="fl">备注</label>
                <section class="fr">${order.userMessage!''}</section>
            </li>
        </ul>
    </div>
</section>
<!-- Center End -->

<!-- Footer Start -->
<div style="width:100%;height:0.2rem"></div>
<footer>
   <div class="addnew" style="background:#f23030">
       <a href="${app.basePath}/mobile/supply/shipment?orderId=${order.orderId!''}" title="立即发货">立即发货</a>
   </div>
   <a href="${app.basePath}/mobile/supply/shipment?orderId=${order.orderId!''}" title="立即发货">
    <span class="footclear"></span>
   </a>
</footer>
<!-- Footer End -->
</body>
</html>