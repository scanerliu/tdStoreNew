<#import "/common/app.ftl" as app>
<script>
    $(function(){
        three_bing();
        left_right();
        cleft_height();
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
                <a href="javascript:;" title="${type.name!''}" <#if type_index==0>class="active"</#if>>${type.name!''}</a>
                </#list>
                </#if>
            </aside>
        </section>
        <section class="cla_right fr" id="cla_right">
        	<#if typeList?? && typeList?size gt 0>
        	<#list typeList as type>
	            <ul <#if type_index==0>class="active"</#if>>
        			<#if type.subList??>
        			<#list type.subList as stype>
		                <li <#if stype_index==0>class="active"</#if>>
		                    <menu>${stype.name!''}</menu>
		                    <aside class="as2">
		                    	<#if stype.subList??>
		                    	<#list stype.subList as etype>
		                    	
		                        <a t=${etype.id?c}  href="javascript:;" title="${etype.name!''}" <#if !experTypeIds?? || !experTypeIds?contains('['+etype.id?c+']')>class="active"</#if>>
		                            <img src="${etype.imageUrl!''}" alt="${etype.name!''}">
		                            <p>${etype.name!''}</p>
		                            
		                            <i></i>
		                        </a>
		                        </#list>
		                        </#if>
		                    </aside>
		                </li>
		         	</#list>
		         	</#if>
	             </ul>
		    </#list>
		   	</#if>
		   	<script>
		   		$(function(){
		   			$('.cla_right a.active').click(function(){
		   				$('#typeId').val($(this).attr('t'));
		   			});
		   		})
		   	</script>
            <div style="width:4.7rem;height:0.8rem"></div>
            <form action = "${app.basePath}/mobile/agent/detail", method="post" id="sub_form">
            	<input type="hidden" name="agentId" id="agentid" value="<#if agent??>${agent.id?c}</#if>" />
	            <input type="hidden" name="typeId" value="" id="typeId" />
	            <input type="hidden" name="regionId" id="regionId"  value="<#if district??>${district.id!'0'}</#if>"/>
	            <input type="hidden" name="disLevel" id="disLevel"  value="<#if district??>${district.level!''}</#if>"/>
	            <input type="hidden" name="level" id="level" value="${agent.level!''}">
	            <aside class="god_btn"><input type="button" onclick="subAgent()"  value="确定选择" /></aside>
            </form>
        </section>
    </div>
</section>
