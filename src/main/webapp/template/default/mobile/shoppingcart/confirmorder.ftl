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
  <form id="confirmorder" method="post" action ="">
	<section class="container">
	
	    <!-- order_detail_title -->
	    <#if address??>
	    <a href="${app.basePath}/mobile/user/shoppingAddress?addressId=${randomNo!''}" title="" class="sub_order p">
	        <p class="p1">
	            <label>${address.name!''}</label>
	            <span>${address.telphone?replace(address.telphone?substring(3,7),"****")}</span>
	        </p>
	        <p class="p2">${address.fullAddress!''}</p>
	    </a>
	    <#else>
	    <a href="${app.basePath}/mobile/user/shoppingAddressAdd?addressId=${randomNo!''}" title="" class="sub_order p">
	        <p class="p1">
	            <span></span>
	        </p>
	        <p class="p2">点击我去添加地址吧</p>
	    </a>
	    </#if>
	    <!-- order_detail_title_end -->
	
	    <!-- order_detail_title -->
	    <ul class="order_goods">
	    <#if shoppingcart?? && shoppingcart.itemList??>
        	<#list shoppingcart.itemList as item>
	        <li class="goods_box p">
	            <img alt="图片" src="${app.basePath}<#if item.product??>${item.product.imageUrl!''}</#if>"/>
	            <section>
	                <h3>${item.product.name!''}</h3>
	                <div>
	                	<#if item.productSku?? && item.productSku.specialList??>
		              	<#list item.productSku.specialList as special>
		              	<span><label for="">${special.sname!''}：</label><font>${special.soption!''}</font></span>
		                </#list>
		                </#if>
		                <span><label for="">数量：</label><font>${item.quantity!'0'}</font></span>
		                <span><label for="">邮费：</label><font>￥${item.postage!'0'}</font></span>
	                </div>
	                <p>￥${item.productSku.salesPrice!'0'}</p>
	            </section>
	        </li>
	        </#list>
	     </#if>
	    </ul>
	    <!-- order_detail_title_end -->
		<style>
	        .payinfo .sli{
	        position:relative;
	        padding-right:.3rem;
	        }
	        .payinfo .sli:after{
	        	content:"";
	        	position:absolute;
	        	right:0;
	        	top:.33rem;
	        	display:block;
	        	width:.14rem;
	        	height:.14rem;
	        	border:1px solid #ddd;
	        	border-left:none;
	        	border-top:none;
	        	transform:rotate(-45deg);
	        }
	        </style>
	    <!-- order-detail-pay -->
	    <ul class="payinfo">
	        <li class="sli">
	            <span>支付方式</span>
	            <select name="paymentId" class="fr" style="height:100%;border:none;appearance:none;-webkit-appearance:none;font-size:.3rem;color:#999;">
	                <#if shoppingcart.canUserAccount==true>
	            	<option value="4">钱包余额支付</option>
	            	</#if>
	            	<option value="1">支付宝支付</option>
	            	<option value="2">微信支付</option>
	            	<option value="3">银联支付</option>
	            </select>
	            <!--<a href="javascript:;"  class="fr wechatpay" title="">微信支付<i></i></a>-->
	        </li>
	        <#if shoppingcart.totalPointsUsed gt 0>
	        <li>
	            <span>使用积分</span>
	
	            <label class="lblcheckbox fr">可使用积分${shoppingcart.totalPointsUsed!'0'}抵扣￥${shoppingcart.totalPointAmount!'0'}<input id="userpoints" type="checkbox" name="usePoints" value="true"/><i class=""></i></label>
	            <script>
	                $(function(){
	                    $("#userpoints").click(function(){
	                        var checkval = $("input[type='checkbox']").is(':checked');
	                        if(checkval==true){
	                            $(this).siblings('i').addClass('checked');
	                            var totalAmount = ${shoppingcart.totalAmount!'0'};
	                            var totalPointAmount = ${shoppingcart.totalPointAmount!'0'};
	                            var amount = totalAmount - totalPointAmount;
	                            $("#totalAmountdv").html("￥"+amount);
	                        }else{
	                            $(this).siblings('i').removeClass('checked');
	                            $("#totalAmountdv").html("￥${shoppingcart.totalAmount!'0'}");
	                        }
	                    });
	                });
	            </script>
	        </li>
			</#if>
	        <li>
	            <span>买家留言</span>
	            <span class="message" style="width:70%;"><input type="text" name="userMsg" value="" placeholder="可填写您与卖家达成一致的要求" style="width:100%;text-align:right;border:none;"/></span>
	        </li>
	
	        <li class="total">
	           <label style="float:right">共${shoppingcart.totalcount}件商品，合计：<font color="red">￥${shoppingcart.totalAmount}</font></label>
	        </li>
	    </ul>
	    <input type="hidden" id="totalAmount" value="${shoppingcart.totalAmount!''}"/>
	    <input type="hidden" id="totalPointAmount" value="${shoppingcart.totalPointAmount!'0'}"/>
	    <input type="hidden" name="addressId" value="<#if address??>${address.id!''}</#if>"/>
	    <!-- order-detail-pay-end -->
	</section>
</form>
	<!-- Footer Start -->
	<footer>
	    <div class="gopay" onclick="genernateOrder()">
	        <span class="totalprice">总计：<font color="red" id="totalAmountdv">￥${shoppingcart.totalAmount!''}</font></span>
	        <a href="javascript:;" onclick="genernateOrder()" class="a-pay" title="">提交订单</a>
	    </div>
	    <span class="footclear" onclick="genernateOrder()"></span>
	</footer>
	<!-- Footer End -->
<script>
$(function(){
	
});
</script>
</body>  
</html>