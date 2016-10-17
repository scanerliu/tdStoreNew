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
    <title>体验店列表 - ${system.webkeywords!''}</title>
    <!-- css -->
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/base.css" />
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/index.css" />
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/lhead.css" />
	<!-- js -->
	<#include "/common/common.ftl" />
	<script type="text/javascript" src="${app.basePath}/static/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/html5.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/library.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/index.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>
    <script src="${app.basePath}/static/js/client/exper/exper.js" type="text/javascript"></script>
</head>
<body>
<h1 style="display:none;">创客</h1>
	<!-- 头部 -->
	<#include "../common/commonheader.ftl">
	<!-- 头部 -->
	<!-- 中间 -->
	<div class="content w1200 learn">
		<!-- 分类 -->
		<form id="searchform">
			<input type="hidden" name="regionId" id="upid" value="">
		</form>
		<div class="otitle">
			<section class="sec1 fl">
				<label for="" class="fl">请选择您要查询的地区：</label>
				<span id="provincespn"></span><span id="cityspn"></span><span id="regionspn"></span><span id="townspn"></span><span id="villagespn"></span>
			</section>
		</div>
		<!-- 列表 -->
		<form id="listform">
		<div class="obody">
			<div id="results"></div>
		</div>
		</form>
		<!-- 列表-结束 -->
	</div>
	<!-- 中间-结束 -->

	<!-- 底部 -->
	<#include "../common/commonfooter.ftl">
	<!-- 底部 -->
	<script type="text/javascript">
	$(function(){
	    getLongDistricts({'obj':null,'num':0,'total':4,'callback':'searchExper'});
	});
</script>
</body>
</html>