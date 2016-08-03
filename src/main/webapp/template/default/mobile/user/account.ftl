<#import "/common/app.ftl" as app> 
<#include "/common/common.ftl" />
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
<script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
<script type="text/javascript" src="${app.basePath}/static/js/mobile/core.js"></script>
<script src="${app.basePath}/static/js/mobile/user/account.js" type="text/javascript"></script>
</head>
<body class="body_gray">

  <!-- header_top -->
  <div class="top_heater">
    <a href="${app.basePath}/mobile/user/center" title="" class="hleft hback"></a>
    <span>我的钱包</span>
  </div>
  <!-- header_top end -->

  <!-- Center Start -->
  <section class="container my-wallet">
    <article class="top-balance">
      <section>
        <div class="div1">￥<span><#if account??>${account.amount!''}</#if></span></div>
        <div class="div2">钱包余额</div>
      </section>
      <a class="btn-withdraw" href="立即提现.html" title="">零钱提现</a>
    </article><!-- top-balance end -->
    <article class="points-detail">
      <section>
        <div class="div1">时间</div>
        <div>说明</div>
        <div>金额</div>
      </section>
      <ul id="results">
      </ul>
    </article>
  </section>
  <!-- Center End -->
<form id="searchForm" autocomplete="false">
		<input type="hidden" name="pageNo" id="pageNo" value="0"/>
</form>
<script type="text/javascript">
	$(function(){
	    searchAccountLogs(true);
	});
	
</script>
</body>
</html>