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
   
<script>
$(function(){
    
});
</script>
