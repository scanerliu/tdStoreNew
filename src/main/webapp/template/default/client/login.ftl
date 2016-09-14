<#import "/common/app.ftl" as app> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="keywords" content="${system.webkeywords!''}">
    <meta name="description" content="${system.webdescription!''}">
    <meta name="copyright" content="${system.webcopyright!''}" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <title>登陆- ${system.webkeywords!''}</title>
    <#include "/common/common.ftl" />
    <link rel="stylesheet" href="${app.basePath}/static/default/client/style/login.css" />
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>
    <script src="${app.basePath}/static/js/client/login.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/Validform_v5.3.2_min.js"></script>
</head>
<body class="body_bg1">
<!-- 顶部 -->
<div class="login-top">
    <h1></h1>
    <a href="index.html" title="">
        <!-- LOGO尺寸 559*71 -->
        <img src="${app.basePath}/static/default/client/images/zj_logo_login.png" alt=""/>
    </a>
</div>
<!-- 顶部 END -->

<!-- 登录 -->
<div class="login-banner">
    <div class="login">
        <img src="${app.basePath}/static/default/client/images/zj_banner_login.jpg" alt=""/>
        <div class="login-form">
            <form id="loginForm" action="${app.basePath}/login" method="post" autocomplete="false">
                <div class="form_div1">
                    <p class="p1">会员登录</p>
                </div>
                <input type="text" class="ipt1" name="username" id="username" value="" placeholder="ID号或手机号"/>
                <input type="password" class="ipt2" name="password" id="password" value="" placeholder="密码"/>

                <div class="form_div2">
                    <p><label><input type="checkbox" name="rememberMe" value="true">&nbsp;自动登录</label></p>
                    <a href="javascript:void(0);" id="a_click" title="">忘记密码？</a>
                </div>
                <input type="button" value="登录" class="form_btn" onclick="login()"/>
                <div class="login_way">
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Center End -->
<!-- Footer Start -->
<footer class="login_footer">
<div class="load-container" id="loaddiv" style="display:none;">
	<div class="loader" style="z-index: 1000;position:fixed;left:0;top:0;width:100%;height:100%;background:url('${app.basePath}/static/default/client/images/opa1.png');text-align:center;"><img style="position:relative;top:50%;margin-top:-18px;" src="${app.basePath}/static/default/client/images/loading.gif"></div>
