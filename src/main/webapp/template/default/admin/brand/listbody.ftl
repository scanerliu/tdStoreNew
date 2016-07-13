<#import "/common/app.ftl" as app>
<div class="subnav">
	<div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="editBrand(0)"><em>添加品牌</em></a>&#12288;</div>
</div>
<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
<th align="left">品牌ID</th>
<th align="left">logo</th>
<th align="left">品牌名称</th>
<th align="left">状态</th>
<th align="left">排序值</th>
<th align="left">更新时间</th>
<th align="left">更新人</th>
<th width="200">管理操作</th>
</tr>
</thead>
<tbody>
<#if brandList??>
<#list brandList as brand>
    <tr>
        <td>${brand.id}</td>
        <td><img src="${app.basePath}${brand.imageUrl}" alt="logo" width="80"/></td>
        <td>${brand.name}</td>
        <td>${brand.getStatusStr()!''}</td>
        <td>${brand.sortBy!''}</td>
        <td><#if brand.updateTime??>${brand.updateTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
        <td><#if brand.updateUser??>${brand.updateUser.unick!''}</#if></td>
        <td align="center">
            <a class="J_showdialog" href="javascript:;" onclick="editBrand(${brand.id})">编辑</a> 
            |<a class="J_confirmurl" href="javascript:;" onclick="editBrand(${brand.id})">删除</a>
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
<#assign pageId="Brands" />
<#include "/admin/common/common_postpage.ftl" />
</div>
<script>

</script>
