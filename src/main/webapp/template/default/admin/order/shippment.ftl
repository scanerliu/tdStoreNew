<#import "/common/app.ftl" as app>
<form id="shipmentForm" action="${app.basePath}/admin/order/shiporder" class="easyui-form" method="post" data-options="novalidate:true">
<div class="pad_lr_10">
<div class="J_tablelist table_list">
<input type="hidden" name="orderId" value="${order.orderId!''}">
<table width="100%" cellspacing="0">
<tbody>
<tr>
	<th width="20%">订单编号：</th>
	<td>${order.orderNo!''}</td>
</tr>
<tr>
	<th>快递公司：</th>
	<td>
		<select name="trackingId">
			<#if expressList??>
			<#list expressList as express>
				<option value="${express.id}">${express.name}</option>
			</#list>
			</#if>
		</select>
	</td>
</tr>
<tr>
	<th>物流单号：</th>
	<td><input type="text" name="trackingNo" class="easyui-textbox" value="" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]"></td>
</tr>
</tbody>
</table>
</div>
</div>
<div data-options="region:'south',border:false" style="text-align:center;padding:5px 0 0;">
    <a class="easyui-linkbutton" href="javascript:;" onclick="shippmentOrder()" style="width:80px">提交</a>
    <a class="easyui-linkbutton" href="javascript:;" onclick="closeShipmentwin()" style="width:80px">取消</a>
</div>
</form>
<script>
$(function(){
});
</script>