<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="managerForm" action="${app.basePath}/admin/manager/save" class="easyui-form" method="post" data-options="novalidate:true">
<table>
	<#if manager.uid??>
	<tr>
        <td>账号：</td>
        <td>${manager.uname!''}</td>
    </tr>
	<#else>
    <tr>
        <td>账号：</td>
        <td><input type="text" name="uname" class="easyui-textbox" value="${manager.uname!''}" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]"></td>
    </tr>
    </#if>
    <tr>
        <td>姓名：</td>
        <td><input type="text" name="unick" class="easyui-textbox" value="${manager.unick!''}" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]"></td>
    </tr>
    <tr>
        <td>电话号：</td>
        <td>
            <input type="text" name="utel" class="easyui-textbox" value="${manager.utel!''}" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]">
        </td>
    </tr>
    <tr>
        <td>状态：</td>
        <td>
            <input type="radio" name="ustatus" value="1" <#if !manager.ustatus?? || (manager.ustatus?? && manager.ustatus==1)>checked</#if>>启用
            <input type="radio" name="ustatus" value="2" <#if manager.ustatus?? && manager.ustatus==2>checked</#if>>禁用
        </td>
    </tr>
    <tr>
        <td><input type="hidden" name="uid" value="${manager.uid!''}"></td>
        <td>&nbsp;&nbsp;</td>
    </tr>
    <tr>
        <td></td>
        <td>
            <button type="button" class="d-button" onclick="saveManager()">保存</button>
        </td>
    </tr>
</table>
</form>
</div>
<script>
$(function(){
});
</script>
