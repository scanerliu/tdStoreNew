<#import "/common/app.ftl" as app>
<#if productList??>
<#list productList as item>
<a href="${app.basePath}/mobile/product/item${item.id!'0'}" title="${item.name!''}" class="">
    <img src="${app.basePath}${item.imageUrl!''}" alt="${item.name!''}">
    <p class="p1">${item.name!''}</p>
    <p class="p2">
        <label class="lab1">¥<#if item.price??>${item.price?string('0.00')}</#if></label>
        <!--<label class="lab2">￥188.00</label>-->
    </p>
</a>
</#list>
</#if>
<script type="text/javascript">
  $(function(){
   	 var pageno = ${sc.pageNo+1};
   	 var fliterType = ${sc.orderby};
   	 $("#pageNo").val(pageno);
   	 $("#fliterType").val(fliterType);
   	<#if sc.hasNextPage?? && sc.hasNextPage==true>
   		$(window).scroll(scrollHandler);
   	</#if>
  })
</script>