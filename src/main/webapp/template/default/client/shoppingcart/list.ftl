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
    <title>购物车 - ${system.webkeywords!''}</title>
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
	            <li>
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
	        <!-- 商品信息表格 -->
	        <div class="pro-table">
	            <div class="table">
	                <div class="list-top">
	                    <p class="p1">商品名称</p>
	                    <p class="p2">商品属性</p>
	                    <p class="p3">单价（元）</p>
	                    <p class="p4">数量</p>
	                    <p class="p3">运费（元）</p>
	                    <p class="p3">操作</p>
	                </div>
	                <#if shoppingcart?? && shoppingcart.itemList?? && shoppingcart.itemList?size gt 0>
        			<#list shoppingcart.itemList as item>
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
			                <label class="lab1">${special.sname!''}：<span>${special.soption!''}</span>&nbsp;&nbsp;</label>
			                </#list>
			                </#if>
	                    </li>
	                    <li class="li3">${item.productSku.salesPrice!'0'}</li>
	                    <li class="li4">
	                        <!-- 数量选择 -->
	                        <div class="numbers">
	                            <input class="button less" type="button" value="-" onclick="additem(${item.id},2)">
	                            <input type="text" name="quantity" itemid="${item.id}" id="quantity_${item.id}" value="${item.quantity}" placeholder="1" class="ipt2" onChange="changeitem(this)" onblur="changeitem(this)" onKeyUp="formatInputInteger(this,1,9999)">
	                            <input class="button add" type="button" value="+" onclick="additem(${item.id},1)">
	                            <input type="hidden" id="stock_${item.id}" value="${item.productSku.stock!''}"/>
	                        </div>
	                    </li>
	                    <li class="li3">${item.postage}</li>
	                    <li class="li3 last">
	                        <a href="javascript:;" title="移除" onclick="removeItems(${item.id!'0'})" >删除</a>
	                    </li>
	                </ul>
	                </#list>
	                <#else>
		                <div class="noorder">
		                    <span class="lbltips">是时候补充一波购物车了 <br/>赶快去挑选几件好货吧！</span>
		                </div>
          			</#if>
	            </div>
	        </div>
	        <!-- 全选 -->
	        <#if shoppingcart?? && shoppingcart.itemList?? && shoppingcart.itemList?size gt 0>
	        <div class="whole-choose">
	            <div class="left">
	            </div>
	            <div class="right">
	                <a href="javascript:;" title="" onclick="nextOrder()">去结算</a>
	                <p>合计  <strong>￥<span id="totalAmount">${shoppingcart.totalAmount}</span></strong>（含运费 ￥<span id="totalPostage">${shoppingcart.totalPostage}</span>)</p>
	            </div>
	        </div>
	        <#else>
	        	<div class="guess-like">
	        		<form id="enjoyForm">
					<input type="hidden" name="pageNo" id="enjoysc_pageNo" value="1">
					<input type="hidden" name="pageSize" value="6"/>
					</form>
		            <div class="title">
		                <label>猜你喜欢</label>
		                <a href="javascript:;" class="a-change" title="" id="enjoybtn">换一批</a>
		            </div>
		            <div class="pro-list">
		                <ul id="enjoyList">
		                </ul>
		            </div>
		        </div>
		        <script>
				$(function(){
					getenjoyproducts();
				});
				</script>
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
	$("#all_sel").click(function() {
		$(this).toggleClass('och');
		$('#product_goods input[name="ids"]').toggleClass('och').prop("checked",this.checked); 
    });
    var $subBox = $("#product_goods input[name='ids']");
    $subBox.click(function(){
    	$(this).toggleClass('och');
        $("#all_sel").prop("checked",$subBox.length == $("#product_goods input[name='ids']:checked").length ? true : false);
        if($("#all_sel").prop("checked")){
        	$("#all_sel").toggleClass('och');
        }else{
        	$("#all_sel").removeClass('och');
        }
    });
});
</script>
</body>  
</html>