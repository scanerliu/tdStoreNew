<#import "/common/app.ftl" as app>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="description" content="中国创客联盟"/>
    <meta name="keywords" content="中国创客联盟"/>
    <meta name="author" content="中国创客联盟"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <title>购买成功- ${system.webkeywords!''}</title>
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
</head>
<body>
<h1 style="display:none;"></h1>
	<!-- Header -->
	<#include "./common/centerheader.ftl">
	<!-- Header -->
	<div class="clear"></div>
	<!-- Center Start -->
	<div class="index-main">
	    <div class="view-logistics submit-orders shopp-car shopping-program">
        <ul class="shop-progress">
            <li class="li01 on">
                <i>1</i>
                <label>查看购物车</label>
            </li>
            <li class="on">
                <i>2</i>
                <label>确认订单信息</label>
            </li>
            <li class="on">
                <i>3</i>
                <label>在线付款</label>
            </li>
            <li class="on">
                <i>4</i>
                <label>交易成功</label>
            </li>
            <li>
                <i>5</i>
                <label>商品评论</label>
            </li>
        </ul>
        <div class="successinfo">
            <span><i></i>您已成功付款
            <#if order?? && order.payAmount??>
            	${order.payAmount?string('0.00')}元<label><a href="${app.basePath}/order/detail${order.orderId!'0'}" title="订单详情">订单详情</a></label>
            <#elseif order?? && order.amount??>
            	${order.amount?string('0.00')}元<label><a href="${app.basePath}/order/list" title="查看订单">查看订单</a></label>
            </#if>
            </span>
        </div>
        <div class="guess-like">
        	<!--猜您喜欢-->
            <form id="enjoyForm">
			<input type="hidden" name="pageNo" id="enjoysc_pageNo" value="1">
			<input type="hidden" name="pageSize" value="8"/>
			</form>
            <div class="title">
                <label>猜你喜欢</label>
                <a href="javascript:;" class="a-change" title="" id="enjoybtn">换一批</a>
            </div>
            <div class="pro-list">
                <ul id="enjoyList">
                </ul>
            </div>
        </div>
	    </div>
	    <div class="clear"></div>
	</div>
	<!-- Center End -->
	<!-- Footer Start -->
	<#include "./common/commonfooter.ftl">
	<!-- Footer End -->
	<script>
		$(function(){
			getenjoyproducts();
		});
	</script>
</body>
</html>