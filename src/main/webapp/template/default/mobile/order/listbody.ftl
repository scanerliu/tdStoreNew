<#import "/common/app.ftl" as app>
<#if orderList??>
	<#list orderList as order>
	<section>
	    <div class="top">
	      <p class="p1">订单号：<span>${order.orderNo!''}</span></p>
	      <p class="p2">交易状态：<span>
	      			<#if order.orderStatus==1>
						待支付
					<#elseif order.orderStatus==2>
			      		待发货
			    	<#elseif order.orderStatus==3>
			      		待收货
			    	<#elseif order.orderStatus==4>
			      		交易完成
			      	<#elseif order.orderStatus==5>
			      		申请退款
			      	<#elseif order.orderStatus==6>
			      		交易完成
			    	</#if>
	      </span></p>
	    </div>
	    <#if order?? && order.productList?? && (order.productList?size > 0)>
	    <#list order.productList as product>
    	<div class="middle">
    		<#if product.attachments??>
    		<#list product.attachments as atta>
    		<#if atta_index==0>
    		<img src="${app.basePath}${atta!''}" alt="商品图片"/>
    		</#if>
    		</#list>
    		<#else>
    		<img src="${app.basePath}" alt="没有图片"/>
    		</#if>
    		<div class="right-content">
	        <h3>${product.title!''} ${product.getItemTypeStr()!''}</h3>
	        <p>
	          <label class="lab1">￥${product.itemPrice!'0'}</label>
	          <label class="lab2">x<span>${product.quantity!'0'}</span></label>
	        </p>
			</div>
    	</div>
    	</#list>
    	</#if>
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
	      <#if order.payStatus==2><a href="${app.basePath}/mobile/order/dopay${order.orderId!'0'}" class="active" title="">立即付款</a></#if>
	      <#if order.payStatus==1&& order.shipmentStatus==1><a class="active" href="javascript:;" onclick="receiptOrder(${order.orderId!'0'})" title="">确认收货</a></#if>
	      <#if order.orderStatus==4 && order.commented==false><a class="active" href="${app.basePath}/mobile/user/comment/${order.orderId!'0'}" title="去评价">去评价</a></#if>
	      <a href="${app.basePath}/mobile/order/detail${order.orderId!'0'}" title="">查看订单</a>
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
