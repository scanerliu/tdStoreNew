<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="managerForm" action="${app.basePath}/admin/manager/save" class="easyui-form" method="post" data-options="novalidate:true">
<table class="table_form" width="100%">
	<#if manager.uid??>
	<tr>
        <th width="150">账号：</th>
        <td>${manager.uname!''}</td>
    </tr>
	<#else>
    <tr>
        <th width="150">账号：</th>
        <td><input type="text" name="uname" class="easyui-textbox" value="${manager.uname!''}" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]"></td>
    </tr>
    <tr>
    	<th width="150">密码</th>
    	<td><input type="password" id="upassword" name="upassword" class="easyui-textbox" value="${manager.upassword!''}" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]"></td>
    <tr>
    <tr>
    	<th width="150">确认密码</th>
    	<td><input type="password" class="easyui-textbox" name="repassword" id="repassword" value="" style="width:200px;height:30px" data-options="required:true" validType="equalTo['#upassword']" invalidMessage="两次输入密码不匹配"></td>
    <tr>
    </#if>
    <tr>
        <th>姓名：</th>
        <td><input type="text" name="unick" class="easyui-textbox" value="${manager.unick!''}" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]"></td>
    </tr>
    <tr>
        <th>电话号：</th>
        <td>
            <input type="text" name="utel" class="easyui-textbox" value="${manager.utel!''}" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]">
        </td>
    </tr>
    <tr>
        <th>性别：</th>
        <td>
            <label><input type="radio" name="ugenter" class="J_change_zidong" value="1" <#if !manager.ugenter?? || (manager.ugenter?? && manager.ugenter==1)>checked</#if>>男&nbsp;&nbsp;</label>
            <label><input type="radio" name="ugenter" class="J_change_zidong" value="2" <#if manager.ugenter?? && manager.ugenter==2>checked</#if>>女</label>
        </td>
    </tr>
    <tr>
        <th>状态：</th>
        <td>
            <label><input type="radio" name="ustatus" value="2" <#if !manager.ustatus?? || (manager.ustatus?? && manager.ustatus==2)>checked</#if>>锁定&nbsp;&nbsp;</label>
           <label><input type="radio" name="ustatus" value="1" <#if manager.ustatus?? && manager.ustatus==1>checked</#if>>启用</label>
        </td>
    </tr>
    <tr>
        <td><input type="hidden" name="uid" value="${manager.uid!''}"></td>
        <td>
            <button type="button" class="smt mr10" onclick="saveManager()">保存</button>
        </td>
    </tr>
</table>
</form>
</div>
<script>
$(function(){
});
</script>
