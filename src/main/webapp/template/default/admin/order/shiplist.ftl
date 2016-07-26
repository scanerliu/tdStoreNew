<#import "/common/app.ftl" as app>
<table width="100%" cellspacing="0">
<#if shipList?? && (shipList?size>0)>
	<#list shipList as ship>
	<tr>
		<td width="5%" valign="top">${ship_index+1}.</td>
		<td width="20%" valign="top">
			<table width="100%" class="table_form">
	    		<tr>
	    			<th>物流公司:</th>
	    			<td><#if ship.trackExpress??>${ship.trackExpress.name!''}</#if></td>
	    		</tr>
	    		<tr>
	    			<th>物流编号:</th>
	    			<td>${ship.trackingNo!''}</td>
	    		</tr>
	    	</table>
	    </td>
	    <td valign="top">
	    	<table width="100%">
	    		<tr>
	    			<th>商品名称</th>
	    			<th>商品规格</th>
	    			<th>商品数量</th>
	    		</tr>
	    		<#if ship?? && ship.itemList??>
	    		<#list ship.itemList as item>
	    		<tr>
	    			<td>${item.itemOrderSku.productName!''}</td>
	    			<td>${item.itemOrderSku.displaySpecifications!''}</td>
	    			<td>${item.quantity!''}</td>
	    		</tr>
	    		</#list>
	    		</#if>
	    	</table>
	    </td>
	</tr>
	</#list>
<#else>
<tr>
		<td>暂无相关信息。</td>
</tr>
</#if>
</table>