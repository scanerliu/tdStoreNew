<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x">
</div></div>

<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
	<th align="center">会员账号</th>
	<th align="center">金额</th>
</tr>
</thead>
<tbody>
<#if accountList??>
<#list accountList as uac>
    <tr>
        <td align="center">${uac.username!''}</td>
        <td align="center"><#if uac.amount??>${uac.amount?c}</#if></td>
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
<input type="hidden" name="username" value="${sc.username!''}">
<input type="hidden" name="sortBy" value="${sc.sortBy!''}">
<#-- 搜索条件 end-->
<#assign pageId="Accounts" />
<#include "/admin/common/common_postpage.ftl" />
</div>
