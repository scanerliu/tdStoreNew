<#import "/common/app.ftl" as app> 
<#if artList??>
	<#list artList as art>
		<li>
    		<a href="${app.basePath}/mobile/article/item${art.aid?c}" title="${art.title!''}">
        		<section class="sec1 fl">
          		  <p class="p1">
           	   	  	<span>${art.updateTime?string('yyyy-MM-dd HH:mm:ss')}</span>
            	  </p>
            	  <p class="p2">${art.title!''}</p>
       		 	</section>
   	 		</a>
	 	</li>
	</#list>
</#if>
<script>
    $(function () {
         var pageno = ${sc.pageNo+1};
	   	 var fliterType = ${sc.cid};
	   	 $("#pageNo").val(pageno);
	   	 $("#cid").val(fliterType);
	   	<#if sc.hasNextPage?? && sc.hasNextPage==true>
	   		$(window).scroll(scrollHandler);
	   	</#if>
    });
</script>
