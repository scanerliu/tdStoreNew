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
    <title>消息列表</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/common.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/main.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/index.css">
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script> 
    <script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/touch/js/index.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/touch/js/ftt.js"></script>
    <script src="${app.basePath}/static/js/mobile/core.js" type="text/javascript"></script>
    <script src="${app.basePath}/static/js/mobile/user/messagelist.js" type="text/javascript"></script>
</head>
<body class="">

    <!-- header_top -->
    <div class="top_heater">
        <a href="${app.basePath}/mobile/user/center" title="返回个人中心" class="hleft hback"></a>
        <span>信息通知</span>
    </div>
    <!-- header_top end -->

    <!-- Center Start -->
    <section class="container">
    	<form id="searchform">
			<input type="hidden" id="sc_fliterType" name="msgType" value="2"/>
		</form>
        <div class="three2" id="three2">
            <a href="javascript:;" title="订单信息" class="active" tid="2">订单信息</a>
            <a href="javascript:;" title="系统信息" tid="1">系统信息</a>
            <a href="javascript:;" title="门店申请" tid="3">门店申请 </a>
        </div>
        <div id="three2_hao">
        	<form id="listform">
            <ul class="infor_match" id="messageList">
            </ul>
            <input type="hidden" name="msgType" id="fliterType" value=""/>
        	<input type="hidden" name="pageNo" id="pageNo" value=""/>
            </form>
        </div>
    </section>
    <!-- Center End -->
    <script>
        $(function () {
            function tab(tabTitle,tabList){
	          $(tabTitle).on('click','a',function(i,o){
	            var $self = $(this);//当前a标签
	            var index = $self.attr("tid");//当前索引
	            $("#sc_fliterType").val(index);
	            searchMessages(true);
	          	$self.addClass('active').siblings().removeClass('active');
	          });
	        };
	        tab('#three2','');
	        searchMessages(true);
        });
    </script>
</body>
</html>