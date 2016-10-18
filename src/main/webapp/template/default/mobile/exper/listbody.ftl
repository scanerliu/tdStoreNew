<#import "/common/app.ftl" as app>
<!-- Center Start -->
<section class="container">
        <!-- 三级联动2-结束 -->
        
        <ul class="nearest">
            <#if experList?? && experList?size gt 0>
            <#list experList as exp>
            <li>
                <a href="${app.basePath}/mobile/experience/detail?id=${exp.id?c}" title="${exp.regionFullName!''}体验代购店${(exp_index+1)}店" class="a1">
                    <p class="p1">${exp.regionFullName!''}体验代购店${(exp_index+1)}店</p>
                    <p class="p2">${exp.regionFullName!''}${exp.address!''}</p>
                </a>
                <a href="${app.basePath}/mobile/experience/map?id=${exp.id?c}" title="导航" class="a2"><span>导航</span></a>
            </li>
            </#list>
            <#else>
            <li class="a1">
                	该地区暂无体验店
            </li>
            </#if>
        </ul>
</section>
<!-- Center End -->
