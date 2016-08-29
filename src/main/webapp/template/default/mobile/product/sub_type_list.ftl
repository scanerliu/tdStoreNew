<#import "/common/app.ftl" as app>

<script type="text/javascript" src="${app.basePath}/static/touch/js/index.js"></script>
 <script>
 Rich.pageSize();
$(document).ready(function(){
 		left_right3();
        cleft_height3();
    })
 </script>
<section class="cla_right2 fr" id="cla_right">
<ul class="active">
	<#if subtypeList?? && subtypeList?size gt 0>
	<#list subtypeList as type>
        <li <#if type_index==0>class="active"</#if>>
            <menu>${type.name!''}</menu>
            <aside class="as2">
				<#if type.subList??>
				<#list type.subList as etype>
                <a href="${app.basePath}/mobile/product/list/${etype.id?c}" title="${etype.name!''}" class="active">
                    <img src="<#if etype.imageUrl?? && etype.imageUrl!="">${etype.imageUrl!''}<#else>${app.basePath}/static/default/images/noimg.png</#if>" alt="${etype.name!''}">
                    <p>${etype.name!''}</p>
                </a>
		     	</#list>
		     	</#if>
            </aside>
        </li>
	</#list>
	</#if>
 </ul>
 </section>