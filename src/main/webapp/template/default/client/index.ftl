<#import "/common/app.ftl" as app>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="description" content="中国创客联盟"/>
    <meta name="keywords" content="中国创客联盟"/>
    <meta name="author" content="中国创客联盟"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <title>首页- ${system.webkeywords!''}</title>
    <!-- 网站图标 -->
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/default/client/style/site.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/chuangkeftt.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/lhead.css">
	<#include "/common/common.ftl" />
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/html5.js"></script>
    <!--通用js-->
    <script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/index.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/ftt_imgslide.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/jquery.SuperSlide.2.1.1.js"></script>
    <script src="${app.basePath}/static/js/client/index.js" type="text/javascript"></script>
     <script language="javascript" type="text/javascript">
        $(function () {
            /*头部菜单js*/
            jQuery("#slidebox").slide({
                mainCell: ".bd ul",
                effect: "fold",
                autoPlay: true,
                interTime: 5000
            });


            $(".picScroll").slide({
                titCell:".hd ul",
                mainCell:".bd ul",
                autoPage:true,
                effect:"leftLoop",
                autoPlay:true,
                vis:1
            });
            /*记录楼层位置*/
            var arr = new Array();
            $('.floor').each(function(){
                arr.push($(this).position().top);
            });

            //滚动条滚动，右侧导航树跟着导航
            $(window).scroll(function(){
                var scrollbar=$(document).scrollTop(); //得到滚动条距离浏览器顶部的高度
                var floorscroll = $('#floor1').offset().top-200;

                if(scrollbar < floorscroll){
                    $('#navtree').fadeOut();
                }else {
                    $('#navtree').fadeIn();
                }
                for ( var i=0; i < arr.length ; i++ ){
                    if(scrollbar>=arr[i] && scrollbar<arr[i+1]-300){
                        $(".navigationtree a").removeClass("on");
                        $(".navigationtree a").eq(""+i).addClass("on");
                    }
                    else if(scrollbar>=arr[arr.length-1]){
                        $(".navigationtree a").removeClass("on");
                        $(".navigationtree a").eq(""+i).addClass("on");
                    }
                }
            });
            $("#rightitems").on("mouseover","span",function(){
            	$(this).addClass("on").siblings().removeClass("on");
            	var index = $(this).index();
            	$("#rightcon div:eq("+index+")").show().siblings().hide();
            });
            searchProductType(2);
			getenjoyproducts();
        });
    </script>
