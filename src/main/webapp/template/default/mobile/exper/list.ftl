<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
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
    <title>附近体验点</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/common.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/main.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/index.css">
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>
    <script src="${app.basePath}/static/js/mobile/core.js" type="text/javascript"></script>
    <script src="${app.basePath}/static/js/mobile/exper/exper.js" type="text/javascript"></script>
    <style>
    .regiondiv select{
    	display: inline-block;
	    height: 0.8rem;
	    line-height: 0.8rem;
	    padding-right: 0.28rem;
	    background-size: 0.18rem 0.11rem;
	    margin-top:5px;
    }
    </style>
</head>

<body class="body_bg">
<div class="top_heater">
    <a href="javascript:history.go(-1);" title="" class="hleft hback"></a>
    <span>附近门店</span>
</div>
<form  id="form" class="regiondiv">
<span id="provincespn"></span><span id="cityspn"></span><span id="regionspn"></span><span id="townspn"></span><span id="villagespn"></span>
<script>
	$(function(){
		getLongDistricts({'obj':null,'num':0,'total':4,'callback':'searchExper'});
    });
</script>
<input type="hidden" value="0" name="regionId" id="upid">
</form>
<div id="results"></div>

<script type="text/javascript">
	$(function(){
	    searchExper(true);
	});
	
</script>
</body>
</html>