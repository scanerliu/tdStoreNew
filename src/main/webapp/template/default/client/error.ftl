<#import "/common/app.ftl" as app>
<body class="body_gray">
  <!-- header_top -->
  <div class="top_heater">
    <a href="${app.basePath}/mobile/shoppingcart/list" title="" class="hleft hback"></a>
    <span>操作失败</span>
  </div>
  <!-- header_top end -->

  <!-- Center Start -->
  <section class="container evaluate-success">
    <img class="icon-success" src="${app.basePath}/static/default/mobile/images/f-fail.png" alt="">
    <div class="div1">对不起！操作失败</div>
    <div class="div2">
    <#if errmsg??>
    <h3>${errmsg}</h3>
	</#if>
	</div>
    <a class="btn-share" href="${app.basePath}/index" title="">点击返回首页</a>
  </section>
  <!-- Center End -->
