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
        //var color_ul=document.getElementById('color_ul');
        //var size_ul=document.getElementById('size_ul');
        //ul_li(color_ul);
        //ul_li(size_ul);
        acare();
    }
</script>
<style>
.ogray{background:#ddd;}
</style>
<body class="body_bg">
  <!-- header_top -->
<div class="top_heater">
    <a href="javascript:;" onclick="window.history.go(-1)" title="" class="hleft hback"></a>
    <span>商品详情</span>
</div>
<!-- header_top end -->

<!-- Center Start -->
<section class="container">
    <div class="detail1"><img src="${app.basePath}${product.imageUrl!''}" alt=""></div>
    <div class="detail2">
        <p>${product.name!''}</p>
        <p>${product.title!''}</p>
        <p><label for="" class="fl">￥<span id="prodprice">${product.price!''}</span></label></p>
    </div>
    <div class="detail3">
        <section>
            <label class="fl">已选</label>
            <aside class="fl" id="prodchecked">
                <span>粉红色</span>
                <span>XL</span>
                <span>1件</span>
            </aside>
        </section>
        <#if taList??>
        <#list taList as typeatt>
        <section>
            <label class="fl">${typeatt.attribute.name}</label>
            <ul class="fl slect" id="attul_${typeatt.attribute.attriId}">
            	<#if typeatt.attribute.tdProductAttributeOptionList??>
            	<#list typeatt.attribute.tdProductAttributeOptionList as option>
                <li attri="${option.id}" <#if option.status==0>class="ogray"</#if>>${option.name}</li>
                </#list>
        		</#if>
            </ul>
            <script>
				$(function(){
					var attul_${typeatt.attribute.attriId} = document.getElementById('attul_'+${typeatt.attribute.attriId});
				    ul_li(attul_${typeatt.attribute.attriId});
				});
			</script>
        </section>
        </#list>
        </#if>
        <section>
            <label class="fl">尺寸</label>
            <ul class="fl slect" id="size_ul">
                <li>S</li>
                <li class="active">M</li>
                <li class="ogray">L</li>
                <li>XL</li>
                <li>XXL</li>
            </ul>
        </section>
        <section>
            <label class="fl">数量</label>
            <aside class="fl">
                <span>-</span>
                <input type="text" placeholder="1" value="1" id="prodquantity">
                <span>+</span>
            </aside>
        </section>
        <input type="hidden" id="skustock" value="0">
    </div>
    <div class="detail4">
        <section>
            <label class="fl">送至</label>
            <aside class="fl">
                <span>重庆市</span>
                <span>渝北区</span>
                <span>城区</span>
                <a href="#" title=""></a>
            </aside>
        </section>
        <section>
            <label class="fl">运费</label>
            <aside class="fl">${product.postage!'0'}元</aside>
        </section>
        <section>
            <label class="fl">服务</label>
            <aside class="fl">由<span>江北区单鞋代理</span>发货并负责售后服务。</aside>
        </section>
        <section>
            <label class="fl">提示</label>
            <aside class="fl">${product.tags!''}</aside>
        </section>
    </div>
    <div class="detail5">
        <a href="商品详情-图文详情.html" title="">图文详情</a>
        <a href="商品详情-评价晒单.html" title="">评价晒单</a>
        <a href="商品详情-包装配送.html" title="">包装与配送</a>
        <a href="商品详情-售后服务.html" title="">售后服务</a>
    </div>
    <div class="detail6">
        <section class="title">商品推荐</section>
        <section class="sec2">
            <aside>
                <a href="#" title="">
                    <img src="images/goods1.png" alt="">
                    <p>井记和田玉平安扣吊坠男女款</p>
                    <p>￥799.00</p>
                </a>
                <a href="#" title="">
                    <img src="images/goods1.png" alt="">
                    <p>井记和田玉平安扣吊坠男女款</p>
                    <p>￥799.00</p>
                </a>
                <a href="#" title="">
                    <img src="images/goods1.png" alt="">
                    <p>井记和田玉平安扣吊坠男女款</p>
                    <p>￥799.00</p>
                </a>
                <a href="#" title="">
                    <img src="images/goods1.png" alt="">
                    <p>井记和田玉平安扣吊坠男女款</p>
                    <p>￥799.00</p>
                </a>
                <a href="#" title="">
                    <img src="images/goods1.png" alt="">
                    <p>井记和田玉平安扣吊坠男女款</p>
                    <p>￥799.00</p>
                </a>
                <a href="#" title="">
                    <img src="images/goods1.png" alt="">
                    <p>井记和田玉平安扣吊坠男女款</p>
                    <p>￥799.00</p>
                </a>
            </aside>
        </section>
    </div>
</section>
<!-- Center end -->

<div style="height:0.1rem"></div>
<!-- Footer Start -->
<footer>
    <div class="gopay">
        <a href="javascript:;" class="acare" id="acare">关注</a>
        <a href="javascript:;" class="ajoin">加入购物车</a>
        <a href="javascript:;" class="apayfor" title="">立即购买</a>
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