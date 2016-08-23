<#import "/common/app.ftl" as app> 
<#if messageList??>
	<#list messageList as orderMessage>
		<li>
    		<a href="${app.basePath}/mobile/user/messageDetail?messageId=${orderMessage.id?c}" title="${orderMessage.title!''}">
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
<script>
    $(function () {
    	 drge('.infor_match li');
         var pageno = ${sc.pageNo+1};
	   	 var fliterType = ${sc.fliterType};
	   	 $("#pageNo").val(pageno);
	   	 $("#fliterType").val(fliterType);
	   	<#if sc.hasNextPage?? && sc.hasNextPage==true>
	   		$(window).scroll(scrollHandler);
	   	</#if>
    });
</script>
