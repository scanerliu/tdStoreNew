<#import "/common/app.ftl" as app>

<script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script>
 <script>
 Rich.pageSize();
$(document).ready(function(){
	 
	 
    })
 </script>
 <ul class="active" id="type_ul">
 
    <#if subtypeList?? && subtypeList?size gt 0>
	<#list subtypeList as type>
            <li <#if type_index==0>class="active"</#if>>
                <menu>${type.name!''}</menu>
                <aside class="as2">
                	<#if type.subList??>
					<#list type.subList as etype>
                    <a t=${etype.id?c}  href="javascript:;" title="${etype.name!''}" <#if !experTypeIds?? || !experTypeIds?contains('['+etype.id?c+']')>class="active"</#if>>
                        <img src="<#if etype.imageUrl?? && etype.imageUrl!="">${etype.imageUrl!''}<#else>${app.basePath}/static/default/images/noimg.png</#if>" alt="${etype.name!''}">
                        <p>${etype.name!''}</p>
                        <i></i>
                    </a>
                    </#list>
                    </#if>
                </aside>
            </li>
     	</#list>
     	</#if>
    </ul>
<script>
$(function(){
	$('.active .as2 a.active').click(function(){
		$('#typeId').val($(this).attr('t'));
	});
})
</script>
<input type="hidden" name="typeId" value="" id="typeId" />