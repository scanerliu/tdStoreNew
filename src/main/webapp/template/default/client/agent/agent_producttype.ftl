<#import "/common/app.ftl" as app>

 <script>

$(document).ready(function(){
	 $("#type_ul").find("a.active").click(function(){
	 	$("#type_ul").find("i").hide();
	 	$(this).find("i").show();
	 })
	 $("#type_ul").find("menu").click(function(){
	 	//$(this).parents("li").siblings().removeClass();
	 	$(this).parents("li").toggleClass("active");
	 })
	 
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