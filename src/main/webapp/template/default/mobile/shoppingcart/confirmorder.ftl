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
    <title>结算</title>
    <#include "/common/common.ftl" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/common.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/main.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/index.css"/>
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/core.js"></script>
    <script src="${app.basePath}/static/js/mobile/index.js" type="text/javascript"></script>
    <script src="${app.basePath}/static/js/mobile/shoppingcart/shoppingcartlist.js" type="text/javascript"></script>
</head>
<script>
</script>
<body class="body_gray">
  <!-- header_top -->
  <div class="top_heater">
    <a href="javascript:;" onclick="window.history.go(-1)" title="" class="hleft hback"></a>
    <span>结算</span>
  </div>
  <!-- header_top end -->
	<section class="container">
	
	    <!-- order_detail_title -->
	    <a href="normalUser/收货地址.html" title="" class="sub_order p">
	        <p class="p1">
	            <label>周大江</label>
	            <span>1388***5583</span>
	        </p>
	        <p class="p2">重庆市渝中区解放碑街道邹容路50号半岛国际大厦18-A</p>
	    </a>
	    <!-- order_detail_title_end -->
	
	    <!-- order_detail_title -->
	    <ul class="order_goods">
	    <#if shoppingcart?? && shoppingcart.itemList??>
        	<#list shoppingcart.itemList as item>
	        <li class="goods_box p">
	            <img alt="图片" src="${app.basePath} <#if item.product??>${item.product.imageUrl!''}</#if>"/>
	            <section>
	                <h3>${item.product.name!''}</h3>
	                <div>
	                	<#if item.productSku?? && item.productSku.specialList??>
		              	<#list item.productSku.specialList as special>
		              	<span><label for="">${special.sname!''}：</label><font>${special.soption!''}</font></span>
		                </#list>
		                </#if>
		                <span><label for="">数量：</label><font>${item.quantity!'0'}</font></span>
	                </div>
	                <p>￥${item.productSku.salesPrice!'0'}</p>
	            </section>
	        </li>
	        </#list>
	     </#if>
	    </ul>
	    <!-- order_detail_title_end -->
	
	    <!-- order-detail-pay -->
	    <ul class="payinfo">
	        <li>
	            <span>支付方式</span>
	            <a href="成为代理-选择付款方式.html"  class="fr wechatpay" title="">微信支付<i></i></a>
	        </li>
	        <li>
	            <span>使用积分</span>
	
	            <label class="lblcheckbox fr">可使用积分${shoppingcart.totalPointsUsed!'0'}抵扣￥${shoppingcart.totalPointAmount!'0'}<input id="checkbox" type="checkbox" checked /><i class="checked"></i></label>
	            <script>
	                $(function(){
	                    $("#checkbox").click(function(){
	                        var checkval = $("input[type='checkbox']").is(':checked');
	                        if(checkval==true){
	                            $(this).siblings('i').addClass('checked');
	                        }else{
	                            $(this).siblings('i').removeClass('checked');
	                        }
	                    });
	                });
	            </script>
	        </li>
	
	        <li>
	            <span>买家留言</span>
	
	            <span class="message"><input type="text" value="" placeholder="可填写您与卖家达成一致的要求"/></span>
	
	        </li>
	
	        <li class="total">
	           <label style="float:right">共${shoppingcart.totalcount}件商品，合计：<font color="red">￥${shoppingcart.totalAmount}</font></label>
	        </li>
	    </ul>
	    <!-- order-detail-pay-end -->
	</section>

	<!-- Footer Start -->
	<footer>
	    <div class="gopay">
	        <span class="totalprice">总计：<font color="red">￥${shoppingcart.totalAmount}</font></span>
	        <a href="javascript:;" onclick="genernateOrder()" class="a-pay" title="">提交订单</a>
	    </div>
	    <span class="footclear"></span>
	</footer>
	<!-- Footer End -->
<script>
$(function(){
	
});
</script>
</body>  
</html>