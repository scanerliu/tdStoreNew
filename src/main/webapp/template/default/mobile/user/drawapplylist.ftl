<#import "/common/app.ftl" as app> 
<!DOCTYPE html>
<html lang="en">
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
    <title>提现申请记录-  ${system.webkeywords!''}</title>
    <!-- css -->
    <link href="${app.basePath}/static/touch/css/common.css" rel="stylesheet" type="text/css" />
<link href="${app.basePath}/static/touch/css/main.css" rel="stylesheet" type="text/css" />
<link href="${app.basePath}/static/touch/css/x_pc.css" rel="stylesheet" type="text/css" />
    <!-- js -->
    <#include "/common/common.ftl" />
	<script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/mobile/core.js"></script>
	<script src="${app.basePath}/static/js/mobile/user/account.js" type="text/javascript"></script>
</head>
<body class="body_gray">

  <!-- header_top -->
  <div class="top_heater">
    <a href="${app.basePath}/mobile/user/account" title="" class="hleft hback"></a>
    <span>提现申请记录</span>
  </div>
  <!-- header_top end -->

  <!-- Center Start -->
  <section class="container my-wallet">
    <article class="points-detail">
      <section>
        <div class="div1">时间</div>
        <div>金额</div>
        <div>状态</div>
      </section>
      <ul id="results">
      </ul>
    </article>
  </section>
  <!-- Center End -->
<form id="searchForm" autocomplete="false">
		<input type="hidden" name="pageNo" id="pageNo" value="1"/>
</form>
<script type="text/javascript">
	$(function(){
	    searchDrawApplys(true);
	});
	
</script>
</body>
</html>