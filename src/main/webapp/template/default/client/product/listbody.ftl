<#import "/common/app.ftl" as app>
<section class="goods_list w1200">
	<ul>
		<#if productList?? && productList?size gt 0>
			<#list productList as pro>
				<li>
					<a href="${app.basePath}/product/item${pro.id}" title="${pro.name!''}"><img src="${app.basePath}${pro.imageUrl!''}" alt="暂无图片" /></a>
					<p class="p1">${pro.name!''}</p>
					<p class="p2">
						<label for="" class="lab1">￥<#if pro.price??>${pro.price?string('0.00')}</#if></label>
						<span></span>
					</p>
				</li>
		    </#list>
		<#else>
			暂无相关商品。
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
<#assign pageId="Products" />
<#include "../common/commonpostpage.ftl" />
</section>
<script>
	$(function(){
		$("#currPageNo").html(${sc.pageNo!'1'});
		$("#currTotalPages").html(${sc.totalPageCount!'1'});
		<#if sc.pageNo gt 1>
			$("#prepagebtn").off("click").on("click",function(e){gotopage${pageId!''}(${sc.pageNo-1})});
		<#else>
			$("#prepagebtn").off("click");
		</#if>
		<#if sc.pageNo lt sc.totalPageCount>
			$("#nextpagebtn").off("click").on("click",function(e){gotopage${pageId!''}(${sc.pageNo+1})});
		<#else>
			$("#nextpagebtn").off("click");
		</#if>
	});
</script>
