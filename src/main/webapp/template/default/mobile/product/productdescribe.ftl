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
    <title>商品详情</title>
    <#include "/common/common.ftl" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/common.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/main.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/index.css"/>
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/core.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/swipe.js"></script>
    <script src="${app.basePath}/static/js/mobile/index.js" type="text/javascript"></script>
    <script src="${app.basePath}/static/js/mobile/product/productdetail.js" type="text/javascript"></script>
</head>
<script>
    window.onload=function(){
        three2();
        acare();
    }
</script>
<body class="">

<!-- header_top -->
<div class="top_heater">
    <a href="javascript:;" onclick="window.history.go(-1)" title="" class="hleft hback"></a>
    <span>商品描述</span>
</div>
<!-- header_top end -->

<!-- Center Start -->
<section class="container">
    <div class="three2" id="three2">
        <a href="javascript:;" <#if sc.type==1>class="active"</#if>>图文详情</a>
        <a href="javascript:;" <#if sc.type==2>class="active"</#if>>包装配送</a>
        <a href="javascript:;" <#if sc.type==3>class="active"</#if>>售后服务 </a>
    </div>
    <ul class="three2_match" id="three2_match">
        <li <#if sc.type==1>class="active"</#if>>
        	${productdesc.description!''}
        </li>
        <li <#if sc.type==2>class="active"</#if>>
        	${delivedesc.description!''}
        </li>
        <li <#if sc.type==3>class="active"</#if>>
        	${servicedesc.description!''}
        </li>
    </ul>
</section>
<!-- Center End -->

<div style="height:0.1rem"></div>
<!-- Footer Start -->
<footer>
    <div class="gopay">
        <a href="javascript:;" class="acare" id="acare">关注</a>
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