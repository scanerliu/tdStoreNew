<#import "/common/app.ftl" as app>
<li class="li_title">
    <span class="sp5 fl">时间</span>
    <span class="sp5 fl">金额(元)</span>
    <span class="sp4 fl">银行卡号</span>
    <span class="sp5 fl">状态</span>
    <span class="fl">说明</span>
</li>
<#if logList?? && logList?size gt 0>
	<#list logList as item>
		<li>
			<span class="sp5 fl"><#if item.createTime??>${item.createTime?string('yyyy-MM-dd')}</#if></span>
            <span class="sp5 fl">${item.amount!'0'}</span>
            <span class="sp4 fl">${item.cardno!''}</span>
            <span class="sp5 fl">${item.statusStr!''}</span>
            <span class="fl" title="${item.remark!''}">${item.remark!''}</span>
        </li>
	</#list>
<#else>
<li>
  <span class="fl">&nbsp;</span>
  <span class="fl">暂无相关记录</span>
  <span class="sp3 fl">&nbsp;</span>
</li>
</#if>
<input type="hidden" value="${sc.status!''}" name="status">
<#if logList?? && logList?size gt 0>
<div class="goods-page-nums mt20 w100 txtr">
	<#assign pageId="DrawApplys" />
	<#include "../common/commonpostpage.ftl" />
</div>
</#if>