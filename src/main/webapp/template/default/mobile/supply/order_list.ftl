<#import "/common/app.ftl" as app> 
<#include "/common/common.ftl" />
  <!-- header_top end -->
  <section class="container my-order">

    <!-- top-nav -->
    <ul class="tab_title">
      <li class="active"><a href="javascript:;" title="">全部订单</a></li>
      <li><a href="javascript:;" title="">待退货</a></li>
      <li><a href="javascript:;" title="">待发货<#--<span class="have-new"></span>--></a></li>
    </ul>

    <ol>
      <!-- 全部订单 -->
      <li></li>
      <!-- 待付款 -->
      <li>
        <article>
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
				      		同意
				      <#elseif ship.status==3>
				      		不同意
				      <#elseif ship.status==4>
				      		完成
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
				      </#if>
	            </div>
	          </section>
	         </#list>
	         </#if>
        </article>
      </li>
      <li>
        <article>
        <#if order_list??>
		<#list order_list as order>
          <section>
            <div class="top">
              <p class="p1">订单号：<span>${order.orderNo!''}</span></p>
              <p class="p2">交易状态：<span> 
              <#if order.payStatus==1 && order.shipmentStatus==2>
	      		待发货
		      <#elseif order.payStatus==1 && order.shipmentStatus==1>
		      		待收货
		      <#elseif order.payStatus==2>
		      		待支付
		      </#if>
		      </span></p>
            </div>
            <#if order.productList?? && order.productList?size gt 0 >
	            <div class="middle">
	    		<#list order.productList as product>
	    		<div class="right-content">
		        <h3>${product.title!''} ${product.getItemTypeStr()!''}</h3>
		        <p>
		          <label class="lab1">￥${product.itemPrice!'0'}</label>
		          <label class="lab2">x<span>${product.quantity!'0'}</span></label>
		        </p>
	    		<#if product.attachments??>
	    			<p>
					<#list product.attachments as atta>
	        			<a href="${app.basePath}${atta}" target="_blank"><img src="${app.basePath}${atta}" alt="图片" width="60"/></a>
	        		</#list>            				
					</p>
				</#if>
				</div>
	    		</#list>
	    	</div>
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
            	
              <a href="${app.basePath}/mobile/supply/order/detail${order.orderId!'0'}" title="">查看订单</a>
               <#if order.payStatus==1 && order.shipmentStatus==2>
              	<a class="active" href="${app.basePath}/mobile/supply/shipment?orderId=${order.orderId!''}" title="立即发货">立即发货</a>
              	</#if>
            </div>
          </section>
         </#list>
         </#if>
        </article>
      </li>
      <!-- 待发货 -->
      
    </ol>
    <script type="text/javascript">
      $(function(){
        function tab(tabTitle,tabList){
          $(tabTitle).on('click','a',function(){
            var $self = $(this);//当前a标签
            var $active = $self.closest('li');//当前点击li
            var index = $active.prevAll('li').length;//当前索引

          $active.addClass('active').siblings('li').removeClass('active');
            $(tabList).find('>li')[index==0?'show':'hide']().eq(index).show();
          });
        };
        tab('.my-order ul','.my-order ol');
      })
    </script>


  </section>

