<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="customerForm" action="${app.basePath}/admin/customer/save" class="easyui-form" method="post" data-options="novalidate:true">
<table class="table_form" width="100%">
	<#if customer.uid??>
	<tr>
        <th width="150">账号：</th>
        <td>${customer.uname!''}</td>
    </tr>
	<#else>
    <tr>
        <th width="150">账号：</th>
        <td><input type="text" name="uname" class="easyui-textbox" value="${customer.uname!''}" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]"></td>
    </tr>
    <tr>
    	<th width="150">密码：</th>
    	<td><input type="password" id="upassword" name="upassword" class="easyui-textbox" value="${customer.upassword!''}" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]"></td>
    <tr>
    <tr>
    	<th width="150">确认密码：</th>
    	<td><input type="password" class="easyui-textbox" name="repassword" id="repassword" value="" style="width:200px;height:30px" data-options="required:true" validType="equalTo['#upassword']" invalidMessage="两次输入密码不匹配"></td>
    <tr>
    </#if>
    <tr>
        <th>昵称：</th>
        <td><input type="text" name="unick" class="easyui-textbox" value="${customer.unick!''}" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]"></td>
    </tr>
    <tr>
        <th>电话号：</th>
        <td>
            <input type="text" name="utel" class="easyui-textbox" value="${customer.utel!''}" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]">
        </td>
    </tr>
    <tr>
        <th>性别：</th>
        <td>
            <label><input type="radio" name="ugenter" class="J_change_zidong" value="1" <#if !customer.ugenter?? || (customer.ugenter?? && customer.ugenter==1)>checked</#if>>男&nbsp;&nbsp;</label>
            <label><input type="radio" name="ugenter" class="J_change_zidong" value="2" <#if customer.ugenter?? && customer.ugenter==2>checked</#if>>女</label>
        </td>
    </tr>
    <tr>
        <th>生日：</th>
        <td>
            <input type="text" name="ubirthday" class="easyui-datebox" value="<#if customer.ubirthday??>${customer.ubirthday?string("yyyy-MM-dd")}</#if>" style="width:200px;height:30px" data-options="required:true,sharedCalendar:'#cc'">
        </td>
    </tr>
    <tr>
        <th>状态：</th>
        <td>
            <label><input type="radio" name="ustatus" value="2" <#if !customer.ustatus?? || (customer.ustatus?? && customer.ustatus==2)>checked</#if>>锁定&nbsp;&nbsp;</label>
           <label><input type="radio" name="ustatus" value="1" <#if customer.ustatus?? && customer.ustatus==1>checked</#if>>启用</label>
        </td>
    </tr>
    <tr>
        <th>验证状态：</th>
        <td>
           <label><input type="radio" name="uverification" value="2" <#if !customer.uverification?? || (customer.uverification?? && customer.uverification==2)>checked</#if>>未验证&nbsp;&nbsp;</label>
           <label><input type="radio" name="uverification" value="1" <#if customer.uverification?? && customer.uverification==1>checked</#if>>已验证</label>
        </td>
    </tr>
    <#if customer.uid??>
    <#else>
	<tr>
        <th width="150">注册地区：</th>
        <td>
        	<span id="provincespn"></span><span id="cityspn"></span><span id="regionspn"></span>
        	<input type="hidden" id="uregionId" name="uregionId" value="${customer.uregionId!''}">
        </td>
    </tr>
	<tr>
        <th width="150">详细地址：</th>
        <td>
        	<input type="text" name="uaddress" class="easyui-textbox" value="${customer.uaddress!''}" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]">
        </td>
    </tr>
	</#if>
    <tr>
        <td><input type="hidden" name="uid" value="${customer.uid!''}"></td>
        <td>
            <button type="button" class="smt mr10" onclick="saveCustomer()">保存</button>
        </td>
    </tr>
</table>
</form>
</div>
<div id="cc" class="easyui-calendar"></div>
<script>
$(function(){
	getDistricts(null,0);
});
</script>
