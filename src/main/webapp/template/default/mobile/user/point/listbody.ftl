<#import "/common/app.ftl" as app>
<#if logList??>
	<#list logList as item>
		<li>
          <div class="left"><#if item.createTime??>${item.createTime?string('yyyy-MM-dd')}</#if></div>
          <div class="middle">${item.typeStr!''}</div>
          <div class="right <#if item.integral?? && item.integral gte 0 >income<#else>pay</#if>"><#if item.integral?? && item.integral gt 0>+</#if>${item.integral!'0'}</div>
        </li>
	</#list>
</#if>
<script type="text/javascript">
  $(function(){
   	 var pageno = ${sc.pageNo+1};
   	 $("#pageNo").val(pageno);
   	<#if sc.hasNextPage?? && sc.hasNextPage==true>
   		$(window).scroll(scrollHandler);
   	</#if>
  })
</script>