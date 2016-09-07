<#import "/common/app.ftl" as app>
<ul class="box3" id="five_ul">
    <#if orderList?? && orderList?size gt 0 >
	<#list orderList as order>
    <li>
        <div class="order_num">
            <label class="lab1"><#if order.createTime??>${order.createTime?string('yyyy-MM-dd')}</#if></label>
            <label class="lab2">订单号： ${order.orderNo!''}</label>
        </div>
        <div class="goodsorder_detail">
            <div class="fl goodsorder_detail_left w590">
            	<#if order?? && order.productList?? && (order.productList?size > 0)>
			    <#list order.productList as product>
		    	<dl class="fl">
		    		<#if product.attachments??>
		    		<#list product.attachments as atta>
		    		<#if atta_index==0>
		    		<dt class="fl"><img src="${app.basePath}${atta!''}" alt="商品图片"/></dt>
		    		</#if>
		    		</#list>
		    		<#else>
		    		<dt class="fl"><img src="${app.basePath}" alt="商品图片"/></dt>
		    		</#if>
		    		<dd class="dd1 w240 fl">
                        <p class="p1">${product.title!''} ${product.getItemTypeStr()!''}</p>
                        <p class="p2">
                        </p>
                    </dd>
                    <dd class="dd2 w80 txtc fl">${product.itemPrice!'0'}</dd>
                    <dd class="dd3 w80 txtc fl">${product.quantity!'0'}</dd>
                    <dd class="dd4 w90 txtc fl">
                        <a href="${app.basePath}/order/complaint${order.orderId!'0'}" title="" class="a2">投诉卖家</a>
                    </dd>
		    	</dl>
		    	</#list>
		    	</#if>
			    <#if order.skuList??>
			    <#list order.skuList as sku>
			    <dl class="fl">
                    <dt class="fl"><a href="${app.basePath}/product/item${sku.productId!''}" title="商品详情"><img src="${app.basePath}${sku.productImage!''}" alt="商品图片"/></a></dt>
                    <dd class="dd1 w240 fl">
                        <p class="p1">${sku.productName!''}</p>
                        <p class="p2">
                        	<#if sku.specialList??>
			              	<#list sku.specialList as special>
			              	<label for="">${special.sname!''}：${special.soption!''}</label>
			                </#list>
			                </#if>
                        </p>
                    </dd>
                    <dd class="dd2 w80 txtc fl">${sku.price!'0'}</dd>
                    <dd class="dd3 w80 txtc fl">${sku.quantity!'0'}</dd>
                    <dd class="dd4 w90 txtc fl">
                        <#if order.orderStatus!=3 ><a href="${app.basePath}/order/applyrefund${order.orderId!'0'}?skuId=${sku.orderSkuId?c}" target="_blank" class="a1">申请退货</a></#if>
                        <a href="${app.basePath}/order/complaint${order.orderId!'0'}" title="" class="a2" target="_blank">投诉卖家</a>
                    </dd>
                </dl>
			    </#list>
			    </#if>
            </div>
            <div class="div5 w118 fl">
                <p class="p1">${order.totalAmount!'0'}</p>
                <p class="p2 color_black">含运费（<span>${order.postage!'0'}</span>）</p>
            </div>
            <div class="div6 w108 fl">
                <p class="p1">
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
			      	<#elseif order.orderStatus==-1>
			      		已取消
			    	</#if>
                </p>
                <p><a href="${app.basePath}/order/detail${order.orderId!'0'}" title="查看详情" target="_blank">订单详情</a></p>
            </div>
            <div class="div7 w89 fl">
            <#if order.orderStatus==1><P><a href="${app.basePath}/order/dopay${order.orderId!'0'}" class="a_sure" title="去付款">去付款</a></P></#if>
            <#if order.orderType==1||order.orderStatus==3>
	      		<#if order.orderStatus==1||order.orderStatus==2><a class="active" href="${app.basePath}/order/cancelorder${order.orderId!'0'}"  title="取消订单">取消订单</a></#if>
		    <#elseif order.orderType==2>
		      	<#if order.orderStatus==1><a class="active" href="${app.basePath}/order/cancelorder${order.orderId!'0'}"  title="取消订单">取消订单</a></#if>
		    </#if>
	        <#if order.orderStatus==3><P><a class="a_sure" href="javascript:;" onclick="receiptOrder(${order.orderId!'0'})" title="确认收货">确认收货</a></P></#if>
	        <#if order.orderStatus==4 && order.commented==false><P><a class="a_sure" href="${app.basePath}/user/comment/${order.orderId!'0'}" title="去评价">去评价</a></P></#if>
            </div>
        </div>
    </li>
    </#list>
    <#else>
    	<li>
    		暂无相关订单！
    	</li>
	</#if>
</ul>
<script>
    function ofive() {
        var oUl = document.getElementById('five_ul');
        var aLi = oUl.getElementsByTagName('li');
        for (i = 0; i < aLi.length; i++) {
            var aDiv = aLi[i].getElementsByTagName('div');
            // 寻找商品个数
            var aDl = aLi[i].getElementsByTagName('dl');
            // 右边高度=左边高度*商品数量
            aDiv[1].style.height = aDiv[2].offsetHeight + 'px';
            // 多个商品border-top设置
            for (j = 0; j < aDl.length; j++) {
                if (j >= 1) {
                    aDl[j].style.borderTop = "1px solid #fff7ec";
                }
            }
        }
    }
    ofive();
</script>
<input type="hidden" value="${sc.fliterType!''}" name="fliterType">
<input type="hidden" value="${sc.keyword!''}" name="keyword">
<div class="goods-page-nums mt20 w100 txtr">
<#assign pageId="Orders" />
<#include "../common/commonpostpage.ftl" />
</div>
<script type="text/javascript">
  $(function(){
   	
  })
</script>
