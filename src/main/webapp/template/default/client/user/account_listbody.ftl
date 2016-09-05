<#import "/common/app.ftl" as app>
<li class="li_title">
    <span class="fl">时间</span>
    <span class="fl">明细</span>
    <span class="fl">金额(元)</span>
</li>
<#if logList?? && logList?size gt 0>
	<#list logList as item>
		<li>
			<span class="fl"><#if item.createTime??>${item.createTime?string('yyyy-MM-dd')}</#if></span>
            <span class="fl">${item.typeStr!''}</span>
            <span class="sp3 fl <#if item.upamount?? && item.upamount gte 0 >sum<#else>dum</#if>"><#if item.upamount?? && item.upamount gt 0>+</#if>${item.upamount!'0'}</span>
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