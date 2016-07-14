<#import "/common/app.ftl" as app>
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
    <title>成为代理</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/common.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/main.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/swipe.css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/swiper.min.css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/index.css">
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script> 
    <script type="text/javascript" src="${app.basePath}/static/touch/js/swipe.js"></script> 
    <script type="text/javascript" src="${app.basePath}/static/touch/js/swiper.min.js"></script> 
    <script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/touch/js/index.js"></script>
</head>
<body class="bg2">

<!-- header_top -->
<div class="top_heater">
    <a href="${app.basePath}/mobile" title="返回" class="hleft hback"></a>
    <span>成为代理</span>
</div>
<!-- header_top end -->

<!-- Center Start -->
<!-- <section class="container"> -->
    <!-- 热销推荐 -->
    <div class="become_list">
    	<#if agentList??>
    	<#list agentList as ag>
        <a href="${app.basePath}/mobile/agent/producttype?agentId=${ag.id?c}" title="${ag.title!''}" class="">
            <img src="${app.basePath}${ag.imageUrl!''}" alt="${ag.title!''}">
            <p class="p1">${ag.title!''}</p>
            <p class="p2">￥<span><#if ag.salesPrice??>${ag.salesPrice?string('0.00')}</#if></span></p>
        </a>
        </#list>
        </#if>
    </div>
    <!-- 热销推荐-结束 -->
<!-- </section> -->
<!-- Center end -->

</body>
</html>