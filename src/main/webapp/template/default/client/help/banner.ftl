<#import "/common/app.ftl" as app>
<ul>
	<#if catList??>
	<#list catList as cat>
    <li>
        <p class="p1">${cat.name}</p>
        <p class="p2">
        	<#if cat.articleList??>
    		<#list cat.articleList as art>
            <a href="${app.basePath}/help/list?aid=${art.aid!'0'}" title="${art.title!''}" class="">${art.title!''}</a>
            </#list>
			</#if>
        </p>
    </li>
    </#list>
	</#if>
</ul>