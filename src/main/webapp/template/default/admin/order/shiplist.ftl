<#import "/common/app.ftl" as app>
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
</tr>
</table>
<script>
$(function(){
});
</script>
