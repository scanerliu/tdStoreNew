<select style="width: 100px;" id="product_type_${level!''}" name="${selectInputName!''}"  onchange="getProductTypeSelections(${level!''},this.value)">
	<option value="-1">-产品类别-</option>
	<#if productTypeList??>
		<#list productTypeList as productType>
			<option value="${productType.id?c}">${productType.name!''}</option>	            	
		</#list>
	</#if>
</select>
