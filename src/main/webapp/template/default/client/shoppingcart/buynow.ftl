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
    <title>结算 - ${system.webkeywords!''}</title>
    <#include "/common/common.ftl" />
    <!-- css -->
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/site.css" />
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/personal-center-common.css" />
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/f_personal_center.css" />
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/lhead.css" />
	<!-- js -->
	<#include "/common/common.ftl" />
	<script type="text/javascript" src="${app.basePath}/static/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/html5.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>
    <script src="${app.basePath}/static/js/client/shoppingcart/shoppingcartlist.js" type="text/javascript"></script>
</head>
<body>
  <h1 style="display:none;"></h1>
  <!-- Header Start -->
  <#include "../common/commonheader.ftl">
  <!-- 头部 -->
  <div class="clear"></div>
  <!-- Center Start -->
	<div class="index-main">
	    <div class="view-logistics submit-orders shopp-car shopping-program">
	        <ul class="shop-progress">
	            <li class="li01 on">
	                <i>1</i>
	                <label>查看购物车</label>
	            </li>
	            <li class=" on">
	                <i>2</i>
	                <label>确认订单信息</label>
	            </li>
	            <li>
	                <i>3</i>
	                <label>在线付款</label>
	            </li>
	            <li>
	                <i>4</i>
	                <label>交易成功</label>
	            </li>
	            <li>
	                <i>5</i>
	                <label>商品评论</label>
	            </li>
	        </ul>
			<#if shoppingcart.needShipment?? && shoppingcart.needShipment==true>
	        <div class="sureorderitem">
	            <span class="title">请选择收货地址</span>
	            <div class="addressitem" id="useraddresslist">
	            	<#if addressList??>
		        	<#list addressList as addr>
	                <div class="selct address<#if (defaulteAddressId?? && addr.id!=defaulteAddressId)||!defaulteAddressId??> new</#if>" tid="${addr.id!''}">
	                    <span class="p1">${addr.name!''} <label>${addr.telphone!''}</label></span>
	                    <span class="p2">${addr.fullAddress!''} ${addr.address!''}</span>
	                </div>
	                </#list>
			     	</#if>
	                <div class="address new">
	                    <a href="${app.basePath}/user/shoppingaddress?redirect=2" class="addnew" title="">新增收货地址</a>
	                </div>
	            </div>
	        </div>
	        </#if>
			<form id="confirmorder" method="post" action ="" autocomplete="off">
	        <div class="sureorderitem">
	            <span class="title">请选择支付方式</span>
	            <div class="payitem">
	                <label title="支付宝支付"><input name="paymentId" checked type="radio" value="1"/><img src="${app.basePath}/static/default/client/images/bank1.png" alt="支付宝支付"/></label>
	                <label title="微信支付"><input name="paymentId" type="radio" value="2"/><img src="${app.basePath}/static/default/client/images/bank2.png" alt="微信支付"/></label>
	                <label title="银联支付"><input name="paymentId" type="radio" value="3"/><img src="${app.basePath}/static/default/client/images/bank3.png" alt="银联支付"/></label>
	                <#if shoppingcart.canUserAccount==true>
	            	<label title="钱包支付"><input name="paymentId" type="radio" value="4"/><img src="${app.basePath}/static/default/client/images/bank4.png" alt="钱包支付"/></label>
	            	</#if>
	            </div>
	        </div>
	        <div class="pro-table">
	            <div class="table">
	                <div class="list-top">
	                    <p class="p1">商品名称</p>
	                    <p class="p2">商品属性</p>
	                    <p class="p3">单价（元）</p>
	                    <p class="p4">数量</p>
	                    <p class="p3">商品邮费（元）</p>
	                </div>
	                <#if shoppingcart?? && shoppingcart.ptype?? && shoppingcart.ptype !=1 && shoppingcart.agentProduct??>
				        <ul>
		                    <li class="li1">
		                        <div class="img">
		                            <img src="${app.basePath}${shoppingcart.agentProduct.imageUrl!''}" alt="${shoppingcart.agentProduct.title!''}" />
		                        </div>
		                        ${shoppingcart.agentProduct.title!''}
		                    </li>
		                    <li class="li2">
		                    	&nbsp;
		                    </li>
		                    <li class="li3">${shoppingcart.agentProduct.salesPrice!'0'}</li>
		                    <li class="li4">
		                        1
		                    </li>
		                    <li class="li3 last"></li>
		                </ul>
				    </#if>
					<#if shoppingcart?? && shoppingcart.itemList??>
		        	<#list shoppingcart.itemList as item>
		        	<#if item.itemType==2>
			        <ul>
	                    <li class="li1">
	                        <div class="img">
	                            <img src="${app.basePath}<#if item.productPackageItem??>${item.productPackageItem.productImage!''}</#if>" alt="${item.productPackageItem.productName!''}" />
	                        </div>
	                        ${item.productPackageItem.productName!''}
	                    </li>
	                    <li class="li2">
	                    	<#if item.productPackageItem.productSku?? && item.productPackageItem.productSku.specialList??>
				              	<#list item.productPackageItem.productSku.specialList as special>
				              	<span><label for="">${special.sname!''}：</label><font>${special.soption!''}</font></span>
				                </#list>
			                </#if>
			                &nbsp;
	                    </li>
	                    <li class="li3">${item.productPackageItem.price!'0'}</li>
	                    <li class="li4">
	                        ${item.quantity!'0'}
	                    </li>
	                    <li class="li3 last"></li>
	                </ul>
		        	<#else>
		        	<ul>
	                    <li class="li1">
	                        <div class="img">
	                            <img src="${app.basePath}<#if item.product??>${item.product.imageUrl!''}</#if>" alt="${item.product.name!''}" />
	                        </div>
	                        <a class="writing" href="${app.basePath}/product/item${item.productId!'0'}" title="${item.product.name!''}" target="_blank">${item.product.name!''}</a>
	                    </li>
	                    <li class="li2">
	                    	<#if item.productSku?? && item.productSku.specialList??>
			              	<#list item.productSku.specialList as special>
			              	<span><label for="">${special.sname!''}：</label><font>${special.soption!''}</font></span>
			                </#list>
			                </#if>
	                    </li>
	                    <li class="li3">${item.productSku.salesPrice!'0'}</li>
	                    <li class="li4">
	                        ${item.quantity!'0'}
	                    </li>
	                    <li class="li3 last">${item.postage!'0'}</li>
	                </ul>
	                </#if>
			        </#list>
			     </#if>
	            </div>
	        </div>
	        <#if shoppingcart.totalPointsUsed gt 0>
	        <div class="table" style="padding:0 30px;text-align:right;height:30px;line-height:30px;">
	            <span style="margin-right:20px;">使用积分</span>
	            <label class="lblcheckbox"><input id="userpoints" type="checkbox" name="usePoints" value="true" style="margin-right:10px;"/>可使用积分${shoppingcart.totalPointsUsed!'0'}抵扣￥${shoppingcart.totalPointAmount!'0'}<i class=""></i></label>
	            <script>
	                $(function(){
	                    $("#userpoints").click(function(){
	                        var checkval = $("input[type='checkbox']").is(':checked');
	                        if(checkval==true){
	                            $(this).siblings('i').addClass('checked');
	                            var totalAmount = ${shoppingcart.totalAmount!'0'};
	                            var totalPointAmount = ${shoppingcart.totalPointAmount!'0'};
	                            var amount = totalAmount - totalPointAmount;
	                            $("#totalAmountdv").html("￥"+amount);
	                        }else{
	                            $(this).siblings('i').removeClass('checked');
	                            $("#totalAmountdv").html("￥${shoppingcart.totalAmount!'0'}");
	                        }
	                    });
	                });
	            </script>
	        </div>
	        </#if>
	        <div class="table">
	            <span class="fl" style="margin-left:30px;">买家留言</span>
	            <span class="message fl" style="width:70%;margin-left:10px;"><input type="text" name="userMsg" value="" placeholder="可填写您与卖家达成一致的要求" style="height:24px;line-height:24px;border:1px solid #ddd;width:100%;text-align:left;"/></span>
	        </div>
	        <!-- 全选 -->
	        <div class="whole-choose">
	            <div class="left">
	            	<label style="float:right">共${shoppingcart.totalcount}件商品，合计：<font color="red">￥${shoppingcart.totalAmount}</font></label>
	            </div>
	            <div class="right">
	                <a href="javascript:;" title="" onclick="genernateOrder2()">提交订单</a>
	                <p>总计：<strong id="totalAmountdv">￥${shoppingcart.totalAmount!''}</strong></p>
	            </div>
	        </div>	
	    </div>
	    <input type="hidden" id="totalAmount" value="${shoppingcart.totalAmount!''}"/>
	    <input type="hidden" id="totalPointAmount" value="${shoppingcart.totalPointAmount!'0'}"/>
	    <input type="hidden" name="productSkuId" value="${orderForm.productSkuId!''}"/>
	    <input type="hidden" name="productId" value="${orderForm.productId!''}"/>
	    <input type="hidden" name="quantity" value="${orderForm.quantity!''}"/>
	    <input type="hidden" name="productType" value="${orderForm.productType!''}"/>
	    <input type="hidden" name="agentProductId" value="${orderForm.agentProductId!''}"/>
	    <input type="hidden" id="needShipment" value="${shoppingcart.needShipment?c}"/>
	    <input type="hidden" name="addressId" id="addressId" value="<#if shoppingcart.needShipment?? && shoppingcart.needShipment==true && defaulteAddressId??>${defaulteAddressId!''}</#if>"/>
	    </form>
	    <div class="clear"></div>
	</div>
	<!-- Center End -->
  
	<#include "../common/commonfooter.ftl">
	<script>
	$(function(){
		$("#useraddresslist .selct").click(function(){
	        var addrid = $(this).attr("tid");
	        $("#addressId").val(addrid);
	        $(this).removeClass('new').siblings('.selct').addClass("new");        
	    });
	});
	</script>
</body>  
</html>