<#import "/common/app.ftl" as app>
<form id=integrallist_form">
<div class="pad_lr_10">
	<div class="J_tablelist table_list">
		<table width="100%" cellspacing="0" id="customer_tree">
			<thead>
				<tr>
					<th>ID</th>
					<th align="left">变更前积分总数</th>
					<th align="left">变更类型</th>
					<th align="left">变更积分数</th>
					<th align="left">变更说明</th>
					<th align="left">时间</th>
				</tr>
			</thead>
		<tbody>
			<#if logList??>
			<#list logList as log>
			    <tr uid="${log.id}">
			        <td>${log.id}</td>
			        <td>${log.preintegral!''}</td>
			        <td>${log.getTypeStr()!''}</td>
			        <td>${log.integral!''}</td>
			        <td>${log.note!''}</td>
			        <td><#if log.createTime??>${log.createTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
			    </tr>
			</#list>
			</#if>
		</tbody>
		</table>
	</div>
	<input type="hidden" name="uid" value="${sc.uid!''}"/>
</div>
<#assign pageId="CustomerPointLogs" />
<#include "/admin/common/common_postpage.ftl" />
</form>
<script>
$(function(){
});
</script>
