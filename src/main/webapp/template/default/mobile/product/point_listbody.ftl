<#import "/common/app.ftl" as app>
<#if pointList?? && pointList?size gt 0>
<section class="sec2" id="results">
            
	<#list pointList as item>
	<a href="${app.basePath}/mobile/product/item${item.id?c}" title="${item.name!''}" class="">
        <img src="${app.basePath}${item.imageUrl!''}" alt="${item.name!''}">
        <p class="p1">${item.name!''}</p>
        <p class="p2">
            <label class="lab1">¥<#if item.price??>${item.price?string('0.00')}</#if></label>
        </p>
    </a>
	</#list>
	
    </section>
	<#else>
	<div style=" background:#f2f2f2;padding-bottom:0;display:inline-block;width:100%; text-align:center;padding-top:3rem;">
                		<span style="background:#f2f2f2;">暂无商品</span>
                	</div>
</#if>

<script type="text/javascript">
  $(function(){
   	 var pageno = ${sc.pageNo+1};
   	 var kind = ${sc.kind};
   	 $("#pageNo").val(pageno);
   	 $("#kind").val(kind);
   	<#if sc.hasNextPage?? && sc.hasNextPage==true>
   		$(window).scroll(scrollHandler);
   	</#if>
  })
</script>
