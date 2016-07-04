<select style="width: 100px;" id="district_id_${level!''}" name="${selectInputName!''}"  onchange="getDistrictSelections(${level!''},this.value)">
	<option value="-1">-地区-</option>
	<#if districtList??>
		<#list districtList as district>
			<option value="${district.id?c}">${district.name!''}</option>	            	
		</#list>
	</#if>
</select>
