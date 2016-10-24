<#import "/common/app.ftl" as app>
<script type="text/javascript">

$(document).ready(function(){
	$(document).off();
	var url = '${app.basePath}/mobile/product/new/more?orderby=<#if sc??>${sc.orderby!'1'}</#if>'
	$('#product_more').refresh(url,"#product_more",1);
})

</script>

<div class="top_heater">
    <a href="javascript:history.go(-1);" title="返回" class="hleft hback"></a>
    <span>排队免单</span>
</div>
<!-- header_top end -->

<!-- ****广告轮播**** -->
<#if adList?? && adList?size gt 0>
<div class="addWrap2">
    <div class="swipe" id="mySwipe">
        <div class="swipe-wrap">
        	<#list adList as ad>
				<div><a href="${ad.linkUrl!''}"><img class="img-responsive" src="${ad.imageUrl!''}"/></a></div>
        	</#list>
        </div>
    </div>
    <ul id="position">
    	  <#if adList?? && adList?size gt 0>
    	  <#list adList as ad>
          <li class="cur"></li>
          </#list>
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
</div>
</#if>
<!-- ****广告轮播-结束**** -->

<!-- Center Start -->
<!-- <section class="container"> -->
<div class="three">
    <a href="javascript:;" title="综合" <#if sc?? && sc.orderby==1>onclick="searchNew(2)" class="down"<#elseif sc?? && sc.orderby==2>onclick="searchNew(1)" class="up"<#else>onclick="searchNew(1)"</#if>>综合</a>
	<a href="javascript:;" title="销量" <#if sc?? && sc.orderby==3>onclick="searchNew(4)" class="down"<#elseif sc?? && sc.orderby==4>onclick="searchNew(3)" class="up"<#else>onclick="searchNew(3)"</#if>>销量</a>
	<a href="javascript:;" title="价格" <#if sc?? && sc.orderby==5>onclick="searchNew(6)" class="down"<#elseif sc?? && sc.orderby==6>onclick="searchNew(5)" class="up"<#else>onclick="searchNew(5)"</#if>>价格</a>
</div>
 

<!-- 热销推荐 -->
<div class="hot">
    
    	<#if new_list?? && new_list?size gt 0>
    	<section class="sec2" id="product_more">
    	<#list new_list as item>
        <a href="${app.basePath}/mobile/product/item${item.id!'0'}" title="${item.name!''}" class="">
            <img src="${app.basePath}${item.imageUrl!''}" alt="${item.name!''}">
            <p class="p1">${item.name!''}</p>
            <p class="p2">
                <label class="lab1">¥<#if item.price??>${item.price?string('0.00')}</#if></label>
                <!--<label class="lab2">￥188.00</label>-->
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