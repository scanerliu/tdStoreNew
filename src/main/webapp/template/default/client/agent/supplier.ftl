<#import "/common/app.ftl" as app>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="keywords" content="${system.webkeywords!''}">
    <meta name="description" content="${system.webdescription!''}">
    <meta name="copyright" content="${system.webcopyright!''}" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <title>创业中心 - ${system.webkeywords!''}</title>
    <!-- css -->
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/site.css" />
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/chuangkeftt.css" />
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/lhead.css" />
	<!-- js -->
	<#include "/common/common.ftl" />
	<script type="text/javascript" src="${app.basePath}/static/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/html5.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/library.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/index.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/agent/agent.js"></script>
</head>
<script>
$(document).ready(function(){
	$("#sub_btn").on("click",buyNow);
    $("#isCheck").change(function(){
        var check = document.getElementById("isCheck");
        if(check.checked){
            $("#sub_btn").css("background","#f23030");
            //$("#sub_btn").attr("href","${app.basePath}/mobile/agent/dopay?id="+${agent.id?c});
            $("#sub_btn").on("click",buyNow);
        }else{
            //$("#sub_btn").attr("href","javascript:;");
            $("#sub_btn").off("click");
            $("#sub_btn").css("background","#999999");
        }
     });
});
</script>
<body >
<h1 style="display:none;">创客</h1>
	<!-- 头部 -->
	<#include "../common/commonheader.ftl">
	<!-- 头部 -->
	<!-- 中间 -->
	<div class="wrapper">
	    <div class="become-agent">
	        <div class="descinfo">
	            <span class="title">${agent.title!''}</span>
	            <p>
	            	${agent.note!''}
	            </p>
	            <p>
	            	￥<span><#if agent.salesPrice??>${agent.salesPrice?string('0.00')}</#if></span>
	            </p>
	        </div>
	        <div class="forminfo">
	        	<!--
	            <div class="chooseitem">
	                <label>请选择地区:</label>
	                <select name="select1">
	                    <option value="1">云南省</option>
	                </select>
	                <select name="select2">
	                    <option value="1">昆明市</option>
	                </select>
	                <select name="select3">
	                    <option value="1">五华市</option>
	                </select>
	            </div>
	            <div class="chooseitem">
	                <label>想要代理的产品分类:</label>
	                <select name="select1">
	                    <option value="1">饰品</option>
	                </select>
	                <select name="select2">
	                    <option value="1">饰品</option>
	                </select>
	                <select name="select3">
	                    <option value="1">黄金</option>
	                </select>
	            </div>
	            -->
	            <dl class="agreeinfo">
	                <dt>
	                    <strong>阅读创客代理条款</strong>
	                    <label for="isCheck"><input type="checkbox" checked="checked" id="isCheck"/>我已同意《创客代理条款》</label>
	                </dt>
	                <dd>
	                   
	                </dd>
	            </dl>
	            <input type="button" id="sub_btn" class="btnjoin" value="立即加入"/>
	            <form id="agentform" method="post" action="">
		        	<input type="hidden" name="agentProductId" id="agentProductId" value="${agent.id!''}"/>
		        	<input type="hidden" name="productType" id="productType" value="2"/>
		        	<input type="hidden" name="regionId" id="regionId" value="${regionId!''}"/>
		        	<input type="hidden" name="productTypeId" id="productTypeId" value="${typeId!''}"/>
		        	<input type="hidden" id="isAgentProductUsePackage" value="${agent.gift?c}"/>
		        	<input type="hidden" id="agentprice" value="${agent.salesPrice?c}"/>
		        </form>
	        </div>
	    </div>
	</div>
	<!-- 中间-结束 -->
	<!-- 底部 -->
	<#include "../common/commonfooter.ftl">
	<!-- 底部 -->
</body>
</html>