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
<div class="index-main">
    <div class="personal-center">
        <!-- 左侧导航 -->
        <ul class="l_left fl">
            <li id="leftmenu">
                <p class="p1">消息中心</p>
                <p class="active"><a href="javascript:;" title="" aid="2">消息详情</a></p>
            </li>
        </ul>
        <!-- 右侧内容 -->
        <div class="need_value l_right bg_white fr">
            <!-- 明细 -->
            <div class="box1">
                <div class="div3 fl">消息详情</div>
            </div>
            <div class="myinfodetail">
            	<span class="top">${message.createTime?string('yyyy-MM-dd HH:mm:ss')}</span>
                <article>${message.content!''}</article>
             </div>
            <!-- 明细-结束 -->
        </div>
        <!-- 右侧内容 END -->
    </div>
    <div class="clear"></div>
</div>
<!-- Center End -->
<script>
	$(function(){
	});
</script>
<!-- Footer Start -->
<#include "../common/commonfooter.ftl">
<!-- Footer End -->
</body>
</html>