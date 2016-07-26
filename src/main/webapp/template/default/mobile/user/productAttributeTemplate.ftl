<#if attributeList??>
	<#list attributeList as attribute>
		<article class="nopadding">
		    <span>${attribute.name!''}</span>
		    <div class="items">
		    	<#if attribute.tdProductAttributeOptionList??>
		    		<#list attribute.tdProductAttributeOptionList as option>
				        <span>${option.name!''}<a href="javascript:;" title="" onclick="deleteOption(this)"></a><input type="hidden" value="${attribute.name}=${option.name}"></span>
		    		</#list>
				</#if>
		    </div>
		</article>
	</#list>
</#if>