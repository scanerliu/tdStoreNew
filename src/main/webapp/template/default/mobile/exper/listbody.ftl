<#import "/common/app.ftl" as app>

<script>
   $(function(){
        three_bing2();
    })
</script>


<!-- Center Start -->
<section class="container">

        <!-- 三级联动2 -->
<form  id="form">
<div class="tarea" id="tarea">
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
            	<option value="${dis.id?c}" <#if city ?? && city.id = dis.id>selected</#if>>${dis.name!''}</option>
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
            	<option value="${dis.id?c}" <#if regin ?? && regin.id = dis.id>selected</#if>>${dis.name!''}</option>
            </#list>
            </#if>
        </select>
    
</div>
</form>
        <!-- 三级联动2-结束 -->
        
        <ul class="nearest">
            <#if experList??>
            <#list experList as exp>
            <li>
                <a href="${app.basePath}/mobile/experience/detail?id=${exp.id?c}" title="${exp.regionFullName!''}体验代购店${(exp_index+1)}店" class="a1">
                    <p class="p1">${exp.regionFullName!''}体验代购店${(exp_index+1)}店</p>
                    <p class="p2">${exp.regionFullName!''}${exp.address!''}</p>
                </a>
                <a href="${app.basePath}/mobile/experience/map?id=${exp.id?c}" title="导航" class="a2"><span>导航</span></a>
            </li>
            </#list>
            </#if>
        </ul>
</section>
<!-- Center End -->
