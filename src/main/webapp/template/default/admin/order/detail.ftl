<#import "/common/app.ftl" as app>
<td colspan="10">
<div class="J_tablelist table_list">
<div class="content_menu ib_a blue line_x"><a href="javascript:;" onclick="shipOrder(${order.orderId!''})" ><em>发货</em></a><span>|</span><a href="javascript:;" onclick="" ><em>退货</em></a><span>|</span><a href="javascript:;" onclick="" ><em>退款</em></a></div>
<div id="dg" style="width:800px;height:350px;">
    <div title="基本信息" style="padding:20px;">
    	<table width="100%" cellspacing="0">
        <tr>
            <td width="50%" valign="top">
            	<table width="100%">
            		<tr>
            			<th>商品名称</th>
            			<th>商品价格</th>
            			<th>商品数量</th>
            		</tr>
            		<#if order?? && order.skuList??>
            		<#list order.skuList as sku>
            		<tr>
            			<td>${sku.productName!''} ${sku.displaySpecifications!''}</td>
            			<td>${sku.price!''}</td>
            			<td>${sku.quantity!''}</td>
            		</tr>
            		</#list>
            		</#if>
            	</table>
            </td>
            <td width="20%" valign="top">
            	<table class="table_form" width="100%">
            		<tr>
            			<th width="40%">商品总金额：</th>
            			<td>${order.productAmount!'0'}</td>
            	    </tr>
            	    <tr>            	    
            			<th>邮费：</th>
            			<td>${order.postage!'0'}</td>
            	    </tr>
            	    <tr>
            			<th>订单总金额：</th>
            			<td>${order.totalAmount!'0'}</td>
            	    </tr>
            	    <tr>
            			<th>积分抵扣金额；</th>
            			<td>${order.pointAmount!'0'}</td>
            	    </tr>
            	    <tr>
            			<th>支付金额：</th>
            			<td>${order.payAmount!'0'}</td>
            	    </tr>
            	    <tr>
            			<th>订单获得积分：</th>
            			<td>${order.gainPoints!'0'}</td>
            	    </tr>
            	</table>
            </td>
            <td width="30%" valign="top">
            	<table class="table_form" width="100%">
            	    <tr>            	    
            			<th width="30%">供应商账号：</th>
            			<td><#if order.supplierUser??>${order.supplierUser.unick!''}</#if></td>
            	    </tr>
            		<tr>
            			<th>下单账号：</th>
            			<td><#if order.orderUser??>${order.orderUser.unick!''}</#if></td>
            	    </tr>
            		<tr>
            			<th>联系人：</th>
            			<td><#if order.orderAddress??>${order.orderAddress.customerName!''}</#if></td>
            	    </tr>
            		<tr>
            			<th>联系电话：</th>
            			<td><#if order.orderAddress??>${order.orderAddress.telphone!''}</#if></td>
            	    </tr>
            	    <tr>            	    
            			<th>收货地址：</th>
            			<td><#if order.orderAddress??>${order.orderAddress.regionFullName!''} ${order.orderAddress.address!''}</#if></td>
            	    </tr>
            	</table>
            </td>
        </tr>
        </table>
    </div>
    <div title="发货信息" style="overflow:auto;padding:20px;" href="${app.basePath}/admin/order/shippments?id=${order.orderId!''}">
    </div>
</div>
</div>
<div id="shipmentwindow" class="easyui-window" closed="true" style="width:600px;height:300px;padding:10px"></div>
<script>
$(function(){
     $('#dg').tabs({
	    border:false,
	    fit:true,
	    tabPosition:"left"
	});
});
</script>
</td>