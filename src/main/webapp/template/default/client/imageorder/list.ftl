<#import "/common/app.ftl" as app> 
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="description" content="中国创客联盟"/>
    <meta name="keywords" content="中国创客联盟"/>
    <meta name="author" content="中国创客联盟"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <title>图片美化- ${system.webkeywords!''}</title>
    <!-- 网站图标 -->
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/default/client/style/site.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/personal-center-common.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/f_personal_center.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/lhead.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/index.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/has.css">
	<#include "/common/common.ftl" />
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/html5.js"></script>
    <!--通用js-->
    <script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/index.js"></script>
</head>
<body">
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
        	
	        <!-- 右边 -->
	        <div class="right-content">
	        	<div class="all_nation white_box" style="padding-bottom:40px;">
		            <div class="otitle">
	                    <label for="" class="lab1 fl">图片美化</label>
	                </div>
	                <div class="tabb obody">
	                    <div class="rule">
	                        <p class="p1">修改图片缴费说明</p>
					        <p class="p2">1、我们按图片数量进行收费，${imageprice!'5'}元/张。</p>
					        <p class="p2">2、您可以选择支付宝或者微信进行支付。</p>
	                    </div>
	                    <div class="fig fig1">
	                        <label for="" class="lab1 fl">商品编号</label>
	                        ${product.code!''}
	                    </div>
	                    <div class="fig fig2">
	                        <label for="" class="lab1 fl"><span>*</span>商品照片</label>
	                        <section class="sig fl" style="padding-bottom:0;">
	                            <ul>
	                            	<#if attachmentList??>
						        	<#list attachmentList as attachment>
						        	<li>
	                                    <img src="${app.basePath}${attachment.attachment!''}" alt="商品图片">
	                                </li>
						            </#list>
						            </#if>
	                            </ul>
	                        </section>
	                    </div>
	                    <div class="rule">
	                        <p class="p1">平台修改图片细则</p>
	                        <p class="p2">1、规则</p>
	                        <p class="p2">2、规则</p>
	                    </div>
	                    <form id="imageForm" method="post" action="">
	                    <div class="fig fig1">
	                        <label for="" class="lab1 fl">买家留言</label>
	                        <textarea name="userMsg" id="" class="fl" maxlength="50"></textarea>
	                    </div>
	                     <input type="hidden" name="productId" value="${product.id}"/>
						 <input type="hidden" name="productType" value="3"/>
						</form>
	                </div>
	                <div class="sub_div" style="padding:0 80px;">
	                <input type="button" value="确认提交" class="btn" style="width:146px; height:40px;border-radius:0;" <#if imagNum gt 0>onclick="buyService()"</#if>></div>
	             </div>
	        </div>
	    </div>
	    <div class="clear"></div>
	</div>
	<!-- Center End -->
	<!-- Footer Start -->
	<#include "../common/commonfooter.ftl">
	<!-- Footer End -->
    <script type="text/javascript">
      $(function(){
      });
      <#if imagNum gt 0>
		function buyService(){
			var url = basePath+"/shoppingcart/singleorder";
			$("#imageForm").attr("action",url);
			$("#imageForm").submit();
		}
	  </#if>
    </script>
</body>
</html>