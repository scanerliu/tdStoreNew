<#import "/common/app.ftl" as app>
<div class="pad_lr_10">
	<div class="J_tablelist table_list">
	<table width="100%" cellspacing="0">
		<thead>
			<tr>
				<th width="5%">ID</th>
				<th align="center">菜单名称</th>
				<th align="center">状态</th>
				<th align="center">排序值</th>
				<th align="center">所选分类</th>
				<th align="center">更新时间</th>
				<th >操作</th>
			</tr>
		</thead>
		<tbody>
		<#if menulist?? && menulist?size gt 0>
				<#list menulist as menu>
					<tr>
				        <td align="center">${menu.mid?c}</td>
				        <td>${menu.name!''}</td>
				        <td align="center"><#if menu.status==1>正常<#else>屏蔽</#if></td>
				        <td align="center">${menu.sort!''}</td>
				        <td align="center">
				        	<#if menu.typeList??>
				            <#list menu.typeList as type>
				            	<#if type.productType??>
				            	${type.productType.name!''}<br/>
				            	</#if>
				            </#list>
				            </#if>
				        </td>
				        <td align="center"><#if menu.updateTime??>${menu.updateTime?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
				        <td align="center">
				            <a class="J_confirmurl" href="javascript:;" onclick="delPmenu(${menu.mid?c})">删除</a>
				           |<a class="J_confirmurl" href="javascript:;" onclick="editPmenu(${menu.mid?c})">编辑</a>
				        </td>
			    	</tr>
			    </#list>
			</#if>
		</tbody>
	</table>
	</div>
</div>
<div class="btn_wrap_fixed">
<#assign pageId="Pmenus" />
<#include "/admin/common/common_postpage.ftl" />
</div>
<script>
$(function(){
});
</script>
