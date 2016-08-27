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
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/swipe.css"/>
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/core.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/swipe.js"></script>
    <script src="${app.basePath}/static/js/mobile/index.js" type="text/javascript"></script>
    <script src="${app.basePath}/static/js/mobile/package/package.js" type="text/javascript"></script>
</head>
<script>
    window.onload=function(){
        //var color_ul=document.getElementById('color_ul');
        //var size_ul=document.getElementById('size_ul');
        //ul_li(color_ul);
        //ul_li(size_ul);
        //acare();
       // iswiper();
    }
</script>
<style>
.ogray{background:#ddd;}
.swip2 img{height:auto;}
.index_box{
    margin-top:.1rem;
    background:#fff;
}
.ibox_title{
    overflow:hidden;
    padding:0 .2rem;
    height:.8rem;
    line-height:.8rem;
    font-size:.26rem;
    border-bottom:1px solid #f5f5f5;
}
.ibox_title a{
    display:block;
    width:.2rem;
    height:.8rem;
    /*background:url(../images/ri.png) no-repeat center;*/
    background-size:.15rem .26rem;
}

.index_cheap{
    overflow:hidden;
    width:100%;
    height:2.5rem;
}
.iswiper{
    position:absolute;
    left:0;
    top:0;
    overflow:hidden;
}
.index_cheap a{
    display:block;
    float:left;
    overflow:hidden;
    width:1.6rem;
    height:2.5rem;
}
.index_cheap a img{
    display:block;
    margin:0.2rem auto 0.1rem;
    width:1.3rem;
    height:1.3rem;
}
.index_cheap a p{
    overflow:hidden;
    padding:0 5%;
    width:90%;
    height:0.35rem;
    line-height:0.35rem;
    text-align:left;
    font-size:0.16rem;
    color:#333;
}
.index_cheap a .p2 .lab1{
    color:#ef0000;
}
.index_cheap a .p2 .lab2{
    font-size:.14rem;
    text-decoration:line-through;
    color:#999;
}
</style>
<body class="body_bg">
  <!-- header_top -->
<div class="top_heater">
    <a href="javascript:;" onclick="window.history.go(-1)" title="" class="hleft hback"></a>
    <span>商品详情</span>
</div>
<!-- header_top end -->

	    <div class="my_banner1" style="height:4.5rem">
			<!-- ****广告轮播**** -->
			<div class="addWrap">
			    <div class="swipe swip2" id="mySwipe">
			        <div class="swipe-wrap">
			        	<#if attachmentList?? && (attachmentList?size > 0)>
			        	<#list attachmentList as attachment>
		                <div><a href="javascript:;"><img class="img-responsive" src="${app.basePath}${attachment.attachment!''}" alt="商品图片"/></a></div>
		                </#list>
		                <#else>
		                <div><a href="javascript:;"><img class="img-responsive" src="${app.basePath}${product.imageUrl!''}" alt="商品图片"/></a></div>
		        		</#if>
			        </div>
			    </div>
			    <ul id="position">
			    	<#if attachmentList?? && (attachmentList?size > 0)>
			        	<#list attachmentList as attachment>
			          	<li class="curr"></li>
			            </#list>
		            <#else>
		                <li class="curr"></li>
		        	</#if>
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
    <div class="detail2">
        <p>${product.name!''}</p>
        <p>${product.title!''}</p>
        <p>
        	<label for="" class="fl">￥<span id="prodprice">${product.price!''}</span></label>
        </p>
    </div>
    <!-- 促销活动 -->
    <div class="index_box">
        <div class="ibox_title">
            <label class="fl">套餐包含商品</label>
        </div>
        <div class="ibox_body index_cheap iswiper_box">
            <div class="iswiper">
            	<#if itemList??>
		        	<#list itemList as item>
		                <a href="${app.basePath}/mobile/product/item${item.preprouductId!'0'}" title="">
		                    <img src="${app.basePath}${item.productImage!''}" alt="" />
		                    <p class="p1">${item.productName!''}</p>
		                    <p class="p2">
		                        <label class="lab1">￥${item.price!'0'}</label>
		                    </p>
		                </a>
	                </#list>
	        	</#if>
            </div>
        </div>
    </div>
    <div class="detail3" id="slect">
    </div>
    <div class="detail4">
        <section>
            <label class="fl">运费</label>
            <aside class="fl">${product.postage!'0'}元</aside>
        </section>
        <section>
            <label class="fl">服务</label>
            <aside class="fl">由<span>平台</span>负责售后服务。</aside>
        </section>
        <#if product.tags??>
        <section>
            <label class="fl">提示</label>
            <aside class="fl">${product.tags!''}</aside>
        </section>
        </#if>
    </div>
    <div class="detail5">
        <a href="${app.basePath}/mobile/product/describe/1/${product.id}" title="">图文详情</a>
        <!--<a href="${app.basePath}/mobile/product/comment${product.id}" title="">评价晒单</a>-->
        <a href="${app.basePath}/mobile/product/describe/2/${product.id}" title="">包装与配送</a>
        <a href="${app.basePath}/mobile/product/describe/3/${product.id}" title="">售后服务</a>
    </div>
    <#if recommendList??>
    <div class="detail6">
        <section class="title">商品推荐</section>
        <section class="sec2">
            <aside>
            	<#list recommendList as recommend>
		            <a href="${app.basePath}/mobile/product/item${recommend.id}" title="${recommend.title!''}">
	                    <img src="${app.basePath}${recommend.imageUrl!''}" alt="图片">
	                    <p>${recommend.name!''}</p>
	                    <p>￥${recommend.price!'0'}</p>
                	</a>
		        </#list>
            </aside>
        </section>
    </div>
    </#if>
</section>
<!-- Center end -->
<form id="agentform" method="post" action="">
	<input type="hidden" name="agentProductId" id="agentProductId" value=""/>
	<input type="hidden" name="productType" id="productType" value="2"/>
	<input type="hidden" name="productTypeId" id="productTypeId" value=""/>
	<input type="hidden" name="regionId" id="regionId" value=""/>
	<input type="hidden" id="quantity" name="quantity" value="1">
    <input type="hidden" id="propostage" value="${product.postage!'0'}">
    <input type="hidden" id="productId" name="productId" value="${product.id!''}">
</form>
<div style="height:0.1rem"></div>
<!-- Footer Start -->
<footer>
    <div class="gopay">
        <a href="javascript:;" class="apayfor" id="buyNow" title="立即购买" onclick="buyNow();">立即购买</a>
    </div>
    <span class="footclear"></span>
</footer>
<!-- Footer End -->
<script>
$(function(){
	//读取配置
	
});
</script>
</body>  
</html>