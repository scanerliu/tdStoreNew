<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="permissionForm" action="${app.basePath}/admin/role/savepermission" method="post">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
    <tr>
        <th width="8"><input type="checkbox" class="J_checkall" id="J_checkall" name="checkall"></th><th align="left">全部选择/取消</th>
    </tr>
<thead>
<tbody>
    <#if permissionList??>
    <#list permissionList as permission>
    <tr>
        <td width="8"><input type="checkbox" value="${permission.id}" name="permissionList[${permission_index}].id" class="J_checkitem" <#if role?? && role.hasPermissionId(permission.id)==true>checked=checked</#if>></td>
        <td align="left">${permission.title!''}</td>
    </tr>
    </#list>
    </#if>
    <tr>
        <td><input type="hidden" name="id" value="${role.id!''}"></td>
        <td>
            <button type="button" class="d-button" onclick="savePermissions()">保存</button>
        </td>
    </tr>
</tbody>
</table>
</div>
</form>
</div>
<script>
$(function(){
$("#permissionForm #J_checkall").click(function() {
        $("#permissionForm :input[class='J_checkitem']").prop("checked",this.checked); 
    });
    var $subBox = $("#permissionForm :input[class='J_checkitem']");
    $subBox.click(function(){
        $("#permissionForm #J_checkall").prop("checked",$subBox.length == $("#permissionForm input[name='J_checkitem']:checked").length ? true : false);
    });
});
</script>
