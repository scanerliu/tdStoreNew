<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="attributeForm" action="${app.basePath}/admin/productattribute/save" class="easyui-form" method="post" data-options="novalidate:true">
<input type="hidden" name="attriId" value="<#if attribute??>${attribute.attriId?c}</#if>">
<table class="table_form" width="100%">
    <tr>
        <th width="150">规格名称：</th>
        <td><input type="text" name="name" class="easyui-textbox" value="<#if attribute??>${attribute.name!''}</#if>"  style="width:200px;height:30px" data-options="required:true" validType="length[2,20]"></td>
    </tr>
    <tr>
        <th>状态：</th>
        <td>
            <input type="radio" name="status" value="1" <#if !attribute?? || (attribute.status?? && attribute.status==1)>checked</#if>>启用
            <input type="radio" name="status" value="2" <#if attribute?? && attribute.status?? && attribute.status==2>checked</#if>>禁用
        </td>
    </tr>
    <tr>
        <th>
        </th>
        <td>
            <button type="button" class="smt mr10" onclick="saveAttribute()">保存</button>
        </td>
    </tr>
</table>
</form>
</div>
<script>
$(function(){
});
</script>
