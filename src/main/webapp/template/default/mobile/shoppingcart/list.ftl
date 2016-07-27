<#import "/common/app.ftl" as app> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="copyright" content="" />
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <title>购物车</title>
    <#include "/common/common.ftl" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/common.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/main.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/index.css"/>
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/core.js"></script>
    <script src="${app.basePath}/static/js/mobile/index.js" type="text/javascript"></script>
    <script src="${app.basePath}/static/js/mobile/shoppingcart/shoppingcartlist.js" type="text/javascript"></script>
</head>
<script>
  window.onload=function(){
    two2();
    //all_sel();
  }
</script>
<body class="body_bg">
  <!-- header_top -->
  <div class="top_heater">
    <a href="${app.basePath}/mobile/index" title="" class="hleft hback"></a>
    <span>购物车</span>
    <a href="javascript:;" title="" class="see_nation" onclick="removeItems()">删除</a>
  </div>
  <!-- header_top end -->
  <!-- Center Start -->
  <section class="container">
    <div class="two" id="two2">
      <a href="javascript:;" title="" class="active"><span>商城</span></a>
      <a href="javascript:;" title=""><span class="dot">积分</span></a>
    </div>
    <div class="two2_match" id="two2_match">
      <div id="product_goods">
        <ul class="car_goods">
        <#if shoppingcart?? && shoppingcart.itemList??>
        	<#list shoppingcart.itemList as item>
          <li>
            <section class="div1"><input type="checkbox" name="ids" class="" value="${item.id}"></section>
            <section class="div2"><a href="javascript:;" title="" class="aimg"><img src="${app.basePath}<#if item.product??>${item.product.imageUrl!''}</#if>" alt="商品图片"></a></section>
            <section class="div3">
              <section class="sec1">${item.product.name!''}</section>
              <section class="sec2">
              	<#if item.productSku?? && item.productSku.specialList??>
              	<#list item.productSku.specialList as special>
                <label class="lab1">${special.sname!''}：<span>${special.soption!''}</span>&nbsp;&nbsp;</label>
                </#list>
                </#if>
              </section>
              <section class="sec3">
                <aside class="as1 fl">
                  <p class="p1">￥${item.productSku.salesPrice!'0'}</p>
                  <p class="p2">（运费：<span>${item.postage}</span>元）</p>
                </aside>
                <aside class="as2 fr">
                  <input type="button" value="-" class="ipt1" onclick="additem(${item.id},2)">
                  <input type="text" name="quantity" itemid="${item.id}" id="quantity_${item.id}" value="${item.quantity}" placeholder="1" class="ipt2" onChange="changeitem(this)" onKeyUp="formatInputInteger(this,1,9999)">
                  <input type="button" value="+" class="ipt3" onclick="additem(${item.id},1)">
                  <input type="hidden" id="stock_${item.id}" value="${item.productSku.stock!''}"/>
                </aside>
              </section>
            </section>
          </li>
          </#list>
          </#if>
        </ul>
      </div>
      <div id="point_products">
      </div>
    </div>
  </section>
  <!-- Center End -->

<!-- Footer Start -->
<footer>
    <div class="gopay">
        <label class="all_sel"><input type="checkbox" id="all_sel"/>全选</label>
        <a href="javascript:;" class="a-pay" title="" onclick="nextOrder()">结算</a>
        <section class="all_price">
          <p class="p1">总计：<font color="red">￥<span id="totalAmount">${shoppingcart.totalAmount}</span></font></p>
          <p class="p2">（运费：<span id="totalPostage">${shoppingcart.totalPostage}</span>元）</p>
        </section>
    </div>
    <span class="footclear"></span>
</footer>
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