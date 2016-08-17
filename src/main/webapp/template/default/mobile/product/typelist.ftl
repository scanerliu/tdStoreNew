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
    <title>分类列表</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/common.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/main.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/index.css">
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script> 
    <script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/touch/js/index.js"></script>
</head>

<script>
    window.onload=function(){
        left_right2();
        cleft_height2();
    }
</script>

<body class="body_bg">

<!-- header_top -->
<div class="top_heater" id="top_heater">
    <a href="javascript:history.go(-1);" title="返回" class="hleft hback"></a>
    <span>分类列表</span>
</div>
<!-- header_top end -->

<!-- Center Start -->
<section class="container">
    <div class="classify" id="classify">
        <section class="cla_left fl" id="cla_left">
            <aside>
            	<#if typeList?? && typeList?size gt 0>
            	<#list typeList as type>
                <a href="javascript:;" title="${type.name!''}" <#if type_index==0>class="active"</#if>>${type.name!''}</a>
                </#list>
                </#if>
            </aside>
        </section>
        <section class="cla_right2 fr" id="cla_right">
            <#if typeList?? && typeList?size gt 0>
        	<#list typeList as type>
	            <ul <#if type_index==0>class="active"</#if>>
        			<#if type.subList??>
        			<#list type.subList as stype>
		                <li <#if stype_index==0>class="active"</#if>>
		                    <menu>${stype.name!''}</menu>
		                    <aside class="as2">
		                    	<#if stype.subList??>
		                    	<#list stype.subList as etype>
		                        <a href="${app.basePath}/mobile/product/list/${etype.id?c}" title="${etype.name!''}" class="active">
		                            <img src="<#if etype.imageUrl!="">${etype.imageUrl!''}<#else>${app.basePath}/static/default/images/noimg.jpg</#if>" alt="${etype.name!''}">
		                            <p>${etype.name!''}</p>
		                        </a>
		                        </#list>
		                        </#if>
		                    </aside>
		                </li>
		         	</#list>
		         	</#if>
	             </ul>
		    </#list>
		   	</#if>
		   	
            
        </section>
    </div>
</section>
<!-- Center End -->
</body>
</html>