<select style="width: 100px;" id="firstProductTypeId" name="firstProductTypeId" onchange="flushProductTypeSelect()">
    <option value="-1">请选择一级分类</option>
    <#if firstProductTypeList??>
    	<#list firstProductTypeList as fpt>
            <option value="${fpt.id?c}" <#if selectedFirstProductTypeId?? && selectedFirstProductTypeId?c==fpt.id?c>selected="selected"</#if>>${fpt.name!''}</option>	            	
		</#list>
	</#if>
</select>
<#if secondProductTypeList??>
	<select style="width: 100px;" id="secondProductTypeId" name="secondProductTypeId" onchange="flushProductTypeSelect()">
	    <option value="-1">请选择二级分类</option>
    	<#list secondProductTypeList as spt>
            <option value="${spt.id?c}" <#if selectedSecondProductTypeId?? && selectedSecondProductTypeId?c==spt.id?c>selected="selected"</#if>>${spt.name!''}</option>	            	
		</#list>
	</select>
</#if>
<#if thirdProductTypeList??>
	<select style="width: 100px;" id="thirdProductTypeId" name="thirdProductTypeId">
	    <option value="-1">请选择三级分类</option>
    	<#list thirdProductTypeList as tpt>
            <option value="${tpt.id?c}" <#if selectedThirdProductTypeId?? && selectedThirdProductTypeId?c==tpt.id?c>selected="selected"</#if>>${tpt.name!''}</option>	            	
		</#list>
	</select>
</#if>
