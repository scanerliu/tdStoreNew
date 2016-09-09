<#import "/common/app.ftl" as app>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="keywords" content="${system.webkeywords!''}">
    <meta name="description" content="${system.webdescription!''}">
    <meta name="copyright" content="${system.webcopyright!''}" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <title>分公司申请</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/common.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/main.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/swipe.css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/index.css">
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script> 
    <script type="text/javascript" src="${app.basePath}/static/touch/js/swipe.js"></script> 
    <script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/touch/js/index.js"></script>
</head>
<script>
$(document).ready(function(){
    
    $("#isCheck").change(function(){
        var check = document.getElementById("isCheck");
        if(check.checked){
            $("#sub_btn").css("background","#f23030");
            //$("#sub_btn").attr("href","${app.basePath}/mobile/agent/dopay?id="+${agent.id?c});
            $("#sub_btn").on("click",buynow);
        }else{
            //$("#sub_btn").attr("href","javascript:;");
            $("#sub_btn").off("click");
            $("#sub_btn").css("background","#999999");
        }
     });
    
    
    
});
</script>

<body class="">

<!-- header_top -->
<div class="top_heater">
    <a href="javascript:history.go(-1);" title="返回" class="hleft hback"></a>
    <span>成为代理</span>
</div>
<!-- header_top end -->

<!-- Center Start -->
<section class="container">
    <div class="become">
    	<#if agent??>
        <section class="sec1"><img src="${app.basePath}${agent.imageUrl!''}" alt="${agent.title!''}"></section>
        <section class="sec2">${agent.title!''}</section>
        <section class="sec3">${agent.note!''}</section>
        <section class="sec4">￥<span><#if agent.salesPrice??>${agent.salesPrice?string('0.00')}</#if></span></section>
        </#if>
        <section class="sec5"><input type="checkbox" checked="checked" id="isCheck">我已阅读并同意<a href="${app.basePath}/mobile/agent/article" title="《创客联盟代理条款》">《创客联盟代理条款》</a></section>
        <section class="sec6"><a href="javascript:;" onclick="buyNow();" title="立即加入" id="sub_btn">立即加入</a></section>
        <form id="agentform" method="post" action="">
        	<input type="hidden" name="agentProductId" id="agentProductId" value="${agent.id!''}"/>
        	<input type="hidden" name="productType" id="productType" value="2"/>
        	<input type="hidden" name="regionId" id="regionId" value="${regionId!''}"/>
        </form>
    </div>
</section>
<!-- Center end -->
<!-- Center end -->
<script>
//立即购买
function buyNow(){
	var agentProductId = $("#agentProductId").val();
	var regionId = $("#regionId").val();
	var productTypeId = $("#productTypeId").val();
	var isAgentProductUsePackage = $("#isAgentProductUsePackage").val();
	var productType = $("#productType").val();
	
	if(agentProductId>0){
		if(agentProductId==1||(agentProductId==4 && isAgentProductUsePackage=="true")){
			var agent = '{"agentProductId":'+agentProductId+',"regionId":'+regionId+',"productTypeId":'+productTypeId+',"productType":'+productType+'}';
			setCookie("agentpackage", agent, 30);
			url = basePath+"/mobile/package/list";
			window.location.href=url;
		}else{
			var url = basePath+"/mobile/shoppingcart/singleorder";
			$("#agentform").attr("action",url);
			$("#agentform").submit();
		}
	}else{
		alert("数据有误，请重新操作！");
	}
}
</script>
</body>
</html>