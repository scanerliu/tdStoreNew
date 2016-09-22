<#import "/common/app.ftl" as app>
<div class="left">
    <div class="sitebanner">
        <div class="picScroll">
            <div class="hd">
                <ul></ul>
            </div>
            <div class="bd">
                <ul class="picList">
                	<#if floorcycleadList??>
    				<#list floorcycleadList as add>
                    <li>
                        <a href="<#if add.linkUrl??>${add.linkUrl!''}<#else>javascript:;</#if>"><img src="${app.basePath}${add.imageUrl!''}" alt="广告图"/></a>
                    </li>
                    </#list>
    				</#if>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="right">
    <div class="rightlist" style="display: block;">
        <div class="right-item1">
        	<#if floorcenteroneadList??>
			<#list floorcenteroneadList as add>
            <li>
                <a href="<#if add.linkUrl??>${add.linkUrl!''}<#else>javascript:;</#if>" class="pic-item pic-item1"><img src="${app.basePath}${add.imageUrl!''}" alt="广告图"/></a>
            </li>
            </#list>
			</#if>
        	<#if floorcentertwoadList??>
			<#list floorcentertwoadList as add>
            <li>
                <a href="<#if add.linkUrl??>${add.linkUrl!''}<#else>javascript:;</#if>" class="pic-item pic-item2"><img src="${app.basePath}${add.imageUrl!''}" alt="广告图"/></a>
            </li>
            </#list>
			</#if>
        </div>
        <div class="right-item2">
        	<#if floorrightadList??>
			<#list floorrightadList as add>
            <li>
                <a href="<#if add.linkUrl??>${add.linkUrl!''}<#else>javascript:;</#if>" class="pic-item pic-item3"><img src="${app.basePath}${add.imageUrl!''}" alt="广告图"/></a>
            </li>
            </#list>
			</#if>
        </div>
    </div>
</div>
<script language="javascript" type="text/javascript">
    $(function () {
        $(".picScroll").slide({
            titCell:".hd ul",
            mainCell:".bd ul",
            autoPage:true,
            effect:"leftLoop",
            autoPlay:true,
            vis:1
        });
    });
</script>