<#import "/common/app.ftl" as app>
<ul>
<#if commentList?? && commentList?size gt 0>
	<#list commentList as comm>
		<li>
            <span class="useravatar">
                <img src="${app.basePath}/images/avatar.png" alt=""/>
                <label>155*****11</label>
                <span class="vip">V1</span>
            </span>
            <div class="right">
                <div class="star star1">
                    <span class="s1"></span>
                    <span class="s2"></span>
                </div>
                <p>
                    ${comm.content!''}
                </p>
                <div class="item">
                    <label>${comm.specifications!''}</label>
                    <label><#if comm.createTime??>${comm.createTime?string('yyyy-MM-dd H:i')}</#if></label>
                </div>
            </div>
        </li>
    </#list>
</#if>
</ul>
<div class="page">
	<#assign pageId="Comments" />
	<#include "../common/commonpostpage.ftl" />   
</div>
<script language="javascript" type="text/javascript">
    $(function () {
		
    });
</script>
