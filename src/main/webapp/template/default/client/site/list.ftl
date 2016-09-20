<#import "/common/app.ftl" as app>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="keywords" content="${system.webkeywords!''}">
    <meta name="description" content="${system.webdescription!''}">
    <meta name="copyright" content="${system.webcopyright!''}" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <title>商品列表 - ${system.webkeywords!''}</title>
    <!-- css -->
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/site.css" />
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/personal-center-common.css" />
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/lhead.css" />
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/helpcenter.css" />
	<!-- js -->
	<#include "/common/common.ftl" />
	<script type="text/javascript" src="${app.basePath}/static/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/html5.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/library.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/index.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/site/sitelist.js"></script>
</head>
<body>
	<h1 style="display:none;">创客</h1>
	<!-- 头部 -->
	<#include "../common/helpheader.ftl">
	<!-- 头部 -->
	<!-- Center Start -->
	<div class="wrapper">
	    <div class="centerwarp">
	        <div class="leftnav2">
	            <dl id="smenu">
	            	<#if artList??>
		        	<#list artList as art>
		            <dd><i>•</i><a href="javascript:;" onclick="showitem(${art.aid!'0'})" aid="${art.aid!'0'}">${art.title!''}</a></dd>
		            </#list>
		            </#if>
	            	<#if catList??>
		        	<#list catList as cat>
		            <dd><i>•</i><a href="javascript:;" onclick="showitems(${cat.cid!'0'})" cid="${cat.cid!'0'}">${cat.name!''}</a></dd>
		            </#list>
		            </#if>
	            </dl>
	        </div>
	        <form id="searchform">
	        	<input type="hidden" id="sc_cid" name="cid" value=""/>
	        </form>
	        <form id="listform">
	        <div class="content2" id="results" style="min-height:600px;">
	        	
	        </div>
	        </form>
	    </div>
	</div>
	<!-- Center End -->
		
	<!-- 底部 -->
	<#include "../common/commonfooter.ftl">
	<!-- 底部 -->
	<script>
		$(function(){
			<#if sc.aid??>
			showitem(${sc.aid!'0'});
			</#if>
		});
	</script>
</body>
</html>