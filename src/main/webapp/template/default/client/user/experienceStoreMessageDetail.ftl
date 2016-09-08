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
    <script src="${app.basePath}/static/js/client/user/user.js" type="text/javascript"></script>
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
            	<span class="top1">${experienceStore.regionFullName!''}门店</span>
                <article>
                	<#if experienceStore.storeImages??>
	            		<#list experienceStore.storeImages?split(":") as storeImg>
			                <a href="javascript:;" title=""><img src="${app.basePath}${storeImg!''}" alt="图片不存在"></a>            		
						</#list>
					</#if>
                </article>
                <ul class="info">
                    <li>
                        <label><font class="color_red">*</font>商品分类:</label>
                        <span>${experienceStore.storeTypeNames!''}</span>
                    </li><li>
                        <label><font class="color_red">*</font>联系方式:</label>
                        <span>${experienceStore.telphone!''}</span>
                    </li><li>
                        <label><font class="color_red">*</font>详细地址:</label>
                        <span>${experienceStore.regionFullName!''}  ${experienceStore.address!''}</span>
                    </li>
                </ul>
                <div class="btn">
                	<#if experienceStore.status?? && experienceStore.status == 2>
				        <div class="store_sec8">
				            <button title="同意申请" onclick="verifyApply('${experienceStore.id}', '1')" class="btn1">同意申请</button>
				            <button title="拒绝申请" onclick="verifyApply('${experienceStore.id}', '3')"class="btn2">拒绝申请</button>
				        </div>                
			        <#else>
			        	<div class="store_sec8">
			        		<label>状态：${experienceStore.statusStr!''}</label>
			        	</div> 
					</#if>
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
	});
</script>
<!-- Footer Start -->
<#include "../common/commonfooter.ftl">
<!-- Footer End -->
</body>
</html>