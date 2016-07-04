<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&nbsp;&nbsp;&nbsp;<#if tdBrancheCompany.status == 1><input type="button" class="btn" value="禁用" onclick="goCheck(${tdBrancheCompany.id!''}, false)"><#else><input type="button" class="btn" value="正常" onclick="goCheck(${tdBrancheCompany.id!''}, true)"></#if>&#12288;</div></div>

<div class="pad_lr_10">
<form id="brancheCompanyForm" action="${app.basePath}/admin/branch/save" class="easyui-form" method="post" data-options="novalidate:true">
<table class="table_form" width="100%">
	<tr>
        <th width="200px;">用户：</th>
        <td>${tdBrancheCompany.user.uname!''}</td>
    </tr>
    <tr>
        <th>级别：</th>
        <td>${tdBrancheCompany.levelStr!''}</td>
    </tr>
     <tr>
        <th>地区：</th>
        <td>${tdBrancheCompany.district.name!''}</td>
    </tr>
	<tr>
        <th>状态：</th>
        <td>${tdBrancheCompany.statusStr!''}</td>
    </tr>
    <#if tdBrancheCompany.updateTime??>
		<tr>
	        <th>修改时间：</th>
	        <td>${tdBrancheCompany.updateTime?string('yyyy-MM-dd HH:mm:ss')}</td>
	    </tr>   
	</#if>
	<#if tdBrancheCompany.updatePerson??>
		<tr>
	        <th>修改人：</th>
	        <td>${tdBrancheCompany.updatePerson.uname!''}</td>
	    </tr>   
	</#if>
</table>
</form>
</div>