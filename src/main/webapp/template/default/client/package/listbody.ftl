<#import "/common/app.ftl" as app>
<section class="gou tabb w1200">
	<ul class="active">
		<#if productList?? && productList?size gt 0>
			<#list productList as pro>
				<li>
					<a href="${app.basePath}/package/item${pro.id}" title="${pro.name!''}"><img src="${app.basePath}${pro.imageUrl!''}" alt="暂无图片" /></a>
					<p class="p1">${pro.name!''}</p>
					<p class="p2">
						<label for="" class="lab1">￥<#if pro.price??>${pro.price?string('0.00')}</#if></label>
						<span></span>
					</p>
				</li>
		    </#list>
		<#else>
			<span class="nothing_tips">暂无相关商品。</span>
		</#if>
	</ul>
</section>
<input type="hidden" value="${sc.acpe!''}" name="acpe">
<#if productList?? && productList?size gt 0>
<section class="page" style="margin:0 auto;margin-top:30px;width:1200px;">
<#assign pageId="Packages" />
<#include "../common/commonpostpage.ftl" />
</section>
<script>
	$(function(){
		
	});
</script>
</#if>