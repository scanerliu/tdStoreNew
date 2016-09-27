<#import "/common/app.ftl" as app>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="keywords" content="${system.webkeywords!''}">
    <meta name="description" content="${system.webdescription!''}">
    <meta name="copyright" content="${system.webcopyright!''}" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <title>成为代理 - ${system.webkeywords!''}</title>
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
    <script src="${app.basePath}/static/js/client/compaign/compaign.js" type="text/javascript"></script>
    <style>
    	.title select{height: 28px; line-height: 28px; color: #666; border: 1px solid #ddd;}
    </style>
</head>
<body>
<h1 style="display:none;">创客</h1>
	<!-- 头部 -->
	<#include "../common/commonheader.ftl">
	<!-- 头部 -->
	<!-- 中间 -->
	<div class="content w1200 local_rank">
		<form id="searchform">
			<input type="hidden" id="regionId" name="regionId" value="">
		</form>
		<section class="title">
			<span id="provincespn"></span><span id="cityspn"></span><span id="regionspn"></span>
            <script>
				$(function(){
					getDistricts({'obj':null,'num':0,'total':3});
			    });
			</script>
		</section>
		<form id="listform">
		<ul class="local_box tabb">
			<li class="active">
				<div id="newsList">
				</div>
				<div id="results"></div>
			</li>
		</ul>
		</form>
	</div>
	<!-- 中间-结束 -->
	<!-- 隐藏部分 -->
	<div class="opa_box local_hide_box">
		<div class="opa_content local_hide">
			<i class="close"></i>
			<section class="fl sec1">
				<img src="" alt="" id="opa_avatar"/>
				<p>
					<label for="" class="fl" id="opa_uanme"></label>
				</p>
				<p>
					<label for="" class="fl">排名：</label>
					<label class="fr" id="opa_level"></label>
				</p>
			</section>
			<section class="sec2 fr" id="opa_resume"></section>
		</div>
	</div>
	<!-- 底部 -->
	<#include "../common/commonfooter.ftl">
	<!-- 底部 -->
</body>
</html>