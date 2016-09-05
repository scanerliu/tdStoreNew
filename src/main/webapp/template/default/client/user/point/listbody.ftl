<#import "/common/app.ftl" as app>
<ul class="active">
<li class="li_title">
    <span class="fl">时间</span>
    <span class="fl">明细</span>
    <span class="fl">积分数(个)</span>
</li>
<#if logList?? && logList?size gt 0>
	<#list logList as item>
		<li>
          <span class="fl"><#if item.createTime??>${item.createTime?string('yyyy-MM-dd')}</#if></span>
          <span class="fl">${item.typeStr!''}</span>
          <span class="sp3 fl <#if item.integral?? && item.integral gte 0 >income<#else>pay</#if>"><#if item.integral?? && item.integral gt 0>+</#if>${item.integral!'0'}</span>
        </li>
	</#list>
<#else>
<li>
  <span class="fl">&nbsp;</span>
  <span class="fl">暂无相关记录</span>
  <span class="sp3 fl">&nbsp;</span>
</li>
</#if>
</ul>
<input type="hidden" value="${sc.incomeType!''}" name="incomeType">
<#if logList?? && logList?size gt 0>
<div class="goods-page-nums mt20 w100 txtr">
	<#assign pageId="PointLogs" />
	<#include "../../common/commonpostpage.ftl" />
</div>
</#if>