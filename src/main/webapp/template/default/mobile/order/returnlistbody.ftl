<#import "/common/app.ftl" as app>
<#if shipList??>
	<#list shipList as ship>
	<section>
	    <div class="top">
	      <p class="p1">订单号：<span>${ship.order.orderNo!''}</span></p>
	      <p class="p2">申请状态：<span>
	      <#if ship.status==1>
	      		待处理
	      <#elseif ship.status==2>
	      		同意退款
	      <#elseif ship.status==3>
	      		不同意退款
	      <#elseif ship.status==4>
	      		待寄回商品
	      <#elseif ship.status==5>
	      		完成退款
	      </#if>
	      </span></p>
	    </div>
	    <#if ship.itemList??>
		<#list ship.itemList as item>
	    <div class="middle">
	      <img src="${app.basePath}${item.itemOrderSku.productImage!''}" alt="商品图片"/>
	      <div class="right-content">
	        <h3>${item.itemOrderSku.productName!''}</h3>
	        <div>
	        	<#if item.itemOrderSku.specialList??>
              	<#list item.itemOrderSku.specialList as special>
              	<span><label for="">${special.sname!''}：</label><font>${special.soption!''}</font></span>
                </#list>
                </#if>
	        </div>
	        <p>
	          <label class="lab1">￥${item.itemOrderSku.price!'0'}</label>
	          <label class="lab2"><span>${item.quantity!'0'}</span></label>
	        </p>
	      </div>
	    </div>
	    </#list>
	    </#if>
	    <div class="bottom">
	      	订单交易金额 <span>￥${ship.order.payAmount!'0'}</span> 退款金额<strong class="price">￥<span>${ship.returnAmount!'0'}</span></strong>
	    </div>
	    <div class="btn-group">
	      <#if ship.status==2 && ship.cargoStatus==2><a href="${app.basePath}/mobile/order/refundtract${ship.id!'0'}" title="">录入物流单号</a></#if>
	    </div>
	  </section>
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
