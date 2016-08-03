<#import "/common/app.ftl" as app> 
<#include "/common/common.ftl" />
<!doctype html>
<html>
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
<title>我的钱包</title>
<!-- css -->
<link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/common.css"/>
<link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/main.css"/>
<link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/x_pc.css"/>
<!-- js -->
<script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
<script type="text/javascript" src="${app.basePath}/static/js/mobile/core.js"></script>
<script src="${app.basePath}/static/js/mobile/user/account.js" type="text/javascript"></script>
</head>
<body class="body_gray">

  <!-- header_top -->
  <div class="top_heater">
    <a href="${app.basePath}/mobile/user/center" title="" class="hleft hback"></a>
    <span>我的流水</span>
  </div>
  <!-- header_top end -->
<form id="searchform">
	<input type="hidden" id="sc_fliterType" name="filterType" value=""/>
</form>
  <!-- Center Start -->
   <section class="container my-running-water">
    <!-- top-nav -->
    <ul class="top-nav">
      <li class="active"><a href="javascript:;" title="">全部流水</a></li>
      <li><a href="javascript:;" title="">三天以内</a></li>
      <li><a href="javascript:;" title="">一周以内</a></li>
    </ul>

    <ol>
      <!-- 全部流水 -->
      <form id="listform">
        <li id="results"></li>
        <input type="hidden" name="filterType" id="filterType" value=""/>
        <input type="hidden" name="pageNo" id="pageNo" value=""/>        
      </form>
    </ol>
    <script type="text/javascript">
      $(function(){
        function tab(tabTitle,tabList){
          $(tabTitle).on('click','a',function(){
            var $self = $(this);//当前a标签
            var $active = $self.closest('li');//当前点击li
            var index = $active.prevAll('li').length;//当前索引
            $("#sc_fliterType").val(index);
            searchProfits(true);
			
          	$active.addClass('active').siblings('li').removeClass('active');
            
          });
        };
        tab('.my-running-water ul','');
        searchProfits(true);
      })
    </script>
  </section>
  <!-- Center End -->
</body>
</html>