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
    <title>提现申请 -  ${system.webkeywords!''}</title>
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
	<script type="text/javascript" src="${app.basePath}/static/js/client/user/drawapplylist.js"></script>
	<!--通用js-->
    <script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>
</head>
<body>
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
        <#include "../common/centerleftmenu.ftl">
         <!-- 右侧内容 -->
        <div class="right-content get_reward">
        	<!-- 我的钱包 -->
            <div class="my_wallet white_box">
                <div class="otitle">
                    <label for="" class="lab1 fl">提现申请记录</label>
                </div>
                <div class="clear"></div>
            </div>
            <!-- 我的钱包-结束 -->
            <!-- 明细 -->
            <div class="detail white_box">
            	<form id="searchForm" >
            		<input type="hidden" name="status" id="sc_incomeType" value=""/>
                </form>
                <div class="otitle tabh" id="selecttab">
                    <a href="javascript:;" aid="0" title="" class="active">所有记录</a>
                    <a href="javascript:;" aid="1" title="" class="">新申请</a>
                    <a href="javascript:;" aid="2" title="" class="">已同意</a>
                    <a href="javascript:;" aid="3" title="" class="">已拒绝</a>
                    <a href="javascript:;" aid="4" title="" class="">已完成</a>
                </div>
                <div class="tabb obody">
                	<form id="listForm" >
                    <ul class="active" id="results">
                    </ul>
                    </form>
                </div>
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
		selectTab("selecttab");
		searchDrawApplys(true);
	});
</script>
<!-- Footer Start -->
<#include "../common/commonfooter.ftl">
<!-- Footer End -->
</body>
</html>