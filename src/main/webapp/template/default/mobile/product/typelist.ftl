<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
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
    
    <script>
		
Rich.pageSize();
$(document).ready(function(){
	
        left_right2();
        cleft_height2();
});
 window.onload=function(){
    
        
 }
		
</script>
</head>



<body class="body_bg">

<!-- header_top -->
<div class="top_heater" id="top_heater">
    <a href="javascript:history.go(-1);" title="返回" class="hleft hback"></a>
    <span>分类列表</span>
</div>
<!-- header_top end -->
<script>

$(function(){

	<#if typeList?? && typeList?size gt 0>
	searchSubtype(${typeList[0].id});
	</#if>
})
	
function searchSubtype(typeId){
	var url = basePath + "/mobile/productType/search";
	var loadData = {"typeId":typeId};
	
	$("#subType").load(url,loadData);
}
</script>
<!-- Center Start -->
<section class="container">
    <div class="classify" id="classify">
        <section class="cla_left fl" id="cla_left">
            <aside>
            	<#if typeList?? && typeList?size gt 0>
            	<#list typeList as type>
                <a href="javascript:searchSubtype(${type.id});" title="${type.name!''}" <#if type_index==0>class="active"</#if>>${type.name!''}</a>
                </#list>
                </#if>
            </aside>
        </section>
        <div id="subType">
            <#include "/mobile/product/sub_type_list.ftl">
        </div>
    </div>
</section>
<!-- Center End -->
</body>
</html>