</div>
<#include "./common/foot.ftl">
</footer>
<!-- Footer End -->
<!--提交成功弹窗-->
<div class="dialog" id="dialog">
    <div class="pop-one1 pop-one">
        <!--忘记密码-验证手机-->
        <div class="forgetpw" id="item1" style="display: block;">
            <div class="forget_head">
                <p class="p1">验证手机<span>&gt</span></p>
                <p>重置密码<span>&gt</span></p>
                <p>重置成功</p>
            </div>
            <div style="width: 600px; height: 14px; background: url('${app.basePath}/static/default/client/images/zj_pw1.png') no-repeat; position: relative; top: -8px;"></div>
            <div class="pw_phone">
            	<form id="phoneform" method="post" action="${app.basePath}/forgetpassword2">
                <input type="text" name="utel" id="utel" class="ipt1" value="" placeholder="请输入您的手机号" datatype="m" nullmsg="请填写手机号！" errormsg="手机号码错误！"/>
                <div class="clear"></div>
                <input type="text" name="valideCode" class="ipt2" value="" placeholder="验证码" datatype="n6-6" nullmsg="请填写验证码！" errormsg="验证码错误！"/><a href="javascript:;" id="getChangePhoneNumValidCode" class="ipt_yzm ipt_yzmac" title="">获取验证码</a>
                <div class="clear"></div>
                <input type="submit" value="下一步" class="form_btn"/>
                <div class="tips_bottom">
                    <p style="font-size: 12px; font-weight: bold; color: #777; margin-top: 20px; line-height: 24px;">
                        	手机收不到验证码怎么办？</p>
                    <p style="font-size: 12px;color: #777; line-height: 24px;">1.检查垃圾短信，在设置中，将发件人设置为白名单。<br/>
                        2.若手机已停用，请拨打我们的服务热线:023-1234566。</p>
                </div>
                </form>
            </div>
        </div>
        <!--忘记密码-重置密码-->
        <div class="forgetpw" id="item2">
            <div class="forget_head">
                <p class="p1">验证手机<span>&gt</span></p>
                <p>重置密码<span>&gt</span></p>
                <p>重置成功</p>
            </div>
            <div style="width: 600px; height: 14px; background: url('${app.basePath}/static/default/client/images/zj_pw2.png') no-repeat; position: relative; top: -8px;"></div>
            <form id="forgetpassword2" method="post" action="${app.basePath}/repassword">
            <div class="pw_phone">
                <input type="password" class="ipt3" placeholder="请输入新密码" name="upassword" id="upassword" datatype="*6-16" nullmsg="请设置密码！" errormsg="密码范围在6~16位之间！"/>
                <div class="clear"></div>
                <input type="password" class="ipt3"  placeholder="再次输入密码" name="repassword" id="repassword" datatype="*" recheck="upassword" nullmsg="请再输入一次密码！" errormsg="您两次输入的账号密码不一致！"/>
                <div class="clear"></div>
                <input type="submit" value="下一步" class="form_btn"/>
                <div class="tips_bottom">
                    <p style="font-size: 12px; font-weight: bold; color: #777; margin-top: 20px; line-height: 24px;">友情提醒
                    </p>
                    <p style="font-size: 12px;color: #777; line-height: 24px;">密码设置完成之后要做好相应记录，以免忘记；
                        <br/> 不同的帐户尽量使用不同的密码，以免一个帐户被盗造成其他帐户同时被盗。</p>
                </div>
                <input type="hidden" id="uuid" name="uid" value="" datatype="n1-11" nullmsg="用户不存在！" errormsg="用户不存在！"/>
        		<input type="hidden" id="usign" name="sign" value="" datatype="*10-64" nullmsg="签名错误！" errormsg="签名错误！"/>
            </div>
            </form>
        </div>
        <!--忘记密码-重置成功-->
        <div class="forgetpw" id="item3">
            <div class="forget_head">
                <p class="p1">验证手机<span>&gt</span></p>
                <p>重置密码<span>&gt</span></p>
                <p>重置成功</p>
            </div>
            <div style="width: 600px; height: 14px; background: url('${app.basePath}/static/default/client/images/zj_pw3.png') no-repeat; position: relative; top: -8px;"></div>
            <div class="pw_phone">
                <div class="pw_succ">
                    <img src="${app.basePath}/static/default/client/images/zj_pw_succ.png" alt="" style="margin: 0 auto;"/>
                    <p style="font-size: 25px; color: #333; line-height: 48px; margin-top: 10px;">恭喜您重置密码成功！</p>
                    <a href="javascript:;" title="" id="gotologin">去登录</a>
                </div>
                <div class="tips_bottom">
                    <p style="font-size: 12px; font-weight: bold; color: #777; margin-top: 20px; line-height: 24px;">
                        友情提醒
                    </p>
                    <p style="font-size: 12px;color: #777; line-height: 24px;">密码设置完成之后要做好相应记录，以免忘记；
                        <br/>
                        不同的帐户尽量使用不同的密码，以免一个帐户被盗造成其他帐户同时被盗。</p>
                </div>
            </div>
        </div>
        <a href="javascript:void(0);" title="" class="pw_close"><img src="${app.basePath}/static/default/client/images/zj_pw_close.png" alt=""/></a>
    </div>
</div>
<!-- 底部-结束 -->
<script type="text/javascript">
        $(function(){
            $('#a_click').click(function (e) { //点击按钮的id
            	formreset("phoneform");
            	formreset("forgetpassword2");
            	showitem('#dialog .pop-one','#item1');
                $('#dialog').fadeIn();
                e.stopPropagation();  //阻止冒泡事件
            });
            $("#dialog .pw_close").click(function () {
                $('#dialog').fadeOut();
            });
            $("#dialog #gotologin").click(function () {
                $('#dialog').fadeOut();
            });
            $("#dialog .pop-one").click(function (e) {
                e.stopPropagation();
            });
            
            $("#getChangePhoneNumValidCode").on("click",function(){
            	getvalidcode(60);
            });
            
            $("#phoneform").Validform({
				tiptype:function(msg,o,cssctl){
				    if(o.type == 3){
				    	alert(msg);
				    }
				},
				beforeCheck:function(curform){
				},
				beforeSubmit:function(curform){
				 	openwaiting();
				},
				postonce:true, // 开启二次提交防御
				ajaxPost:true, 
				callback:function(data){
					closewaiting();
					if(data.code == 1){
						$("#uuid").val(data.token);
						$("#usign").val(data.sign);
						showitem('#dialog .pop-one','#item2');
					}else{
						alert(data.msg);
					}
				}
			});
            $("#forgetpassword2").Validform({
				tiptype:function(msg,o,cssctl){
				    if(o.type == 3){
				    	alert(msg);
				    }
				},
				beforeCheck:function(curform){
				},
				beforeSubmit:function(curform){
				 	openwaiting();
				},
				postonce:true, // 开启二次提交防御
				ajaxPost:true, 
				callback:function(data){
					closewaiting();
					if(data.code == 1){
						showitem('#dialog .pop-one','#item3');
					}else{
						alert(data.msg);
					}
				}
			});
        });
        function showitem(wrap,showitemid){
            $(wrap).children('div').hide();
            $(showitemid).stop().fadeIn();
        }
        
        
    </script>
</body>  
</html>