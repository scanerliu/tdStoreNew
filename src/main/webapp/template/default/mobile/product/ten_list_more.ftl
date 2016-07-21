<#import "/common/app.ftl" as app>
<#if tenList??>
<#list tenList as item>
	<a href="${app.basePath}/mobile/product/item${item.id?c}" title="${item.name!''}" class="">
	    <img src="${app.basePath}${item.imageUrl!''}" alt="${item.name!''}">
	    <p class="p1">${item.name!''}</p>
	    <p class="p2">
	        <label class="lab1">¥<#if item.price??>${item.price?string('0.00')}</#if></label>
	        <label class="lab2">￥188.00</label>
	    </p>
	</a>
</#list>
</#if>