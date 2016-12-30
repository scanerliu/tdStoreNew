<#import "/common/app.ftl" as app>
<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0" id="customer_tree">
<thead>
<tr>
<th width="40"><input type="checkbox" class="J_checkall" id="J_checkall" name="checkall"></th>
<th>账号</th>
<th align="left">昵称</th>
<th align="left">性别</th>
<th align="left">状态</th>
<th align="left">最近登陆时间</th>
<th align="left">登陆ip</th>
<th align="left">更新时间</th>
<th align="left">更新人</th>
<th width="320">管理操作</th>
</tr>
</thead>
<tbody>
<#if userList??>
<#list userList as user>
    <tr uid="${user.uid}">
        <td align="center">
        <input type="checkbox" value="${user.uid}" name="subbox" class="J_checkitem"></td>
        <td align="center">${user.uname}</td>
        <td>${user.unick!''}</td>
        <td>${user.getUgenterStr()!''}</td>
        <td>${user.getUstatusStr()!''}</td>
        <td><#if user.lastLoginTime??>${user.lastLoginTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
        <td>${user.lastLoginIp!''}</td>
        <td><#if user.updateTime??>${user.updateTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
        <td><#if user.updateUser??>${user.updateUser.unick!''}</#if></td>
        <td align="center">
        	<a href="javascript:;" onclick="customerDetail(${user.uid})">详情</a> |
        	<a href="javascript:;" onclick="changePassword(${user.uid})">修改密码</a> |
        	<#if user.ustatus==2>
    			<a href="javascript:;" onclick="activeCustomer(${user.uid})">启用</a> | 
    		<#else>
    			<a href="javascript:;" onclick="forbiddenCustomer(${user.uid})">锁定</a> | 
    		</#if>
    		<#if !user.tempsupplier?? ||(user.tempsupplier?? && user.tempsupplier==0)>
    			<a href="javascript:;" onclick="tempsupplierCustomer(${user.uid})">临时供应商</a> | 
    		<#elseif user.tempsupplier?? && user.tempsupplier==1>
    			<a href="javascript:;" onclick="cancelTempsupplierCustomer(${user.uid})">取消零时供应商</a> | 
    		</#if> 
            <a href="javascript:;" onclick="editRoles(${user.uid})">授权</a> | 
            <a class="J_showdialog" href="javascript:;" onclick="editCustomer(${user.uid})">编辑</a> | 
            <a class="J_confirmurl" href="javascript:;" onclick="delCustomer(${user.uid})">删除</a>
        </td>
    </tr>
</#list>
</#if>
</tbody>
</table>
</div>
<div class="btn_wrap_fixed">
	<input type="hidden" name="keyword" value="${sc.keyword!''}"/>
	<input type="hidden" name="ustatus" value="${sc.ustatus!''}"/>
	<input type="hidden" name="uverification" value="${sc.uverification!''}"/>
	<input type="hidden" name="supplierType" value="${sc.supplierType!''}"/>
	<input type="hidden" name="agent" value="${sc.agent?c}"/>
	<input type="hidden" name="branch" value="${sc.branch?c}"/>
</div>
</div>
<div class="btn_wrap_fixed">
<#assign pageId="Customers" />
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
    //$("#customer_tree").treetable({expandable: true, indent:20, initialState: "collapsed",expanderTemplate:"<a href='javascript:;'></a>",stringExpand:"查看详情",stringCollapse:"收起",onNodeExpand:nodeExpand});
});
</script>
