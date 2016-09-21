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
    <script src="${app.basePath}/static/js/client/order/orderlist.js" type="text/javascript"></script>
     <script language="javascript" type="text/javascript">
        $(function () {
            /*头部菜单js*/
            indexmenutoggle('menu', "menuitem", "childitem");
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
            togglenav1('#floor1 .subnav a', 'current', "#floor1 .floorcontent .right");
            togglenav1('#floor2 .subnav a', 'current', "#floor2 .floorcontent .right");
            togglenav1('#floor3 .subnav a', 'current', "#floor3 .floorcontent .right");
            togglenav1('#floor4 .subnav a', 'current', "#floor4 .floorcontent .right");

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
	                <div class="item1" id="rightitem">
	                    <div class="nav">
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
	
	    <div id="floor1" class="index-content floor">
	        <div class="subtitle">
	            <span class="title">1F 女装/内衣</span>
	            <div class="subnav">
	                <a href="javascript:void(0);" class="current" title="">热门推荐</a>
	                <a href="javascript:void(0);" title="">特色服装</a>
	                <a href="javascript:void(0);" title="">内裤背心</a>
	                <a href="javascript:void(0);" title="">袜子配饰</a>
	                <a href="javascript:void(0);" title="">文胸塑型</a>
	            </div>
	        </div>
	        <div class="floorcontent">
	            <div class="left">
	                <div class="sitebanner">
	                    <div class="picScroll">
	                        <div class="hd">
	                            <ul></ul>
	                        </div>
	                        <div class="bd">
	                            <ul class="picList">
	                                <li>
	                                    <a href="#" title=""><img src="images/20.jpg" alt=""/></a>
	                                </li>
	                                <li>
	                                    <a href="#" title=""><img src="images/20.jpg" alt=""/></a>
	                                </li>
	                                <li>
	                                    <a href="#" title=""><img src="images/20.jpg" alt=""/></a>
	                                </li>
	                                <li>
	                                    <a href="#" title=""><img src="images/20.jpg" alt=""/></a>
	                                </li>
	                                <li>
	                                    <a href="#" title=""><img src="images/20.jpg" alt=""/></a>
	                                </li>
	
	                            </ul>
	                        </div>
	                    </div>
	                    <div class="a-item">
	                        <a href="#" title="">女士T恤</a>
	                        <a href="#" title="">连衣裙</a>
	                        <a href="#" title="">雪纺衫</a>
	                        <a href="#" title="">中老年服装</a>
	                        <a href="#" title=""> 圆领</a>
	                        <a href="#" title=""> V领</a>
	                        <a href="#" title=""> POLO</a>
	                        <a href="#" title=""> 立领</a>
	                        <a href="#" title=""> 一字领</a>
	                        <a href="#" title=""> 半开领</a>
	                    </div>
	                </div>
	                <div class="brands">
	                    <!--图片尺寸127*80-->
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                </div>
	            </div>
	            <div class="right">
	                <div class="rightlist" style="display: block;">
	                    <div class="right-item1">
	                        <a href="#" title="" class="pic-item pic-item1">
	                            <img src="images/16.jpg" class="img" alt=""/>
	                        </a>
	                        <a href="#" title="" class=" pic-item pic-item2">
	                            <img src="images/17.jpg" class="img" alt=""/>
	                        </a>
	                        <a href="#" title="" class=" pic-item pic-item2">
	                            <img src="images/18.jpg" class="img" alt=""/>
	                        </a>
	                    </div>
	                    <div class="right-item2">
	                        <a href="#" title="" class=" pic-item pic-item3">
	                            <img src="images/19.jpg" class="img" alt=""/>
	                        </a><a href="#" title="" class=" pic-item pic-item3">
	                        <img src="images/19.jpg" class="img" alt=""/>
	                    </a><a href="#" title="" class=" pic-item pic-item3">
	                        <img src="images/19.jpg" class="img" alt=""/>
	                    </a>
	                    </div>
	                </div>
	                <div class="rightlist">
	                    <ul class="productlist">
	                        <li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li>
	                    </ul>
	                </div>
	                <div class="rightlist">
	                    <ul class="productlist">
	                        <li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li>
	                    </ul>
	                </div>
	                <div class="rightlist">
	                    <ul class="productlist">
	                        <li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li>
	                    </ul>
	                </div>
	                <div class="rightlist">
	                    <ul class="productlist">
	                        <li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li>
	                    </ul>
	                </div>
	            </div>
	        </div>
	    </div>
	    <div id="floor2" class="index-content floor">
	        <div class="subtitle">
	            <span class="title">2F 男装/户外运动</span>
	            <div class="subnav">
	                <a href="javascript:void(0);" class="current" title="">热门推荐</a>
	                <a href="javascript:void(0);" title="">特色服装</a>
	                <a href="javascript:void(0);" title="">内裤背心</a>
	                <a href="javascript:void(0);" title="">袜子配饰</a>
	                <a href="javascript:void(0);" title="">文胸塑型</a>
	            </div>
	        </div>
	        <div class="floorcontent">
	            <div class="left">
	                <div class="sitebanner">
	                    <div class="picScroll">
	                        <div class="hd">
	                            <ul></ul>
	                        </div>
	                        <div class="bd">
	                            <ul class="picList">
	                                <li>
	                                    <a href="#" title=""><img src="images/20.jpg" alt=""/></a>
	                                </li>
	                                <li>
	                                    <a href="#" title=""><img src="images/20.jpg" alt=""/></a>
	                                </li>
	                                <li>
	                                    <a href="#" title=""><img src="images/20.jpg" alt=""/></a>
	                                </li>
	                                <li>
	                                    <a href="#" title=""><img src="images/20.jpg" alt=""/></a>
	                                </li>
	                                <li>
	                                    <a href="#" title=""><img src="images/20.jpg" alt=""/></a>
	                                </li>
	
	                            </ul>
	                        </div>
	                    </div>
	                    <div class="a-item">
	                        <a href="#" title="">女士T恤</a>
	                        <a href="#" title="">连衣裙</a>
	                        <a href="#" title="">雪纺衫</a>
	                        <a href="#" title="">中老年服装</a>
	                        <a href="#" title=""> 圆领</a>
	                        <a href="#" title=""> V领</a>
	                        <a href="#" title=""> POLO</a>
	                        <a href="#" title=""> 立领</a>
	                        <a href="#" title=""> 一字领</a>
	                        <a href="#" title=""> 半开领</a>
	                    </div>
	                </div>
	                <div class="brands">
	                    <!--图片尺寸127*80-->
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                </div>
	            </div>
	            <div class="right">
	                <div class="rightlist" style="display: block;">
	                    <div class="right-item1">
	                        <a href="#" title="" class="pic-item pic-item1">
	                            <img src="images/16.jpg" class="img" alt=""/>
	                        </a>
	                        <a href="#" title="" class=" pic-item pic-item2">
	                            <img src="images/17.jpg" class="img" alt=""/>
	                        </a>
	                        <a href="#" title="" class=" pic-item pic-item2">
	                            <img src="images/18.jpg" class="img" alt=""/>
	                        </a>
	                    </div>
	                    <div class="right-item2">
	                        <a href="#" title="" class=" pic-item pic-item3">
	                            <img src="images/19.jpg" class="img" alt=""/>
	                        </a><a href="#" title="" class=" pic-item pic-item3">
	                        <img src="images/19.jpg" class="img" alt=""/>
	                    </a><a href="#" title="" class=" pic-item pic-item3">
	                        <img src="images/19.jpg" class="img" alt=""/>
	                    </a>
	                    </div>
	                </div>
	                <div class="rightlist">
	                    <ul class="productlist">
	                        <li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                        <img src="images/3.jpg" alt=""/>
	                        <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                        <p class="price">￥99.00</p>
	                    </li><li>
	                        <img src="images/3.jpg" alt=""/>
	                        <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                        <p class="price">￥99.00</p>
	                    </li><li>
	                        <img src="images/3.jpg" alt=""/>
	                        <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                        <p class="price">￥99.00</p>
	                    </li><li>
	                        <img src="images/3.jpg" alt=""/>
	                        <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                        <p class="price">￥99.00</p>
	                    </li><li>
	                        <img src="images/3.jpg" alt=""/>
	                        <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                        <p class="price">￥99.00</p>
	                    </li><li>
	                        <img src="images/3.jpg" alt=""/>
	                        <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                        <p class="price">￥99.00</p>
	                    </li><li>
	                        <img src="images/3.jpg" alt=""/>
	                        <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                        <p class="price">￥99.00</p>
	                    </li>
	                    </ul>
	                </div>
	            </div>
	        </div>
	    </div>
	    <div id="floor3" class="index-content floor">
	        <div class="subtitle">
	            <span class="title">3F 男装/户外运动</span>
	            <div class="subnav">
	                <a href="javascript:void(0);" class="current" title="">热门推荐</a>
	                <a href="javascript:void(0);" title="">特色服装</a>
	                <a href="javascript:void(0);" title="">内裤背心</a>
	                <a href="javascript:void(0);" title="">袜子配饰</a>
	                <a href="javascript:void(0);" title="">文胸塑型</a>
	            </div>
	        </div>
	        <div class="floorcontent">
	            <div class="left">
	                <div class="sitebanner">
	                    <div class="picScroll">
	                        <div class="hd">
	                            <ul></ul>
	                        </div>
	                        <div class="bd">
	                            <ul class="picList">
	                                <li>
	                                    <a href="#" title=""><img src="images/20.jpg" alt=""/></a>
	                                </li>
	                                <li>
	                                    <a href="#" title=""><img src="images/20.jpg" alt=""/></a>
	                                </li>
	                                <li>
	                                    <a href="#" title=""><img src="images/20.jpg" alt=""/></a>
	                                </li>
	                                <li>
	                                    <a href="#" title=""><img src="images/20.jpg" alt=""/></a>
	                                </li>
	                                <li>
	                                    <a href="#" title=""><img src="images/20.jpg" alt=""/></a>
	                                </li>
	
	                            </ul>
	                        </div>
	                    </div>
	                    <div class="a-item">
	                        <a href="#" title="">女士T恤</a>
	                        <a href="#" title="">连衣裙</a>
	                        <a href="#" title="">雪纺衫</a>
	                        <a href="#" title="">中老年服装</a>
	                        <a href="#" title=""> 圆领</a>
	                        <a href="#" title=""> V领</a>
	                        <a href="#" title=""> POLO</a>
	                        <a href="#" title=""> 立领</a>
	                        <a href="#" title=""> 一字领</a>
	                        <a href="#" title=""> 半开领</a>
	                    </div>
	                </div>
	                <div class="brands">
	                    <!--图片尺寸127*80-->
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                </div>
	            </div>
	            <div class="right">
	                <div class="rightlist" style="display: block;">
	                    <div class="right-item1">
	                        <a href="#" title="" class="pic-item pic-item1">
	                            <img src="images/16.jpg" class="img" alt=""/>
	                        </a>
	                        <a href="#" title="" class=" pic-item pic-item2">
	                            <img src="images/17.jpg" class="img" alt=""/>
	                        </a>
	                        <a href="#" title="" class=" pic-item pic-item2">
	                            <img src="images/18.jpg" class="img" alt=""/>
	                        </a>
	                    </div>
	                    <div class="right-item2">
	                        <a href="#" title="" class=" pic-item pic-item3">
	                            <img src="images/19.jpg" class="img" alt=""/>
	                        </a><a href="#" title="" class=" pic-item pic-item3">
	                        <img src="images/19.jpg" class="img" alt=""/>
	                    </a><a href="#" title="" class=" pic-item pic-item3">
	                        <img src="images/19.jpg" class="img" alt=""/>
	                    </a>
	                    </div>
	                </div>
	                <div class="rightlist">
	                    <ul class="productlist">
	                        <li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                        <img src="images/3.jpg" alt=""/>
	                        <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                        <p class="price">￥99.00</p>
	                    </li><li>
	                        <img src="images/3.jpg" alt=""/>
	                        <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                        <p class="price">￥99.00</p>
	                    </li><li>
	                        <img src="images/3.jpg" alt=""/>
	                        <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                        <p class="price">￥99.00</p>
	                    </li><li>
	                        <img src="images/3.jpg" alt=""/>
	                        <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                        <p class="price">￥99.00</p>
	                    </li><li>
	                        <img src="images/3.jpg" alt=""/>
	                        <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                        <p class="price">￥99.00</p>
	                    </li><li>
	                        <img src="images/3.jpg" alt=""/>
	                        <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                        <p class="price">￥99.00</p>
	                    </li><li>
	                        <img src="images/3.jpg" alt=""/>
	                        <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                        <p class="price">￥99.00</p>
	                    </li>
	                    </ul>
	                </div>
	            </div>
	        </div>
	    </div>
	    <div id="floor4" class="index-content floor">
	        <div class="subtitle">
	            <span class="title">4F 男装/户外运动</span>
	            <div class="subnav">
	                <a href="javascript:void(0);" class="current" title="">热门推荐</a>
	                <a href="javascript:void(0);" title="">特色服装</a>
	                <a href="javascript:void(0);" title="">内裤背心</a>
	                <a href="javascript:void(0);" title="">袜子配饰</a>
	                <a href="javascript:void(0);" title="">文胸塑型</a>
	            </div>
	        </div>
	        <div class="floorcontent">
	            <div class="left">
	                <div class="sitebanner">
	                    <div class="picScroll">
	                        <div class="hd">
	                            <ul></ul>
	                        </div>
	                        <div class="bd">
	                            <ul class="picList">
	                                <li>
	                                    <a href="#" title=""><img src="images/20.jpg" alt=""/></a>
	                                </li>
	                                <li>
	                                    <a href="#" title=""><img src="images/20.jpg" alt=""/></a>
	                                </li>
	                                <li>
	                                    <a href="#" title=""><img src="images/20.jpg" alt=""/></a>
	                                </li>
	                                <li>
	                                    <a href="#" title=""><img src="images/20.jpg" alt=""/></a>
	                                </li>
	                                <li>
	                                    <a href="#" title=""><img src="images/20.jpg" alt=""/></a>
	                                </li>
	
	                            </ul>
	                        </div>
	                    </div>
	                    <div class="a-item">
	                        <a href="#" title="">女士T恤</a>
	                        <a href="#" title="">连衣裙</a>
	                        <a href="#" title="">雪纺衫</a>
	                        <a href="#" title="">中老年服装</a>
	                        <a href="#" title=""> 圆领</a>
	                        <a href="#" title=""> V领</a>
	                        <a href="#" title=""> POLO</a>
	                        <a href="#" title=""> 立领</a>
	                        <a href="#" title=""> 一字领</a>
	                        <a href="#" title=""> 半开领</a>
	                    </div>
	                </div>
	                <div class="brands">
	                    <!--图片尺寸127*80-->
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                    <a href="#" title=""><img src="images/15.jpg" alt=""/></a>
	                </div>
	            </div>
	            <div class="right">
	                <div class="rightlist" style="display: block;">
	                    <div class="right-item1">
	                        <a href="#" title="" class="pic-item pic-item1">
	                            <img src="images/16.jpg" class="img" alt=""/>
	                        </a>
	                        <a href="#" title="" class=" pic-item pic-item2">
	                            <img src="images/17.jpg" class="img" alt=""/>
	                        </a>
	                        <a href="#" title="" class=" pic-item pic-item2">
	                            <img src="images/18.jpg" class="img" alt=""/>
	                        </a>
	                    </div>
	                    <div class="right-item2">
	                        <a href="#" title="" class=" pic-item pic-item3">
	                            <img src="images/19.jpg" class="img" alt=""/>
	                        </a><a href="#" title="" class=" pic-item pic-item3">
	                        <img src="images/19.jpg" class="img" alt=""/>
	                    </a><a href="#" title="" class=" pic-item pic-item3">
	                        <img src="images/19.jpg" class="img" alt=""/>
	                    </a>
	                    </div>
	                </div>
	                <div class="rightlist">
	                    <ul class="productlist">
	                        <li>
	                            <img src="images/3.jpg" alt=""/>
	                            <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                            <p class="price">￥99.00</p>
	                        </li><li>
	                        <img src="images/3.jpg" alt=""/>
	                        <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                        <p class="price">￥99.00</p>
	                    </li><li>
	                        <img src="images/3.jpg" alt=""/>
	                        <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                        <p class="price">￥99.00</p>
	                    </li><li>
	                        <img src="images/3.jpg" alt=""/>
	                        <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                        <p class="price">￥99.00</p>
	                    </li><li>
	                        <img src="images/3.jpg" alt=""/>
	                        <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                        <p class="price">￥99.00</p>
	                    </li><li>
	                        <img src="images/3.jpg" alt=""/>
	                        <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                        <p class="price">￥99.00</p>
	                    </li><li>
	                        <img src="images/3.jpg" alt=""/>
	                        <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                        <p class="price">￥99.00</p>
	                    </li><li>
	                        <img src="images/3.jpg" alt=""/>
	                        <a href="#" title="">唐狮夏季牛仔裙唐狮夏季牛仔裙唐狮夏季牛仔裙</a>
	                        <p class="price">￥99.00</p>
	                    </li>
	                    </ul>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- Center End -->
	
	<!--楼层引导-->
	<div class="navigationtree" id="navtree">
	    <a href="javascript:void(0);" onclick="hreftofloor('floor1')"><img src="images/21.jpg" alt=""/><label>1F</label></a>
	    <a href="javascript:void(0);" onclick="hreftofloor('floor2')"><img src="images/21.jpg" alt=""/><label>2F</label></a>
	    <a href="javascript:void(0);" onclick="hreftofloor('floor3')"><img src="images/21.jpg" alt=""/><label>3F</label></a>
	    <a href="javascript:void(0);" onclick="hreftofloor('floor4')"><img src="images/21.jpg" alt=""/><label>4F</label></a>
	    <a href="javascript:void(0);" onclick="hreftofloor(0);" class="totop"><img src="images/f-icon20.jpg" alt=""/><label>Top</label></a>
	</div>
	<!-- Footer Start -->
	<#include "./common/commonfooter.ftl">
	<!-- Footer End -->
</body>
</html>