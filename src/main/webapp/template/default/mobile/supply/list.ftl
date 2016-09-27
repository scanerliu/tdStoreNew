<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
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
    <title>我的订单</title>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/common.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/main.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/x_pc.css"/>
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
    <script src="${app.basePath}/static/js/mobile/core.js" type="text/javascript"></script>
    <script src="${app.basePath}/static/js/mobile/supply/supply.js" type="text/javascript"></script>
</head>
<body class="body_gray">
  <!-- header_top -->
  <div class="top_heater">
    <a class="hleft hback" href="${app.basePath}/mobile/user/center" title="返回"></a>
    <span>发货列表</span>
  </div>
  <!-- header_top end -->
  <section class="container my-order">

	<form id="searchform">
		<input type="hidden" id="sc_fliterType" name="fliterType" value="0"/>
	</form>
    <!-- top-nav -->
    <ul class="tab_title">
      <li class="active"><a href="javascript:;" title="">全部订单</a></li>
      <li><a href="javascript:;" title="">待退货</a></li>
      <li><a href="javascript:;" title="">待发货</a></li>
    </ul>

	<form id="listform">
	    <ol>
	    	<li id="results"></li>
	    </ol>
	    <input type="hidden" name="fliterType" id="fliterType" value=""/>
	    <input type="hidden" name="pageNo" id="pageNo" value=""/>        
    </form>
  <script type="text/javascript">
	$(function(){
		function tab(tabTitle,tabList){
         	$(tabTitle).on('click','a',function(){
	            var $self = $(this);//当前a标签
	            var $active = $self.closest('li');//当前点击li
	            var index = $active.prevAll('li').length;//当前索引
	            $("#sc_fliterType").val(index);
	            searchOrders(true);
	          	$active.addClass('active').siblings('li').removeClass('active');
          });
        };
        tab('.my-order ul','');
	    searchOrders(true);
	});
	
</script>
</body>
</html>