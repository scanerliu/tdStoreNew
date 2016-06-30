<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<div class="J_tablelist table_list">
<div id="dg" style="width:800px;height:650px;">
    <div title="基本信息" style="padding:20px;">
    	<table class="tabcontent" width="100%" cellspacing="0">
        <tr>
            <td width="150" align="right">账号:</td>
            <td><img src="${customer.uavatar!''}" alt="头像地址"/> ${customer.uname}</td>
        </tr>
        <tr>
            <td width="150" align="right">等级:</td>
            <td>${customer.membershipId!''}</td>
        </tr>
        <tr>
            <td align="right">昵称:</td>
            <td>${customer.unick!''}</td>
        </tr>
        <tr>
            <td align="right">性别:</td>
            <td>${customer.getUgenterStr()!''}</td>
        </tr>
        <tr>
            <td align="right">手机号:</td>
            <td>${customer.utel!''}</td>
        </tr>
        <tr>
            <td align="right">生日:</td>
            <td><#if customer.ubirthday??>${customer.ubirthday?string("yyyy-MM-dd")}</#if></td>
        </tr>
        <tr>
            <td align="right">注册地址:</td>
            <td><#if customer.region??>${customer.region.getRegionFullName()!''}</#if> ${customer.uaddress!''}</td>
        </tr>
        <tr>
            <td align="right">验证状态:</td>
            <td>${customer.getUverificationStr()!''}</td>
        </tr>
        <tr>
            <td align="right">状态:</td>
            <td>${customer.getUstatusStr()!''}</td>
        </tr>
        <tr>
            <td align="right">最近登陆时间:</td>
            <td><#if customer.lastLoginTime??>${customer.lastLoginTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
        </tr>
        <tr>
            <td align="right">登陆ip:</td>
            <td>${customer.lastLoginIp!''}</td>
        </tr>
        </table>
    </div>
    <div title="钱包信息" style="overflow:auto;padding:20px;" href="/admin/customer/customerpoints?id=${customer.uid!''}">
    	
    </div>
    <div title="积分信息" style="overflow:auto;padding:20px;">
    	<table class="tabcontent"  width="100%" cellspacing="0">
        <tr>
            <td width="120" align="right">接口地址:</td>
            <td></td>
        </tr>
        <tr>
            <td align="right">开发者主账号:</td>
            <td></td>
        </tr>
        <tr>
            <td align="right">开发者主账号令牌:</td>
            <td></td>
        </tr>
        <tr>
            <td align="right">应用Id:</td>
            <td></td>
        </tr>
        </table>
    </div>
</div>
</div>
<div class="btn_wrap_fixed">
</div>
</div>
<div class="btn_wrap_fixed">
</div>
<script>
$(function(){
     $('#dg').tabs({
	    border:false,
	    fit:true
	});
});
</script>
