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
    <title>零元区</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/common.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/main.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/swipe.css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/index.css">
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script> 
    <script type="text/javascript" src="${app.basePath}/static/touch/js/swipe.js"></script> 
    <script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/touch/js/index.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/product/search.js"></script>
</head>
<script>
    window.onload=function(){
        //two2();
    }
    
 $(document).ready(function(){   
	var url = '${app.basePath}/mobile/product/search/more';
	var cc=document.getElementById('two2');
	var aA=cc.getElementsByTagName('a');
	if(aA[0].className=='active'){
		$('#kill_list').refresh(url+"?kind=3","#kill_list",0);
	}
	if(aA[1].className=='active'){
		$('#persell_list').refresh(url+"?kind=4","#persell_list",0);
	}
})
  
</script>

<body class="body_bg">

<!-- header_top -->
<div class="top_heater">
    <a href="javascript:history.go(-1);" title="" class="hleft hback"></a>
    <span>零元区</span>
</div>
<!-- header_top end -->

<!-- Center Start -->
<section class="container">
    <!--<div class="two2" id="two2">
        <a href="javascript:;" title="零元购" class="active">零元购</a>
        <a href="javascript:;" title="10元购" class="">10元购</a>
    </div>-->
    <!-- 热销推荐 -->
    <div class="two2_match" id="two2_match">
        <div class="hot" style="display:block;">
            
            	<#if zeroList?? && zeroList?size gt 0>
            	<section class="sec2" id="kill_list">
            	<#list zeroList as item>
                <a href="${app.basePath}/mobile/product/item${item.id?c}" title="${item.name!''}" class="">
                    <img src="${app.basePath}${item.imageUrl!''}" alt="${item.name!''}">
                    <p class="p1">${item.name!''}</p>
                    <p class="p2">
                        <label class="lab1">¥<#if item.price??>${item.price?string('0.00')}</#if></label>
                        <label class="lab3 fr" style="font-size:.2rem;color:#999;">运费￥<#if item.postage??>${item.postage?string('0.00')}</#if></label>
                    </p>
                </a>
                </#list>
                
            	</section>
            	<#else>
            	<div style=" background:#f2f2f2;padding-bottom:0;display:inline-block;width:100%; text-align:center;padding-top:3rem;">
                		<span style="background:#f2f2f2;">暂无商品</span>
                	</div>
            </#if>
        </div>
        <div class="hot">
            <section class="sec2" id="persell_list">
            	<#if tenList??>
            	<#list tenList as item>
                <a href="${app.basePath}/mobile/product/item${item.id?c}" title="${item.name!''}" class="">
                    <img src="${app.basePath}${item.imageUrl!''}" alt="${item.name!''}">
                    <p class="p1">${item.name!''}</p>
                    <p class="p2">
                        <label class="lab1">¥<#if item.price??>${item.price?string('0.00')}</#if></label>
                        <label class="lab2">￥188.00</label>
                    </p>
                </a>
                </#list>
                </#if>
                
            </section>
        </div>
    </div>
    <!-- 热销推荐-结束 -->
</section>
<!-- Center end -->

</body>
</html>