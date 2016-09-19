<#import "/common/app.ftl" as app>
<ul class="box3" id="five_ul">
    <#if shipList??>
	<#list shipList as ship>
    <li>
        <div class="order_num">
            <label class="lab1"><#if ship.createTime??>${ship.createTime?string('yyyy-MM-dd')}</#if></label>
            <label class="lab2">订单号： ${ship.order.orderNo!''}</label>
        </div>
        <div class="goodsorder_detail">
            <div class="fl goodsorder_detail_left w500">
			    <#if ship.itemList??>
			    <#list ship.itemList as item>
			    <dl class="fl">
                    <dt class="fl"><img src="${app.basePath}${item.itemOrderSku.productImage!''}" alt="商品图片"/></dt>
                    <dd class="dd1 w240 fl">
                        <p class="p1">${item.itemOrderSku.productName!''}</p>
                        <p class="p2">
                        	<#if item.itemOrderSku.specialList??>
			              	<#list item.itemOrderSku.specialList as special>
			              	<label for="">${special.sname!''}：${special.soption!''}</label>
			                </#list>
			                </#if>
                        </p>
                    </dd>
                    <dd class="dd2 w80 txtc fl">${item.itemOrderSku.price!'0'}</dd>
                    <dd class="dd3 w80 txtc fl">${item.quantity!'0'}</dd>
                </dl>
			    </#list>
			    </#if>
            </div>
            <div class="div5 w118 fl">
                <p class="p1">${ship.returnAmount!'0'}</p>
            </div>
            <div class="div6 w108 fl">
                <p class="p1">
                	<#if ship.status==1>
				      		待处理
				      <#elseif ship.status==2>
				      		同意退款
				      <#elseif ship.status==3>
				      		不同意退款
				      <#elseif ship.status==4>
				      		待退款
				      <#elseif ship.status==5>
				      		完成退款
				      </#if>
                </p>
                <p><a href="${app.basePath}/order/refunddetail${ship.id!'0'}" title="查看详情" target="_blank">退货详情</a></p>
            </div>
            <div class="div7 w89 fl">
	        <#if ship.status==2 && ship.cargoStatus==1><P><a href="${app.basePath}/order/refundtract${ship.id!'0'}" class="a_sure" title="录入物流单号">录入物流单号</a></P></#if>
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
<input type="hidden" value="${sc.status!''}" name="status">
<div class="goods-page-nums mt20 w100 txtr">
<#assign pageId="Refunds" />
<#include "../common/commonpostpage.ftl" />
</div>
