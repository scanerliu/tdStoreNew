<#import "/common/app.ftl" as app>
<#if orderList??>
	<#list orderList as order>
	<section>
	    <div class="top">
	      <p class="p1">订单号：<span>${order.orderNo!''}</span></p>
	      <p class="p2">交易状态：<span>
	      <#if order.payStatus==1 && order.shipmentStatus==2>
	      
	      <#else if >
	      </#if>
	      </span></p>
	    </div>
	    <#if order.skuList??>
	    <#list order.skuList as sku>
	    <div class="middle">
	      <img src="${app.basePath}${sku.productImage!''}" alt="商品图片"/>
	      <div class="right-content">
	        <h3>${sku.productName!''}</h3>
	        <div>
	        	<#if sku.specialList??>
              	<#list sku.specialList as special>
              	<span><label for="">${special.sname!''}：</label><font>${special.soption!''}</font></span>
                </#list>
                </#if>
	        </div>
	        <p>
	          <label class="lab1">￥${sku.price!'0'}</label>
	          <label class="lab2">x<span>${sku.quantity!'0'}</span></label>
	        </p>
	      </div>
	    </div>
	    </#list>
	    </#if>
	    <div class="bottom">
	      	共 <span>${order.itemNum!'0'}</span> 件商品  合计<strong class="price">￥<span>${order.totalAmount!'0'}</span></strong>（含运费：<strong>￥<span>${order.postage!'0'}</span></strong>）
	    </div>
	    <div class="btn-group">
	      <a href="javascript:;" title="">立即付款</a>
	      <a href="订单详情.html" title="">查看订单</a>
	      <a class="active" href="我的评价.html" title="">去评价</a>
	    </div>
	  </section>
	</#list>
</#if>

<script type="text/javascript">
  $(function(){
   	 var pageno = ${sc.pageNo+1};
   	 var fliterType = ${sc.fliterType};
   	 $("#pageNo").val(pageno);
   	 $("#fliterType").val(fliterType);
   	<#if sc.hasNextPage?? && sc.hasNextPage==true>
   		$(window).scroll(scrollHandler);
   	</#if>
  })
</script>
