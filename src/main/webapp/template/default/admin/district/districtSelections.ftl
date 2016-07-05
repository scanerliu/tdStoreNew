<select style="width: 100px;" id="${prefix}_district_id_${level!''}" name=""  onchange="getDistrictSelections(${level!''},this.value, '${prefix}', null)">
	<option value="-1">-地区-</option>
	<#if districtList??>
		<#list districtList as district>
			<option value="${district.id?c}">${district.name!''}</option>	            	
		</#list>
	</#if>
</select>
