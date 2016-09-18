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
    <title>订单详情</title>
    <#include "/common/common.ftl" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/common.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/main.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/x_pc.css"/>
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/core.js"></script>
    <script src="${app.basePath}/static/js/mobile/order/orderlist.js" type="text/javascript"></script>
</head>
<body class="body_gray">
  <!-- header_top -->
  <div class="top_heater">
    <a class="hleft hback" href="javascript:;" onclick="window.history.go(-1)" title=""></a>
    <span>订单详情</span>
  </div>
  <!-- header_top end -->
  <section class="container order-details">
  	<#if order.orderAddress??>
	<div class="order_detail p">
		<label>${order.orderAddress.customerName!''}</label>
		<span>${order.orderAddress.getSecretTel()!''}</span>
		<p>${order.orderAddress.regionFullName!''} ${order.orderAddress.address!''}</p>
	</div>
	</#if>
	<!-- order_detail_title_end -->
	<!-- order_detail_title -->
	<ul class="order_goods">
		<li class="order_name p">
			<p class="left">
				<label>订单号：</label>
				<span>${order.orderNo!''}</span>
			</p>
			<p class="right">
				<label>交易状态：</label>
				<span>
					<#if order.orderStatus==1>
						待支付
					<#elseif order.orderStatus==2>
			      		待发货
			    	<#elseif order.orderStatus==3>
			      		待收货
			    	<#elseif order.orderStatus==4>
			      		交易完成
			      	<#elseif order.orderStatus==5>
			      		申请退款
			      	<#elseif order.orderStatus==6>
			      		交易完成
			      	<#elseif order.orderStatus==-1>
			      		已取消
			    	</#if>
				</span>
			</p>
		</li>
		<#if order?? && order.productList?? && (order.productList?size > 0)>
	    <#list order.productList as product>
		<li class="goods_box p">
			<#if product.attachments??>
    		<#list product.attachments as atta>
    		<#if atta_index==0>
    		<img src="${app.basePath}${atta!''}" alt="商品图片"/>
    		</#if>
    		</#list>
    		</#if>
			<section>
				<h3>${product.title!''} ${product.getItemTypeStr()!''}</h3>
				<div>
	          		<span><label class="lab2"><span>${product.quantity!'0'}</span></label></span>
				</div>
				<p>￥${product.itemPrice!'0'}</p>
				<div class="button-group">
					<a href="${app.basePath}/mobile/order/complaint${order.orderId!'0'}" class="alinkb">投诉建议</a>
				</div>
	
			</section>
		</li>
		</#list>
	    </#if>
		<#if order.skuList??>
	    <#list order.skuList as sku>
		<li class="goods_box p">
			<img src="${app.basePath}${sku.productImage!''}" alt="商品图片"/>
			<section>
				<h3>${sku.productName!''}</h3>
				<div>
					<#if sku.specialList??>
	              	<#list sku.specialList as special>
	              	<span><label for="">${special.sname!''}：</label><font>${special.soption!''}</font></span>
	                </#list>
	                </#if>
					<span><label for="">数量：</label><font>X${sku.quantity!'0'}</font></span>
				</div>
				<p>￥${sku.price!'0'}</p>
				<div class="button-group">
					<!--
	            立即退货 ：
	             点第一次的时候，提交退货申请
	             点第二次的时候，提交物流信息
	              第二次点完之后，按钮变成待商家收货，商家收货成功后变成退货成功
	            如果商家拒绝退货，立即退货按钮旁边有个投诉建议按钮 点进去是投诉建议页面
	            -->
					<#if order.orderStatus==3 || order.orderStatus==4><a href="${app.basePath}/mobile/order/applyrefund${order.orderId!'0'}?skuId=${sku.orderSkuId?c}" class="alinkb">申请退货</a></#if>
					<!--↑点击之后变成↓-->
					<!--<a href="../提交物流信息.html" class="alinkb">提交物流信息</a>-->
					<!-- ↑点击之后变成↓ -->
					<!--<a href="../申请退货.html" class="alinkb">等待商家收货</a>-->
	
					<a href="${app.basePath}/mobile/order/complaint${order.orderId!'0'}" class="alinkb">投诉建议</a>
				</div>
	
			</section>
		</li>
		</#list>
	    </#if>
		<li class="order_foot p">
			共 <label for="">${order.itemNum!'0'}</label>件商品，总价：<span>￥${order.totalAmount!'0'}</span>
		</li>
	</ul>
	<!-- order_detail_title_end -->
	<!-- order_time -->
	<#if ordershipment??>
	<ul class="detail_order">
		<li class="title">
			物流详情
		</li>
		<li class="name">
			<label>物流公司：</label>
			<span>${ordershipment.trackExpress.name!''}</span>
			<label>物流单号：</label>
			<span>${ordershipment.trackingNo!''}</span>
		</li>
		<dl id="postinfo"></dl>
	</ul>
	 <script>
        $(function () {
            searchpostinfo("${ordershipment.trackExpress.com!''}","${ordershipment.trackingNo!''}");
        });
    </script>
	</#if>
	<!-- order_time_end -->
</section>
</body>
</html>