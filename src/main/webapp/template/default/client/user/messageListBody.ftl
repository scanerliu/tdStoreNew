<#import "/common/app.ftl" as app> 
<ul class="infolist" id="five_ul">
<#if messageList?? && messageList?size gt 0>
	<#list messageList as orderMessage>
		<li>
    		<a href="${app.basePath}/user/messageDetail?messageId=${orderMessage.id?c}" target="_blank" title="${orderMessage.title!''}">
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
<#else>
<li>
  <span class="fl">&nbsp;</span>
  <span class="fl">暂无相关记录</span>
  <span class="sp3 fl">&nbsp;</span>
</li>
</#if>
</ul>
<input type="hidden" value="${sc.msgType!''}" name="msgType">
<input type="hidden" name="status" value="${sc.status!''}"/>
<#if messageList?? && messageList?size gt 0>
<div class="goods-page-nums mt20 w100 txtr">
	<#assign pageId="Messages" />
	<#include "../common/commonpostpage.ftl" />
</div>
</#if>
