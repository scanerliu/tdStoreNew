<#import "/common/app.ftl" as app> <#include "/common/common.ftl" /><!doctype html><html><head>	<meta charset="UTF-8">    <meta name="description" content="中国创客联盟"/>    <meta name="keywords" content="中国创客联盟"/>    <meta name="author" content="中国创客联盟"/>    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>    <title>收货地址 - ${system.webkeywords!''}</title>    <!-- 网站图标 -->    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />    <!-- css -->    <link rel="stylesheet" href="${app.basePath}/static/default/client/style/site.css" type="text/css"/>    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/personal-center-common.css">    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/f_personal_center.css">    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/lhead.css">    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/index.css">    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/has.css">	<#include "/common/common.ftl" />    <!-- js -->    <script type="text/javascript" src="${app.basePath}/static/js/jquery-1.12.3.min.js"></script>    <script type="text/javascript" src="${app.basePath}/static/js/client/html5.js"></script>    <script type="text/javascript" src="${app.basePath}/static/js/client/user/user.js"></script>    <script type="text/javascript" src="${app.basePath}/static/js/client/user/shoppingAddressAdd.js"></script>    <script type="text/javascript" src="${app.basePath}/static/js/client/user/jquery.cityselect.js"></script>    <!--通用js-->    <script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>    <script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>    <script type="text/javascript" src="${app.basePath}/static/js/client/index.js"></script>  </head><body>	<h1 style="display:none;"></h1>	<!-- Header -->	<#include "../common/centerheader.ftl">	<!-- Header -->	<div class="clear"></div>	<!-- header_top -->	<!-- Center Start -->	<div class="index-main">	    <div class="personal-center">	        <!-- 左侧导航 -->	        <#include "../common/centerleftmenu.ftl">           <!-- 右边-收货地址管理 -->		    <div class="change_password modify_personal address_manage">		       <div class="h2">收货地址管理   <a href="javascript:;" onclick="editAddress(0)">添加新地址</a></div>		       <div id="results"></div>		       <div id="formdiv"></div>		         		      </div>		      <!-- 右侧内容 END -->		    </div>	    <div class="clear"></div>	</div>	<!-- Center End -->	<!-- Footer Start -->	<#include "../common/commonfooter.ftl">	<!-- Footer End -->	<script>		$(function(){			searchAddress(true);		});	</script></body></html>