<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="editManager(0)"><em>添加管理员</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
<th width="40"><input type="checkbox" class="J_checkall" id="J_checkall" name="checkall"></th>
<th>ID</th>
<th align="left">管理员账号</th>
<th align="left">姓名</th>
<th align="left">性别</th>
<th align="left">状态</th>
<th align="left">最近登陆时间</th>
<th align="left">登陆ip</th>
<th align="left">更新时间</th>
<th align="left">更新人</th>
<th width="200">管理操作</th>
</tr>
</thead>
<tbody>
<#if userList??>
<#list userList as user>
    <tr>
        <td align="center">
        <input type="checkbox" value="${user.uid}" name="subbox" class="J_checkitem"></td>
        <td align="center">${user.uid}</td>
        <td>${user.uname}</td>
        <td>${user.unick!''}</td>
        <td>${user.getUgenterStr()!''}</td>
        <td>${user.getUstatusStr()!''}</td>
        <td><#if user.lastLoginTime??>${user.lastLoginTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
        <td>${user.lastLoginIp!''}</td>
        <td><#if user.updateTime??>${user.updateTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
        <td><#if user.updateUser??>${user.updateUser.unick!''}</#if></td>
        <td align="center">
        	<#if manager.uid==1>
        		<a href="javascript:;" onclick="changePassword(${user.uid})">修改密码</a> | 
        		<#if user.ustatus==2>
        			<a href="javascript:;" onclick="activeManager(${user.uid})">启用</a> | 
        		<#else>
        			<a href="javascript:;" onclick="forbiddenManager(${user.uid})">锁定</a> | 
        		</#if>
	            <a href="javascript:;" onclick="editRoles(${user.uid})">授权</a> | 
	            <a class="J_showdialog" href="javascript:;" onclick="editManager(${user.uid})">编辑</a> | 
	            <a class="J_confirmurl" href="javascript:;" onclick="delManager(${user.uid})">删除</a>
            <#elseif user.uid!=1>
            	<a href="javascript:;" onclick="changePassword(${user.uid})">修改密码</a> |
            	<#if user.ustatus==2>
        			<a href="javascript:;" onclick="activeManager(${user.uid})">启用</a> | 
        		<#else>
        			<a href="javascript:;" onclick="forbiddenManager(${user.uid})">锁定</a> | 
        		</#if> 
	            <a href="javascript:;" onclick="editRoles(${user.uid})">授权</a> | 
	            <a class="J_showdialog" href="javascript:;" onclick="editManager(${user.uid})">编辑</a> | 
	            <a class="J_confirmurl" href="javascript:;" onclick="delManager(${user.uid})">删除</a>
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
<#assign pageId="Managers" />
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
