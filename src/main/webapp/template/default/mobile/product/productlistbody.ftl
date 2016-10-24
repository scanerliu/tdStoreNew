<#import "/common/app.ftl" as app>
<!-- header_top -->
<div class="top_heater">
    <a href="javascript:history.go(-1);" title="返回" class="hleft hback"></a>
    <span>产品列表</span>
</div>

<!-- Center Start -->
<!-- <section class="container"> -->
    <div class="three">
    	<a href="javascript:;" title="综合" <#if sc?? && sc.orderby==1>onclick="searchList(2)" class="down"<#elseif sc?? && sc.orderby==2>onclick="searchList(1)" class="up"<#else>onclick="searchList(1)"</#if>>综合</a>
    	<a href="javascript:;" title="销量" <#if sc?? && sc.orderby==3>onclick="searchList(4)" class="down"<#elseif sc?? && sc.orderby==4>onclick="searchList(3)" class="up"<#else>onclick="searchList(3)"</#if>>销量</a>
    	<a href="javascript:;" title="价格" <#if sc?? && sc.orderby==5>onclick="searchList(6)" class="down"<#elseif sc?? && sc.orderby==6>onclick="searchList(5)" class="up"<#else>onclick="searchList(5)"</#if>>价格</a>
    </div>
 
    <!-- 热销推荐 -->
    <div class="hot">
        <section class="sec2" id="product_more">
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
        </section>
    </div>
    <!-- 热销推荐-结束 -->
<!-- </section> -->
<!-- Center end -->
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
