<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x">
</div></div>

<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
	<th align="center">变更类型</th>
	<th align="center">变更前余额</th>
	<th align="center">变更金额</th>
	<th align="center">变更时间</th>
	<th align="center">变更说明</th>
</tr>
</thead>
<tbody>
<#if acountLogList??>
<#list acountLogList as acountLog>
    <tr>
        <td align="center">${acountLog.typeStr!''}</td>
        <td align="center"><#if acountLog.preamount??>${acountLog.preamount?c}</#if></td>
        <td align="center"><#if acountLog.upamount??>${acountLog.upamount?c}</#if></td>
        <td align="center">${acountLog.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
        <td align="center">${acountLog.note!''}</td>
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
<#-- 搜索条件 begin -->
<input type="hidden" name="beginDate" value="<#if sc.beginDate??>${sc.beginDate?string('yyyy-MM-dd')}</#if>">
<input type="hidden" name="endDate" value="<#if sc.endDate??>${sc.endDate?string('yyyy-MM-dd')}</#if>">
<#-- 搜索条件 end -->
<#assign pageId="AcountLog" />
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
