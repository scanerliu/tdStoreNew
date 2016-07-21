<#import "/common/app.ftl" as app>

<script type="text/javascript">
$(document).ready(function(){
	$(document).off();
	var url = '${app.basePath}/mobile/search/more?keyword=${sc.keyword!''}&orderby=${sc.orderby!'1'}'
	$('#product_more').refresh(url,"#product_more",1);
})

</script>
<!-- header_top -->
<div class="top_heater">
    <a href="javascript:history.go(-1);" title="返回" class="hleft hback"></a>
    <span><#if sc??>${sc.keyword!''}</#if></span>
</div>
<!-- header_top end -->

<!-- Center Start -->
<!-- <section class="container"> -->
    <div class="three">
    	<a href="javascript:;" title="综合" onclick="searchKeyword('${sc.keyword!''}',1)" <#if sc?? && sc.orderby==1>class="active"</#if>>综合</a>
    	<a href="javascript:;" title="销量" onclick="searchKeyword('${sc.keyword!''}',2)" <#if sc?? && sc.orderby==2>class="active"</#if>>销量</a>
    	<a href="javascript:;" onclick="searchKeyword('${sc.keyword!''}',<#if sc??><#if sc.orderby==3 || sc.orderby==4><#if sc.orderby==3>4<#else>3</#if><#else>3</#if><#else>3</#if>)" title="价格" <#if sc?? && sc.orderby==3 || sc.orderby==4>class="active"</#if>>价格</a>
    </div>
 
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
