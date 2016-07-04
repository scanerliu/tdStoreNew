<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="subnav gray">提示：设置的数字为整数，代表百分比例，如：设置5，代表5%。</div>
<div class="pad_lr_10">
<form id="benefitForm" action="${app.basePath}/admin/benefit/save" class="easyui-form" method="post" data-options="novalidate:true">
<#assign _n=0 />
<table class="table_form" width="100%">
    <tr>
        <td align="left">${benefitType.name!''} 设置</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td align="center">单类代理</td>
        <td align="center">公司</td>
        <td align="center">三级分销</td>
    </tr>
    <tr>
        <td>
        	<table class="table_form" width="100%">
        		<#if benefitType?? && benefitType.benefitList??>
        		<#list benefitType.benefitList as benefit>
        		<#if benefit?? && benefit.groupId==1>
			    <tr>
			        <th>${benefit.getLevelStr()!''}<input type="hidden" name="benefitList[${_n}].id" value="${benefit.id!''}"></th>
			        <td><input type="text" name="benefitList[${_n}].percent" class="easyui-numberspinner" value="${benefit.percent!''}" id="benefit_${benefit.id!''}" style="width:200px;height:30px" data-options="min:0,max:100,required:true"></th>
			    </tr>
			    <#assign _n++ />
			    </#if>
			    </#list>
			    </#if>
			</table>
        </td>
        <td>
            <table class="table_form" width="100%">
        		<#if benefitType?? && benefitType.benefitList??>
        		<#list benefitType.benefitList as benefit>
        		<#if benefit?? && benefit.groupId==2>
			    <tr>
			        <th>${benefit.getLevelStr()!''}<input type="hidden" name="benefitList[${_n}].id" value="${benefit.id!''}"></th>
			        <td><input type="text" name="benefitList[${_n}].percent" class="easyui-numberspinner" value="${benefit.percent!''}" id="benefit_${benefit.id!''}" style="width:200px;height:30px" data-options="min:0,max:100,required:true"></th>
			    </tr>
			    <#assign _n++ />
			    </#if>
			    </#list>
			    </#if>
			</table>
        </td>
        <td>
            <table class="table_form" width="100%">
        		<#if benefitType?? && benefitType.benefitList??>
        		<#list benefitType.benefitList as benefit>
        		<#if benefit?? && benefit.groupId==3>
			    <tr>
			        <th>${benefit.getLevelStr()!''}<input type="hidden" name="benefitList[${_n}].id" value="${benefit.id!''}"></th>
			        <td><input type="text" name="benefitList[${_n}].percent" class="easyui-numberspinner" value="${benefit.percent!''}" id="benefit_${benefit.id!''}" style="width:200px;height:30px" data-options="min:0,max:100,required:true"></th>
			    </tr>
			    <#assign _n++ />
			    </#if>
			    </#list>
			    </#if>
			</table>
        </td>
    </tr>
    <tr>
        <td><input type="hidden" name="id" value="${benefitType.id!''}"></td>
        <td>
            <button type="button" class="smt mr10" onclick="saveBenefit()">保存</button>
        </td>
        <td>
        </td>
    </tr>
</table>
</form>
</div>
<script>
$(function(){
});
</script>
