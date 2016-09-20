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
	<script type="text/javascript" src="${app.basePath}/static/js/client/help/helplist.js"></script>
</head>
<body>
	<h1 style="display:none;">创客</h1>
	<!-- 头部 -->
	<#include "../common/helpheader.ftl">
	<!-- 头部 -->
	<!-- Center Start -->
	<div class="wrapper">
	    <div class="centerwarp">
	        <div class="leftnav1" id="hmenu">
	        	<#if catList??>
	        	<#list catList as cat>
	            <dl>
	                <dt style="border-color:#ff7979; color:#ff7979;">${cat.name}</dt>
	                <dd>
	                	<#if cat.articleList??>
	                	<#list cat.articleList as art>
	                    <a href="javascript:;" title="${art.title!''}" onclick="showitem(${art.aid!'0'})" aid="${art.aid!'0'}"><i>•</i>${art.title!''}</a>
	                    </#list>
	            		</#if>
	                </dd>
	            </dl>
	            </#list>
	            </#if>
	        </div>
	        <div class="content1" id="results">
	        </div>
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