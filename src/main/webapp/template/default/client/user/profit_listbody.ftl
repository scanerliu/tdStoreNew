<#import "/common/app.ftl" as app>
<li class="li_title">
    <span class="fl">订单号</span>
    <span class="fl">买家名称</span>
    <span class="fl">总金额(元)</span>
    <span class="fl">我的收益(元)</span>
</li>
<#if logList?? && logList?size gt 0>
	<#list logList as item>
	<li>
		<span class="fl">${item.profit.orderNo!''}</span>
	    <span class="fl">${item.profit.buyUserName!''}</span>
	    <span class="fl">${item.profit.totalAmount!''}</span>
	    <span class="fl">${item.upamount!'0'}</span>
	</li>
	</#list>
<#else>
<li>
  <span class="fl">&nbsp;</span>
  <span class="fl">暂无相关记录</span>
  <span class="sp3 fl">&nbsp;</span>
</li>
</#if>
<input type="hidden" value="${sc.incomeType!''}" name="incomeType">
<#if logList?? && logList?size gt 0>
<div class="goods-page-nums mt20 w100 txtr">
	<#assign pageId="AccountLogs" />
	<#include "../common/commonpostpage.ftl" />
</div>
</#if>
