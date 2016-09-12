<#if parent.level?? && parent.level==1>
	<#if province.gentralCity()>
	<select style="width: 100px;" id="cityId" name="cityId"  onchange="getDistricts({'obj':this,'num':3,'total':${sc.totalLevel!'1'},'callback':'${sc.callback!''}'})">
		<option value="-1">请选择</option>
		<#if regionList??>
			<#list regionList as region>
				<option value="${region.id?c}">${region.name!''}</option>	            	
			</#list>
		</#if>
	</select>
	<#else>
	<select style="width: 100px;" id="cityId" name="cityId" onchange="getDistricts({'obj':this,'num':2,'total':${sc.totalLevel!'1'},'callback':'${sc.callback!''}'})">
		<option value="-1">请选择</option>
		<#if regionList??>
			<#list regionList as region>
				<option value="${region.id?c}">${region.name!''}</option>	            	
			</#list>
		</#if>
	</select>
	</#if>
<#elseif parent.level?? && parent.level==2>
	<select style="width: 100px;" id="regionId" name="regionId" onchange="getDistricts({'obj':this,'num':3,'total':${sc.totalLevel!'1'},'callback':'${sc.callback!''}'})">
		<option value="-1">请选择</option>
		<#if regionList??>
			<#list regionList as region>
				<option value="${region.id?c}">${region.name!''}</option>	            	
			</#list>
		</#if>
	</select>
<#else>
    <select style="width: 100px;" id="provinceId" name="provinceId" onchange="getDistricts({'obj':this,'num':1,'total':${sc.totalLevel!'1'},'callback':'${sc.callback!''}'})">
		<option value="-1">请选择</option>
		<#if regionList??>
			<#list regionList as region>
				<option value="${region.id?c}">${region.name!''}</option>	            	
			</#list>
		</#if>
	</select>
</#if>
