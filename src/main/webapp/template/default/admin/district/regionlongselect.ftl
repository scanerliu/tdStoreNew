<#if regionList?? && regionList?size gt 0>
<#if sc.level==1>
<select style="width: 100px;" id="provinceId" name="provinceId" onchange="getDistricts({'obj':this,'num':1,'level':1,'total':${sc.totalLevel!'1'},'callback':'${sc.callback!''}'})">
	<option value="">请选择</option>
	<#if regionList??>
		<#list regionList as region>
			<option value="${region.id?c}">${region.name!''}</option>	            	
		</#list>
	</#if>
</select>
<#elseif sc.level==2>
<select style="width: 100px;" id="cityId" name="cityId"  onchange="getDistricts({'obj':this,'num':2,'level':2,'total':${sc.totalLevel!'1'},'callback':'${sc.callback!''}'})">
	<option value="">请选择</option>
	<#if regionList??>
		<#list regionList as region>
			<option value="${region.id?c}">${region.name!''}</option>	            	
		</#list>
	</#if>
</select>
<#elseif sc.level==3>
<select style="width: 100px;" id="districtId" name="districtId" onchange="getDistricts({'obj':this,'num':3,'level':3,'total':${sc.totalLevel!'1'},'callback':'${sc.callback!''}'})">
	<option value="">请选择</option>
	<#if regionList??>
		<#list regionList as region>
			<option value="${region.id?c}">${region.name!''}</option>	            	
		</#list>
	</#if>
</select>
<#elseif sc.level==4>
<select style="width: 100px;" id="townId" name="townId" onchange="getDistricts({'obj':this,'num':4,'level':4,'total':${sc.totalLevel!'1'},'callback':'${sc.callback!''}'})">
	<option value="">请选择</option>
	<#if regionList??>
		<#list regionList as region>
			<option value="${region.id?c}">${region.name!''}</option>	            	
		</#list>
	</#if>
</select>
<#elseif sc.level==5>
<select style="width: 100px;" id="villageId" name="villageId" onchange="getDistricts({'obj':this,'num':5,'level':5,'total':${sc.totalLevel!'1'},'callback':'${sc.callback!''}'})">
	<option value="">请选择</option>
	<#if regionList??>
		<#list regionList as region>
			<option value="${region.id?c}">${region.name!''}</option>	            	
		</#list>
	</#if>
</select>
</#if>
</#if>

