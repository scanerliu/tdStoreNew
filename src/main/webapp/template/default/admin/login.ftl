<#import "/common/app.ftl" as app> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>后台管理登陆</title>
    <#include "/common/common.ftl" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <link rel="stylesheet" href="${app.basePath}/static/default/admin/style/style.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/js/easyui/easyui.css"/>
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
    <script src="${app.basePath}/static/js/admin/login.js" type="text/javascript"></script>
</head>
<body>
<div class="login_wrap">
  <div class="login_box">
    <form id="loginForm" action="${app.basePath}/admin/login" class="easyui-form" method="post" data-options="novalidate:true">
      <div class="login_funbox">
      <table>
        <tr>
            <td>账号：</td>
            <td><input type="text" name="username" id="uname" class="login_ipt easyui-textbox" value="" placeholder="账号" style="width:200px;height:32px" data-options="required:true"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td>
                <input type="password" name="password" id="upassword" class="login_ipt easyui-textbox" value="" placeholder="密码" style="width:200px;height:32px" data-options="required:true">
            </td>
        </tr>
      </table>
     </div>
   <div class="login_funbox error">
   <#if loginForm?? && loginForm.errcode?? && loginForm.errcode==1>用户名或密码错误！</#if>
   </div>
   <div class="login_funbox">
   <button type="button" class="loginbtn" onclick="login()">登陆</button>
   </div>
  </form>
 </div>
  <div id="outside"></div>
  <div class="footer">
    <div class="footer_main">天度版权所有&nbsp;©&nbsp;2011-2016 </div>
  </div>
</div>
<script>
$(function(){
});
</script>
</body>  
</html>