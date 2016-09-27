<#import "/common/app.ftl" as app> 
  <#if shipList??>
	<#list shipList as ship>
      <section>
        <div class="top">
          <p class="p1">订单号：<span><#if ship.order??>${ship.order.orderNo!''}</#if></span></p>
          <p class="p2">退货状态：<span> 
          <#if ship.status??>
  			  <#if ship.status==1>
      			新申请
		      <#elseif ship.status==2>
		      		已同意
		      <#elseif ship.status==3>
		      		不同意
		      <#elseif ship.status==4>
		      		等待客户寄回商品
	      	<#elseif ship.status==5>
	      		已完成
		      </#if>
	      </#if>
	      </span></p>
        </div>
        <#assign quantity = 0>
    	<#if ship.itemList??>
		    <#list ship.itemList as item>
		    <#assign quantity = item.quantity>
		    <div class="middle">
		      <img src="<#if item.itemOrderSku??>${app.basePath}${item.itemOrderSku.productImage!''}</#if>" alt="<#if item.itemOrderSku??>${item.itemOrderSku.productName!''}</#if>"/>
		      <div class="right-content">
		        <h3><#if item.itemOrderSku??>${item.itemOrderSku.productName!''}</#if></h3>
		        <div>
		        	<#if item.itemOrderSku.specialList??>
	              	<#list item.itemOrderSku.specialList as special>
	              	<span><label for="">${special.sname!''}：</label><font>${special.soption!''}</font></span>
	                </#list>
	                </#if>
		        </div>
		      </div>
		    </div>
		    </#list>
		    </#if>
        <div class="bottom">
          共 <span>${quantity!''}</span> 件商品  合计<strong class="price">￥<span>${ship.returnAmount!''}</span></strong>
        </div>
        <div class="btn-group">
        	
          <a href="${app.basePath}/mobile/supply/return/detail${ship.id}" title="查看订单">查看订单</a>
            <#if ship.status??>
      			  <#if ship.status==1>
	              	<a href="${app.basePath}/mobile/supply/refuse?shipId=${ship.id}"  title="拒绝退货">拒绝退货</a>
      				<a class="active" href="javascript:;" onclick="agreeReturn(${ship.id})" title="同意退货">同意退货</a>
			      </#if>
			      <#if ship.status==4><a href="javascript:;" class="active" onclick="completeReturn(${ship.id!''})">完成退货</a></#if>
		      </#if>
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
