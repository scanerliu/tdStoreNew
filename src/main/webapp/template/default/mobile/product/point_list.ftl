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
    <title>促销活动</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/common.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/main.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/swipe.css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/index.css">
    <!-- js -->
    <#include "/common/common.ftl" />
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/core.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/product/product.js"></script>
</head>
<style>
.two2_match div {
    display: block;
}
</style>
<body class="body_bg">

<!-- header_top -->
<div class="top_heater">
    <a href="javascript:history.go(-1);" title="" class="hleft hback"></a>
    <span>促销活动</span>
</div>
<!-- header_top end -->

<!-- Center Start -->
<section class="container">
    <div class="two2" id="two2">
        <a href="javascript:;" title="全积分兑换" class="active">全积分兑换</a>
        <a href="javascript:;" title="部分积分兑换" class="">部分积分兑换</a>
    </div>
    <form id="searchform">
		<input type="hidden" id="sc_kind" name="kind" value="7"/>
	</form>
    <!-- 热销推荐 -->
    <div class="two2_match" id="two2_match">
        <div class="hot">
        	<form id="listform">
            <section class="sec2" id="results">
            </section>
            <input type="hidden" name="kind" id="kind" value=""/>
	        <input type="hidden" name="pageNo" id="pageNo" value=""/>        
	        </form>
        </div>
    </div>
    <!-- 热销推荐-结束 -->
</section>
<!-- Center end -->
<script type="text/javascript">
  $(function(){
    function tab(tabTitle,tabList){
      $(tabTitle).on('click','a',function(){
        var $self = $(this);//当前a标签
        var index = $self.prevAll('a').length;//当前索引
        $("#sc_kind").val(index+7);
        searchPointProducts(true);
      	$self.addClass('active').siblings('a').removeClass('active');
      });
    };
    tab('#two2','');
    searchPointProducts(true);
  })
</script>
</body>
</html>