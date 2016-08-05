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
    <title>搜索</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/common.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/main.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/index.css">
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script> 
    <script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/core.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/search/search.js"></script>
</head>

<script>
   $(function(){
	    getCookieValue();
	}); 
</script>

<body class="">

<!-- header_top -->
<div class="top_heater">
    <a href="javascript:history.go(-1);" title="返回" class="hleft hback"></a>
    <span>搜索</span>
</div>
<!-- header_top end -->

<!-- Center Start -->
<section class="container">
    <div class="find_page">
        <section class="sec1">
            <input type="text" placeholder="请输入关键字搜索..." id="keywords" class="fl">
            <a href="javascript:;" title="搜索" onclick="searchkey();" class="fl"></a>
        </section>
        <section class="sec2">
            <aside class="as1">
                <p class="p_title">搜索历史</p>
                <p class="p2" id="key1">
                </p>
            </aside>
            <aside class="as2">
                <p class="p_title">热门搜索</p>
                <p class="p2">
                	<#if system.hotsearchword??>
                	<#list system.hotsearchword?split(",") as key>
                    	<#if key!="">
                    	<a href="${app.basePath}/mobile/search/list?keyword=${key!''}" title="${key!''}">${key!''}</a>
                    	</#if>
                    </#list>
                	</#if>
                </p>
        </section>
    </div>
</section>
<!-- Center End -->
</body>
</html>