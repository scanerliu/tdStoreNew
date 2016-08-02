<#import "/common/app.ftl" as app>
<#if productList??>
	<#list productList as item>
		<a href="${app.basePath}/mobile/package/item${item.id!'0'}" title="" class="">
            <img src="${app.basePath}${item.imageUrl!''}" alt="${item.name!''}">
            <p class="p1">${item.name!''}</p>
            <p class="p2">
                <label class="lab1">¥700.00</label>
                <label class="lab2">￥<#if item.price??>${item.price?string('0.00')}</#if></label>
            </p>
        </a>
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