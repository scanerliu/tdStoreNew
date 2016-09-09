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
	<script type="text/javascript" src="${app.basePath}/static/js/client/product/productdetail.js"></script>
	<style>
	  .ogray {float: left;padding: 0 17px;height: 35px;line-height: 34px;margin-right: 10px;border: 1px solid #e7e7e7;border-radius: 3px;overflow: hidden;background: #fff;margin-bottom: 5px;-moz-user-select: none;-webkit-user-select: none;-ms-user-select: none;-khtml-user-select: none;user-select: none;cursor:not-allowed;color:#fff;background:#ddd;}
	</style>
</head>
<script language="javascript" type="text/javascript">
    $(function () {
        imgside();
        searchProductType(1);
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
    });
</script>
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
	                    <#if collect?? && collect==true>
	                    <a href="javascript:;" class="a-collection" title="收藏商品">已收藏</a>
	                    <#else>
	                    <a href="javascript:;" class="a-collection" title="收藏商品" onclick="addCollect(${product.id?c})">收藏商品</a>
	                    </#if>
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
	                <div class="program1">
	                    <span>商品评价：</span>
	                    <p>好评度<strong class="red">${productStat.positivePercent!'0'}%</strong> ${productStat.reviewCount!'0'}人评价</p>
	                </div>
	                <div class="program1 border-bottom">
	                    <span>服&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务：</span>
	
	                    <p>由<strong>${region.name!''}<#if productType??>${productType.name!''}</#if>代理</strong> 发货并负责售后服务。</p>
	
	                </div>
	                <#if taList?? && taList?size gt 0>
				    <#list taList as typeatt>
				    <div class="program1">
	                    <span>${typeatt.sname}:</span>
				        <div class="para slect" id="attul_${typeatt.sname}">
				        	<#if typeatt.soptions??>
				        	<#list typeatt.soptions as option>
				        	<span attri="${option}">${option}</span>
				            </#list>
				    		</#if>
				        </div>
				        <script>
							$(function(){
							    $("#attul_${typeatt.sname}").on("click","span",function(){
							    	if(this.className!="ogray"){
										$(this).siblings().removeClass("checked");
										$(this).addClass("checked");
										changeProductSku(__skuJsons);
									}
							    });
							});
						</script>
				    </div>
				    </#list>
				    </#if>
				    <input type="hidden" id="skustock" value="${product.quantum!'0'}">
			        <input type="hidden" id="skuId" value="${product.defaultSkuId!'0'}">
			        <input type="hidden" id="skuPrice" value="0">
			        <input type="hidden" id="propostage" value="${product.postage!'0'}">
			        <input type="hidden" id="productId" value="${product.id!''}">
			        <input type="hidden" id="productKind" value="${product.kind!'0'}">
			        <input type="hidden" id="pointpercent" value="${integralexchangerate!'1000'}">
	                <div class="program1">
	                    <span>购买数量：</span>
	                    <div class="number">
	                        <a class="reduce" onclick="additem(2)"><strong>-</strong></a>
	                        <input class="txtnum" readonly value="1" type="text" id="prodquantity" onKeyUp="formatInputSkuNum(this)"/>
	                        <a class="add" onclick="additem(1)"><strong>+</strong></a>
	                    </div>
	                </div>
	                <#if product.kind ==5 || product.kind ==6>
	                <div class="program1">
	                    <span class="red">剩余时间：</span>
	                    <div class="timeout" id="timeLeft">
	                        <span>00</span>
	                        <label>天</label>
	                        <span>00</span>
	                        <label>时</label>
	                        <span>00</span>
	                        <label>分</label>
	                        <span>00</span>
	                        <label>秒</label>
	                    </div>
	                    <script>
							$(document).ready(function(){
							    timer();
							    setInterval("timer()",1000);
							});
							
							function checkTime(i)  
							{  
							    if (i < 10) {  
							        i = "0" + i;  
							    }  
							    return i;  
							}
							
							function timer()
							{
								    var ts = (new Date(${product.endTime?string("yyyy")}, 
							                parseInt(${product.endTime?string("MM")}, 10)-1, 
							                ${product.endTime?string("dd")}, 
							                ${product.endTime?string("HH")}, 
							                ${product.endTime?string("mm")}, 
							                ${product.endTime?string("ss")})) - (new Date());//计算剩余的毫秒数
							  
							    var allts = (new Date(${product.endTime?string("yyyy")}, 
							                parseInt(${product.endTime?string("MM")}, 10)-1, 
							                ${product.endTime?string("dd")}, 
							                ${product.endTime?string("HH")}, 
							                ${product.endTime?string("mm")}, 
							                ${product.endTime?string("ss")}))
							               - (new Date(${product.startTime?string("yyyy")}, 
							                parseInt(${product.startTime?string("MM")}, 10)-1, 
							                ${product.startTime?string("dd")}, 
							                ${product.startTime?string("HH")}, 
							                ${product.startTime?string("mm")}, 
							                ${product.startTime?string("ss")}));//总共的毫秒数 
							    
							    if (0 == ts)
							    {
							        window.location.reload();
							    }
							  
							    var date = new Date();
							    var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);//计算剩余的天数
							    var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);//计算剩余的小时数
							    var mm = parseInt(ts / 1000 / 60 % 60, 10);//计算剩余的分钟数
							    var ss = parseInt(ts / 1000 % 60, 10);//计算剩余的秒数
							     if(ss < 0){
							    	ss = 0;
							    }
							    if(mm < 0){
							    	mm = 0;
							    }
							    if(hh < 0){
							    	hh = 0;
							    }
							    if(dd < 0){
							    	dd = 0;
							    }
							    dd = checkTime(dd);
							    hh = checkTime(hh);
							    mm = checkTime(mm);
							    ss = checkTime(ss);
							    $("#timeLeft").html("<span>"+dd+"</span><label>天</label><span>"+hh+"</span><label>时</label><span>"+mm+"</span><label>分</label><span>"+ss+"</span><label>秒</label>");
							    
							    // 结束
							    if(dd == 0 && hh == 0 && mm == 0 && ss == 0){
							    	$("#addCart").removeAttr("onclick");
							    	$("#buyNow").removeAttr("onclick");
							    	$("#addCart").css("background","#ddd")
							    	$("#buyNow").css("background","#ddd")
							    }
							}
						</script> 
	                </div>
	                </#if>
	                <div class="program1">
	                    <span>库&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存：</span>
	                    <i id="s_skustock">${product.quantum!'0'}</i>
	                </div>
	                <div class="btnwrap">
	                    <input type="button" value="立即购买"  id="buyNow" class="orange" onclick="buyNow();"/>
	                    <#if product.kind==2 || product.kind==3>
	                    <#else>
	                    <input type="button" value="加入购物车" id="addCart" class="white" onclick="addToShoppingcart();"/>
	                    </#if>
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
	    <div class="ftt-detail2">
	        <div class="about-recommend">
	            <strong class="title">相关推荐</strong>
	            <ul class="pro-list1" id="typerecommendList">
	            </ul>
	        </div>
	
	        <div class="product-detail">
	            <div id="subnav" class="subnav" style="position: static; top: 0;">
	                <a href="javascript:void(0);" onclick="hreftotop($(this),1);" class="on">商品详情</a>
	                <a href="javascript:void(0);" onclick="hreftotop($(this),2)">商品评价</a>
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
		var __skuJsons = ${productjson};
		__integralexchangerate = ${integralexchangerate!'1000'};
		$(function(){
		});
	</script>
</body>  
</html>