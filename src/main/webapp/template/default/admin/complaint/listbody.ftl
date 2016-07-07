<#import "/common/app.ftl" as app>
<div class="subnav">
	<div class="content_menu ib_a blue line_x">
		<#--
		<a data-height="190" data-width="450" data-id="add" data-title="添加类型" href="javascript:;" class="add fb J_showdialog" onclick="editProductAttribute(0)">
		<em>添加类型</em></a>&#12288;
		-->
	</div>
</div>
<div class="pad_lr_10">
<div class="J_tablelist table_list">
	<div id="district">
		<!--
		<input type="button" value="批量删除" onclick="batchDelete()">&nbsp;&nbsp;&nbsp;
		<input type="button" value="添加" onclick="addDistrict()">
		-->
    </div>

<table width="100%" cellspacing="0">
	<thead>
		<tr>
			<th width="4%"><input type="checkbox" class="J_checkall" id="J_checkall" name="checkall"></th>
			<th width="4%">ID</th>
			<th align="center">投诉人</th>
			<th align="center">投诉商家</th>
			<th align="center">订单</th>
			<th align="center">投诉内容</th>
			<th align="center">状态</th>
			<th align="center">投诉时间</th>
			<th >操作</th>
		</tr>
	</thead>
<tbody>
<#if complaintList?? && complaintList?size gt 0>
		<#list complaintList as comp>
			<tr>
		        <td align="center">
		        	<input type="checkbox" value="${comp.id?c}" name="subbox" class="J_checkitem">
		        </td>
		        <td align="center">${comp.id?c}</td>
		        <td align="center"><#if comp.complaintUser??>${comp.complaintUser.uname!''}</#if></td>
		        <td align="center"><#if comp.supplierUser??>${comp.supplierUser.uname!''}</#if></td>
		        <td align="center"><#if comp.order??>${comp.order.orderNo!''}</#if></td>
		        <td align="center" width="35%">${comp.complaint!''}</td>
		        <td align="center"><#if comp.status??><#if comp.status==1>新投诉<#elseif comp.status==2>处理中<#else>处理完成</#if></#if></td>
		        <td align="center"><#if comp.createTime??>${comp.createTime?string('yyyy-MM-dd')}</#if></td>
		        <td align="center">
		            <a class="J_confirmurl" href="javascript:;" onclick="delComplaint(${comp.id?c})">删除</a>
		           |<a class="J_confirmurl" href="javascript:;" onclick="editComplaint(${comp.id?c})">编辑</a>
		        </td>
	    	</tr>
	    </#list>
	</#if>
</tbody>
</table>
</div>
<div class="btn_wrap_fixed"></div>
<div class="btn_wrap_fixed">
<#assign pageId="attributes" />
<#include "/admin/common/common_postpage.ftl" />
</div>
<script>
$(function(){
    $("#J_checkall").click(function() {
        $('input[name="subbox"]').prop("checked",this.checked); 
    });
    var $subBox = $("input[name='subbox']");
    $subBox.click(function(){
        $("#J_checkall").prop("checked",$subBox.length == $("input[name='subbox']:checked").length ? true : false);
    });
});



</script>
