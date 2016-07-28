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
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/index.css">
	<script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
    <script src="${app.basePath}/static/js/mobile/supply/supply.js" type="text/javascript"></script>
</head>
<body class="body_gray">
  <!-- header_top -->
  <div class="top_heater">
    <a class="hleft hback" href="javascript:history.go(-1);" title="返回"></a>
    <span>发货列表</span>
  </div>
<!-- Center Start -->
<section class="container">
    <div class="sorder1">
        <div class="sorder_title">订单信息</div>
        <div class="sorder2">
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
	                        <label class="lab2">数量：x<span>${sku.quantity!'0'}</span></label>
	                    </p>
	                </div>
	            </div>
            </#list>
            </#if>
        </div>
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
    <form id="shipmentform" >
    <div class="sale_write">
        <div class="sorder_title">卖家填写</div>
        <input type="hidden" value="${order.orderId!''}" name="orderId">
        <section class="sale1">
            <span>订单编号</span>
            <input type="text" value="${order.orderNo!''}" readonly>
        </section>
        <section class="sale2">
            <span>快递</span>
            <select name="trackingId">
                <#if expressList??>
				<#list expressList as express>
					<option value="${express.id}">${express.name}</option>
				</#list>
				</#if>
            </select>
        </section>
        <section class="sale2">
            <span>物流编号</span>
            <input type="text" name="trackingNo" placeholder="*请输入物流编号">
        </section>
        <section class="sale3"><textarea name="returnReason" id="" cols="" rows="" placeholder="商家备注请在这里填写"></textarea></section>
    </div>
    </form>
</section>
<!-- Center End -->

<!-- Footer Start -->
<div style="width:100%;height:0.2rem"></div>
<footer>
   <div class="addnew" style="background:#f23030">
       <a href="javscript:;" onclick="shippmentOrder();" title="完成">完成</a>
   </div>
    <span class="footclear" onclick="shippmentOrder();"></span>
</footer>
<!-- Footer End -->
</body>
</html>