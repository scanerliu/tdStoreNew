<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="attributeForm" action="${app.basePath}/admin/complaint/save" class="easyui-form" method="post" data-options="novalidate:true">
<input type="hidden" name="complaintId" value="<#if complaint??>${complaint.id?c}</#if>">
<table class="table_form" width="100%">
    <tr>
        <th width="150">投诉人：</th>
        <td width="12%"><#if complaint.complaintUser??>${complaint.complaintUser.uname!''}</#if></td>
        <th width="150">投诉商家：</th>
        <td><#if complaint.supplierUser??>${complaint.supplierUser.uname!''}</#if></td>
    </tr>
    <tr>
        <th width="150">相关订单：</th>
        <td width="12%"><#if complaint.order??>${complaint.order.orderNo!''}</#if></td>
        <th width="150">投诉时间：</th>
        <td><#if complaint.createTime??>${complaint.createTime?string('yyyy-MM-dd')}</#if></td>
    </tr>
    <tr>
    	<th width="150">投诉内容：</th>
    	<td colspan ="3">${complaint.complaint!''}</td>
    </tr>
    <tr>
        <th>处理状态：</th>
        <td colspan ="3">
        	<#if complaint.status??>
    			<#if complaint.status ==1>
    				<input type="radio" name="status" value="1" checked>新投诉&emsp;
		            <input type="radio" name="status" value="2" >处理中&emsp;
		            <input type="radio" name="status" value="3" >处理完成&emsp;
    			<#elseif complaint.status ==2>
    				<input type="radio" name="status" value="1" >新投诉&emsp;
		            <input type="radio" name="status" value="2" checked>处理中&emsp;
		            <input type="radio" name="status" value="3" >处理完成&emsp;
    			<#elseif complaint.status ==3>
    				<span style="color: #08B308;">处理完成</span>
    			</#if>
        	</#if>
        </td>
    </tr>
    <tr>
    	<th>处理备注</th>
    	<td colspan ="3">
    		<textarea rows="5" cols="60" name="remark" <#if complaint.status ==3>disabled</#if>>${complaint.remark!''}</textarea>
    	</td>
    </tr>
    <tr>
        <td colspan ="4" align="center">
            <button type="button" class="smt mr10" onclick="saveAttribute()">保存</button>
        </td>
    </tr>
</table>
</form>
</div>
<script>
$(function(){
});
</script>
