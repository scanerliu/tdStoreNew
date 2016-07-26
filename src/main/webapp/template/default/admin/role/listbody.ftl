<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a data-height="190" data-width="450" data-id="add" data-title="添加角色" href="javascript:;" class="add fb J_showdialog" onclick="editRole(0)"><em>添加角色</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
<th width="40"><input type="checkbox" class="J_checkall" id="J_checkall" name="checkall"></th>
<th>ID</th>
<th align="left">角色名称</th>
<th align="left">角色说明</th>
<th align="left">角色类型</th>
<th align="left">更新时间</th>
<th align="left">更新人</th>
<th width="150">管理操作</th>
</tr>
</thead>
<tbody>
<#if roleList??>
<#list roleList as role>
    <tr>
        <td align="center">
        <input type="checkbox" value="${role.id}" name="subbox" class="J_checkitem"></td>
        <td align="center">${role.id}</td>
        <td>${role.name}</td>
        <td>${role.title}</td>
        <td>${role.getTypeStr()!''}</td>
        <td><#if role.updateTime??>${role.updateTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
        <td><#if role.updateUser??>${role.updateUser.unick!''}</#if></td>
        <td align="center">
        	<#if role.id!=2>
            <a href="javascript:;" onclick="editPermissions(${role.id})">授权</a><#if role.id!=1> | 
            <a class="J_showdialog" href="javascript:;" onclick="editRole(${role.id})">编辑</a> | 
            <a class="J_confirmurl" href="javascript:;" onclick="delRole(${role.id})">删除</a>
            </#if>
            </#if>
        </td>
    </tr>
</#list>
</#if>
</tbody>
</table>
</div>
<div class="btn_wrap_fixed">
</div>
</div>
<div class="btn_wrap_fixed">
<#assign pageId="Roles" />
<#include "/admin/common/common_postpage.ftl" />
</div>
<script>
$(function(){
    $("#J_checkall").click(function() {
        $('input[name="subbox"]').prop("checked",this.checked); 
    });
    var $subBox = $("input[name='subbox']");
    $subBox.click(function(){
        $("#J_checkall").prop("checked",$subBox.length == $("input[name='subbox']:checked").length ? true : false);
    });   
});
</script>
