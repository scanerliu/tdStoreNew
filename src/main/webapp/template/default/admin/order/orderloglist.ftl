<#import "/common/app.ftl" as app>
<table width="100%" cellspacing="0">
<#if logList?? && (logList?size>0)>
	<tr>
		<th width="5%">序号</th>
		<th>操作描述</th>
		<th width="6%">操作人</th>
		<th width="10%">操作时间</th>
	<#list logList as log>
	<tr>
		<td>${log_index+1}</td>
		<td>${log.note}</td>
		<td>${log.updateUser.unick!''}</td>
	    <td>
	    	<#if log.createTime??>${log.createTime?string("yyyy-MM-dd HH:mm:ss")}</#if>
	    </td>
	</tr>
	</#list>
<#else>
<tr>
	<td>暂无相关信息。</td>
</tr>
</#if>
</table>