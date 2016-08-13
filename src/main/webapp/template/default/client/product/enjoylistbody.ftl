<#import "/common/app.ftl" as app>
<#if productList?? && productList?size gt 0>
	<#list productList as pro>
		<li>
			<a href="${app.basePath}/product/item${pro.id}" title="${pro.name!''}"><img src="${app.basePath}${pro.imageUrl!''}" alt="暂无图片" /></a>
			<p class="p1"><a href="${app.basePath}/product/item${pro.id}" title="${pro.name!''}">${pro.name!''}</a></p>
			<p class="p2">￥<#if pro.price??>${pro.price?string('0.00')}</#if></p>
		</li>
    </#list>
</#if>
<script>
	$(function(){
		$("#enjoysc_pageNo").val(${sc.pageNo+1});
		$("#enjoybtn").on("click",function(){getenjoyproducts();});
	});
</script>