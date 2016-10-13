<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x">
</div></div>

<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
	<th align="center">省份</th>
	<th align="center">人数</th>
</tr>
</thead>
<tbody>
<#if reportList??>
<#assign totalcount =0>
<#list reportList as item>
    <tr>
        <td align="center">${item.name!'未知地区'}</td>
        <td align="center">${item.num!'0'}</td>
    </tr>
    <#assign totalcount =totalcount+item.num>
</#list>
	<tr>
        <td align="center">总计</td>
        <td align="center">${totalcount!'0'}</td>
    </tr>
</#if>
</tbody>
</table>
</div>
<div class="btn_wrap_fixed">
</div>
</div>
