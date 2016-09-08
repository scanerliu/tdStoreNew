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
    <title>我的消息 -  ${system.webkeywords!''}</title>
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
	<script type="text/javascript" src="${app.basePath}/static/js/client/user/account.js"></script>
	<!--通用js-->
    <script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>
    <script src="${app.basePath}/static/js/client/user/messagelist.js" type="text/javascript"></script>
</head>
<body class="">
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
        <ul class="l_left fl">
            <li id="leftmenu">
                <p class="p1">消息中心</p>
                <p class="active"><a href="javascript:;" title="" aid="2">订单消息</a></p>
                <p><a href="javascript:;" title="" aid="1">系统消息</a></p>
                <p><a href="javascript:;" title="" aid="3">门店消息</a></p>
            </li>
        </ul>
        <!-- 右侧内容 -->
        <div class="need_value l_right bg_white fr" id="listdiv">
            <!-- 明细 -->
            <div class="box1">
                <div class="div3 fl">系统消息 </div>
                <div class="div2 fl" id="selecttab">
                    <a href="javascript:;" aid="">全部</a>
                    <a href="javascript:;" aid="1">未读</a>
                    <a href="javascript:;" aid="2">已读</a>
                </div>
                <form id="searchform">
					<input type="hidden" id="sc_fliterType" name="msgType" value="2"/>
					<input type="hidden" id="sc_status" name="status" value=""/>
				</form>
            </div>
        	<form id="listform" >
            <div id="results"></div>
            </form>
            <!-- 明细-结束 -->
        </div>
        <div id="formdiv"></div>
        <!-- 右侧内容 END -->
    </div>
    <div class="clear"></div>
</div>
<!-- Center End -->
<script>
	$(function(){
		selectMenu("leftmenu");
		selectTab("selecttab");
		searchMessages(true);
	});
</script>
<!-- Footer Start -->
<#include "../common/commonfooter.ftl">
<!-- Footer End -->
</body>
</html>