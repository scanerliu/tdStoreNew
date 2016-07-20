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
    <title>排行榜-全国</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/common.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/main.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/index.css">
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/touch/js/index.js"></script>
</head>

<script>
    window.onload=function(){
        ranktop();
        three_bing();
    }
</script>

<body class="body_bg">

<!-- header_top -->
<div class="top_heater">
    <a href="${app.basePath}/mobile" title="" class="hleft hback"></a>
    <span>排行榜</span>
</div>
<!-- header_top end -->

<!-- 地区三级联动 -->
<form action="${app.basePath}/mobile/campaign/list" id="form">
<div class="three3" id="three3">
    <section>
        <span><#if province??>${province.name!''}<#else>请选择</#if></span>
        <select name="provinceId" id="" >
            <option value="">请选择</option>
        	<#if districtList??>
        	<#list districtList as dis>
            	<option value="${dis.id?c}" <#if dissc.provinceId?? && dissc.provinceId = dis.id>selected</#if>>${dis.name!''}</option>
            </#list>
            </#if>
        </select>
    </section>
    <section>
        <span><#if city??>${city.name!''}<#else>请选择</#if></span>
        <select name="cityId" id="">
            <option value="">请选择</option>
            <#if cityList??>
        	<#list cityList as dis>
            	<option value="${dis.id?c}" <#if dissc.cityId?? && dissc.cityId = dis.id>selected</#if>>${dis.name!''}</option>
            </#list>
            </#if>
        </select>
    </section>
    <section>
        <span><#if regin??>${regin.name!''}<#else>请选择</#if></span>
        <select name="regionId" id="">
            <option value="">请选择</option>
            <#if regionList??>
        	<#list regionList as dis>
            	<option value="${dis.id?c}" <#if dissc.regionId?? && dissc.regionId = dis.id>selected</#if>>${dis.name!''}</option>
            </#list>
            </#if>
        </select>
    </section>
</div>
<!-- 地区三级联动-结束 -->

<!-- Center Start -->
<section class="container">
    <ul class="rank_top" id="rank_top">
        <#if campaignList??>
        <#list campaignList as comp>
        <li>
            <a class="fl">
                <img src="${app.basePath}${comp.uavatar!''}" alt="">
                <i></i>
            </a>
            <section class="fr">
                <aside class="as1">${comp.uname!''}</aside>
                <aside class="as2">${comp.resume!''}</aside>
                <aside class="as3"><menu>1962</menu>人</aside>
            </section>
        </li>
        </#list>
        </#if>
    </ul>
    <ul class="rank_match" id="rank_match">
    	<#if campaignList??>
        <#list campaignList as comp>
        <li class="">
            <div class="opa_bg" id="opa_bg">
                <section><img src="${app.basePath}${comp.uavatar!''}" alt="${comp.uname!''}"></section>
                <section>${comp.uname!''}</section>
                <section><span></span></section>
                <section>${comp.declaration!''}</section>
                <menu></menu>
            </div>
        </li>
        </#list>
     	</#if>
    </ul>
</section>
<!-- Center End -->

</body>
</html>