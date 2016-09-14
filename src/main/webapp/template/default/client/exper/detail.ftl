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
    <title>${experexce.regionFullName!''}体验店- ${system.webkeywords!''}</title>
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
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=L21DrKlfsLR03VWDWDk0OLzYZroKHV62"></script>
</head>
<body>
<h1 style="display:none;">创客</h1>
	<!-- 头部 -->
	<#include "../common/commonheader.ftl">
	<!-- 头部 -->
	<!-- 中间 -->
	<div class="content w1200 learn_detail">
		<section class="sec1">
			<aside class="as1 fl">
				<article class="art1">
					<p class="p1">${experexce.regionFullName!''}体验店</p>
				</article>
				<article class="art2">
					<p class="p1">店铺地址： ${experexce.regionFullName!''}${experexce.address!''}</p>
					<p class="p2">销售热线：${experexce.telphone!''}</p>
				</article>
				<article class="art3">
					代理分类：
					<#if experexce.storeTypeNames??> 
		                <#list experexce.storeTypeNames?split(",") as type>
		                    <#if type !="">
		                        <span>${type!''}</span>
		                    </#if>
		                </#list>
		            </#if>
				</article>
			</aside>
			<!-- banner焦点图 -->
			<aside class="as2 fl">
				<div class="banner" id="banner">
					<div class="banner_img_box">
						<ul class="banner_img">
							<#if experexce.attachments??> 
				                <#list experexce.attachments as uri>
				                    <#if uri!="">
				                    	<li><a href="javascript:;" title=""><img src="${app.basePath}${uri!''}" alt="店铺图片" /></a></li>
				                    </#if>
				                </#list>
				            </#if>
						</ul>
					</div>
					<div class="banner_dot" style="display:none;">
						<#if experexce.attachments??> 
			                <#list experexce.attachments as uri>
			                    <#if uri!="">
			                    	<span class="active"></span>
			                    </#if>
			                </#list>
			            </#if>
					</div>
					<a href="javascript:;" class="ban_last"></a>
					<a href="javascript:;" class="ban_next"></a>
				</div>
			</aside>
			<!-- banner焦点图-结束 -->

		</section>
		<div id="allmap" <#if experexce.lat?? && experexce.lng??>style="width:1200px;height:600px;"</#if>></div>
	</div>
	<!-- 中间-结束 -->

	<!-- 底部 -->
	<#include "../common/commonfooter.ftl">
	<!-- 底部 -->
	<script type="text/javascript">
		$(function(){
			autoPlay($('#banner'),600,3500);
		});
		<#if experexce.lat?? && experexce.lng??>
		// 百度地图API功能
		var map = new BMap.Map("allmap");
		var point = new BMap.Point(${experexce.lat},${experexce.lng});
		map.centerAndZoom(point, 11);
		var marker = new BMap.Marker(point);  // 创建标注
		map.addOverlay(marker);
		var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
		var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
		var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角
		map.addControl(top_left_control);        
		map.addControl(top_left_navigation);     
		map.addControl(top_right_navigation);
		</#if>
	</script>
</body>
</html>