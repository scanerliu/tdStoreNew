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
    <title>成为代理 - ${system.webkeywords!''}</title>
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
	function buyNow(){
		var agentlevel = ${agent.level};
		var agentgroupId = ${agent.groupId};
		var agentProductId = $("#agentProductId").val();
		var regionId = $("#regionId").val();
		var productTypeId = $("#productTypeId").val();
		var isAgentProductUsePackage = $("#isAgentProductUsePackage").val();
		var productType = $("#productType").val();
		var price = $("#agentprice").val();
		if(agentProductId>0){
			if(agentlevel>1 && (regionId==""||regionId=="0")){
				alert("请选择地区");
				return false;
			}
			if(agentgroupId==1 && productTypeId==""){
				alert("请选择代理单类");
				return false;
			}
			if(isAgentProductUsePackage=="true"){
				var agent = '{"agentProductId":"'+agentProductId+'","regionId":"'+regionId+'","productTypeId":"'+productTypeId+'","productType":"'+productType+'"}';
				setCookie("agentpackage", agent, 30);
				url = basePath+"/package/list?acpe="+price;
				window.location.href=url;
			}else{
				var url = basePath+"/shoppingcart/singleorder";
				$("#agentform").attr("action",url);
				$("#agentform").submit();
			}
		}else{
			alert("数据有误，请重新操作！");
		}
	}
	</script>
</head>
<body>
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
	        	<#if agent.level gt 1>
	        	<div class="chooseitem" id="address">
	        		<label>请选择地区:</label>
	        		<span id="provincespn"></span><span id="cityspn"></span><span id="regionspn"></span>
	        	</div>
	        	<#if agent.level==2>
	        	<script>
					$(function(){
						<#if agent.groupId == 1>
						getDistricts({'obj':null,'num':0,'total':1,'callback':'refreshtypelist'});
						<#else>
						getDistricts({'obj':null,'num':0,'total':1});
						</#if>
				    });
				</script>
	        	<#elseif agent.level==3>
	           	<script>
					$(function(){
						<#if agent.groupId == 1>
							getDistricts({'obj':null,'num':0,'total':3,'callback':'refreshtypelist'});
						<#else>
							getDistricts({'obj':null,'num':0,'total':3});
						</#if>
						
				    });
				</script>
	            </#if>
	            </#if>
	            <#if agent.groupId == 1>
	            <div class="chooseitem">
	                <label>想要代理的产品分类:</label>
	                <span id="onetypespn"></span><span id="twotypespn"></span><span id="typespn"></span>
	            </div>
	            <#if agent.level==1>
	           	<script>
					$(function(){
						getTypes({'obj':null,'num':0})
				    });
				</script>
	            </#if>
	            </#if>
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
		        	<input type="hidden" name="regionId" id="regionId" value="0"/>
		        	<input type="hidden" name="productTypeId" id="productTypeId" value=""/>
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