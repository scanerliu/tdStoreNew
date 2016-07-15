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
    <title>产品列表</title>
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
<body class="body_bg">

<!-- header_top -->
<div class="top_heater">
    <a href="javascript:history.go(-1);" title="返回" class="hleft hback"></a>
    <span><#if productType??>${productType.name!''}</#if></span>
</div>
<!-- header_top end -->

<!-- Center Start -->
<!-- <section class="container"> -->
    <div class="three">
        <a href="#" title="" class="active">综合</a>
        <a href="#" title="" class="">销量</a>
        <a href="#" title="" class="">价格</a>
    </div>
 
    <div class="strenth">
        <!-- Swiper -->
        <div class="swiper-container">
            <div class="swiper-wrapper" id="swiper-wrapper">
                <div class="swiper-slide"><a href="产品列表.html" title="">翡翠玉石</a></div>
                <div class="swiper-slide"><a href="#" title="">发饰/发卡</a></div>
                <div class="swiper-slide"><a href="#" title="">3</a></div>
                <div class="swiper-slide"><a href="#" title="">4</a></div>
                <div class="swiper-slide"><a href="#" title="">5</a></div>
                <div class="swiper-slide"><a href="#" title="">6</a></div>
                <div class="swiper-slide"><a href="#" title="">7</a></div> 
            </div>
            <!-- Add Pagination -->
            <!-- <div class="swiper-pagination"></div> -->
        </div>
        <!-- Initialize Swiper -->
        <script>
        var swiper = new Swiper('.swiper-container', {
            pagination: '.swiper-pagination',
            slidesPerView: 3,
            paginationClickable: true,
            // spaceBetween: 30
        });
        </script>
        <!-- Swiper-end -->
    </div>

    <!-- 热销推荐 -->
    <div class="hot">
        <section class="sec2">
        	<#if productList??>
        	<#list productList as item>
            <a href="商品详情.html" title="${item.name!''}" class="">
                <img src="${app.basePath}${item.imageUrl!''}" alt="${item.name!''}">
                <p class="p1">${item.name!''}</p>
                <p class="p2">
                    <label class="lab1">¥128.00</label>
                    <label class="lab2">￥188.00</label>
                </p>
            </a>
            </#list>
            </#if>
        </section>
    </div>
    <!-- 热销推荐-结束 -->
<!-- </section> -->
<!-- Center end -->

</body>
</html>