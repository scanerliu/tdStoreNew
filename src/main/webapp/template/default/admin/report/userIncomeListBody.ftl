<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x">
</div></div>

<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
	<th align="center">会员账号</th>
	<th align="center">收入金额</th>
</tr>
</thead>
<tbody>
<#if userAccountLogList??>
<#list userAccountLogList as uac>
    <tr>
        <td align="center">${uac.username!''}</td>
        <td align="center"><#if uac.totalUpamount??>${uac.totalUpamount?c}</#if></td>
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
<#-- 搜索条件 begin-->
<input type="hidden" name="beginDate" value="<#if sc.beginDate??>${sc.beginDate?string('yyyy-MM-dd')}</#if>">
<input type="hidden" name="endDate" value="<#if sc.endDate??>${sc.endDate?string('yyyy-MM-dd')}</#if>">
<input type="hidden" name="username" value="${sc.username!''}">
<input type="hidden" name="isAsc" value="<#if sc.isAsc??>${sc.isAsc?c}</#if>">
<#-- 搜索条件 end-->
<#assign pageId="UserIncome" />
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
