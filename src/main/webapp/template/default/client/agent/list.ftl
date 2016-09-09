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
    <title>创业中心 - ${system.webkeywords!''}</title>
    <!-- css -->
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/site.css" />
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/chuangkeftt.css" />
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/lhead.css" />
	<!-- js -->
	<#include "/common/common.ftl" />
	<script type="text/javascript" src="${app.basePath}/static/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/html5.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/library.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/index.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>
	<script type="text/javascript">
        $(function(){
            jQuery("#slideBox").slide({
                titCell: ".hd ul",
                mainCell: ".bd ul",
                autoPage: true,
                effect: "leftLoop",
                autoPlay: true,
                delayTime: 700,
                vis: 4,
                scroll: 1
            });
        });

    </script>
</head>
<body>
<h1 style="display:none;">创客</h1>
	<!-- 头部 -->
	<#include "../common/commonheader.ftl">
	<!-- 头部 -->
	<!-- 中间 -->
	<div class="agentbanner">
		<div class="wrapper">
	        <div id="slideBox" class="bannersilde">
	            <div class="bd">
	                <ul>
	                	<#if agentList??>
    					<#list agentList as ag>
	                    <li>
	                        <a href="${app.basePath}/agent/producttype?agentId=${ag.id?c}" title="${ag.title!''}" class="title"><img src="${app.basePath}${ag.imageUrl!''}" alt="${ag.title!''}"/></a>
	                        <div class="infoitem">
	                            <a href="${app.basePath}/agent/producttype?agentId=${ag.id?c}" title="${ag.title!''}" class="title">${ag.title!''}</a>
	                            <p class="desc">${ag.note!''}</p>
	                            <span class="price red">￥<label><#if ag.salesPrice??>${ag.salesPrice?string('0.00')}</#if></label></span>
	                            <span class="continue"></span>
	                        </div>
	                    </li>
	                    </#list>
        				</#if>
	                </ul>
	            </div>
	            <a class="prev" href="javascript:void(0)"></a>
	            <a class="next" href="javascript:void(0)"></a>
	        </div>
	    </div>
	</div>
	<!-- 中间-结束 -->
	<!-- 底部 -->
	<#include "../common/commonfooter.ftl">
	<!-- 底部 -->
</body>
</html>