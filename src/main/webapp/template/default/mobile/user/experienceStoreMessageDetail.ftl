<#import "/common/app.ftl" as app> 
<#include "/common/common.ftl" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="copyright" content="" />
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <title>消息列表-门店申请内容审核</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/common.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/main.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/index.css">
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script> 
    <script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/touch/js/index.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/user/user.js"></script>
</head>

<script>
    window.onload=function(){
    }
</script>

<body class="body_bg">

<!-- header_top -->
<div class="top_heater">
    <a href="${app.basePath}/mobile/user/messageList?active=${active!''}" title="返回门店申请列表" class="hleft hback"></a>
    <span>门店申请</span>
</div>
<!-- header_top end -->

<!-- Center Start -->
<section class="container">
    <div class="store_get">
        <section class="sec1">详细地址</section>
        <section class="sec3"><input type="text" "readonly" value="${experienceStore.regionFullName!''}"></section>
        <section class="sec4"><input type="text" "readonly" value="${experienceStore.address!''}"></section>
        <section class="sec5">手机号码:<span>${experienceStore.telphone!''}</span></section>
    <div class="detail6">
        <section class="title">门店照片</section>
        <div class="store_pic">
            <aside>
            	<#if experienceStore.storeImages??>
            		<#list experienceStore.storeImages?split(":") as storeImg>
		                <a href="#" title=""><img src="${storeImg!''}" alt="图片不存在"></a>            		
					</#list>
				</#if>
            </aside>
        </div>
    </div>
        <section class="sec7">
            <label for="" class="fl">商品分类</label>
            <label style="display:block;float:right;">${experienceStore.storeTypeNames!''}</label>
        </section>
        <#if experienceStore.status?? && experienceStore.status == 2>
	        <div class="store_sec8">
	            <a href="#" title="同意申请" onclick="verifyApply('${experienceStore.id}', '1')" class="fl">同意申请</a>
	            <a href="#" title="拒绝申请" onclick="verifyApply('${experienceStore.id}', '3')"class="fr">拒绝申请</a>
	        </div>                
        <#else>
        	<div class="store_sec8">
        		<label>状态：${experienceStore.statusStr!''}</label>
        	</div> 
		</#if>
    </div>
</section>
<!-- Center End -->

</body>
</html>