<#import "/common/app.ftl" as app>
<#if ppiList??>
	<#list ppiList as ppi>
		<div style="float:left;margin-left:10px;" skuId="" onclick="removeSkuId(this)">
			<div id="productImage">
				<img width="100px" height="100px" src="${ppi.productImage!''}">
			</div>
			<div>
				商品名称：<label id="productName">${ppi.productName!''}</label><br>
				销售价：<label id="price"><#if ppi.price??>${ppi.price?c}</#if></label><br>
				<input type="hidden" id="specifications" value="${ppi.specifications!'' }">
			</div>
		</div>
	</#list> 
</#if>