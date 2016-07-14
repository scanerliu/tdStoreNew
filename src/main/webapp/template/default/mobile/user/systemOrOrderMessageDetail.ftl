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
    <title>消息列表 - 系统消息内容</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/common.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/main.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/index.css">
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script> 
    <script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/touch/js/index.js"></script>
</head>

<script>
    window.onload=function(){
    }
</script>

<body class="">

<!-- header_top -->
<div class="top_heater">
    <a href="${app.basePath}/mobile/user/messageList?active=${active!''}" title="返回信息列表" class="hleft hback"></a>
    <span>信息通知</span>
</div>
<!-- header_top end -->

<!-- Center Start -->
<section class="container">
    <div class="three2" id="three2">
        <a href="${app.basePath}/mobile/user/messageList?active=activeOrderMessage" title="订单信息" <#if active?? && active=="activeOrderMessage">class="active"</#if>>订单信息</a>
        <a href="${app.basePath}/mobile/user/messageList?active=activeSystemMessage" title="系统信息" <#if active?? && active=="activeSystemMessage">class="active"</#if>>系统信息</a>
        <a href="${app.basePath}/mobile/user/messageList?active=activeExperienceStoreMessage" title="门店申请" <#if active?? && active=="activeExperienceStoreMessage">class="active"</#if>>门店申请 </a>
    </div>
    <div class="infor_detail">
        <section class="title">
            <span>${message.createTime?string('yyyy-MM-dd HH:mm:ss')}</span>
        </section>
        <section class="sec2">${message.content!''}</section>
    </div>
</section>
<!-- Center End -->

</body>
</html>