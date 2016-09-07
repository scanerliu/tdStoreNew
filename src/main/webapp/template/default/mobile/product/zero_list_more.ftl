<#import "/common/app.ftl" as app>
<#if zeroList??>
<#list zeroList as item>
	<a href="${app.basePath}/mobile/product/item${item.id?c}" title="${item.name!''}" class="">
	    <img src="${app.basePath}${item.imageUrl!''}" alt="${item.name!''}">
	    <p class="p1">${item.name!''}</p>
	    <p class="p2">
	        <label class="lab1">¥<#if item.price??>${item.price?string('0.00')}</#if></label>
	        <label class="lab3 fr" style="font-size:.2rem;color:#999;">运费￥<#if item.postage??>${item.postage?string('0.00')}</#if></label>
	    </p>
	</a>
</#list>
</#if>