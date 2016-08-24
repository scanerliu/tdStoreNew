<#import "/common/app.ftl" as app>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="copyright" content="" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <title>我的积分</title>
    <!-- css -->
    <#include "/common/common.ftl" />
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/common.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/main.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/x_pc.css"/>
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/core.js"></script>
    <script src="${app.basePath}/static/js/mobile/index.js" type="text/javascript"></script>
    <script src="${app.basePath}/static/js/mobile/user/point/point.js" type="text/javascript"></script>
</head>
<body class="body_gray">
	<div class="top_heater">
	    <a href="javascript:;" onclick="window.history.go(-1)" title="" class="hleft hback"></a>
	    <span>我的积分</span>
	</div>
    <!-- Center Start -->
	<section class="container my-wallet">
	    <article class="top-balance">
	      <section>
	      	<img src='${app.basePath}/static/touch/x-img/bg_my_wallet_2.png' class='bg'/>
	        <div class="div1">${integral.integral!'0'}</div>
	        <div class="div2">当前积分</div>
	      </section>
	      <a class="btn-withdraw" href="${app.basePath}/mobile/product/point/list" title="">积分兑换</a>
	    </article>
	    <!-- top-balance end -->
	    <article class="points-detail">
	      <section>
	        <div class="div1">时间</div>
	        <div>说明</div>
	        <div>积分数量</div>
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
	    searchPointLogs(true);
	});
	
</script>
</body>
</html>