<#import "/common/app.ftl" as app>
<#if logList?? && logList?size gt 0>
	<#list logList as item>
		<li>
			<a href="${app.basePath}/mobile/user/drawapplydetail?id=${item.id!'0'}">
			<div class="left"><#if item.createTime??>${item.createTime?string('yyyy-MM-dd')}</#if></div>
            <div class="middle">${item.amount!'0'}</div>
            <div class="right">${item.statusStr!''}</div>
            </a>
        </li>
	</#list>
<#else>
<li>
  <div class="left">&nbsp;</div>
  <div class="middle">暂无相关记录</div>
  <div class="right">&nbsp;</div>
</li>
</#if>
<input type="hidden" value="${sc.status!''}" name="status">
<script type="text/javascript">
  $(function(){
   	 var pageno = ${sc.pageNo+1};
   	 $("#pageNo").val(pageno);
   	<#if sc.hasNextPage?? && sc.hasNextPage==true>
   		$(window).scroll(scrollDrawApplyHandler);
   	</#if>
  })
</script>