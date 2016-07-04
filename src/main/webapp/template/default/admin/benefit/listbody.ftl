<#import "/common/app.ftl" as app>
<div class="subnav">
<!-- <div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="editBenefit(0)"><em>添加分润设置</em></a>&#12288;</div> -->
</div>
<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
<th align="left">ID</th>
<th align="left">分润设置名称</th>
<th align="left">更新时间</th>
<th align="left">更新人</th>
<th width="200">管理操作</th>
</tr>
</thead>
<tbody>
<#if benefitTypeList??>
<#list benefitTypeList as benefitType>
    <tr>
        <td>${benefitType.id}</td>
        <td>${benefitType.name!''}</td>
        <td><#if benefitType.updateTime??>${benefitType.updateTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
        <td><#if benefitType.updateUser??>${benefitType.updateUser.unick!''}</#if></td>
        <td align="center">
            <a class="J_showdialog" href="javascript:;" onclick="editBenefit(${benefitType.id})">编辑</a> 
            <!-- |<a class="J_confirmurl" href="javascript:;" onclick="delBenefit(${benefitType.id})">删除</a> -->
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
