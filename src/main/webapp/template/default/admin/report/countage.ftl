<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x">
</div></div>

<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
	<th align="center">未知年龄人数</th>
	<th align="center">18岁以下人数</th>
	<th align="center">18-25岁人数</th>
	<th align="center">25-35岁人数</th>
	<th align="center">35-45岁人数</th>
	<th align="center">45-55岁人数</th>
	<th align="center">55-65岁人数</th>
	<th align="center">65-75岁人数</th>
	<th align="center">75岁以上人数</th>
</tr>
</thead>
<tbody>
<#if report??>
<#assign totalcount =0>
    <tr>
        <td align="center">${report.nose!'0'}</td>
        <td align="center">${report.blow18!'0'}</td>
        <td align="center">${report.blow25!'0'}</td>
        <td align="center">${report.blow35!'0'}</td>
        <td align="center">${report.blow45!'0'}</td>
        <td align="center">${report.blow55!'0'}</td>
        <td align="center">${report.blow65!'0'}</td>
        <td align="center">${report.blow75!'0'}</td>
        <td align="center">${report.over75!'0'}</td>
    </tr>
    <#assign totalcount =report.nose+report.blow18+report.blow25+report.blow35+report.blow45+report.blow55+report.blow65+report.blow75+report.over75>
	<tr>
        <td align="center">总计</td>
        <td align="center">${totalcount!'0'}</td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
    </tr>
</#if>
</tbody>
</table>
</div>
<div class="btn_wrap_fixed">
</div>
</div>
