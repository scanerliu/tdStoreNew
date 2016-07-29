<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x">
</div></div>

<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
	<th align="center">商品名称</th>
	<th align="center">销量</th>
	<th align="center">销售额</th>
	<th align="center">成本</th>
	<th align="center">利润</th>
</tr>
</thead>
<tbody>
<#if saleProductReportList??>
<#list saleProductReportList as saleProduct>
    <tr>
        <td align="center">${saleProduct.pname!''}</td>
        <td align="center"><#if saleProduct.totalNum??>${saleProduct.totalNum?c}</#if></td>
        <td align="center"><#if saleProduct.totalPrice??>${saleProduct.totalPrice?c}</#if></td>
        <td align="center"><#if saleProduct.totalSupplierPrice??>${saleProduct.totalSupplierPrice?c}</#if></td>
        <td align="center"><#if saleProduct.totalProfit??>${saleProduct.totalProfit?c}</#if></td>
    </tr>
</#list>
</#if>
</tbody>
</table>
</div>
<div class="btn_wrap_fixed">
</div>
</div>
<div class="btn_wrap_fixed">
<#-- 搜索条件 begin-->
<input type="hidden" name="beginTime" value="<#if sc.beginTime??>${sc.beginTime?string('yyyy-MM-dd')}</#if>">
<input type="hidden" name="endTime" value="<#if sc.endTime??>${sc.endTime?string('yyyy-MM-dd')}</#if>">
<input type="hidden" name="productName" value="${sc.productName!''}">
<input type="hidden" name="isAsc" value="<#if sc.isAsc??>${sc.isAsc?c}</#if>">
<#-- 搜索条件 end-->
<#assign pageId="Sale" />
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
