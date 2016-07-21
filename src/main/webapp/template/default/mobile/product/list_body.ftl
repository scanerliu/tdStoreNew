<#import "/common/app.ftl" as app>

<script type="text/javascript">
$(document).ready(function(){
	$(document).off();
	var url = '${app.basePath}/mobile/product/list/more/${sc.typeId?c}?orderby=${sc.orderby!'1'}'
	$('#product_more').refresh(url,"#product_more",1);
})

</script>
<!-- header_top -->
<div class="top_heater">
    <a href="javascript:history.go(-1);" title="返回" class="hleft hback"></a>
    <span><#if productType??>${productType.name!''}</#if></span>
</div>
<!-- header_top end -->

<!-- Center Start -->
<!-- <section class="container"> -->
    <div class="three">
    	<a href="javascript:;" title="综合" onclick="searchList(${sc.typeId?c},1)" <#if sc?? && sc.orderby==1>class="active"</#if>>综合</a>
    	<a href="javascript:;" title="销量" onclick="searchList(${sc.typeId?c},2)" <#if sc?? && sc.orderby==2>class="active"</#if>>销量</a>
    	<a href="javascript:;" onclick="searchList(${sc.typeId?c},<#if sc??><#if sc.orderby==3 || sc.orderby==4><#if sc.orderby==3>4<#else>3</#if><#else>3</#if><#else>3</#if>)" title="价格" <#if sc?? && sc.orderby==3 || sc.orderby==4>class="active"</#if>>价格</a>
    </div>
 
 	<#--
    <div class="strenth">
        <div class="swiper-container">
            <div class="swiper-wrapper" id="swiper-wrapper">
                <div class="swiper-slide"><a href="产品列表.html" title="">翡翠玉石</a></div>
                <div class="swiper-slide"><a href="#" title="">发饰/发卡</a></div>
                <div class="swiper-slide"><a href="#" title="">3</a></div>
                <div class="swiper-slide"><a href="#" title="">4</a></div>
                <div class="swiper-slide"><a href="#" title="">5</a></div>
                <div class="swiper-slide"><a href="#" title="">6</a></div>
                <div class="swiper-slide"><a href="#" title="">7</a></div> 
            </div>
        </div>
        <script>
        var swiper = new Swiper('.swiper-container', {
            pagination: '.swiper-pagination',
            slidesPerView: 3,
            paginationClickable: true,
            // spaceBetween: 30
        });
        </script>
    </div>
    -->

    <!-- 热销推荐 -->
    <div class="hot">
        <section class="sec2" id="product_more">
        	<#if productList??>
        	<#list productList as item>
            <a href="${app.basePath}/mobile/product/item${item.id!'0'}" title="${item.name!''}" class="">
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
    <!-- 热销推荐-结束 -->
<!-- </section> -->
<!-- Center end -->
