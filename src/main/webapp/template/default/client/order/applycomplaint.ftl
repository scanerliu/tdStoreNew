<#import "/common/app.ftl" as app>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="description" content="中国创客联盟"/>
    <meta name="keywords" content="中国创客联盟"/>
    <meta name="author" content="中国创客联盟"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <title>投诉- ${system.webkeywords!''}</title>
    <!-- 网站图标 -->
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/default/client/style/site.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/personal-center-common.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/f_personal_center.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/lhead.css">
	<#include "/common/common.ftl" />
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/html5.js"></script>
    <!--通用js-->
    <script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/index.js"></script>
    <script src="${app.basePath}/static/js/client/order/orderlist.js" type="text/javascript"></script>
</head>
<body>
	<h1 style="display:none;"></h1>
	<!-- Header -->
	<#include "../common/centerheader.ftl">
	<!-- Header -->
	<div class="clear"></div>
	<!-- header_top end -->
	<!-- Center Start -->
	<div class="index-main">
	    <div class="view-logistics submit-orders">
	    	<div class="pop bg_white progressitem">
                <div class="content contact_box opinion">
                    <form class="obody" id="complaintForm" method="post" action="${app.basePath}/order/complaintorder">
                        <input type="hidden" name="orderId" value="${order.orderId!'0'}"/>
						<section class="sec2">
                            <textarea name="complaint" id="complaint" cols="" rows="" placeholder="请在这里输入文字，少于100文字，平台工作人员会在3个工作日内进行回复。"></textarea>
                        </section>
                        <section class="sec3"><input type="button" onclick="applyComplaint()" value="提交"></section>
                    </form>
                </div>
            </div>
	    </div>
	</div>
	<!-- Center End -->
	<!-- Footer Start -->
	<#include "../common/commonfooter.ftl">
	<!-- Footer End -->
	<script>
		$(function(){
		});
	</script>
</body>
</html>