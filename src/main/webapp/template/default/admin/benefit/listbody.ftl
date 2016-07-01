<#import "/common/app.ftl" as app>
<div class="subnav">
<div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="editBenefit(0)"><em>添加代理产品</em></a>&#12288;</div>
</div>
<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
<th align="left">ID</th>
<th align="left">分润类型</th>
<th align="left">成本价</th>
<th align="left">销售价</th>
<th align="left">代理类型</th>
<th align="left">级别</th>
<th align="left">更新时间</th>
<th align="left">更新人</th>
<th width="200">管理操作</th>
</tr>
</thead>
<tbody>
<#if benefitList??>
<#list benefitList as benefit>
    <tr>
        <td>${benefit.id}</td>
        <td>${benefit.benefitType!''}</td>
        <td>${benefit.groupId!''}</td>
        <td>${benefit.level!''}</td>
        <td>${benefit.percent!''}</td>
        <td><#if product.updateTime??>${product.updateTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
        <td><#if product.updateUser??>${product.updateUser.unick!''}</#if></td>
        <td align="center">
            <a class="J_showdialog" href="javascript:;" onclick="editBenefit(${product.id})">编辑</a> 
            |<a class="J_confirmurl" href="javascript:;" onclick="delBenefit(${product.id})">删除</a>
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
<#assign pageId="Benefits" />
<#include "/admin/common/common_postpage.ftl" />
</div>
<script>

</script>
