<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="roleForm" action="${app.basePath}/admin/role/save" class="easyui-form" method="post" data-options="novalidate:true">
<table>
    <tr>
        <td>角色名称：</td>
        <td><input type="text" name="name" class="easyui-textbox" value="${role.name!''}" placeholder="角色名称" style="width:200px;height:32px" data-options="required:true"></td>
    </tr>
    <tr>
        <td>角色说明：</td>
        <td>
            <input type="text" name="title" class="easyui-textbox" value="${role.title!''}" placeholder="角色说明" style="width:200px;height:32px" data-options="required:true">
        </td>
    </tr>
    <tr>
        <td><input type="hidden" name="id" value="${role.id!''}"></td>
        <td>
            <button type="button" class="d-button" onclick="saveRole()">保存</button>
        </td>
    </tr>
</table>
</form>
</div>
<script>
$(function(){
});
</script>
