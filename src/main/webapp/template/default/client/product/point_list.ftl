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
    <title>商品列表 - ${system.webkeywords!''}</title>
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
	<script type="text/javascript" src="${app.basePath}/static/js/client/product/productlist.js"></script>
	<script>
		$(function(){
			//searchProductType(1);
			searchPointProducts(true);
			getenjoyproducts();
		});
	</script>
</head>
<body>
<h1 style="display:none;">创客</h1>
	<!-- 头部 -->
	<#include "../common/commonheader.ftl">
	<!-- 头部 -->
	<!-- 中间 -->
	<div class="content">
		<form id="searchform">
		<input type="hidden" id="sc_pointType" name="pointType" value="0"/>
		<!-- 价格区间 -->
		<section class="oprice w1200">
			<aside class="as1 fl">
				<div class="a1 fl"><span>排序方式</span></div>
				<div class="a1 fl">
					<span>按综合由高到低</span>
					<div class="oprice_hide" id="sortby">
						<a href="javascript:;" title="" tid="2">按综合由高到低</a>
						<a href="javascript:;" title="" tid="1">按综合由低到高</a>
						<a href="javascript:;" title="" tid="4">按销量由高到低</a>
						<a href="javascript:;" title="" tid="3">按销量由低到高</a>
						<a href="javascript:;" title="" tid="6">按价格由高到低</a>
						<a href="javascript:;" title="" tid="5">按价格由低到高</a>
					</div>
					<script>
					$(function(){
						$(".oprice .as1 .a1").each(function(i,ele){
							$(ele).hover(function(){
								$(this).find('.oprice_hide').show();
							},function(){
								$(this).find('.oprice_hide').hide();
							})
						})
						$('#sortby').find('a').click(function(){
							$(this).parent().prev().html($(this).html()+' &or;');
							$(this).parent().hide();
							$("#sc_orderby").val($(this).attr("tid"));
							searchPointProducts(true);
						})
					})
					</script>
				</div>
				<div class="a1 fl">
					<span>积分兑换规则</span>
					<div class="oprice_hide" id="pointTypeBy">
						<a href="javascript:;" title="" tid="0">全部</a>
						<a href="javascript:;" title="" tid="1">全积分兑换</a>
						<a href="javascript:;" title="" tid="2">部分积分兑换</a>
					</div>
					<script>
					$(function(){
						$('#pointTypeBy').find('a').click(function(){
							$(this).parent().prev().html($(this).html()+' &or;');
							$(this).parent().hide();
							$("#sc_pointType").val($(this).attr("tid"));
							searchPointProducts(true);
						})
					})
					</script>
				</div>
				<input type="text" name="startprice" class="ipt fl" onKeyUp="formatInputPriceDefault(this)" onblur="formatInputPriceDefault(this)"/>
				<span class="fl" style="margin-left:10px;">-</span>
				<input type="text" name="endprice" class="ipt fl" onKeyUp="formatInputPriceDefault(this)" onblur="formatInputPriceDefault(this)"/>
				<input type="hidden" name="orderby" id="sc_orderby" value="2"/>
				<input type="button" value="确定" class="ipt_sub fl" onclick="searchProducts(true);"/>
			</aside>
			<aside class="as2 fr">
				<a href="javascript:;" title="下一页" class="aright fr" id="nextpagebtn">&gt;</a>
				<a href="javascript:;" title="上一页" class="aleft fr" id="prepagebtn">&lt;</a>
				<label for="" class="lab1 fr"><span id="currPageNo">1</span>/<i id="currTotalPages">1</i></label>
				<label for="" class="lab2 fr">
					<input type="checkbox" name="postage" value="1"/>
					<span>商家包邮</span>
				</label>
				<label for="" class="lab2 fr">
					<input type="checkbox" name="stock" value="1"/>
					<span>仅显示有货</span>
				</label>
			</aside>
		</section>
		<!-- 价格区间-结束 -->
		</form>
		<!-- 列表 -->
		<form id="listform">
		<div id="results"></div>
		</form>
		<!-- 列表-结束 -->
		<!-- 猜你喜欢 -->
		<section class="youlike w1200">
			<form id="enjoyForm">
			<input type="hidden" name="pageNo" id="enjoysc_pageNo" value="1">
			<input type="hidden" name="pageSize" value="5"/>
			</form>
			<aside class="otitle">
				<label for="" class="fl">猜你喜欢</label>
				<span class="fr" id="enjoybtn">换一批</span>
			</aside>
			<aside class="obody">
				<ul id="enjoyList">
				</ul>
			</aside>
		</section>
		<!-- 猜你喜欢-结束 -->
	</div>
	<!-- 中间-结束 -->

	<!-- 底部 -->
	<#include "../common/commonfooter.ftl">
	<!-- 底部 -->
</body>
</html>