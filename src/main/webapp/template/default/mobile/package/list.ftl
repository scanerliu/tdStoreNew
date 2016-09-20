<#import "/common/app.ftl" as app>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="copyright" content="" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <title>礼品包列表</title>
    <!-- css -->
    <#include "/common/common.ftl" />
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/common.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/main.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/index.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/swipe.css"/>
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/core.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/swipe.js"></script>
    <script src="${app.basePath}/static/js/mobile/index.js" type="text/javascript"></script>
    <script src="${app.basePath}/static/js/mobile/package/package.js" type="text/javascript"></script>
</head>
<style>
.strenth aside select{
    position:relative;
    appearance:none;
    -webkit-appearance:none;
    -moz-appearance:none;
    -o-appearance:none;
    padding: 0 .3rem;
    width:100%;
    height:.8rem;
    font-size:.26rem;
    color:#fff;
    border:none;
    box-sizing:border-box;
    background:#333;
}
.strenth aside:after{
    content:'';
    position:absolute;
    top:.33rem;
    right:.3rem;
    z-index:2;
    width:.14rem;
    height:.14rem;
    border:1px solid #fff;
    border-left:none;
    border-top:none;
    transform:rotate(45deg);
}
</style>
<body class="body_bg">
	<div class="top_heater">
	    <a href="javascript:;" onclick="window.history.go(-1)" title="" class="hleft hback"></a>
	    <span>礼品包</span>
	</div>
    <!--<div class="strenth">
        <aside class="">
            <select name="" id="">
                <option value="">700元礼包</option>
            </select>
        </aside>
    </div>-->
   <!-- ****广告轮播**** -->
<#if adList?? && adList?size gt 0>
<div class="addWrap2">
    <div class="swipe" id="mySwipe">
        <div class="swipe-wrap">
        	<#list adList as ad>
				<div><a href="${ad.linkUrl!''}"><img class="img-responsive" src="${ad.imageUrl!''}"/></a></div>
        	</#list>
        </div>
    </div>
    <ul id="position">
    	  <#if adList?? && adList?size gt 0>
    	  <#list adList as ad>
          <li class="cur"></li>
          </#list>
          </#if>
    </ul>
</div> 
<script type="text/javascript">
    var bullets = document.getElementById('position').getElementsByTagName('li');
    var banner = Swipe(document.getElementById('mySwipe'), {
        auto: 3000,
        continuous: true,
        disableScroll:false,
        callback: function(pos) {
            var i = bullets.length;
            while (i--) {
              bullets[i].className = ' ';
            }
            bullets[pos].className = 'cur';
        }
    });
</script>
</div>
</#if>
<!-- ****广告轮播-结束**** --> 
    
	<form id="searchForm" autocomplete="false">
		<input type="hidden" name="pageNo" id="pageNo" value="0"/>
		<input type="hidden" name="acpe" value="<#if sc.acpe??>${sc.acpe!''}</#if>"/>
	</form>
	<div class="hot">
        <section class="sec2" id="results">
        </section>
    </div>
<script type="text/javascript">
	$(function(){
	    searchPackage(true);
	});
	
</script>

</body>
</html>