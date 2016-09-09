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
    <title>${product.name!''} - ${system.webkeywords!''}</title>
    <#include "/common/common.ftl" />
    <!-- css -->
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/site.css" />
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/chuangkeftt.css" />
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/lhead.css" />
	<!-- js -->
	<#include "/common/common.ftl" />
	<script type="text/javascript" src="${app.basePath}/static/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/html5.js"></script>
	<!--图片轮动js-->
    <script type="text/javascript" src="${app.basePath}/static/js/client/ftt_imgslide.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/index.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>
    <script src="${app.basePath}/static/js/client/package/package.js" type="text/javascript"></script>
    <script language="javascript" type="text/javascript">
	    $(function () {
	        imgside();
	        //searchProductType(1);
	        getrecommendproducts("hotrecommendList",1,10);
	        getrecommendproducts("typerecommendList",2,3);
	        getenjoyproducts();
	        getdescribe(${product.id?c});
	        
	        //$('.para span').click(function () {
	        //    $(this).addClass('checked').siblings().removeClass('checked');
	        //});
	
	        //redu(".reduce");
	        //add(".add");
	
	        $('#subnav').divfixed({"top":"0"});
	        $('#detailcontent > div').each(function(index,ele){
	            $(window).bind('scroll',function(){
	                if($(window).scrollTop() >= $(ele).offset().top-80 && $('html,body,document').scrollTop() <= ($(ele).offset().top-80)+$(ele).outerHeight()+$(ele).next().outerHeight()) {
	                    $('#subnav a').removeClass('on');
	                    $('#subnav a').eq(index).addClass('on');
	                }
	            });
	        });
	        $("#pic-left .pro-list").slide({
                titCell: ".hd ul",
                mainCell: ".bd ul",
                autoPage: true,
                effect: "left",
                autoPlay: false,
                vis: 6,
                scroll:6,
                trigger: "click"
            });
	        
	    });
	</script>
