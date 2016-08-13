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
			searchProductType(1);
			searchProducts(true);
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
		<!-- 分类 -->
		<section class="all_classify w1200">
		<ul>
			<li class="li1">
				<aside class="as1 fl"><p>品牌</p></aside>
				<aside class="as2 fl">
					<article class="art1">
						<a href="javascript:;" title="A">A</a>
						<a href="javascript:;" title="B">B</a>
						<a href="javascript:;" title="C">C</a>
						<a href="javascript:;" title="D">D</a>
						<a href="javascript:;" title="E">E</a>
						<a href="javascript:;" title="F">F</a>
						<a href="javascript:;" title="G">G</a>
						<a href="javascript:;" title="H">H</a>
						<a href="javascript:;" title="I">I</a>
						<a href="javascript:;" title="J">J</a>
						<a href="javascript:;" title="K">K</a>
						<a href="javascript:;" title="L">L</a>
						<a href="javascript:;" title="M">M</a>
						<a href="javascript:;" title="N">N</a>
						<a href="javascript:;" title="O">O</a>
						<a href="javascript:;" title="P">P</a>
						<a href="javascript:;" title="Q">Q</a>
						<a href="javascript:;" title="R">R</a>
						<a href="javascript:;" title="S">S</a>
						<a href="javascript:;" title="T">T</a>
						<a href="javascript:;" title="U">U</a>
						<a href="javascript:;" title="V">V</a>
						<a href="javascript:;" title="W">W</a>
						<a href="javascript:;" title="X">X</a>
						<a href="javascript:;" title="Y">Y</a>
						<a href="javascript:;" title="Z">Z</a>
					</article>
					<article class="art2">
						<#if brandList??>
						<#list brandList as brand>
						<a href="javascript:;" title="${brand.name!''}" class="aimg" letter="${brand.letter!''}" tid="${brand.id!''}"><img src="${app.basePath}${brand.imageUrl!''}" alt="${brand.name!''}" /></a>
						</#list>
						</#if>
					</article>
					<article class="art3">
						<a href="javascript:;" title="" class="a1">确定</a>
						<a href="javascript:;" title="" class="a2">取消</a>
					</article>
				</aside>
			</li>
		</ul>
		<script>
			$(function(){
				$('.all_classify ul .li1 .as2 .art1 a').click(function(){
					$(this).addClass('active').siblings().removeClass('active');
					$('.all_classify .li1 .as2').css({'height':'auto'});
					$('.all_classify .li1 .as1').css({'height':$('.all_classify .li1').outerHeight()});
					var letter = $(this).attr("title");
					var showbutton = false;
					$('.all_classify ul .li1 .as2 .art2 a').each(function(){
						if($(this).attr("letter")==letter){
							$(this).show();
							showbutton = true;
						}else{
							$(this).hide();
						}
					});
					if(showbutton){
						$('.all_classify ul .li1 .as2 .art3').show();
					}else{
						$('.all_classify ul .li1 .as2 .art3').hide();
					}
				})
				$('.all_classify ul .li1 .as2 .art2 a').click(function(){
					$(this).css('border','1px solid #2399dd').addClass("active");
					$(this).siblings().css('border','1px solid #ddd').removeClass("active");
				})
				$('.all_classify ul .li1 .as2 .art3 .a1').click(function(){
					var bid = $('.all_classify ul .li1 .as2 .art2 a[class$="active"]').attr("tid");
					$("#sc_brand").val(bid);
					searchProducts(true);
				})
				$('.all_classify ul .li1 .as2 .art3 .a2').click(function(){
					$('.all_classify .li1 .as2').css('height',34);
					$('.all_classify .li1 .as1').css('height',$('.all_classify .li1 .as2').outerHeight());
					$('.all_classify ul .li1 .as2 .art2 a').css('border','1px solid #ddd').removeClass("active");
					$('.all_classify ul .li1 .as2 .art1 a').removeClass("active");
					$("#sc_brand").val("");
					searchProducts(true);					
				})
				$('.amore').each(function(i,ele){
					$(ele).attr('jud',false);
							console.log($(this).attr('jud'));
				})
				$('.amore').each(function(i,ele){
					$(ele).click(function(){
						if(!$(this).attr('jud')){
							$(this).html('收起'+'&and;');
							$(this).attr('jud',true);
							console.log(1+':'+$(this).attr('jud'));
						}else{
							$(this).html('更多'+'&or;');
							$(this).attr('jud',false);
							console.log(2+':'+$(this).attr('jud'));
						}
						$(this).prev().toggleClass('as2noneh');
						$(this).prevAll('.as1').css('height',$(this).prev().outerHeight());
					})
				})
			})
		</script>
		</section>
		<!-- 分类-结束 -->
		<form id="searchform">
		<input type="hidden" id="sc_brand" name="brandId" value=""/>
		<input type="hidden" id="sc_type" name="typeId" value="${sc.typeId!''}"/>
		<input type="hidden" name="name" value="${sc.name!''}"/>
		<!-- 价格区间 -->
		<section class="oprice w1200">
			<aside class="as1 fl">
				<div class="a1 active fl"><span>综合</span></div>
				<div class="a1 fl">
					<span>销量 &or;</span>
					<div class="oprice_hide"><a href="javascript:;" title="">按销量由高到低</a></div>
				</div>
				<div class="a1 fl">
					<span>价格 &or;</span>
					<div class="oprice_hide">
						<a href="javascript:;" title="">按价格由高到低</a>
						<a href="javascript:;" title="">按价格由低到高</a>
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
						$('.oprice_hide').find('a').click(function(){
							$(this).parent().prev().html($(this).html()+' &or;');
							$(this).parent().hide();
						})
					})
					</script>
				</div>
				<input type="text" name="startprice" class="ipt fl" onKeyUp="formatInputPriceDefault(this)" onblur="formatInputPriceDefault(this)"/>
				<span class="fl" style="margin-left:10px;">-</span>
				<input type="text" name="endprice" class="ipt fl" onKeyUp="formatInputPriceDefault(this)" onblur="formatInputPriceDefault(this)"/>
				<input type="hidden" name="orderby" value=""/>
				<input type="button" value="确定" class="ipt_sub fl" />
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