<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x">
	<a data-height="190" data-width="450" data-id="add" data-title="添加会员等级" href="javascript:;" class="add fb J_showdialog" onclick="editMembership(0)"><em>添加会员等级</em></a>&#12288;
	<input type="button" value="批量删除" onclick="batchDelete()">&nbsp;&nbsp;&nbsp;
</div></div>
<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
	<th width="40"><input type="checkbox" class="J_checkall" id="J_checkall" name="checkall"></th>
	<th>ID</th>
	<th align="center">等级名称</th>
	<th align="center">级别</th>
	<th align="center">三级会员下限</th>
	<th align="center">升级提示</th>
	<th align="center">状态</th>
	<th align="center">操作</th>
</tr>
</thead>
<tbody>
<#if membershipList??>
<#list membershipList as membership>
    <tr>
        <td align="center">
        <input type="checkbox" value="${membership.id?c}" name="subbox" class="J_checkitem"></td>
        <td align="center">${membership.id?c}</td>
        <td>${membership.name!''}</td>
        <td align="center">${membership.level!''}</td>
        <td align="center">${membership.upgradeAgents?c!''}</td>
        <td align="center">${membership.tip!''}</td>
        <td align="center">${membership.statusStr!''}</td>
        <td align="center">
            <a class="J_showdialog" href="javascript:;" onclick="editMembership(${membership.id?c})">编辑</a> | 
            <a class="J_confirmurl" href="javascript:;" onclick="delMembership(${membership.id?c})">删除</a>
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
<#assign pageId="Membership" />
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
