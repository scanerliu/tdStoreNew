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
    <title>商家查看退换货</title>
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
    <a href="javascript:history.go(-1);" title="" class="hleft hback"></a>
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
                <section class="fr"><#if ship.order??>${ship.order.orderNo!''}</#if></section>
            </li>
            <li>
                <label class="fl">下单时间</label>
                <section class="fr">
                    <span class="sp_time1"><#if order?? && order.createTime??>${order.createTime?string('yyyy-MM-dd HH:mm')}</#if></span>
                </section>
            </li>
            <li>
                <label class="fl">退款时间</label>
                <section class="fr">
                    <span class="sp_time1"><#if ship.createTime??>${ship.createTime?string('yyyy-MM-dd HH:mm')}</#if></span>
                </section>
            </li>
            <li>
                <label class="fl">退款原因</label>
                <section class="fr">
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
                </section>
            </li>
            <li>
                <label class="fl">退款金额</label>
                <section class="fr"><#if ship.returnAmount??>${ship.returnAmount?string('0.00')}</#if></section>
            </li>
            <li>
                <label class="fl">状态</label>
                <section class="sec_status fr">
                	<#if ship.status??>
          			  <#if ship.status==1>
		      			新申请
				      <#elseif ship.status==2>
				      		同意
				      <#elseif ship.status==3>
				      		不同意
				      <#elseif ship.status==4>
				      		完成
				      </#if>
			      </#if></section>
            </li>
        </ul>
    </div>
    <div class="sorder2">
        <div class="sorder_title">退货信息</div>
        <#assign quantity = 0>
    	<#if ship.itemList??>
	    <#list ship.itemList as item>
		    <#assign quantity = item.quantity>
	        <div class="somiddle">
	            <img src="<#if item.itemOrderSku??>${app.basePath}${item.itemOrderSku.productImage!''}</#if>" alt="<#if item.itemOrderSku??>${item.itemOrderSku.productName!''}</#if>">
	            <div class="right-content">
	                <h3><#if item.itemOrderSku??>${item.itemOrderSku.productName!''}</#if></h3>
	                <div>
	                    <#if item.itemOrderSku.specialList??>
		              	<#list item.itemOrderSku.specialList as special>
		              	<span><label for="">${special.sname!''}：</label><font>${special.soption!''}</font></span>
		                </#list>
		                </#if>
	                </div>
	            </div>
	        </div>
        </#list>
        </#if>
        <div class="sobottom">
            共 <span>${quantity!''}</span> 件商品  合计<strong class="price">￥<span>${ship.returnAmount!''}</span></strong>
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
<#if ship.status ==1>
<footer>
   <div class="sp_foot">
       <a href="${app.basePath}/mobile/supply/refuse?shipId=${ship.id}" title="拒绝退款" class="sp_a1 fl">拒绝退款</a>
       <a href="javascript:;" onclick="agreeReturn(${ship.id})" title="同意退款" class="sp_a2 fl">同意退款</a>
   </div>
    <span class="footclear"></span>
</footer>
</#if>
<!-- Footer End -->
</body>
</html>