<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="membershipForm" action="${app.basePath}/admin/membership/save" class="easyui-form" method="post" data-options="novalidate:true">
<table class="table_form" width="100%">
	<tr>
        <th>等级名称：</th>
        <td>
            <input type="text" name="name" class="easyui-textbox" value="${membership.name!''}" style="width:200px;height:30px" data-options="required:true" validType="length[1,100]">
        </td>
    </tr>
	<tr>
        <th width="150">级别：</th>
        <td><input type="text" name="level" class="easyui-numberbox" value="<#if membership.level??>${membership.level?c}</#if>" data-options="required:true,min:0"></td>
    </tr>
    <tr>
        <th width="150">三级会员下限：</th>
        <td><input type="text" name="upgradeAgents" class="easyui-numberbox" value="<#if membership.level??>${membership.upgradeAgents?c}</#if>" data-options="required:true,min:0"></td>
    </tr>
    <tr>
        <th width="150">升级提示：</th>
        <td><textarea rows="5" cols="60" name="tip">${membership.tip!''}</textarea></td>
    </tr>
    <tr>
        <th>状态：</th>
        <td>
            <input type="radio" name="status" value="1" <#if !membership.status?? || (membership.status?? && membership.status==1)>checked</#if>>正常
            <input type="radio" name="status" value="2" <#if membership.status?? && membership.status==2>checked</#if>>屏蔽
        </td>
    </tr>
    <tr>
        <td>
        	<input type="hidden" name="id" value="<#if membership.id??>${membership.id?c}</#if>">
        </td>
        <td>
            <button type="button" class="smt mr10" onclick="saveMembership()">保存</button>
        </td>
    </tr>
</table>
</form>
</div>