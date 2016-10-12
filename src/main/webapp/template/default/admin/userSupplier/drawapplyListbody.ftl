<#import "/common/app.ftl" as app>
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
	<th>ID</th>
	<th align="center">用户</th>
	<th align="center">提现金额</th>
	<th align="center">银行卡号</th>
	<th align="center">银行卡姓名</th>
	<th align="center">状态</th>
	<th align="center">申请时间</th>
	<th align="center">更新时间</th>
	<th align="center">操作</th>
</tr>
</thead>
<tbody>
<#if drawapplyList??>
<#list drawapplyList as drawapply>
    <tr>
        <td align="center">${drawapply.id?c}</td>
        <td align="center">${drawapply.user.uname!''}</td>
        <td align="center">${drawapply.amount!''}</td>
        <td align="center">${drawapply.cardno!''}</td>
        <td align="center">${drawapply.carduser!''}</td>
        <td align="center">${drawapply.statusStr!''}</td>
        <td align="center">${drawapply.createTime?string('yyyy-MM-dd HH:mm:ss')!''}</td>
        <td align="center"><#if drawapply.updateTime??>${drawapply.updateTime?string('yyyy-MM-dd HH:mm:ss')!''}</#if></td>
        <td align="center">
            <a class="J_showdialog" href="javascript:;" onclick="editDrawApply(${drawapply.id?c})">详情</a>
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
<input type="hidden" name="startTime" value="<#if sc.startTime??>${sc.startTime?string('yyyy-MM-dd')}</#if>">
<input type="hidden" name="endTime" value="<#if sc.endTime??>${sc.endTime?string('yyyy-MM-dd')}</#if>">
<input type="hidden" value="${sc.uid!''}" name="uid">
<input type="hidden" value="${sc.bankid!''}" name="bankid">
<input type="hidden" value="${sc.username!''}" name="username">
<input type="hidden" value="${sc.status!''}" name="status">
<div class="btn_wrap_fixed">
<#assign pageId="UserSupplier" />
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
