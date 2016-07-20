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
    <span>新品专区</span>
</div>
<!-- header_top end -->

<!-- Center Start -->
<!-- <section class="container"> -->
<div class="three">
    <a href="javascript:;" title="综合" onclick="searchNew(1)" <#if sc?? && sc.orderby==1>class="active"</#if>>综合</a>
    <a href="javascript:;" title="销量" onclick="searchNew(2)" <#if sc?? && sc.orderby==2>class="active"</#if>>销量</a>
    <a href="javascript:;" onclick="searchNew(<#if sc??><#if sc.orderby==3 || sc.orderby==4><#if sc.orderby==3>4<#else>3</#if><#else>3</#if><#else>3</#if>)" title="价格" <#if sc?? && sc.orderby==3 || sc.orderby==4>class="active"</#if>>价格</a>
</div>
 

<!-- 热销推荐 -->
<div class="hot">
    <section class="sec2" id="product_more">
    	<#if new_list??>
    	<#list new_list as item>
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