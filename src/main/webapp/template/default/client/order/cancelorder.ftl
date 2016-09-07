<#import "/common/app.ftl" as app>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="description" content="中国创客联盟"/>
    <meta name="keywords" content="中国创客联盟"/>
    <meta name="author" content="中国创客联盟"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <title>取消订单- ${system.webkeywords!''}</title>
    <!-- 网站图标 -->
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/default/client/style/site.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/personal-center-common.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/f_personal_center.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/lhead.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/index.css">
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
	    <div class="personal-center">
	        <!-- 左侧导航 -->
        	<#include "../common/centerleftmenu.ftl">
        	
	        <!-- 右边 -->
        	<div class="need_value l_right bg_white fr">
	            <div class="box1">
	                <div class="div1 fl">取消订单</div>
	            </div>
	            <div class="form_box3 all_nation">
					<form id="cancelForm" class="obody">
					<input type="hidden" name="orderId" value="${order.orderId!'0'}"/>
	                <div class="div1 div_obox">
	                    <label class="lab1 fl"><span>*</span>申请服务</label>
	                    <span>取消订单</span>
	                </div>
	                <div class="div1 div_obox">
	                    <label class="lab1 fl"><span>*</span>取消原因</label>
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
	                </div>
	                <div class="div2 div_obox">
	                    <label class="lab1 fl">温馨提示：</label>
						<label class="lab2 fl">订单成功取消后无法恢复。 该订单已付金额将返还个人钱包账号中。</label>
	                </div>
	                <div class="div_sure sub_div"><input type="button" value="确认提交" class="btn" onclick="cancelOrder()" style="width:146px; height:40px;border-radius:0;"></div>
	            </div>
				</form>
	        </div>
	    </div>
	    <div class="clear"></div>
	</div>
	<!-- Center End -->
	<!-- Footer Start -->
	<#include "../common/commonfooter.ftl">
	<!-- Footer End -->
<script>
	$(function(){
	});
</script>
</body>
</html>