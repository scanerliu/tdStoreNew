<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="expressForm" action="${app.basePath}/admin/express/save" class="easyui-form" method="post" data-options="novalidate:true">
<table>
    <tr>
        <td>公司名称：</td>
        <td><input type="text" name="name" class="easyui-textbox" value="${express.name!''}" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]"></td>
    </tr>
    <tr>
        <td>公司编码：</td>
        <td>
            <input type="text" name="com" class="easyui-textbox" value="${express.com!''}" style="width:200px;height:30px" data-options="required:true" validType="length[1,20]">
        </td>
    </tr>
    <tr>
        <td>显示序号(只接受整数)：</td>
        <td>
            <input type="text" name="displayOrder" class="easyui-textbox" value="${express.displayOrder!''}" style="width:200px;height:30px" data-options="required:true" validType="digits">
        </td>
    </tr>
    <tr>
        <td><input type="hidden" name="id" value="${express.id!''}"></td>
        <td>&nbsp;&nbsp;</td>
    </tr>
    <tr>
        <td></td>
        <td>
            <button type="button" class="d-button" onclick="saveExpress()">保存</button>
        </td>
    </tr>
</table>
</form>
</div>
<script>
$(function(){
});
</script>
