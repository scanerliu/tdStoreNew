<#import "/common/app.ftl" as app>
<#if collectList?? && collectList?size gt 0>
<#list collectList as col>
<li>
	  <a href="<#if col.product??>${app.basePath}/product/item${col.itemId?c}<#else>javascript:;</#if>" title="" class="goods_a">
	    <img src="<#if col.product??>${app.basePath}${col.product.imageUrl!''}</#if>" alt="<#if col.product??>${col.product.name!''}</#if>">
	  </a>
	  <div class="l_addshop" style="display: none;">
	    <a href="javascript:;" title="" class="a2 fr" onclick="addCollect(${col.itemId?c});">删除</a>
	  </div>
	  <p class="p1"><#if col.product??>${col.product.name!''}</#if></p>
	  <p class="p2">￥<#if col.product?? && col.product.price??>${col.product.price?string('0.00')}</#if></p>
</li>
</#list>
<div class="goods-page-nums mt20 w100 txtr">
<#assign pageId="Orders" />
<#include "../../common/commonpostpage.ftl" />
</div>
<script type="text/javascript">
  $(function(){
   		lgoods();
  })
</script>
<#else>
<li>
	  <p class="p1">暂无收藏</p>
</li>
</#if>