</head>
<body>
<h1 style="display:none;"></h1>
	<!-- Header -->
	<#include "./common/commonheader.ftl">
	<!-- Header -->
	<div class="clear"></div>
	<!-- Center Start -->
	<div class="index-item1">
	    <div class="wrapper">
	        <div class="initem1">
	            <!--首页banner-->
	            <div class="indexbanner" id="slidebox">
	                <div class="bd">
	                    <ul>
	                    	<#if adList??>
	                    	<#list adList as add>
	                        <li><a href="<#if add.linkUrl??>${add.linkUrl!''}<#else>javascript:;</#if>" target="_blank"><img src="${app.basePath}${add.imageUrl!''}"/></a></li>
	                        </#list>
	                        </#if>
	                    </ul>
	                </div>
	            </div>
	            <div class="rightcontent">
	                <div class="item1">
	                    <div class="nav" id="rightitems">
	                        <span class="on">公告</span>
	                        <span>新闻</span>
	                    </div>
	                    <div class="content" id="rightcon">
	                        <div style="display: block">
	                        	<#if artList??>
		                    	<#list artList as item>
		                        <a href="${app.basePath}/site/list?aid=${item.aid!'0'}" title="${item.title!''}" target="_blank"><i>•</i>${item.title!''}</a>
		                        </#list>
		                        </#if>
	                        </div>
	                        <div>
	                            <#if newsList??>
		                    	<#list newsList as item>
		                        <a href="${app.basePath}/site/list?aid=${item.aid!'0'}" title="${item.title!''}" target="_blank"><i>•</i>${item.title!''}</a>
		                        </#list>
		                        </#if>
	                        </div>
	                    </div>
	                </div>
	                <!--
	                <div class="item2">
	                    <div class="title">
	                        <span>股东竞选</span>
	                        <a href="#" title="">+</a>
	                    </div>
	                    <div class="content1">
	                        <div class="partner part1">
	                            <img src="images/avatar.png" alt=""/>
	                            <em>NO.1</em>
	                            <span>二牛</span>
	                        </div>
	                        <div class="partner part2">
	                            <img src="images/avatar.png" alt=""/>
	                            <em>NO.2</em>
	                            <span>李晓梅</span>
	                        </div>
	                        <div class="partner part3">
	                            <img src="images/avatar.png" alt=""/>
	                            <em>NO.2</em>
	                            <span>董春辉</span>
	                        </div>
	                    </div>
	                    <span class="line"></span>
	
	                    <div class="appitem">
	                        <a href="#" class="ios" title="">苹果版App</a>
	                        <a href="#" class="android" title="">安卓版App</a>
	                    </div>
	                </div>
	                -->
	            </div>
	        </div>
	    </div>
	</div>
	<div class="wrapper wrelative">
	    <div class="index-content">
	    	<#if middeadList??>
        	<#list middeadList as add>
            <li><a href="<#if add.linkUrl??>${add.linkUrl!''}<#else>javascript:;</#if>" target="_blank"><img src="${app.basePath}${add.imageUrl!''}"/></a></li>
            <div class="item1">
	            <img src="${app.basePath}${add.imageUrl!''}" alt="广告图"/>
	            <a href="<#if add.linkUrl??>${add.linkUrl!''}<#else>javascript:;</#if>" title="" class="go">GO</a>
	        </div>
            </#list>
            </#if>
	    </div>
	    <!--
	    <div class="index-content">
	        <div class="item2">
	            <a href="#" title=""><img src="images/14.jpg" alt=""/></a>
	        </div>
	        <div class="item3">
	            <a href="#" title=""><img src="images/15.jpg" alt="图片尺寸157*98"/></a>
	            <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	            <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	            <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	            <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	            <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	            <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	            <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	            <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	            <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	            <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	            <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	            <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	            <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	            <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	            <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	            <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	            <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	            <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	            <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	        </div>
	    </div>
	    -->
	    <div class="index-content">
	        <div class="guess-like">
	        	<form id="enjoyForm">
				<input type="hidden" name="pageNo" id="enjoysc_pageNo" value="1">
				<input type="hidden" name="pageSize" value="6"/>
				</form>
	            <div class="title">
	                <label>猜你喜欢</label>
	                <a href="javascript:;" class="a-change" title="" id="enjoybtn">换一批</a>
	            </div>
	            <ul class="pro-list" id="enjoyList">
	            </ul>
	        </div>
	    </div>
		<#if floorList??>
		<#assign floor_cell = 1>
		<#list floorList as floor>
			<div id="floor${floor_cell}" class="index-content floor">
		        <div class="subtitle">
		            <span class="title">${floor_cell}F ${floor.title!''}</span>
		            <div class="subnav">
		                <a href="javascript:void(0);" class="current" title="">热门推荐</a>
		                <#if floor.typeList??>
		                <#list floor.typeList as type>
		                <#if type.productType??>
		                <a href="${app.basePath}/product/list?typeId=${type.tid!'0'}" title="${type.productType.name!''}">${type.productType.name!''}</a>
		                </#if>
		                </#list>
		                </#if>
		            </div>
		        </div>
		        <div class="floorcontent" id="floorcontent${floor_cell}">
		        </div>
		        <script>
		        	$(function () {
		        		getFloorads(${floor_cell},${floor.fid!'0'});
				    });
		        </script>
		    </div>
		    <#assign floor_cell++ >
		</#list>
		</#if>
	</div>
	<!-- Center End -->
	
	<!--楼层引导-->
	<div class="navigationtree" id="navtree">
		<#if floorList??>
		<#assign floor_cell = 1>
		<#list floorList as floor>
	    <a href="javascript:void;" onclick="hreftofloor('floor${floor_cell}')"><img src="${app.basePath}/static/default/client/images/21.jpg" alt=""/><label>${floor_cell}F</label></a>
	    <#assign floor_cell++ >
		</#list>
		</#if>
	    <a href="javascript:void;" onclick="hreftofloor(0);" class="totop"><img src="${app.basePath}/static/default/client/images/f-icon20.jpg" alt=""/><label>Top</label></a>
	</div>
	<!-- Footer Start -->
	<#include "./common/commonfooter.ftl">
	<!-- Footer End -->
</body>
</html>