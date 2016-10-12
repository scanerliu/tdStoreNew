<#import "/common/app.ftl" as app> 
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="keywords" content="${system.webkeywords!''}">
    <meta name="description" content="${system.webkeywords!''}">
    <meta name="copyright" content="${system.webkeywords!''}" />
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <title>大额提现 申请-  ${system.webkeywords!''}</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/default/client/style/site.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/personal-center-common.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/f_personal_center.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/lhead.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/index.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/has.css">
    <!-- js -->
    <#include "/common/common.ftl" />
    <script type="text/javascript" src="${app.basePath}/static/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/html5.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/library.js"></script>
	<!--通用js-->
    <script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/Validform_v5.3.2_min.js"></script>
	<script src="${app.basePath}/static/js/mobile/user/withdraw.js" type="text/javascript"></script>
</head>
<body>
<h1 style="display:none;"></h1>

<!-- header_top -->
<#include "../common/centerheader.ftl">
<!-- header_top end -->
<div class="clear"></div>
<!-- Center Start -->
<!-- Center Start -->
<div class="index-main">
    <div class="personal-center">
        <!-- 左侧导航 -->
        <#include "../common/centerleftmenu.ftl">
         <!-- 右侧内容 -->
        <div class="right-content get_reward">
        	<!-- 我的钱包 -->
            <div class="my_wallet white_box">
                <div class="otitle">
                    <label for="" class="lab1 fl">我的钱包</label>
                </div>
                <div class="clear"></div>
                <div class="sbox">
                	<input type="hidden" id="aamount" value="${account.amount!''}">
                    <section class="fl sec1">
                        <p class="p1">钱包余额(元)</p>
                        <p class="p2"><#if account??>${account.amount!''}</#if></p>
                    </section>
                    <section class="fr sec2">
                    	<a href="${app.basePath}/user/drawapplylist" title="" class="">提现申请记录</a>
                    </section>
                </div>
            </div>
            <!-- 我的钱包-结束 -->
            <!-- 银行卡 -->
            <div class="bank_card white_box">
            	<form id="withdrawForm" method="post" action="${app.basePath}/user/dodrawapply">
                <div class="otitle">
                    <label for="" class="lab1 fl">提取余额到银行卡</label>
                </div>
                <div class="clear"></div>
                <div class="sbox">
                    <section class="sec2">
                        <label for="" class="lab1 fl">提现金额</label>
                        <input type="text" class="fl" id="damount" name="amount" placeholder="输入提现金额，最低200元" datatype="/(^[1-9]\d{0,7}(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/i" nullmsg="请填写提现金额！" errormsg="提现金额格式错误！">
                        <label for="" class="fl lab2">元</label>
                        <span>（最低200元）</span>
                    </section>
                    <section class="sec2">
                        <label for="" class="lab1 fl">所在银行</label>
                        <select name="bankid">
                        	<option value="1">中国工商银行</option>
                        	<option value="2">中国建设银行</option>
                        	<option value="3">中国银行 </option>
                        	<option value="4">中国农业银行</option>
                        	<option value="5">交通银行</option>
                        	<option value="6">招商银行</option>
                        	<option value="7">中国邮政储蓄银行</option>
                        	<option value="8">中信银行</option>
                        	<option value="9">光大银行</option>
                        	<option value="10">民生银行</option>
                        	<option value="11">兴业银行</option>
                        	<option value="12">华夏银行</option>
                        	<option value="13">平安银行</option>
                        </select>
                    </section>
                    <section class="sec2">
                        <label for="" class="lab1 fl">银行卡号</label>
                        <input type="text" class="fl" id="cardno" name="cardno" placeholder="输入银行卡号" datatype="n8-30" nullmsg="请填写银行卡号！" errormsg="银行卡号格式错误！">
                    </section>
                    <section class="sec2">
                        <label for="" class="lab1 fl">银行卡开户身份证号</label>
                        <input type="text" class="fl" id="idno" name="idno" placeholder="输入银行卡开户身份证号" datatype="n8-30" nullmsg="请填写银行卡开户身份证号！" errormsg="银行卡开户身份证号格式错误！">
                    </section>
                    <section class="sec2">
                        <label for="" class="lab1 fl">银行卡开户姓名</label>
                        <input type="text" class="fl" id="carduser" name="carduser" placeholder="输入银行卡开户姓名" datatype="s2-30" nullmsg="请填写银行卡开户姓名！" errormsg="银行卡开户姓名格式错误！">
                    </section>
                    <section class="sec3">
                        <label for="" class="lab1 fl">手续费</label>
                        <span class="fl">${feetip!''}</span>
                    </section>
                    <div class="sub_div" style="padding:0 125px;">
		                <input type="hidden" name="type" value="1">
					    <button class="btn" type="submit">提交申请</button>
                    </div>
                </div>
                </form>
            </div>
            <!-- 银行卡-结束 -->
        </div>
        <!-- 右侧内容 END -->
    </div>
    <div class="clear"></div>
</div>
<!-- Center End -->
<script type="text/javascript">
	$(function(){
        $("#withdrawForm").Validform({
			tiptype:function(msg,o,cssctl){
			    //msg：提示信息;
			    //o:{obj:*,type:*,curform:*},
			    //obj指向的是当前验证的表单元素（或表单对象，验证全部验证通过，提交表单时o.obj为该表单对象），
			    //type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, 
			    //curform为当前form对象;
			    //cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
			    if(o.type == 3){
			    	alert(msg);
			    }
			},
			beforeCheck:function(curform){
			},
			beforeSubmit:function(curform){
				var aamount = Number($("#withdrawForm #aamount").val());
				var damount = Number($("#withdrawForm #damount").val());
				if(damount> aamount){
					alert("提现金额过大，账户余额不足！");
					return false;
				}
				if(damount<200){
					alert("提现金额过小，省点手续费吧！");
					return false;
				}
			 	openwaiting();
			},
			postonce:true, // 开启二次提交防御
			ajaxPost:true, 
			callback:function(data){
				closewaiting();
				alert(data.msg);
				if(data.code == 1){
					window.location.href="${app.basePath}/user/drawapplylist";
				}
			}
		});
      })
</script>
<!-- Footer Start -->
<#include "../common/commonfooter.ftl">
<!-- Footer End -->
</body>
</html>