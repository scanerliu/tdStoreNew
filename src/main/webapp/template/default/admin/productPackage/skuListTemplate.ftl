<#import "/common/app.ftl" as app>
<#if selectedSku??>
	<#list selectedSku as sku>
		<div style="float:left;margin-left:10px;" skuId="${sku.id?c}" onclick="removeSkuId(this)">
			<div>
				<img width="100px" height="100px" src="${app.basePath}${sku.product.imageUrl!''}">				
			</div>
			<div>
				商品名称：<label>${sku.product.name!''}</label><br>
				销售价：<label>${sku.product.price!''}</label><br>
			</div>
		</div>
	</#list> 
</#if>