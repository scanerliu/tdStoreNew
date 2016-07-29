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
    <title>图片美化</title>
    <#include "/common/common.ftl" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/common.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/main.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/f-personalcenter.css"/>
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/core.js"></script>
    <script src="${app.basePath}/static/js/mobile/index.js" type="text/javascript"></script>
</head>
<body class="body_gray">
  <!-- header_top -->
  <div class="top_heater">
    <a href="javascript:;" onclick="window.history.go(-1)" class="hleft hback"></a>
    <span>图片美化</span>
    <a href="javascript:;" title="" class="see_nation" onclick="removeItems()">删除</a>
  </div>
  <!-- header_top end -->
 <!-- Center Start -->
<section class="container edit-images">
    <div class="tips">
        <p>修改图片缴费说明</p>

        <p>1、我们按图片数量进行收费，${imageprice!'5'}元/张。</p>

        <p>2、您可以选择支付宝或者微信进行支付。</p>
    </div>
    <article>
        <span>商品编号</span>
        <span>${product.code!''}</span>
    </article>
    <article>
        <span>美图单价</span>
        <span>${imageprice!'5'}元/张</span>
    </article>
    <article>
        <span>图片数量</span>
        <span id="photonum">${imagNum!'0'}张</span>
    </article>

    <article>
        <span>商品图片</span>
        <div class="imgchecked">
        	<#if attachmentList??>
        	<#list attachmentList as attachment>
            <div>
                <span></span>
                <img src="${app.basePath}${attachment.attachment!''}" class="pic" alt="商品图片"/>
            </div>
            </#list>
            </#if>
        </div>

    </article>

    <div class="tips">
        <p>平台修改图片细则</p>

        <p>1、这里什么修改图片细则</p>

        <p>2、这里什么修改图片细则</p>
    </div>
<form id="imageForm" method="post" action="">
    <article>
        <span>买家留言</span>
        <textarea name="userMsg" placeholder="留言说明"></textarea>
    </article>
    <input type="hidden" name="productId" value="${product.id}"/>
    <input type="hidden" name="productType" value="3"/>
</form>

    <a href="javascript:;" class="create-code" title="" <#if imagNum gt 0>onclick="buyService()"</#if>>完成</a>
</section>
<!-- Center End -->

<!--footer Start-->
<footer>
    <span class="footclear"></span>
</footer>
<!--footer End-->
<script>
$(function(){
	
});
<#if imagNum gt 0>
function buyService(){
	var url = basePath+"/mobile/shoppingcart/singleorder";
	$("#imageForm").attr("action",url);
	$("#imageForm").submit();
}
</#if>
</script>
</body>  
</html>