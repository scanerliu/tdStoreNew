<#import "/common/app.ftl" as app>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="description" content="中国创客联盟"/>
    <meta name="keywords" content="中国创客联盟"/>
    <meta name="author" content="中国创客联盟"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <title>订单详情- ${system.webkeywords!''}</title>
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
    <script src="${app.basePath}/static/js/client/order/orderlist.js" type="text/javascript"></script>
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
				    	</#if>
	                </strong>
	                <label>订单号：${order.orderNo!''}</label>
	                <#if order.payStatus==2><a href="${app.basePath}/order/dopay${order.orderId!'0'}" class="a-pay" title="">点击去付款</a></#if>
			        <#if order.orderStatus==3><a class="a-pay" href="javascript:;" onclick="receiptOrder2(${order.orderId!'0'})" title="">点击确认收货</a></#if>
			        <#if order.orderStatus==4 && order.commented==false><a class="a-pay" href="${app.basePath}/user/comment/${order.orderId!'0'}" title="去评价">点击去评价</a></#if>
	            </div>
	            <ul class="progress">
	                <li>
	                    <span class="icon icon1 on">
	                        <i></i>
	                        <label>提交订单</label>
	                        <p>&gt;</p>
	                    </span>
	                    <!--<span class="time">2016-02-15 10:24:56</span>-->
	                </li>
	                <li>
	                    <span class="icon icon2 <#if order.orderStatus?? && order.orderStatus gte 2>on</#if>">
	                        <i></i>
	                        <label>付款成功</label>
	                        <p>&gt;</p>
	                    </span>
	                    <!--<span class="time">2016-02-15 10:24:56</span>-->
	                </li>
	                <li>
	                    <span class="icon icon3 <#if order.orderStatus?? && order.orderStatus gte 3>on</#if>">
	                        <i></i>
	                        <label>商品出库</label>
	                        <p>&gt;</p>
	                    </span>
	                    <!--<span class="time">2016-02-15 10:24:56</span>-->
	                </li>
	                <li>
	                    <span class="icon icon4 <#if order.orderStatus?? && order.orderStatus gte 4>on</#if>">
	                        <i></i>
	                        <label>确认收货</label>
	                        <p>&gt;</p>
	                    </span>
	                    <!--<span class="time">2016-02-15 10:24:56</span>-->
	                </li>
	                <li>
	                    <span class="icon icon5 <#if order.commented?? && order.commented ==true>on</#if>">
	                        <i></i>
	                        <label>评价</label>
	                    </span>
	                    <!--<span class="time">2016-02-15 10:24:56</span>-->
	                </li>
	            </ul>
	        </div>
	        <!-- 订单信息 -->
	        <div class="logistic-info">
	            <div class="order-number">
	                <div class="h1">
	                    <label>订单状态</label>
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
				    	</#if>
				    	</span>
	                </div>
	                <div>
	                    <label>订&nbsp;&nbsp;单&nbsp;号</label>
	                    <span>${order.orderNo!''}</span>
	                </div>
	                <!--
	                <div>
	                    <label>物流跟踪</label>
	
	                    <div class="wuliuinfo" style="height: 30px;">
	                        <ul>
	                            <li class="top"><label>2016-02-15 12:24:56</label> 您提交了订单，请等待系统确认。</li>
	                            <li><label>2016-02-15 12:24:56</label> 已到达XXX</li>
	                            <li><label>2016-02-15 12:24:56</label> 已到达XXX</li>
	                            <li><label>2016-02-15 12:24:56</label> 已到达XXX</li>
	                            <li><label>2016-02-15 12:24:56</label> 已到达XXX</li>
	                        </ul>
	                        <a href="javascript:void(0);" id="wuliumore" class="amore" title="">展开</a>
	                    </div>
	
	                    <script>
	                        $(function () {
	                            var i = false;
	                            $('#wuliumore').click(function () {
	                                if(i==false){
	                                    $(this).parent().animate({'height':'auto'},600);
	                                    $(this).addClass('on').html('收起');
	                                }else{
	                                    $(this).parent().animate({'height':'30px'},600);
	                                    $(this).removeClass('on').html('展开');
	                                }
	                                i == false ? i = true : i = false;
	                            });
	                        });
	                    </script>
	                </div>
	                -->
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
	                    <span>${order.orderAddress.getSecretTel()!''}</span>
	                </div>
					</#if>
	                <div>
	                    <label>支付方式</label>
	                    <span>${order.paymentStr!''}</span>
	                </div>
	                <div>
	                    <label>运&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费</label>
	                    <span>￥${order.postage!'0'}</span>
	                </div>
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
	                    <p class="p3">商品总价（元）</p>
	                </div>
	                <#if order?? && order.productList?? && (order.productList?size > 0)>
				    <#list order.productList as product>
				    <ul>
	                    <li class="li1">
	                        <div class="img">
	                        	<#if product.attachments??>
					    		<#list product.attachments as atta>
					    		<#if atta_index==0>
					    		<img src="${app.basePath}${atta!''}" alt="商品图片"/>
					    		</#if>
					    		</#list>
					    		</#if>
	                        </div>
	                        <a class="writing" href="javascript:;" title="">${product.title!''} ${product.getItemTypeStr()!''}</a>
	                    </li>
	                    <li class="li2"></li>
	                    <li class="li3">${product.itemPrice!'0'}</li>
	                    <li class="li4">${product.quantity!'0'}</li>
	                    <li class="li3 last">${product.totalProductAmount!'0'}</li>
	                </ul>
					</#list>
				    </#if>
					<#if order.skuList??>
				    <#list order.skuList as sku>
				    <ul>
	                    <li class="li1">
	                        <div class="img">
	                            <img src="images/3.jpg" alt=""/>
	                        </div>
	                        <a class="writing" href="${app.basePath}/product/item${sku.productId!''}" title="">${sku.productName!''}</a>
	                    </li>
	                    <li class="li2">
	                    	<#if sku.specialList??>
			              	<#list sku.specialList as special>
			              	${special.sname!''}：${special.soption!''}&nbsp;&nbsp;
			                </#list>
			                </#if>
	                    </li>
	                    <li class="li3">${sku.price!''}</li>
	                    <li class="li4">${sku.quantity!'0'}</li>
	                    <li class="li3 last">${sku.totalProductAmount!'0'}</li>
	                </ul>
					</#list>
				    </#if>
	            </div>
	            <div class="jifen">
	                <div>
	                    <p class="p1">总商品金额</p>
	                    <p class="p2">￥${order.productAmount!'0'}</p>
	                </div>
	                <div>
	                    <p class="p1">- 积分</p>
	                    <p class="p2">${order.pointAmount!'0'}</p>
	                </div>
	                <div>
	                    <p class="p1">+ 运费</p>
	                    <p class="p2">${order.postage!'0'}</p>
	                </div>
	                <div class="div4">
	                    <p class="p1">应付总额</p>
	                    <p class="p2">¥${order.payAmount!'0'}</p>
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