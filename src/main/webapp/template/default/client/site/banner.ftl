<#import "/common/app.ftl" as app>
<#if artList??>
<#list artList as art>
<a href="${app.basePath}/site/list?aid=${art.aid!'0'}" title="${art.title!''}" <#if art_index == 0>class="a1"</#if>>${art.title!''}</a>
</#list>
</#if>
