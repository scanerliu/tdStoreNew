<#import "/common/app.ftl" as app>
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
    <title>成为代理-选择付款方式</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/common.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/main.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/index.css">
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script> 
    <script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>
</head>
<body class="">

<!-- header_top -->
<div class="top_heater">
    <a href="javascript:history.go(-1);" title="返回" class="hleft hback"></a>
    <span>支付方式</span>
</div>
<!-- header_top end -->

<!-- Center Start -->
<!-- <section class="container"> -->
    <div class="payway">
        <a href="${app.basePath}/order/gopay${order.orderId?c}?paymentId=1" title="支付宝付款">支付宝付款</a>
        <!--<a href="${app.basePath}/order/gopay${order.orderId?c}?paymentId=2" title="微信支付">微信支付</a>-->
        <!--<a href="${app.basePath}/order/gopay${order.orderId?c}?paymentId=3" title="银行卡支付">银行卡支付</a>-->
    </div>
<!-- </section> -->
<!-- Center End -->
</body>
</html>