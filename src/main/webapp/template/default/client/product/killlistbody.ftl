<#import "/common/app.ftl" as app>
<section class="gou tabb w1200">
	<ul class="active">
		<#if productList?? && productList?size gt 0>
			<#list productList as pro>
				<li>
					<a href="${app.basePath}/product/item${pro.id}" title="${pro.name!''}"><img src="${app.basePath}${pro.imageUrl!''}" alt="暂无图片" /></a>
					<p class="p1">${pro.name!''}</p>
					<p class="p2">
						<label for="" class="lab1">￥<#if pro.price??>${pro.price?string('0.00')}</#if></label>
						<span style="font-size:.2rem;color:#999;text-decoration: blink;">邮费<#if pro.postage??>${pro.postage?string('0.00')}</#if></span>
					</p>
				</li>
		    </#list>
		<#else>
			<span class="nothing_tips">暂无相关商品。</span>
		</#if>
	</ul>
</section>
<input type="hidden" value="${sc.name!''}" name="name">
<input type="hidden" value="${sc.brandId!''}" name="brandId">
<input type="hidden" value="${sc.typeId!''}" name="typeId">
<input type="hidden" value="${sc.orderby!''}" name="orderby">
<input type="hidden" value="${sc.stock!''}" name="stock">
<input type="hidden" value="${sc.postage!''}" name="postage">
<input type="hidden" value="${sc.startPrice!''}" name="startPrice">
<input type="hidden" value="${sc.endPrice!''}" name="endPrice">

<section class="page" style="margin:0 auto;margin-top:30px;width:1200px;">
<#assign pageId="ZeroProducts" />
<#if productList?? && productList?size gt 0>
<#include "../common/commonpostpage.ftl" />
</section>
<script>
	$(function(){
		
	});
</script>
</#if>
