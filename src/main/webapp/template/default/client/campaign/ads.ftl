<#import "/common/app.ftl" as app>
<section class="local_news">
	<#if advertList?? && advertList?size gt 0>
	<#list advertList as art>
	<aside class="as1 fl"><a href="#" title="" class=""><img src="list/images/4.png" alt="" /></a></aside>
	</#list>
	</#if>
	<aside class="as2 fr">
	<div>
		<#if artList?? && artList?size gt 0>
        <#list artList as art>
		<a href="#" title="${art.title!''}" class="">
			<p class="li_title">${art.title!''}</p>
			<p class="li_word">${art.title!''}</p>
		</a>
		</#list>
		</#if>
	</div>
	</aside>
</section>