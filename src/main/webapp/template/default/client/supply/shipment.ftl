<#import "/common/app.ftl" as app>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="description" content="中国创客联盟"/>
    <meta name="keywords" content="中国创客联盟"/>
    <meta name="author" content="中国创客联盟"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <title>订单发货- ${system.webkeywords!''}</title>
    <!-- 网站图标 -->
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/default/client/style/site.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/personal-center-common.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/f_personal_center.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/lhead.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/index.css">
	<#include "/common/common.ftl" />
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/html5.js"></script>
    <!--通用js-->
    <script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/index.js"></script>
    <script src="${app.basePath}/static/js/client/supply/supply.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/Validform_v5.3.2_min.js"></script>
</head>
<body>
	<h1 style="display:none;"></h1>
	<!-- Header -->
	<#include "../common/centerheader.ftl">
	<!-- Header -->
	<div class="clear"></div>
	<!-- Center Start -->
	<div class="index-main">
	    <div class="personal-center">
	        <!-- 左侧导航 -->
        	<#include "../common/centerleftmenu.ftl">
        	
        	<div class="need_value l_right bg_white fr">
	            <!-- 全部订单、待评价分类 -->
	            <div class="box1">
	            	<div class="div1 fl">商家发货</div>
	            </div>
	            <!-- 商品名称、数量分类 -->
	            <div class="box2">
	                <p class="p1 w340 fl">商品名称</p>
	                <p class="p2 w80 fl">单价（元）</p>
	                <p class="p3 w80 fl">数量</p>
	                <p class="p5 w120 fl">实付款（元）</p>
	                <p class="p6 w110 fl">交易状态 </p>
	            </div>
	            <!-- 列表 -->
	            <ul class="box3" id="five_ul">
				    <#if order??>
				    <li>
				        <div class="order_num">
				            <label class="lab1"><#if order.createTime??>${order.createTime?string('yyyy-MM-dd')}</#if></label>
				            <label class="lab2">订单号： ${order.orderNo!''}</label>
				        </div>
				        <div class="goodsorder_detail">
				            <div class="fl goodsorder_detail_left w500">
				            	<#if order?? && order.productList?? && (order.productList?size > 0)>
							    <#list order.productList as product>
						    	<dl class="fl">
						    		<#if product.attachments??>
						    		<#list product.attachments as atta>
						    		<#if atta_index==0>
						    		<dt class="fl"><img src="${app.basePath}${atta!''}" alt="商品图片"/></dt>
						    		</#if>
						    		</#list>
						    		<#else>
						    		<dt class="fl"><img src="${app.basePath}" alt="商品图片"/></dt>
						    		</#if>
						    		<dd class="dd1 w240 fl">
				                        <p class="p1">${product.title!''} ${product.getItemTypeStr()!''}</p>
				                        <p class="p2">
				                        </p>
				                    </dd>
				                    <dd class="dd2 w80 txtc fl">${product.itemPrice!'0'}</dd>
				                    <dd class="dd3 w80 txtc fl">${product.quantity!'0'}</dd>
				                    <dd class="dd4 w90 txtc fl">
				                    </dd>
						    	</dl>
						    	</#list>
						    	</#if>
							    <#if order.skuList??>
							    <#list order.skuList as sku>
							    <dl class="fl">
				                    <dt class="fl"><a href="${app.basePath}/product/item${sku.productId!''}" target="_blank" title="商品详情"><img src="${app.basePath}${sku.productImage!''}" alt="商品图片"/></a></dt>
				                    <dd class="dd1 w240 fl">
				                        <p class="p1">${sku.productName!''}</p>
				                        <p class="p2">
				                        	<#if sku.specialList??>
							              	<#list sku.specialList as special>
							              	<label for="">${special.sname!''}：${special.soption!''}</label>
							                </#list>
							                </#if>
				                        </p>
				                    </dd>
				                    <dd class="dd2 w80 txtc fl">${sku.price!'0'}</dd>
				                    <dd class="dd3 w80 txtc fl">${sku.quantity!'0'}</dd>
				                </dl>
							    </#list>
							    </#if>
				            </div>
				            <div class="div5 w118 fl">
				                <p class="p1">${order.totalAmount!'0'}</p>
				                <p class="p2 color_black">含运费（<span>${order.postage!'0'}</span>）</p>
				            </div>
				            <div class="div6 w108 fl">
				                <p class="p1">
				                	<#if order.orderStatus==1>
										待支付
									<#elseif order.orderStatus==2>
							      		待发货
							    	<#elseif order.orderStatus==3>
							      		已发货
							    	<#elseif order.orderStatus==4>
							      		交易完成
							      	<#elseif order.orderStatus==5>
							      		申请退款
							      	<#elseif order.orderStatus==6>
							      		交易完成
							    	</#if>
				                </p>
				            </div>
				        </div>
				    </li>
				    <#else>
				    	<li>
				    		无数据信息！
				    	</li>
					</#if>
				</ul>
	        </div>
	        <!-- 右边 -->
        	<div class="need_value l_right bg_white fr" style="margin-top: 20px;">
	            <div class="box1">
	                <div class="div1 fl">收货地址</div>
	            </div>
	            <div class="form_box3">
	                <div class="div1">
	                    <label class="lab1 fl">姓名</label>
	                    <label class="lab2 fl"><#if order.orderAddress??>${order.orderAddress.customerName!''}</#if></label>
	                </div>
	                <div class="div1">
	                    <label class="lab1 fl">电话</label>
	                    <label class="lab2 fl"><#if order.orderAddress??>${order.orderAddress.telphone!''}</#if></label>
	                </div>
	                <div class="div1">
	                    <label class="lab1 fl">地址</label>
	                    <label class="lab2 fl"><#if order.orderAddress??>${order.orderAddress.regionFullName!''}</#if></label>
	                </div>
	                <div class="div1">
	                    <label class="lab1 fl">备注</label>
	                    <label class="lab2 fl">${order.userMessage!''}</label>
	                </div>
	            </div>
	        </div>
	        <!-- 右边 -->
	        <#if order.orderStatus==2>
        	<div class="need_value l_right bg_white fr" style="margin-top: 20px;">
	            <div class="box1">
	                <div class="div1 fl">卖家填写</div>
	            </div>
	            <div class="form_box3 all_nation">
					<form id="shipmentForm" method="post" action="${app.basePath}/supply/shiporder" class="obody">
					<input type="hidden" name="orderId" value="${order.orderId!'0'}"/>
	                <div class="div1 div_obox">
	                    <label class="lab1 fl"><span>*</span>快递</label>
			            <select name="trackingId">
			                <#if expressList??>
							<#list expressList as express>
								<option value="${express.id}">${express.name}</option>
							</#list>
							</#if>
			            </select>
	                </div>
	                <div class="div2 div_obox">
	                    <label class="lab1 fl" Validform_label><span>*</span>快递单号</label>
						<input type="text" style="float: left;width: 178px;height: 30px;border: 1px solid #ddd;" value="" name="trackingNo" datatype="s6-20" nullmsg="请填写快递单号！" errormsg="快递单号格式错误！"/>
	                </div>
	                <div class="div_sure sub_div"><input type="submit" value="确认提交" class="btn" style="width:146px; height:40px;border-radius:0;"></div>
	            </div>
				</form>
	        </div>
	        </#if>
	    </div>
	    <div class="clear"></div>
	</div>
	<!-- Center End -->
	<!-- Footer Start -->
	<#include "../common/commonfooter.ftl">
	<!-- Footer End -->
<script>
	$(function(){
		$("#shipmentForm").Validform({
			tiptype:function(msg,o,cssctl){
			    //msg：提示信息;
			    //o:{obj:*,type:*,curform:*},
			    //obj指向的是当前验证的表单元素（或表单对象，验证全部验证通过，提交表单时o.obj为该表单对象），
			    //type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, 
			    //curform为当前form对象;
			    //cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
			    if(o.type == 3){
			    	alert(msg);
			    }
			},
			beforeCheck:function(curform){
			},
			beforeSubmit:function(curform){
			 	openwaiting();
			},
			postonce:true, // 开启二次提交防御
			ajaxPost:true, 
			callback:function(data){
				//返回数据data是json对象，{"msg":"demo info","code":"1"}
				//info: 输出提示信息;
				//status: 返回提交数据的状态,是否提交成功。如可以用"y"表示提交成功，"n"表示提交失败，在ajax_post.php文件返回数据里自定字符，主要用在callback函数里根据该值执行相应的回调操作;
				closewaiting();
				alert(data.msg);
				if(data.code == 1){
					window.location.href=basePath+"/supply/order";
				}
			}
		});
	});
</script>
</body>
</html>