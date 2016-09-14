<#import "/common/app.ftl" as app>
<section class="sec2 fl">
<ul>
	<#if experList?? && experList?size gt 0>
	<#list experList as exp>
	<a href="${app.basePath}/experience/detail?id=${exp.id?c}" title="${exp.regionFullName!''}体验店">
	<li>
		<p class="p1">${exp.regionFullName!''}体验店</p>
		<p class="p2">
			<label for="" class="fl">店铺地址：</label>
			<span class="fl">${exp.regionFullName!''}${exp.address!''}</span>
			<label for="" class="fl">销售热线：</label>
			<span class="fl">${exp.telphone!''}</span>
		</p>
	</li>
	</a>
	</#list>
	<#else>
		<li><p class="p2">
			该地区暂无体验店。
		</p>
		</li>
	</#if>
</ul>
</section>
<input type="hidden" value="${sc.regionId!''}" name="regionId">
<#if experList?? && experList?size gt 0>
<section class="page fl" style="margin:0 auto;margin-top:30px;width:1200px;">
<#assign pageId="Exper" />
<#include "../common/commonpostpage.ftl" />
</section>
</#if>
