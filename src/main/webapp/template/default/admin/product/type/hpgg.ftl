<#if attrList??>
	<#list attrList as attribute>
		<#if attribute??>
		<#if (attribute_index != 0) && (attribute_index lt attrList?size)><br/></#if>
		<label>${attribute.name!''}：<label>
		<#if attribute.tdProductAttributeOptionList??>
			<#list attribute.tdProductAttributeOptionList as option>
				${option.name!''}<input type="checkbox" onchange="flushTable()" id="spe_${attribute.name!''}_${option.name!''}" name="${attribute.name!''}">&nbsp;&nbsp;&nbsp;
			</#list>
		</#if>
		</#if>
	</#list>
</#if>
<script>
	$(document).ready(function(){
		psize =${psize!0};
	});
</script>