<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="passwordForm" action="${app.basePath}/admin/customer/savepassword" class="easyui-form" method="post" data-options="novalidate:true">
<div class="J_tablelist table_list">
<table class="table_form" width="100%">
	<tr>
        <th width="150">账号：</th>
        <td>${manager.uname!''}</td>
    </tr>
    <tr>
    	<th width="150">新密码：</th>
    	<td><input type="password" id="upassword" name="upassword" class="easyui-textbox" value="" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]"></td>
    <tr>
    <tr>
    	<th width="150">确认密码：</th>
    	<td><input type="password" class="easyui-textbox" name="repassword" id="repassword" value="" style="width:200px;height:30px" data-options="required:true" validType="equalTo['#upassword']" invalidMessage="两次输入密码不匹配"></td>
    <tr>
    <tr>
        <td><input type="hidden" name="uid" value="${manager.uid!''}"><input type="hidden" name="uname" value="${manager.uname!''}"></td>
        <td>
            <button type="button" class="smt mr10" onclick="savePassword()">保存</button>
        </td>
    </tr>
</table>
</form>
</div>
<script>
$(function(){
});
</script>

