<#import "/common/app.ftl" as app>
<ul id="commlist">
<#if commentList?? && commentList?size gt 0>
	<#list commentList as comm>
		<li>
            <span class="useravatar">
                <img src="<#if comm.commentUser.uavatar??><#if comm.commentUser.uavatar?index_of("http")==0><#else>${app.basePath}</#if>${comm.commentUser.uavatar!''}<#else>${app.basePath}/static/default/mobile/images/defaultavtar.png</#if>" alt=""/>
                <label>${comm.commentUser.unick!''}</label>
                <span class="vip">V<#if comm.commentUser.cmlevel??>${comm.commentUser.cmlevel!'1'}<#else>1</#if></span>
            </span>
            <div class="right">
                <div class="star star1">
                	<#list comm.score..1 as score>
                    <span class="s1"></span>
                    </#list>
                    <#if comm.score lt 5> 
                    <#list 1..(5-comm.score) as score>
                    <span class="s2"></span>
                    </#list>
                    </#if>
                </div>
                <p>
                    ${comm.content!''}
                </p>
                <#if comm.imageArray??>
                <p>
                    <#list comm.imageArray as image>
                    <img src="${app.basePath}${image}" class="comimage cimge"/>
                    </#list>
                </p>
                </#if>
                <div class="item">
                    <label>${comm.specifications!''}</label>
                    <label><#if comm.createTime??>${comm.createTime?string('yyyy-MM-dd HH:mm')}</#if></label>
                </div>
            </div>
        </li>
    </#list>
</#if>
</ul>
<input type="hidden" name="productId" value="${sc.productId!''}"/>
<div class="page">
	<#assign pageId="Comments" />
	<#include "../common/commonpostpage.ftl" />   
</div>
<script language="javascript" type="text/javascript">
    $(function () {
		$("#commlist .comimage").on("click",function(){
			$(this).toggleClass("cimge").siblings().addClass("cimge");
		});
    });
</script>
