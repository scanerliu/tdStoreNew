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
    <title>附近门店详情页</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/common.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/main.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/swipe.css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/index.css">
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/touch/js/swipe.js"></script> 
    <script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>
</head>

<script>
    window.onload=function(){
        
    }
</script>

<body class="bg_2">

<!-- header_top -->
<div class="top_heater">
    <a href="javascript:history.go(-1);" title="返回" class="hleft hback"></a>
    <span>附近门店</span>
</div>
<!-- header_top end -->

<div class="my_banner2">
    <!-- ****广告轮播**** -->
    <div class="addWrap">
        <div class="swipe" id="mySwipe">
            <div class="swipe-wrap">
            	<#if experexce.storeImages??> 
	                <#list experexce.storeImages?split(",") as uri>
	                    <#if uri!="">
	                        <div><a href="javascript:;"><img  class="img-responsive" src="${uri!''}"/></a></div>
	                    </#if>
	                </#list>
	            </#if>
            </div>
        </div>
        <ul id="position">
              <li class="cur"></li>
              <li class=""></li>
              <li class=""></li>
        </ul>
    </div> 
    <script type="text/javascript">
        var bullets = document.getElementById('position').getElementsByTagName('li');
        var banner = Swipe(document.getElementById('mySwipe'), {
            auto: 3000,
            continuous: true,
            disableScroll:false,
            callback: function(pos) {
                var i = bullets.length;
                while (i--) {
                  bullets[i].className = ' ';
                }
                bullets[pos].className = 'cur';
            }
        });
    </script>
    <!-- ****广告轮播-结束**** -->
</div>

<!-- Center Start -->
<section class="container">
    <div class="store_detail">
        <section class="sec1">${experexce.regionFullName!''}体验代购店</section>
        <section class="sec2">
            <aside class="as1">
                <label for="" class="fl">联系电话</label>
                <span class="fr">${experexce.telphone!''}</span>
            </aside>
            <aside class="as2">
                <label for="" class="fl">详细地址</label>
                <span class="fr">${experexce.regionFullName!''}${experexce.address!''}</span>
            </aside>
            <article class="art3">
                <p class="p1">代理类别</p>
                <p class="p2">
                	<#if experexce.storeTypeNames??> 
		                <#list experexce.storeTypeNames?split(",") as type>
		                    <#if type !="">
		                        <a href="javascript" title="${type!''}" class="">${type!''}</a>
		                    </#if>
		                </#list>
		            </#if>
                </p>
            </article>
        </section>
        <a href="导航" title="${app.basePath}/mobile/experience/map?id=${experexce.id?c}" class="bottom_nav"><span>导航</span></a>
    </div>
</section>
<!-- Center End -->
</body>
</html>