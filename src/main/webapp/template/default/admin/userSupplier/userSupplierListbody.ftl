<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x">
</div></div>
<div class="pad_lr_10">
<select style="width: 100px;" name="status">
    <option value="-1">请选择状态...</option>
    <option value="1" <#if sc.status?? && sc.status==1>selected="selected"</#if>>未审核</option>
    <option value="2" <#if sc.status?? && sc.status==2>selected="selected"</#if>>已通过</option>
    <option value="3" <#if sc.status?? && sc.status==3>selected="selected"</#if>>未通过</option>
</select>
<input type="text" name="searchName" value="${sc.searchName!''}"><input type="button" value="搜索" onclick="searchUserSupplier(false)">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
	<th>ID</th>
	<th align="center">用户</th>
	<th align="center">资质类型</th>
	<th align="center">状态</th>
	<th align="center">申请时间</th>
	<th align="center">回复时间</th>
	<th align="center">审核人</th>
	<th align="center">操作</th>
</tr>
</thead>
<tbody>
<#if userSupplierList??>
<#list userSupplierList as userSupplier>
    <tr>
        <td align="center">${userSupplier.id?c}</td>
        <td align="center">${userSupplier.user.uname!''}</td>
        <td align="center">${userSupplier.supplierTypeStr!''}</td>
        <td align="center">${userSupplier.statusStr!''}</td>
        <td align="center">${userSupplier.createTime?string('yyyy-MM-dd HH:mm:ss')!''}</td>
        <td align="center"><#if userSupplier.updateTime??>${userSupplier.updateTime?string('yyyy-MM-dd HH:mm:ss')!''}</#if></td>
        <td align="center"><#if userSupplier.updatePerson??>${userSupplier.updatePerson.uname!''}</#if></td>
        <td align="center">
            <a class="J_showdialog" href="javascript:;" onclick="editUserSupplier(${userSupplier.id?c})">详情</a>
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
<#assign pageId="UserSupplier" />
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
