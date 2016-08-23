<#import "/common/app.ftl" as app>
<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
    <meta name="description" content="中国创客联盟"/>
    <meta name="keywords" content="中国创客联盟"/>
    <meta name="author" content="中国创客联盟"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <title>我的退款- ${system.webkeywords!''}</title>
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
	<!-- Center Start -->
	<div class="index-main">
	    <div class="personal-center">
	        <!-- 左侧导航 -->
        	<#include "../common/centerleftmenu.ftl">
        	
	        <!-- 右边 -->
	        <div class="need_value l_right bg_white fr">
	            <!-- 全部订单、待评价分类 -->
	            <div class="box1">
	            	<form id="searchform">
						<input type="hidden" id="sc_fliterType" name="status" value=""/>
	                <div class="div1 fl">我的退款</div>
	                <div class="div2 fl" id="orderTab">
	                    <a href="javascript:;" title="" tid="" class="active">全部订单</a>
	                    <a href="javascript:;" title="" tid="1">退款申请中</a>
	                    <a href="javascript:;" title="" tid="2">同意退款</a>
	                    <a href="javascript:;" title="" tid="3">不同意退款</a>
	                    <a href="javascript:;" title="" tid="5">退款完成</a>
	                </div>
	                <div class="search_box_right fr">
	                </div>
	                </form>
	            </div>
	            <!-- 商品名称、数量分类 -->
	            <div class="box2">
	                <p class="p1 w340 fl">商品名称</p>
	                <p class="p2 w80 fl">单价</p>
	                <p class="p3 w80 fl">数量</p>
	                <p class="p5 w120 fl">退款金额（元）</p>
	                <p class="p6 w110 fl">申请状态 </p>
	                <p class="p7 w90 fl">操作</p>
	            </div>
	            <!-- 列表 -->
	            <form id="listform">
	            <div id="results"></div>
	            </form>
	        </div>
	    </div>
	    <div class="clear"></div>
	</div>
	<!-- Center End -->
	<!-- Footer Start -->
	<#include "../common/commonfooter.ftl">
	<!-- Footer End -->
    <script type="text/javascript">
      $(function(){
        function tab(tabTitle,tabList){
          $(tabTitle).on('click','a',function(){
            var $self = $(this);//当前a标签
            var index = $self.attr('tid');//当前索引
            $("#sc_fliterType").val(index);
            searchRefunds(true);
          	$self.addClass('active').siblings().removeClass('active');
          });
        };
        tab('#orderTab','');
        searchRefunds(true);
      })
    </script>
</body>
</html>