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
	                <#if taList??>
				    <#list taList as typeatt>
				    <div class="program1">
	                    <span>${typeatt.attribute.name}:</span>
				        <div class="para slect" id="attul_${typeatt.attribute.attriId}">
				        	<#if typeatt.attribute.tdProductAttributeOptionList??>
				        	<#list typeatt.attribute.tdProductAttributeOptionList as option>
				        	<#if option.status==0>
				        	<label class="ogray" title="无货">${option.name}</label>
				        	<#else>
				        	<span attri="${option.id}">${option.name}</span>
				        	</#if>
				            </#list>
				    		</#if>
				        </div>
				        <script>
							$(function(){
							    $("#attul_"+${typeatt.attribute.attriId}).on("click","span",function(){
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
				    <input type="hidden" id="skustock" value="0">
			        <input type="hidden" id="skuId" value="0">
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
	                <div class="btnwrap">
	                    <input type="button" value="立即购买" class="orange"/>
	                    <input type="button" value="加入购物车" class="white"/>
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
	                <a href="javascript:void(0);" onclick="hreftotop($(this),2)">具体参数</a>
	                <a href="javascript:void(0);" onclick="hreftotop($(this),3)">商品评价</a>
	                <a href="javascript:void(0);" onclick="hreftotop($(this),4)">包装与配送</a>
	                <a href="javascript:void(0);" onclick="hreftotop($(this),5)" class="noborder">售后服务</a>
	            </div>
	            <div id="detailcontent" class="content">
	                <div id="1" class="description">
	                    <table class="param" cellpadding="0" cellspacing="0">
	                        <tr>
	                            <td>商品名称：xxxxxxxxxxxxxxxxxxxxx</td>
	                            <td>店铺名称：xxxxxxxxx旗舰店</td>
	                            <td>品牌:XXXXXXXX</td>
	                        </tr>
	                        <tr>
	                            <td>风格：xxxx</td>
	                            <td>材质：棉麻</td>
	                            <td>季节：夏季</td>
	                        </tr>
	                        <tr>
	                            <td>裙长：xxxx</td>
	                            <td>上衣袖型：短袖</td>
	                            <td>颜色：两色</td>
	                        </tr>
	                        <tr>
	                            <td>尺码：xxxx</td>
	                        </tr>
	                    </table>
	                    <div class="info">
	                        <img src="images/1.jpg" alt=""/>
	                    </div>
	                </div>
	                <div id="2" class="main-param">
	                    <table cellspacing="1" cellpadding="1">
	                        <tr>
	                            <th colspan="2">主观参数</th>
	                        </tr>
	                        <tr>
	                            <td class="td1">花型图案</td>
	                            <td>印花</td>
	                        </tr>
	                        <tr>
	                            <td class="td1">风格</td>
	                            <td>韩版</td>
	                        </tr>
	                        <tr>
	                            <td class="td1">袖型</td>
	                            <td>A字裙</td>
	                        </tr>
	                        <tr>
	                            <td class="td1">风格</td>
	                            <td>韩版</td>
	                        </tr>
	                        <tr>
	                            <td class="td1">风格</td>
	                            <td>韩版</td>
	                        </tr>
	                        <tr>
	                            <td class="td1">风格</td>
	                            <td>韩版</td>
	                        </tr>
	                        <tr>
	                            <td class="td1">风格</td>
	                            <td>韩版</td>
	                        </tr>
	                        <tr>
	                            <td class="td1">风格</td>
	                            <td>韩版</td>
	                        </tr>
	
	                        <tr>
	                            <th colspan="2">实质参数</th>
	                        </tr>
	
	                        <tr>
	                            <td class="td1">适用人群</td>
	                            <td>女士</td>
	                        </tr>
	                        <tr>
	                            <td class="td1">适用年龄</td>
	                            <td>25-30岁</td>
	                        </tr>
	                        <tr>
	                            <td class="td1">适用季节</td>
	                            <td>春季</td>
	                        </tr>
	                        <tr>
	                            <td class="td1">适用人群</td>
	                            <td>女士</td>
	                        </tr>
	                    </table>
	                </div>
	                <div id="3" class="evalute">
	                    <div class="enav">
	                        <a class="on"><label>全部（904）</label></a>
	                        <a><label>好评（689）</label></a>
	                        <a><label>中评（135）</label></a>
	                        <a><label class="noborder">差评（32）</label></a>
	                    </div>
	                    <div class="evalute-list">
	                        <ul>
	                            <li>
	                                <span class="useravatar">
	                                    <img src="images/avatar.png" alt=""/>
	                                    <label>155*****11</label>
	                                    <span class="vip">V1</span>
	                                </span>
	
	                                <div class="right">
	                                    <div class="star star1">
	                                        <span class="s1"></span>
	                                        <span class="s2"></span>
	                                    </div>
	                                    <p>
	                                        卖家服务态度好，性价比高，值得入手，推荐！收到宝贝时包装完好无损。宝贝与商家店铺的宝贝描述是一致的。非常喜欢这件宝贝。全是5分好评。下次还来购买卖家服务态度好，性价比高，值得入手，推荐！收到宝贝时包装完好无损。宝贝与商家店铺的宝贝描述是一致的。非常喜欢这件宝贝。全是5分好评。下次还来购买卖家服务态度好，性价比高，值得入手，推荐！收到宝贝时包装完好无损。宝贝与商家店铺的宝贝描述是一致的。非常喜欢这件宝贝。全是5分好评。下次还来购买卖家服务态度好，性价比高，值得入手，推荐！收到宝贝时包装完好无损。宝贝与商家店铺的宝贝描述是一致的。非常喜欢这件宝贝。全是5分好评。下次还来购买卖家服务态度好，性价比高，值得入手，推荐！收到宝贝时包装完好无损。宝贝与商家店铺的宝贝描述是一致的。非常喜欢这件宝贝。全是5分好评。下次还来购买</p>
	
	                                    <div class="item">
	                                        <label>尺码：L</label>
	                                        <label>颜色：黑色</label>
	                                        <label>2016-04-11 20:30:20</label>
	                                    </div>
	                                </div>
	                            </li>
	                            <li>
	                                <span class="useravatar">
	                                    <img src="images/avatar.png" alt=""/>
	                                    <label>155*****11</label>
	                                    <span class="vip">V1</span>
	                                </span>
	
	                                <div class="right">
	                                    <div class="star star2">
	                                        <span class="s1"></span>
	                                        <span class="s2"></span>
	                                    </div>
	                                    <p>
	                                        卖家服务态度好，性价比高，值得入手，推荐！收到宝贝时包装完好无损。宝贝与商家店铺的宝贝描述是一致的。非常喜欢这件宝贝。全是5分好评。下次还来购买卖家服务态度好，性价比高，值得入手，推荐！</p>
	
	                                    <div class="item">
	                                        <label>尺码：L</label>
	                                        <label>颜色：黑色</label>
	                                        <label>2016-04-11 20:30:20</label>
	                                    </div>
	                                </div>
	                            </li>
	                            <li>
	                                <span class="useravatar">
	                                    <img src="images/avatar.png" alt=""/>
	                                    <label>155*****11</label>
	                                    <span class="vip">V1</span>
	                                </span>
	
	                                <div class="right">
	                                    <div class="star star3">
	                                        <span class="s1"></span>
	                                        <span class="s2"></span>
	                                    </div>
	                                    <p>
	                                        卖家服务态度好，性价比高，值得入手，推荐！收到宝贝时包装完好无损。宝贝与商家店铺的宝贝描述是一致的。非常喜欢这件宝贝。全是5分好评。下次还来购买卖家服务态度好，性价比高，值得入手，推荐！</p>
	
	                                    <div class="item">
	                                        <label>尺码：L</label>
	                                        <label>颜色：黑色</label>
	                                        <label>2016-04-11 20:30:20</label>
	                                    </div>
	                                </div>
	                            </li>
	                            <li>
	                                <span class="useravatar">
	                                    <img src="images/avatar.png" alt=""/>
	                                    <label>155*****11</label>
	                                    <span class="vip">V1</span>
	                                </span>
	
	                                <div class="right">
	                                    <div class="star star4">
	                                        <span class="s1"></span>
	                                        <span class="s2"></span>
	                                    </div>
	                                    <p>
	                                        卖家服务态度好，性价比高，值得入手，推荐！收到宝贝时包装完好无损。宝贝与商家店铺的宝贝描述是一致的。非常喜欢这件宝贝。全是5分好评。下次还来购买卖家服务态度好，性价比高，值得入手，推荐！</p>
	
	                                    <div class="item">
	                                        <label>尺码：L</label>
	                                        <label>颜色：黑色</label>
	                                        <label>2016-04-11 20:30:20</label>
	                                    </div>
	                                </div>
	                            </li>
	                            <li>
	                                <span class="useravatar">
	                                    <img src="images/avatar.png" alt=""/>
	                                    <label>155*****11</label>
	                                    <span class="vip">V1</span>
	                                </span>
	
	                                <div class="right">
	                                    <div class="star star5">
	                                        <span class="s1"></span>
	                                        <span class="s2"></span>
	                                    </div>
	                                    <p>
	                                        卖家服务态度好，性价比高，值得入手，推荐！收到宝贝时包装完好无损。宝贝与商家店铺的宝贝描述是一致的。非常喜欢这件宝贝。全是5分好评。下次还来购买卖家服务态度好，性价比高，值得入手，推荐！</p>
	
	                                    <div class="item">
	                                        <label>尺码：L</label>
	                                        <label>颜色：黑色</label>
	                                        <label>2016-04-11 20:30:20</label>
	                                    </div>
	                                </div>
	                            </li>
	                        </ul>
	                        <div class="page">
	                            <a class="on">1</a>
	                            <a>2</a>
	                            <a>3</a>
	                            <a>4</a>
	                            <a class="more">...</a>
	                            <a class="next">下一页 &gt;</a>
	                        </div>
	                        <span class="noinfo">
	                            暂无评论
	                        </span>
	                    </div>
	                </div>
	                <div id="4" class="iteminfo">
	                    <div class="title">
	                        <label>包装与配送</label>
	                    </div>
	                    <div class="itemcontent">
	                        <strong>包装清单</strong><br/>
	                        由于厂商产品批次不同，具体包装清单可能各有不同，请以实物为准 ！ <br/><br/>
	                        <strong>服务承诺</strong><br/>
	                        网站所售产品均为厂商正品，如有任何问题可与我们客服人员联系，我们会在第一时间跟您沟通处理。我们将争取以更具竞争力的价格、更优质的服务来满足您最大的需求。开箱验货：签收时在付款后与配送人员当面核对：商品及配件、应付金额、商品数量及发货清单、发票（如有）、赠品（如有）等；如存在包装破损、商品错误、商品短缺、商品存在质量问题等影响签收的因素，请您可以拒收全部或部分商品，相关的赠品，配件或捆绑商品应一起当场拒收；为了保护您的权益，建议您尽量不要委托他人代为签收；如由他人代为签收商品而没有在配送人员在场的情况下验货，则视为您所订购商品的包装无任何问题。<br/><br/>
	
	                        <strong>温馨提示</strong><br/>
	                        由于部分商品包装更换较为频繁，因此您收到的货品有可能与图片不完全一致，请您以收到的商品实物为准，同时我们会尽量做到及时更新，由此给您带来不便多多谅解，谢谢！<br/><br/>
	
	                        <strong>配送说明</strong><br/>
	                        所有商品24小时内发货，请注意查收您的消息！本平台购买的商品，都是次日达!
	                    </div>
	                </div>
	                <div id="5" class="iteminfo">
	                    <div class="title">
	                        <label>售后服务</label>
	                    </div>
	                    <div class="itemcontent">
	
	
	                        <strong>售后服务</strong><br/>
	                        本商家商品保证正品行货，严格按照国家三包政策提供售后服务，因质量问题或实物与描述不符产生的退换货服务运费由本店承担。<br/><br/>
	                        <strong>温馨提示</strong><br/>
	                        亲爱的顾客，为保障您的权益，请您对配送商品查验确认合格后签收，如有问题，请及时与商家联系。如需退货，请将包装一并寄回哦。<br/><br/>
	
	                        <strong>特别声明</strong><br/>
	                        本站商品信息均来自于创客商家，其真实性、准确性和合法性由信息发布者（商家）负责。本站不提供任何保证，并不承担任何法律责任。因厂家会在没有任何提前通知的情况下更改产品包装、产地或者一些附件，本站不能确保客户收到的货物与网站图片、产地、附件说明完全一致，网站商品的功能参数仅供参考，请以实物为准。若本站没有及时更新，请您谅解！<br/><br/>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <div class="guess-like">
	        <div class="title">
	            <label>猜你喜欢</label>
	            <a href="#" class="a-change" title="">换一批</a>
	        </div>
	        <ul class="pro-list">
	            <li>
	                <a href="#" class="img" title=""><img src="images/fimg.png" alt=""/></a>
	                <a href="#" class="ptitle" title="">黑白条纹宽松休闲圆领显瘦t恤修身夏季韩版荷</a>
	                <span class="price">
	                    ￥<label>119.00</label>
	
	                </span>
	            </li>
	            <li>
	                <a href="#" class="img" title=""><img src="images/fimg.png" alt=""/></a>
	                <a href="#" class="ptitle" title="">黑白条纹宽松休闲圆领显瘦t恤修身夏季韩版荷</a>
	                <span class="price">
	                    ￥<label>119.00</label>
	                </span>
	            </li>
	            <li>
	                <a href="#" class="img" title=""><img src="images/fimg.png" alt=""/></a>
	                <a href="#" class="ptitle" title="">黑白条纹宽松休闲圆领显瘦t恤修身夏季韩版荷</a>
	                <span class="price">
	                    ￥<label>119.00</label>
	                </span>
	            </li>
	            <li>
	                <a href="#" class="img" title=""><img src="images/fimg.png" alt=""/></a>
	                <a href="#" class="ptitle" title="">黑白条纹宽松休闲圆领显瘦t恤修身夏季韩版荷</a>
	                <span class="price">
	                    ￥<label>119.00</label>
	                </span>
	            </li>
	            <li>
	                <a href="#" class="img" title=""><img src="images/fimg.png" alt=""/></a>
	                <a href="#" class="ptitle" title="">黑白条纹宽松休闲圆领显瘦t恤修身夏季韩版荷</a>
	                <span class="price">
	                    ￥<label>119.00</label>
	                </span>
	            </li>
	            <li>
	                <a href="#" class="img" title=""><img src="images/fimg.png" alt=""/></a>
	                <a href="#" class="ptitle" title="">黑白条纹宽松休闲圆领显瘦t恤修身夏季韩版荷</a>
	                <span class="price">
	                    ￥<label>119.00</label>
	                </span>
	            </li>
	
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