<#import "/common/app.ftl" as app> <!DOCTYPE html><html lang="en"><head>    <meta charset="UTF-8">    <meta name="description" content="中国创客联盟"/>    <meta name="keywords" content="中国创客联盟"/>    <meta name="author" content="中国创客联盟"/>    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>    <title>我的订单- ${system.webkeywords!''}</title>    <!-- 网站图标 -->    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />    <!-- css -->    <link rel="stylesheet" href="${app.basePath}/static/default/client/style/site.css" type="text/css"/>    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/personal-center-common.css">    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/f_personal_center.css">    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/lhead.css">	<#include "/common/common.ftl" />    <!-- js -->    <script type="text/javascript" src="${app.basePath}/static/js/jquery-1.12.3.min.js"></script>    <script type="text/javascript" src="${app.basePath}/static/js/client/html5.js"></script>    <!--通用js-->    <script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>    <script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>    <script type="text/javascript" src="${app.basePath}/static/js/client/index.js"></script>    <script type="text/javascript" src="${app.basePath}/static/js/client/user/collectlist.js"></script></head><body>	<h1 style="display:none;"></h1>	<!-- Header -->	<#include "../../common/centerheader.ftl">	<!-- Header -->	<div class="clear"></div>	<!-- Center Start -->	<div class="index-main">	    <div class="personal-center">	        <!-- 左侧导航 -->        	<#include "../../common/centerleftmenu.ftl">        		        <!-- 右边 -->	        <div class="need_value l_right bg_white fr">	            <!-- 全部订单、待评价分类 -->	            <div class="box1">	            	<form id="searchform">	                <div class="div1 fl">我的收藏</div>	                </form>	            </div>	            <!-- 列表 -->	            <form id="listform">	            <div id="results"></div>	            </form>	        </div>	    </div>	    <div class="clear"></div>	</div>	<!-- Center End -->	<!-- Footer Start -->	<#include "../../common/commonfooter.ftl">	<!-- Footer End -->    <script type="text/javascript">      $(function(){        searchCollects(true);      })    </script></body></html>