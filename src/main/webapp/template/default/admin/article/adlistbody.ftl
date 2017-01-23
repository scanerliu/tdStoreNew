<#import "/common/app.ftl" as app>
<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
	<thead>
		<tr>
			<th width="4%"><input type="checkbox" class="J_checkall" id="J_checkall" name="checkall"></th>
			<th width="15%">ID</th>
			<th align="center">广告标题</th>
			<th align="center">广告位</th>
			<th align="center" width="15%">图片</th>
			<th align="center" width="15%">链接地址</th>
			<th align="center">开始时间</th>
			<th align="center">结束时间</th>
			<th align="center">状态</th>
			<th align="center">地区</th>
			<th align="center">排序值</th>
			<th >操作</th>
		</tr>
	</thead>
<tbody>
<#if adlist?? && adlist?size gt 0>
		<#list adlist as ad>
			<tr>
		        <td align="center">
		        	<input type="checkbox" value="${ad.id?c}" name="subbox" class="J_checkitem">
		        </td>
		        <td align="center">${ad.id?c}</td>
		        <td>${ad.title!''}</td>
		        <td align="center"><#if ad.adsense??>${ad.adsense.name!''}</#if></td>
		        <td align="center"><img width='100' height='60' src='${ad.imageUrl!''}'/></td>
		        <td align="center">${ad.linkUrl!''}</td>
		        <td align="center"><#if ad.createTime??>${ad.createTime?string('yyyy-MM-dd')}</#if></td>
		        <td align="center"><#if ad.endTime??>${ad.endTime?string('yyyy-MM-dd')}</#if></td>
		        <td align="center"><#if ad.status==1>正常<#else>屏蔽</#if></td>
		        <td align="center"><#if ad.region??>${ad.region.name!''}</#if></td>
		        <td align="center">${ad.sort!''}</td>
		        <td align="center">
		            <a class="J_confirmurl" href="javascript:;" onclick="delAd(${ad.id?c})">删除</a>
		           |<a class="J_confirmurl" href="javascript:;" onclick="editAd(${ad.id?c})">编辑</a>
		        </td>
	    	</tr>
	    </#list>
	</#if>
</tbody>
</table>
</div>
<div class="btn_wrap_fixed">
<input type="hidden" name="keyword" value="${sc.keyword!''}"/>
<input type="hidden" name="adsId" value="${sc.adsId!''}"/>
</div>
</div>
<div class="btn_wrap_fixed">
<#assign pageId="Advertisement" />
<#include "/admin/common/common_postpage.ftl" />
</div>
<script>
$(function(){
    $("#J_checkall").click(function() {
        $('input[name="subbox"]').prop("checked",this.checked); 
    });
    var $subBox = $("input[name='subbox']");
    $subBox.click(function(){
        $("#J_checkall").prop("checked",$subBox.length == $("input[name='subbox']:checked").length ? true : false);
    });
});
</script>
