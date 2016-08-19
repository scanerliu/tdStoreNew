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
    <title>商品详情-评价晒单</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/common.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/main.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/index.css">
    <!-- js -->
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script> 
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/index.js"></script>
    <script src="${app.basePath}/static/js/mobile/core.js" type="text/javascript"></script>
    <script src="${app.basePath}/static/js/mobile/product/productdetail.js" type="text/javascript"></script>
</head>
<body class="body_bg">

<!-- header_top -->
<div class="top_heater">
    <a href="javascript:history.go(-1);" title="返回" class="hleft hback"></a>
    <span>评价晒单</span>
</div>
<!-- header_top end -->

<!-- Center Start -->
<section class="container">
	<form id="searchform">
		<input type="hidden" id="sc_fliterType" name="fliter" value="0"/>
		<input type="hidden" name="productId" value="${productId!''}"/>
	</form>
    <div class="four" id="four">
        <section class="active" tid="0">
            <p>全部评价</p>
            <p><#if stat??>${stat.showreviewCount!'0'}</#if></p>
        </section>
        <section tid="1">
            <p>好评</p>
            <p><#if stat??>${stat.positiveRate!'0'}</#if></p>
        </section>
        <section tid="2">
            <p>中评</p>
            <p><#if stat??>${stat.neutralRate!'0'}</#if></p>
        </section>
        <section tid="3">
            <p>差评</p>
            <p><#if stat??>${stat.negativeRate!'0'}</#if></p>
        </section>
    </div>
    <ul class="four_match">
    	<form id="listform">
        <li class="active" id="results"></li>
        <input type="hidden" name="fliter" id="fliterType" value=""/>
        <input type="hidden" name="pageNo" id="pageNo" value="1"/> 
        <input type="hidden" name="productId" value="${productId!''}"/>       
        </form>
	</ul>
    
</section>
<!-- Center End -->

<!-- Footer Start -->
<#--
<footer>
    <div class="gopay">
        <a href="#" class="ajoin">加入购物车</a>
        <a href="#" class="apayfor" title="">立即购买</a>
        <a href="javascript:addCollect(${product.id?c})" class="<#if collect?? && collect ==true>acare2<#else>acare</#if>" id="acare">关注</a>
        <a href="javascript:;" class="ajoin" id="addCart" onclick="addToShoppingcart();">加入购物车</a>
        <a href="javascript:;" class="apayfor" id="buyNow" title="立即购买" onclick="buyNow();">立即购买</a>
    </div>
    <span class="footclear"></span>
</footer>
-->
<!-- Footer End -->
<script type="text/javascript">
      $(function(){
        function tab(tabTitle,tabList){
          $(tabTitle).on('click','section',function(){
            var $self = $(this);//当前a标签
            var index = $self.attr("tid");
            $("#sc_fliterType").val(index);
            searchComments(true);
			
          	$self.addClass('active').siblings().removeClass('active');
            
          });
        };
        tab('#four','');
        searchComments(true);
      })
    </script>
</body>
</html>