<script>
$(function(){
     	citylist();
	});
</script>
<section>
    <span><#if province??>${province.name!''}<#else>请选择</#if></span>
    <select name="provinceId" id="" >
        <option value="">请选择</option>
    	<#if districtList??>
    	<#list districtList as dis>
        	<option value="${dis.id?c}" <#if province?? && province.id = dis.id>selected</#if>>${dis.name!''}</option>
        </#list>
        </#if>
    </select>
</section>
<section>
    <span><#if city??>${city.name!''}<#else>请选择</#if></span>
    <select name="cityId" id="">
        <option value="">请选择</option>
        <#if cityList??>
    	<#list cityList as dis>
        	<option value="${dis.id?c}" <#if city?? && city.id = dis.id>selected</#if>>${dis.name!''}</option>
        </#list>
        </#if>
    </select>
</section>
<section>
    <span><#if regin??>${regin.name!''}<#else>请选择</#if></span>
    <select name="regionId" id="">
        <option value="">请选择</option>
        <#if regionList??>
    	<#list regionList as dis>
        	<option value="${dis.id?c}" <#if regin?? && regin.id = dis.id>selected</#if>>${dis.name!''}</option>
        </#list>
        </#if>
    </select>
</section>