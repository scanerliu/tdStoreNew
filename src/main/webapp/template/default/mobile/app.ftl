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
    <title>下载app</title>
    <#include "/common/common.ftl" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/common.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/main.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/f-personalcenter.css"/>
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
</head>
<body>
<!-- header_top -->
<div class="top_heater">
    <a href="javascript:;" onclick="window.history.go(-1)" title="" class="hleft hback"></a>
    <span>下载app</span>
</div>
<!-- header_top end -->

<!-- Center Start -->
<section class="container appdownload">
    <span>Android下载</span>
    <article class="codeimg">
        <img src="${app.basePath}/static/default/mobile/images/codeimg.png" alt=""/>
        <a href="#" title="">Android下载</a>
    </article>
    <span>IOS下载</span>
    <article class="codeimg">
        <img src="${app.basePath}/static/default/mobile/images/codeimg.png" alt=""/>
        <a href="#" title="">IOS下载</a>
    </article>
</section>
<!-- Center End -->
<script>
$(function(){
});
</script>
</body>  
</html>