<#import "/common/app.ftl" as app>
<section class="local_list">
	<dl>
		<#if campaignList?? && campaignList?size gt 0>
        <#list campaignList as comp>
		<dt cid="${comp.id!'0'}">
			<aside class="as1 fl">
				<a href="javascript:;"><img id="avatar_${comp.id!'0'}" src="<#if comp.uavatar?index_of("http")==0><#else>${app.basePath}</#if>${comp.uavatar!''}" alt="头像" /></a>
				<div id="resume_${comp.id!'0'}" style="display:none">${comp.resume!''}</div>
				<div id="level_${comp.id!'0'}" style="display:none">
					<#if comp.status==1>
						${comp.level!'0'}
					<#else>
						暂无排名
					</#if>
				</div>
			</aside>
			<aside class="as2 fr">
				<p class="li_title">
					<label for="" class="lab1" id="uname_${comp.id!'0'}">${comp.uname!''}</label>
					<!--<a href="javascript:;" title="" class="un">1574</a>-->
				</p>
				<p class="li_word">${comp.declaration!''}</p>
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
<script>
$(function(){
	$('.local_list').find('dt').click(function(){
		var cid= $(this).attr("cid");
		$("#opa_avatar").attr("src",$("#avatar_"+cid).attr("src"));
		$("#opa_uanme").html($("#uname_"+cid).html());
		$("#opa_level").html($("#level_"+cid).html());
		$("#opa_resume").html($("#resume_"+cid).html());
		$('.opa_box').css('display','block');
	})
	$('.close').click(function(){
		$('.opa_box').css('display','none');
	})
})
</script>
</#if>