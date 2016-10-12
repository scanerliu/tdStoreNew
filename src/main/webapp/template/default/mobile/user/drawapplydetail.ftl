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
<title>大额提现申请详情</title>
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
    <span>大额提现申请详情</span>
  </div>
  <!-- header_top end -->
<section class="container now-withdraw">
		<ol>
			<!-- 银行卡提现 -->
			<li style="display:block;">
				<div class="inpt">
					<label>提现金额</label>
                    <input type="text" value="${drawapply.amount!''}" disabled="disabled">
				</div>
				<div class="inpt">
					<label>银行</label>
					<input type="text" value="${drawapply.bankname!''}" disabled="disabled">
				</div>
				<div class="inpt">
					<label>银行卡号</label>
					<input type="text" value="${drawapply.cardno!''}" disabled="disabled">
				</div>
				<div class="inpt">
					<label>身份证号</label>
					<input type="text" value="${drawapply.idno!''}" disabled="disabled">
				</div>
				<div class="inpt">
					<label>开户姓名</label>
					<input type="text" value="${drawapply.carduser!''}" disabled="disabled">
				</div>
				<div class="inpt">
					<label>手续费</label>
					<input type="text" value="${drawapply.fee!'0'}" disabled="disabled">
				</div>
				<div class="inpt">
					<label>状态</label>
					<input type="text" value="${drawapply.statusStr!''}" disabled="disabled">
				</div>
				<div class="inpt">
					<label>说明</label>
				</div>
				<span class="fl">${drawapply.remark!''}</span>
			</li>
		</ol>
	</section>
</body>
</html>