<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="roleForm" action="${app.basePath}/admin/role/save" class="easyui-form" method="post" data-options="novalidate:true">
<table class="table_form" width="100%">
    <tr>
        <th width="150">角色名称：</th>
        <td><input type="text" name="name" class="easyui-textbox" value="${role.name!''}" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]"></td>
    </tr>
    <tr>
        <th width="150">角色说明：</th>
        <td>
            <input type="text" name="title" class="easyui-textbox" value="${role.title!''}" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]">
        </td>
    </tr>
    <tr>
        <th width="150">角色类型：</th>
        <td>
            <label><input type="radio" name="type" class="J_change_zidong" value="1" <#if !role.type?? || (role.type?? && role.type==1)>checked</#if>>平台角色&nbsp;&nbsp;</label>
            <label><input type="radio" name="type" class="J_change_zidong" value="2" <#if role.type?? && role.type==2>checked</#if>>会员角色</label>
        </td>
    </tr>
    <tr>
        <td><input type="hidden" name="id" value="${role.id!''}"></td>
        <td>
            <button type="button" class="smt mr10" onclick="saveRole()">保存</button>
        </td>
    </tr>
</table>
</form>
</div>
<script>
$(function(){
});
</script>
