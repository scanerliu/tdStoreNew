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
    <title>首页</title>
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
        // news_lab2();
    }
</script>

<body class="body_bg">
<!-- 头部 -->
<div class="index_header">
    <div class="fl"><img src="${app.basePath}/static/touch/images/logo.png" alt=""></div>
    <a href="${app.basePath}/mobile/search" title="搜索" class="fl">搜索我的宝贝</a>
    <a href="#" title="" class="fr">分享</a>
</div>
<!-- 头部-结束 -->

<div class="my_banner1">
<!-- ****广告轮播**** -->
<div class="addWrap">
    <div class="swipe" id="mySwipe">
        <div class="swipe-wrap">
        	<#if adList?? && adList?size gt 0>
        	<#list adList as ad>
				<div><a href="${ad.linkUrl!''}"><img class="img-responsive" src="${ad.imageUrl!''}"/></a></div>
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

<!-- 股东竞选 -->
<div class="rank">
    <span></span>
    <label class="lab1">股东竞选</label>
    <section class="lab2" id="news_lab2">
        <ul>
        	<#if compAdList?? && compAdList?size gt 0>
        	<#list compAdList as comp>
            	<li><a href="${comp.linkUrl!''}" title="${comp.title!''}">${comp.title!''}</a></li>
			</#list>
			</#if>
        </ul>
    </section>
        <a href="javascript:;" title="更多" class="a_link"></a>
</div>
<!-- 股东竞选-结束 -->
<!-- 分类按钮 -->
<div class="clickbtn">
    <a href="${app.basePath}/mobile/agent/list" title="创业中心"><p>创业中心</p></a>
    <a href="${app.basePath}/mobile/campaign/list" title=""><p>排行榜</p></a>
    <a href="#" title=""><p>物流查询</p></a>
    <a href="${app.basePath}/mobile/user/center" title=""><p>个人中心</p></a>
    <a href="0元购列表.html" title=""><p>0元专区</p></a>
    <a href="秒杀列表.html" title=""><p>秒杀专区</p></a>
    <a href="产品列表.html" title=""><p>新品专区</p></a>
    <a href="${app.basePath}/mobile/productType/list" title="分类选择"><p>分类选择</p></a>
</div>
<!-- 分类按钮-结束 -->
<!-- 精品专区 -->
<div class="bestarea">
    <section class="index_title">
        <label class="lab1 fl">精品专区</label>
        <a href="产品列表.html" class="a_link"></a>
    </section>
    <section class="sec2">
    	<#if hotAdList?? && hotAdList?size gt 0>
    	<#list hotAdList as ad>
        	<a href="${ad.linkUrl!''}" title="${ad.title!''}" class=""><img src="${ad.imageUrl!''}" alt="${ad.title!''}"></a>
        </#list>
        </#if>
    </section>
</div>
<!-- 精品专区-结束 -->
<!-- 热销推荐 -->
<#if productList?? && productList?size gt 0 >
<div class="hot">
    <section class="index_title">
        <label class="lab1 fl">热销推荐</label>
        <a href="产品列表.html" class="a_link"></a>
    </section>
    <section class="sec2">
    	<#list productList as pro>
        <a href="${app.basePath}/mobile/product/item${pro.id!'0'}" title="${pro.name!''}" class="">
            <img src="${app.basePath}${pro.imageUrl!''}" alt="${pro.name}">
            <p class="p1">${pro.name}</p>
            <p class="p2">
                <label class="lab1">¥<#if pro.price??>${pro.price?string('0.00')}</#if></label>
                <label class="lab2">￥188.00</label>
            </p>
        </a>
        </#list>
    </section>
</div>
</#if>
<!-- 热销推荐-结束 -->
<!-- 底部 -->
<div style="width:100%;height:0.9rem"></div>
<div class="index_foot">
    <a href="${app.basePath}/mobile" title="首页" class="active"><p>首页</p></a>
    <a href="${app.basePath}/mobile/productType/list" title=""><p>分类</p></a>
    <a href="${app.basePath}/mobile/shoppingcart/list" title=""><p>购物车</p></a>
    <a href="${app.basePath}/mobile/user/center" title="个人中心"><p>我的</p></a>
</div>
<!-- 底部-结束 -->
</body>
</html>