<#import "/common/app.ftl" as app>
	<span class="title"><#if cat??>${cat.name!''}</#if></span>
	<div class="newslist">
	    <dl>
	    	<#if artList??>
	        <#list artList as art>
	        <dd>
	            <a href="${app.basePath}/site/list?aid=${art.aid!'0'}" title="${art.title!''}"><i>></i>${art.title!''}</a>
	            <span class="date"><#if art.updateTime??>${art.updateTime?string('yyyy-MM-dd')}</#if></span>
	        </dd>
	        </#list>
		    </#if>
	    </dl>
	    <#if artList?? && artList?size gt 0 >
	    <section class="goods-page-nums txtr">
		<#assign pageId="Articles" />
		<#include "../common/commonpostpage.ftl" />
		</section>
		</#if>
	</div>
<input type="hidden" value="${sc.cid!''}" name="cid">
