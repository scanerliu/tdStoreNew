<#import "/common/app.ftl" as app>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="keywords" content="创客联盟">
    <meta name="description" content="创客联盟">
    <meta name="copyright" content="创客联盟" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <title>提示信息</title>
    <#include "/common/common.ftl" />
    <!-- css -->
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/site.css" />
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/personal-center-common.css" />
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/f_personal_center.css" />
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/lhead.css" />
	<!-- js -->
	<#include "/common/common.ftl" />
	<script type="text/javascript" src="${app.basePath}/static/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/html5.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>
</head>
<body>
<h1 style="display:none;"></h1>
	<!-- Header Start -->
	<#include "./common/commonheader.ftl">
	<!-- 头部 -->
	<div class="clear"></div>
	<!-- Center Start -->
	<div class="index-main">
	    <div class="view-logistics submit-orders shopp-car shopping-program">
	        <div class="successinfo">
	            <span><#if errmsg??>
    <h3>${errmsg}</h3>
	</#if><label><a class="btn-share" href="${app.basePath}/index" title="">点击返回首页</a></label></span>
	        </div>
	    </div>
	    <div class="clear"></div>
	</div>
	<!-- Center End -->
	
	<#include "./common/commonfooter.ftl">
</body>  
</html>