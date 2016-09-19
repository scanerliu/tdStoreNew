<#import "/common/app.ftl" as app>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="description" content="中国创客联盟"/>
    <meta name="keywords" content="中国创客联盟"/>
    <meta name="author" content="中国创客联盟"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <title>退货详情- ${system.webkeywords!''}</title>
    <!-- 网站图标 -->
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/default/client/style/site.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/personal-center-common.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/f_personal_center.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/lhead.css">
	<#include "/common/common.ftl" />
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/html5.js"></script>
    <!--通用js-->
    <script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/index.js"></script>
    <script src="${app.basePath}/static/js/client/supply/supply.js" type="text/javascript"></script>
    <style>
    .view-logistics .logistic-info .order-number .hei30{height:30px;}
    </style>
</head>
<body>
<h1 style="display:none;"></h1>
	<!-- Header -->
	<#include "../common/centerheader.ftl">
	<!-- Header -->
	<div class="clear"></div>
	<!-- Center Start -->
	<div class="index-main">
	    <div class="view-logistics submit-orders">
	        <!-- 进度信息 -->
	        <div class="progressitem">
	            <div class="progressinfo">
	                <strong class="p1">
	                	<#if ship.status??>
		          			  <#if ship.status==1>
				      			新申请
						      <#elseif ship.status==2>
						      		同意
						      <#elseif ship.status==3>
						      		不同意
						      <#elseif ship.status==4>
						      		待退款
						      <#elseif ship.status==5>
						      		完成
						      </#if>
					      </#if>
	                </strong>
	                <label>订单号：<#if ship.order??>${ship.order.orderNo!''}</#if></label>
	                <#if ship.status==2 && ship.cargoStatus==1><a href="${app.basePath}/order/refundtract${ship.id!'0'}" class="a-pay">同意退货</a></#if>
	            </div>
	        </div>
	        <!-- 订单信息 -->
	        <div class="logistic-info">
	            <div class="order-number">
	                <div class="h1">
	                    <label>退货状态</label>
	                    <span>
	                    <#if ship.status??>
		          			  <#if ship.status==1>
				      			新申请
						      <#elseif ship.status==2>
						      		同意
						      <#elseif ship.status==3>
						      		不同意
						      <#elseif ship.status==4>
						      		待退款
						      <#elseif ship.status==5>
						      		完成
						      </#if>
					      </#if>
				    	</span>
	                </div>
	                <div>
	                    <label>订&nbsp;&nbsp;单&nbsp;号</label>
	                    <span><#if ship.order??>${ship.order.orderNo!''}</#if></span>
	                </div>
	                <#if ship.trackExpress??>
	                <div>
	                    <label>物流信息</label>
	                    <span>${ship.trackExpress.name!''} ${ship.trackingNo!''}</span>
	                </div>
	                <div>
	                    <label>物流跟踪</label>
	                    <div id="postinfo" class="wuliuinfo hei30">
	                    </div>
	                    <script>
	                        $(function () {
	                            searchpostinfo("${ship.trackExpress.com!''}","${ship.trackingNo!''}");
	                        });
	                    </script>
	                </div>
	                </#if>
					<div>
	                    <label>下单时间</label>
	                    <span><#if order?? && order.createTime??>${order.createTime?string('yyyy-MM-dd HH:mm')}</#if></span>
	                </div>
	                <div>
	                    <label>退款时间</label>
	                    <span><#if ship.createTime??>${ship.createTime?string('yyyy-MM-dd HH:mm')}</#if></span>
	                </div>
	                <div>
	                    <label>退款原因</label>
	                    <span>${ship.returnCauseStr!''}</span>
	                </div>
					<div>
	                    <label>退货说明</label>
	                    <span>${ship.returnReason!''}</span>
	                </div>
					<div>
	                    <label>图片凭证</label>
	                    <span>
	                    <#if ship.voucherImageList??>
	                    <#list ship.voucherImageList as image>
	                    <a href="${app.basePath}${image!''}" target="_blank"><img src="${app.basePath}${image!''}" width="80px" height="80"/></a>
	                    </#list>
	                    </#if>
	                    </span>
	                </div>
	                <#if ship.remark??>
	                <div>
	                    <label>拒绝理由</label>
	                    <span>${ship.remark!''}</span>
	                </div>
	                </#if>
	            </div>
	        </div>
	        <!-- 商品信息表格 -->
	        <div class="pro-table">
	            <div class="table">
	                <div class="list-top">
	                    <p class="p1">商品名称</p>
	                    <p class="p2">商品属性</p>
	                    <p class="p3">单价（元）</p>
	                    <p class="p4">数量</p>
	                </div>
					<#if ship.itemList??>
	    			<#list ship.itemList as item>
				    <ul>
	                    <li class="li1">
	                        <div class="img">
	                            <img src="${app.basePath}${item.itemOrderSku.productImage!''}" alt=""/>
	                        </div>
	                        <a class="writing" href="${app.basePath}/product/item${item.itemOrderSku.productId!''}" title="">${item.itemOrderSku.productName!''}</a>
	                    </li>
	                    <li class="li2">
	                    	<#if item.itemOrderSku.specialList??>
			              	<#list item.itemOrderSku.specialList as special>
			              	${special.sname!''}：${special.soption!''}&nbsp;&nbsp;
			                </#list>
			                </#if>
	                    </li>
	                    <li class="li3">${item.itemOrderSku.price!''}&nbsp;</li>
	                    <li class="li4">${item.quantity!'0'}</li>
	                </ul>
					</#list>
				    </#if>
	            </div>
	            <div class="jifen">
	                <div>
	                    <p class="p1">申请退款金额</p>
	                    <p class="p2">￥${ship.returnAmount!''}</p>
	                </div>
	            </div>
	        </div>
	        <!-- 订单信息 -->
	        <div class="logistic-info">
	            <div class="order-number">
	                <#if order.orderAddress??>
					<div>
	                    <label>收&nbsp;&nbsp;货&nbsp;人</label>
	                    <span>${order.orderAddress.customerName!''}</span>
	                </div>
	                <div>
	                    <label>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</label>
	                    <span>${order.orderAddress.regionFullName!''}</span>
	                </div>
	                <div>
	                    <label>手机号码</label>
	                    <span>${order.orderAddress.telphone!''}</span>
	                </div>
					</#if>
					<div>
	                    <label>客户备注</label>
	                    <span>${order.userMessage!''}</span>
	                </div>
	                <div>
	                    <label>支付方式</label>
	                    <span>${order.paymentStr!''}</span>
	                </div>
	                <div>
	                    <label>运&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费</label>
	                    <span>￥${order.postage!'0'}</span>
	                </div>
	                <div>
	                    <label>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</label>
	                    <span>${order.userMessage!''}</span>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- Center End -->
	<!-- Footer Start -->
	<#include "../common/commonfooter.ftl">
	<!-- Footer End -->
</body>
</html>