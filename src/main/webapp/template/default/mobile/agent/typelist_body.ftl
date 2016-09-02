<#import "/common/app.ftl" as app>
<script>
    $(function(){
        three_bing();
       cleft_height();
       left_right();
         <#if typeList?? && typeList?size gt 0>
    		searchAgentType(${typeList[0].id},${regionId!'0'});
    	</#if>
    })
 <#if msg??>alert('${msg}');</#if>
</script>

<body class="body_bg">

<!-- header_top -->
<div class="top_heater" id="top_heater">
    <a href="javascript:history.go(-1);" title="返回" class="hleft hback"></a>
    <span>分类列表</span>
</div>
<!-- header_top end -->

<!-- 地区三级联动 -->
<form  id="form">
<input type="hidden" name="agentId" id="agentId" value="<#if agent??>${agent.id?c}</#if>" />
<div class="three3" id="three3">
    <section>
        <span><#if province??>${province.name!''}<#else>请选择</#if></span>
        <select name="provinceId" id="" >
            <option value="">请选择</option>
        	<#if districtList??>
        	<#list districtList as dis>
            	<option value="${dis.id?c}" <#if dissc.provinceId?? && dissc.provinceId = dis.id>selected</#if>>${dis.name!''}</option>
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
            	<option value="${dis.id?c}" <#if dissc.cityId?? && dissc.cityId = dis.id>selected</#if>>${dis.name!''}</option>
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
            	<option value="${dis.id?c}" <#if dissc.regionId?? && dissc.regionId = dis.id>selected</#if>>${dis.name!''}</option>
            </#list>
            </#if>
        </select>
    </section>
</div>
</form>
<!-- 地区三级联动-结束 -->

<!-- Center Start -->
<section class="container">
    <div class="classify" id="classify">
        <section class="cla_left fl" id="cla_left">
            <aside>
                <#if typeList?? && typeList?size gt 0>
            	<#list typeList as type>
                <a href="javascript:searchAgentType(${type.id},${regionId!'0'});" title="${type.name!''}" <#if type_index==0>class="active"</#if>>${type.name!''}</a>
                </#list>
                </#if>
            </aside>
        </section>
	    <form action = "${app.basePath}/mobile/agent/detail", method="post" id="sub_form">
        <section class="cla_right fr" id="cla_right">
        <div id="sub_type_list">
        </div>
        
   	
	    <div style="width:4.7rem;height:0.8rem"></div>
    	<input type="hidden" name="agentId" id="agentid" value="<#if agent??>${agent.id?c}</#if>" />
        
        <input type="hidden" name="regionId" id="regionId"  value="<#if district??>${district.id!'0'}</#if>"/>
        <input type="hidden" name="disLevel" id="disLevel"  value="<#if district??>${district.level!''}</#if>"/>
        <input type="hidden" name="level" id="level" value="${agent.level!''}">
        <input type="hidden" name="groupId" id="groupId" value="${agent.groupId!'0'}">
        <aside class="god_btn"><input type="button" onclick="subAgent()"  value="确定选择" /></aside>
		</section>
	    </form>
    </div>
</section>
