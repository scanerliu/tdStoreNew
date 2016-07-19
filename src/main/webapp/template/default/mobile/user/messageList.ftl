<#import "/common/app.ftl" as app> 
<#include "/common/common.ftl" />
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
    <title>消息列表</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/common.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/main.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/index.css">
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script> 
    <script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/touch/js/index.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/touch/js/ftt.js"></script>
</head>
<script>
    window.onload=function(){
        three2_hao();
    }
</script>

<body class="">

    <!-- header_top -->
    <div class="top_heater">
        <a href="${app.basePath}/mobile/user/center" title="返回个人中心" class="hleft hback"></a>
        <span>信息通知</span>
    </div>
    <!-- header_top end -->

    <!-- Center Start -->
    <section class="container">
        <div class="three2" id="three2">
            <a href="#" title="订单信息" <#if !active?? || (active?? && active=="activeOrderMessage")>class="active"</#if>>订单信息</a>
            <a href="#" title="系统信息" <#if active?? && active=="activeSystemMessage">class="active"</#if>>系统信息</a>
            <a href="#" title="门店申请" <#if active?? && active=="activeExperienceStoreMessage">class="active"</#if>>门店申请 </a>
        </div>
        <div id="three2_hao">
            <ul class="infor_match">
				<#if orderMessageList??>
					<#list orderMessageList as orderMessage>
                		<li>
                    		<a href="${app.basePath}/mobile/user/messageDetail?active=activeOrderMessage&messageId=${orderMessage.id?c}" title="${orderMessage.title!''}">
                        		<section class="sec1 fl">
                          		  <p class="p1">
                           	   	  	<span>${orderMessage.createTime?string('yyyy-MM-dd HH:mm:ss')}</span>
                            	  </p>
                            	  <p class="p2">${orderMessage.title!''}</p>
                       		 	</section>
                      		  	<menu class="fr dele"></menu>
                   	 		</a>
               		 	</li>
					</#list>
				</#if>
            </ul>
            <ul class="infor_match">
                <#if systemMessageList??>
					<#list systemMessageList as systemMessage>
                		<li>
                    		<a href="${app.basePath}/mobile/user/messageDetail?active=activeSystemMessage&messageId=${systemMessage.id?c}" title="${systemMessage.title!''}">
                        		<section class="sec1 fl">
                          		  <p class="p1">
                           	   	  	<span>${systemMessage.createTime?string('yyyy-MM-dd HH:mm:ss')}</span>
                            	  </p>
                            	  <p class="p2">${systemMessage.title!''}</p>
                       		 	</section>
                      		  	<menu class="fr dele"></menu>
                   	 		</a>
               		 	</li>
					</#list>
				</#if>
            </ul>
            <ul class="infor_match">
                <#if experienceStoreMessageList??>
					<#list experienceStoreMessageList as experienceStoreMessage>
                		<li>
                    		<a href="${app.basePath}/mobile/user/messageDetail?active=activeExperienceStoreMessage&messageId=${experienceStoreMessage.id?c}" title="${experienceStoreMessage.title!''}">
                        		<section class="sec1 fl">
                          		  <p class="p1">
                           	   	  	<span>${experienceStoreMessage.createTime?string('yyyy-MM-dd HH:mm:ss')}</span>
                            	  </p>
                            	  <p class="p2">${experienceStoreMessage.title!''}</p>
                       		 	</section>
                      		  	<menu class="fr dele"></menu>
                   	 		</a>
               		 	</li>
					</#list>
				</#if>
            </ul>
        </div>
    </section>
    <!-- Center End -->

    <script>
        $(function () {
            drge('.infor_match li');
        });
    </script>
</body>
</html>