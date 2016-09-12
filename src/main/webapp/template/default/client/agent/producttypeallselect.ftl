<#import "/common/app.ftl" as app>
<#if parent?? && parent.level?? && parent.level==1>
	<select style="width: 100px;" id="twotypeId" onchange="getAllTypes({'obj':this,'num':2})">
		<option value="-1">请选择</option>
		<#if typeList??>
			<#list typeList as region>
				<option value="${region.id?c}">${region.name!''}</option>	            	
			</#list>
		</#if>
	</select>
<#elseif parent?? && parent.level?? && parent.level==2>
	<select style="width: 100px;" id="threetypeId" onchange="getAllTypes({'obj':this,'num':3})">
		<option value="">请选择</option>
		<#if typeList??>
			<#list typeList as region>
				<option value="${region.id?c}">${region.name!''}</option>	            	
			</#list>
		</#if>
	</select>
<#elseif parent??>
    <select style="width: 100px;" id="ontypeId" onchange="getAllTypes({'obj':this,'num':1})">
		<option value="-1">请选择</option>
		<#if typeList??>
			<#list typeList as region>
				<option value="${region.id?c}">${region.name!''}</option>	            	
			</#list>
		</#if>
	</select>
</#if>