<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
	<tr>
		<th align="center">商品名称</th>
		<th align="center">图片</th>
		<th align="center">销售价</th>
		<th align="center">库存</th>
		<th align="center">规格</th>
		<th align="center">状态</th>
	</tr>
</thead>
<tbody>
<#if skuList??>
	<#list skuList as sku>
	    <tr onclick="selectSku(this)">
	        <td align="center" data="productName">${sku.product.name!''}</td>
	        <td align="center" data="productImage"><img src="${app.basePath}${sku.product.imageUrl!''}" style="width:50px;height:50px;"></td>
	        <td align="center" data="price">${sku.product.price?c}</td>
	        <td align="center">${sku.stock?c}</td>
	        <td align="center" data="specStr">${sku.specStr!''}</td>
	        <td align="center">${sku.statusStr!''}</td>
	        <input type="hidden" value="${sku.id?c}">
	    </tr>
	</#list>
</#if>
</tbody>
</table>
</div>
</div>
<form id="pageForm">
<#assign pageId="ProductSku" />
<#include "/admin/common/common_postpage.ftl" />
<input type="hidden" id="productName" value="${sc.productName!''}" name="productName">
</form>
<script>
	function selectSku(theTr){
		var productName = $(theTr).find("td[data='productName']").text();
		var productImage = $(theTr).find("td[data='productImage'] img").attr("src");
		var price = $(theTr).find("td[data='price']").text();
		var specStr = $(theTr).find("td[data='specStr']").text();
		var theId = $(theTr).find("input").val();
		$("#selectedSkuDiv").append("<label onclick='$(this).remove()' specStr='"+specStr+"' price='"+price+"' productImage='"+productImage+"'  productName='"+productName+"' theId='"+ theId +"'>[" + productName + "]</label>");
	}
</script>

