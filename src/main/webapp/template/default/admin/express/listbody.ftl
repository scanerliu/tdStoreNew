<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x">
	<a data-height="190" data-width="450" data-id="add" data-title="添加角色" href="javascript:;" class="add fb J_showdialog" onclick="editExpress(0)"><em>添加快递公司</em></a>&#12288;
	<input type="button" value="批量删除" onclick="batchDelete()">&nbsp;&nbsp;&nbsp;
</div></div>
<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
<th width="40"><input type="checkbox" class="J_checkall" id="J_checkall" name="checkall"></th>
<th>ID</th>
<th align="left">公司名称</th>
<th align="left">公司编码</th>
<th width="150">管理操作</th>
</tr>
</thead>
<tbody>
<#if expressList??>
<#list expressList as express>
    <tr>
        <td align="center">
        <input type="checkbox" value="${express.id}" name="subbox" class="J_checkitem"></td>
        <td align="center">${express.id}</td>
        <td>${express.name}</td>
        <td>${express.com}</td>
        <td align="center">
            <a class="J_showdialog" href="javascript:;" onclick="editExpress(${express.id})">编辑</a> | 
            <a class="J_confirmurl" href="javascript:;" onclick="delExpress(${express.id})">删除</a>
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
<#assign pageId="Expresses" />
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
