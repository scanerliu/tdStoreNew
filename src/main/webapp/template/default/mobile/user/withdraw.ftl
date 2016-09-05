<#import "/common/app.ftl" as app> 
<!doctype html>
<html>
<head>
<meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <meta name="keywords" content="${system.webkeywords!''}">
  <meta name="description" content="${system.webdescription!''}">
  <meta name="copyright" content="${system.webcopyright!''}" />
  <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
<meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<title>我的钱包</title>
<!-- css -->
<link href="${app.basePath}/static/touch/css/common.css" rel="stylesheet" type="text/css" />
<link href="${app.basePath}/static/touch/css/main.css" rel="stylesheet" type="text/css" />
<link href="${app.basePath}/static/touch/css/x_pc.css" rel="stylesheet" type="text/css" />
<!-- js -->
<#include "/common/common.ftl" />
<script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
<script type="text/javascript" src="${app.basePath}/static/js/mobile/core.js"></script>
<script type="text/javascript" src="${app.basePath}/static/js/Validform_v5.3.2_min.js"></script>
<script src="${app.basePath}/static/js/mobile/user/withdraw.js" type="text/javascript"></script>
</head>
<body class="body_gray">

  <!-- header_top -->
  <div class="top_heater">
    <a href="${app.basePath}/mobile/user/account" title="" class="hleft hback"></a>
    <span>立即提现</span>
  </div>
  <!-- header_top end -->
<section class="container now-withdraw">
		<ul>
			<li class="active"><a href="javascript:;" title="">微信红包</a></li>
			<!--<li><a href="javascript:;" title="">银行卡提现</a></li>-->
		</ul>
		<ol>
			<!-- 微信红包 -->
			<li style="display:block;">
				<form id="withdrawForm" method="post" action="${app.basePath}/mobile/user/dowithdraw">
				<div class="inpt">
					<label>账户余额</label>
					<input type="text" id="aamount" value="${account.amount!''}" disabled="disabled">
				</div>
				<input class="withdraw-num" id="damount" name="amount" type="text" placeholder="输入提现金额，最低10元最高200元" datatype="/(^[1-9]\d{0,7}(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/i" nullmsg="请填写提现金额！" errormsg="提现金额格式错误！">
				<div class="inpt">
					<label>手续费</label>
					<input type="text" value="" disabled="disabled">
				</div>
				<div class="inpt">
					${feetip!''}
				</div>
				<input type="hidden" name="type" value="1">
				<button class="btn-now-withdraw" type="submit">立即提现</button>
				</form>
			</li>
			<!-- 银行卡提现 -->
			<li>
				<div class="inpt">
					<label>提现账户</label>
					<input type="text" value="凌山" disabled="disabled">
				</div>
				<div class="inpt">
					<label>账户余额</label>
					<input type="text" value="255.95" disabled="disabled">
				</div>
				<div class="inpt">
					<label>选择银行</label>
					<select>
						<option>请选择银行</option>
						<option>中国建设银行</option>
						<option>中国农业银行</option>
						<option>中国工商银行</option>
					</select>
				</div>
				<input class="withdraw-num" type="text" placeholder="输入提现金额，最低5元最高10万元">
				<div class="inpt">
					<label>手续费</label>
					<input type="text" value="10.00" disabled="disabled">
				</div>
			</li>
		</ol>
	</section>
	<!-- <div class="clear" style="width:100%;height:.8rem;"></div> -->
		<#-- 引入警告提示样式 -->
        <#include "/common/common_warn.ftl">
        <#-- 引入等待提示样式 -->
        <#include "/common/common_wait.ftl">
        <script type="text/javascript">
			$(function(){
		        function tab(tabTitle,tabList){
		          $(tabTitle).on('click','a',function(){
		            var $self = $(this);//当前a标签
		            var $active = $self.closest('li');//当前点击li
		            var index = $active.prevAll('li').length;//当前索引
		
		          $active.addClass('active').siblings('li').removeClass('active');
		            $(tabList).find('>li')[index==-1?'show':'hide']().eq(index).show();
		          });
		        };
		        tab('.now-withdraw ul','.now-withdraw ol');
		        
		        $("#withdrawForm").Validform({
					tiptype:function(msg,o,cssctl){
					    //msg：提示信息;
					    //o:{obj:*,type:*,curform:*},
					    //obj指向的是当前验证的表单元素（或表单对象，验证全部验证通过，提交表单时o.obj为该表单对象），
					    //type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, 
					    //curform为当前form对象;
					    //cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
					    if(o.type == 3){
					    	warning(msg);
					    }
					},
					beforeCheck:function(curform){
					},
					beforeSubmit:function(curform){
						var aamount = Number($("#withdrawForm #aamount").val());
						var damount = Number($("#withdrawForm #damount").val());
						if(damount> aamount){
							warning("提现金额过大，账户余额不足！");
							return false;
						}
						if(damount>200){
							warning("提现金额过大，微信系统不支持！");
							return false;
						}
						if(damount<10){
							warning("提现金额过小，省点手续费吧！");
							return false;
						}
					 	wait();
					},
					postonce:true, // 开启二次提交防御
					ajaxPost:true, 
					callback:function(data){
						close(1);
						warning(data.msg);
						if(data.code == 1){
							window.location.reload();
						}
					}
				});
		      })
		</script>
</body>
</html>