<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x">
	<a data-height="190" data-width="450" data-id="add" data-title="添加商品包" href="javascript:;" class="add fb J_showdialog" onclick="editProductPackage(0)"><em>添加商品包</em></a>&#12288;
	<input type="button" value="批量删除" onclick="batchDelete()">&nbsp;&nbsp;&nbsp;
</div></div>
<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
	<tr>
		<th width="40"><input type="checkbox" class="J_checkall" id="J_checkall" name="checkall"></th>
		<th width="4%">ID</th>
		<th align="center" width="20%">名称</th>
		<th align="center">编号</th>
		<th align="center">创建时间</th>
		<th align="center">销售价</th>
		<th align="center">库存</th>
		<th align="center">状态</th>
		<th align="center">上架状态</th>
		<th >操作</th>
	</tr>
</thead>
<tbody>
<#if productPakcageList??>
<#list productPakcageList as productPackage>
    <tr>
        <td align="center">
        <input type="checkbox" value="${productPackage.id}" name="subbox" class="J_checkitem"></td>
        <td align="center">${productPackage.id?c}</td>
        <td align="center">${productPackage.name!''}</td>
        <td align="center">${productPackage.code!''}</td>
        <td align="center"><#if productPackage.createTime??>${productPackage.createTime?string('yyyy-MM-dd')}</#if></td>
        <td align="center"><#if productPackage.price??>${productPackage.price?string('0.00')}</#if></td>
        <td align="center"><#if productPackage.quantum??>${productPackage.quantum?c}<#else>0</#if></td>
        <td align="center">${productPackage.getStatusStr()!''}</td>
		<td align="center">${productPackage.getOnshelfStr()!''}</td>
        <td align="center">
            <a class="J_showdialog" href="javascript:;" onclick="editProductPackage(${productPackage.id?c})">编辑</a> | 
            <a class="J_confirmurl" href="javascript:;" onclick="delProductPackage(${productPackage.id?c})">删除</a>
        </td>
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
<#assign pageId="ProductPackage" />
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
