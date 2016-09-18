<#import "/common/app.ftl" as app>
<li class="dleft">
<#if presp?? && presp.data??>
<#assign pnum = presp.data?size>
<#list presp.data as item>
	<#if item_index lt (pnum-1) >
	<div><span <#if item_index ==0>class="active"</#if>></span><span></span></div>
	</#if>
</#list>
<#else>
<div><span class="active"></span></div>
</#if>
</li>
<li class="dright">
<#if presp?? && presp.data??>
<#list presp.data as item>
<p <#if item_index ==0>class="active"</#if>>${item.context!''}<span>${item.ftime!''}</span></p>
</#list>
<#else>
<p class="active">暂无物流信息。</p>
</#if>
</li>