</head>
<body>
  <h1 style="display:none;"></h1>
	<!-- Header Start -->
	<#include "../common/commonheader.ftl">
	<!-- 头部 -->
	
	<!-- Center Start -->
	<div class="wrapper">
	    <div class="ftt-detail1">
	        <div class="left">
	            <div class="item1">
	                <div class="imgslidebox" id="imgslide">
	                    <div class="myimages">
	                        <img src="${app.basePath}${product.imageUrl!''}" alt="${product.name!''}" class="myImgs">
	                    </div>
	                    <div class="scrollitem">
	                        <a href="javascript:void(0);" class="prev" title=""></a>
	                        <div class="scrollimg">
	                            <div class="imgitem">
	                            	<#if attachmentList?? && (attachmentList?size > 0)>
						        	<#list attachmentList as attachment>
					                <a href="javascript:;"><img src="${app.basePath}${attachment.attachment!''}" alt="${product.name!''}"/></a>
					                </#list>
					                <#else>
					                <a href="javascript:;"><img src="${app.basePath}${product.imageUrl!''}" alt="${product.name!''}"/></a>
					        		</#if>
	                            </div>
	                        </div>
	                        <a href="javascript:void(0);" class="next" title=""></a>
	                    </div>
	                </div>
	                <div class="sharewrap">
	                    <label>分享到：</label>
	                    <div class="bdsharebuttonbox" data-tag="share_1">
	                        <a class="bds_qzone" data-cmd="qzone" href="#"></a>
	                        <a class="bds_tsina" data-cmd="tsina"></a>
	                        <a class="bds_tqq" data-cmd="tqf"></a>
	                        <a class="bds_renren" data-cmd="renren"></a>
	                        <a class="bds_weixin" data-cmd="weixin"></a>
	                    </div>
	                    <script>
	                        window._bd_share_config = {
	                            share: [{
	                                "bdSize": 16
	                            }]
	                        };
	                        with (document)0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion=' + ~(-new Date() / 36e5)];
	                    </script>
	                </div>
	            </div>
	            <div class="item2" id="slect">
	                <span class="title1">${product.name!''}</span>
	                <span class="title2">${product.title!''}</span>
	                <div class="price">
	                    <span class="p1"><#if product.kind==7><strong id="prodprice">${product.exchangepoints!''}</strong>积分<#else>￥<strong id="prodprice">${product.price!''}</strong></#if></span>
	                    <s class="p2" id="marketprice"></s>
	                    <span class="pid">商品编码：${product.code!''}</span>
	                </div>
	                <div class="program1 border-bottom">
	                    <span>服&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务：</span>
	
	                    <p>由<strong>平台</strong> 发货并负责售后服务。</p>
	
	                </div>
				    <form id="agentform" method="post" action="">
						<input type="hidden" name="agentProductId" id="agentProductId" value=""/>
						<input type="hidden" name="productType" id="productType" value="2"/>
						<input type="hidden" name="productTypeId" id="productTypeId" value=""/>
						<input type="hidden" name="regionId" id="regionId" value=""/>
						<input type="hidden" id="quantity" name="quantity" value="1">
					    <input type="hidden" id="propostage" value="${product.postage!'0'}">
					    <input type="hidden" id="productId" name="productId" value="${product.id!''}">
					</form>
	                <div class="program1">
	                    <span>购买数量：</span>
	                    1
	                        <input class="txtnum" readonly value="1" type="hidden" id="prodquantity" />
	                </div>
	                <div class="program1">
	                    <span>库&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存：</span>
	                    <i id="s_skustock">${product.quantum!'0'}</i>
	                </div>
	                <div class="btnwrap">
	                    <input type="button" value="立即购买"  id="buyNow" class="orange" onclick="buyNow();"/>
	                </div>
	            </div>
	        </div>
	        <div class="hotrecommend">
	            <span class="title"><strong>热门推荐</strong></span>
	
	            <div class="pro-list">
	                <div class="bd">
	                    <ul class="picList" id="hotrecommendList">
	                    </ul>
	                </div>
	                <div class="hd">
	                    <a class="next"></a>
	                    <a class="prev"></a>
	                </div>
	            </div>
	        </div>
	    </div>
	    <div class="ftt-detail3" id="pic-left">
	        <div class="title">
	            <label>套餐包含产品</label>
	        </div>
	        <div class="pro-list">
	            <div class="hd">
	                <ul></ul>
	            </div>
	            <div class="bd">
	                <ul class="picList">
	                	<#if itemList??>
				        	<#list itemList as item>
				        		<li>
			                        <a href="${app.basePath}/product/item${item.preprouductId!'0'}" class="img" title=""><img src="${app.basePath}${item.productImage!''}" alt="商品图片"/></a>
			                        <a href="${app.basePath}/product/item${item.preprouductId!'0'}" class="ptitle" title="">${item.productName!''}</a>
			                        <span class="price">
			                            <label class="p1">￥${item.price!'0'}</label>
			                        </span>
			                    </li>
			                </#list>
			        	</#if>
	                </ul>
	            </div>
	        </div>
	    </div>
	    <div class="ftt-detail2">
	        <div class="about-recommend">
	            <strong class="title">相关推荐</strong>
	            <ul class="pro-list1" id="typerecommendList">
	            </ul>
	        </div>
	
	        <div class="product-detail">
	            <div id="subnav" class="subnav" style="position: static; top: 0;">
	                <a href="javascript:void(0);" onclick="hreftotop($(this),1);" class="on">商品详情</a>
	                <a href="javascript:void(0);" onclick="hreftotop($(this),3)">包装与配送</a>
	                <a href="javascript:void(0);" onclick="hreftotop($(this),4)" class="noborder">售后服务</a>
	            </div>
	            <div id="detailcontent" class="content">
	            </div>
	        </div>
	    </div>
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
	<!-- Center End -->
	<!-- Footer Start -->
	<#include "../common/commonfooter.ftl">
	<!-- Footer End -->
	
	<script>
		$(function(){
		});
	</script>
</body>  
</html>