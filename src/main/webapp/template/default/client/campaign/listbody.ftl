<#import "/common/app.ftl" as app>
<section class="local_list">
	<dl>
		<#if campaignList?? && campaignList?size gt 0>
        <#list campaignList as comp>
		<dt>
			<aside class="as1 fl"><a href="javaacript:;"><img src="${app.basePath}${comp.uavatar!''}" alt="" /></a></aside>
			<aside class="as2 fr">
				<p class="li_title">
					<label for="" class="lab1">${comp.uname!''}</label>
					<!--<a href="javascript:;" title="" class="un">1574</a>-->
				</p>
				<p class="li_word">${comp.resume!''}</p>
			</aside>
		</dt>
		</#list>
		<#else>
		<span class="nothing_tips">暂无相关内容。</span>
        </#if>
	</dl>
</section>
<input type="hidden" value="${sc.regionId!''}" name="regionId">
<#if campaignList?? && campaignList?size gt 0>
<section class="page" style="margin:0 auto;margin-top:30px;width:1200px;">
<#assign pageId="Compaign" />
<#include "../common/commonpostpage.ftl" />
</section>
<script type="text/javascript">
  $(function(){
   	
  })
</script>
</#if>