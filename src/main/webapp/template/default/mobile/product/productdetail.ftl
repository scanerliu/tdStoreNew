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
.swip2 img{height:auto;}
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
        	<#-- 预售、秒杀倒计时  -->
        	<#if product.kind ==5 || product.kind ==6>
        	 <label for="" id="timeLeft" class="fr">离结束：<span>00</span>天 <span>00</span>时<span>00</span>分<span>00</span>秒</label>
<script>
$(document).ready(function(){
    setInterval("timer()",1000);
});

function checkTime(i)  
{  
    if (i < 10) {  
        i = "0" + i;  
    }  
    return i;  
}

function timer()
{
	    var ts = (new Date(${product.endTime?string("yyyy")}, 
                parseInt(${product.endTime?string("MM")}, 10)-1, 
                ${product.endTime?string("dd")}, 
                ${product.endTime?string("HH")}, 
                ${product.endTime?string("mm")}, 
                ${product.endTime?string("ss")})) - (new Date());//计算剩余的毫秒数
  
    var allts = (new Date(${product.endTime?string("yyyy")}, 
                parseInt(${product.endTime?string("MM")}, 10)-1, 
                ${product.endTime?string("dd")}, 
                ${product.endTime?string("HH")}, 
                ${product.endTime?string("mm")}, 
                ${product.endTime?string("ss")}))
               - (new Date(${product.startTime?string("yyyy")}, 
                parseInt(${product.startTime?string("MM")}, 10)-1, 
                ${product.startTime?string("dd")}, 
                ${product.startTime?string("HH")}, 
                ${product.startTime?string("mm")}, 
                ${product.startTime?string("ss")}));//总共的毫秒数 
    
    if (0 == ts)
    {
        window.location.reload();
    }
  
    var date = new Date();
    var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);//计算剩余的天数
    var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);//计算剩余的小时数
    var mm = parseInt(ts / 1000 / 60 % 60, 10);//计算剩余的分钟数
    var ss = parseInt(ts / 1000 % 60, 10);//计算剩余的秒数
     if(ss < 0){
    	ss = 0;
    }
    if(mm < 0){
    	mm = 0;
    }
    if(hh < 0){
    	hh = 0;
    }
    if(dd < 0){
    	dd = 0;
    }
    dd = checkTime(dd);
    hh = checkTime(hh);
    mm = checkTime(mm);
    ss = checkTime(ss);
    $("#timeLeft").html("离结束：<span>"+dd+"</span>天<span>"+hh+"</span>时<span>"+mm+"</span>分<span>"+ss+"</span>秒");
    
    // 结束
    if(dd == 0 && hh == 0 && mm == 0 && ss == 0){
    	$("#addCart").removeAttr("onclick");
    	$("#buyNow").removeAttr("onclick");
    	$("#addCart").css("background","#ddd")
    	$("#buyNow").css("background","#ddd")
    }
}
</script>        	 
        	</#if>
        </p>
        
    </div>
    <div class="detail3" id="slect">
        <section>
            <label class="fl">已选</label>
            <aside class="fl" id="prodchecked">
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
				    $("#attul_"+${typeatt.attribute.attriId}).on("click","li",function(){
				    	if(this.className!="ogray"){
							$(this).siblings().removeClass("active");
							$(this).addClass("active");
							changeProductSku(__skuJsons);
						}
				    });
				});
			</script>
        </section>
        </#list>
        </#if>
        <section>
            <label class="fl">数量</label>
            <aside class="fl">
                <span  onclick="additem(2)">-</span>
                <input type="text" placeholder="1" value="1" id="prodquantity" onKeyUp="formatInputSkuNum(this)">
                <span onclick="additem(1)">+</span>
            </aside>
        </section>
        <input type="hidden" id="skustock" value="0">
        <input type="hidden" id="skuId" value="0">
        <input type="hidden" id="skuPrice" value="0">
        <input type="hidden" id="propostage" value="${product.postage!'0'}">
        <input type="hidden" id="productId" value="${product.id!''}">
        <input type="hidden" id="productKind" value="${product.kind!'0'}">
    </div>
    <div class="detail4">
        <section>
            <label class="fl">运费</label>
            <aside class="fl">${product.postage!'0'}元</aside>
        </section>
        <section>
            <label class="fl">服务</label>
            <aside class="fl">由<span>${region.name!''}${productType.name!''}代理</span>负责售后服务。</aside>
        </section>
        <section>
            <label class="fl">提示</label>
            <aside class="fl">${product.tags!''}</aside>
        </section>
    </div>
    <div class="detail5">
        <a href="${app.basePath}/mobile/product/describe/1/${product.id}" title="">图文详情</a>
        <a href="${app.basePath}/mobile/product/comment${product.id}" title="">评价晒单</a>
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

<div style="height:0.1rem"></div>
<!-- Footer Start -->
<footer>
    <div class="gopay">
        <a href="javascript:;" class="acare" id="acare">关注</a>
        <a href="javascript:;" class="ajoin" id="addCart" onclick="addToShoppingcart();">加入购物车</a>
        <a href="javascript:;" class="apayfor" id="buyNow" title="立即购买" onclick="buyNow();">立即购买</a>
    </div>
    <span class="footclear"></span>
</footer>
<!-- Footer End -->
<script>
	var __skuJsons = ${productjson};
$(function(){
});
</script>
</body>  
</html>