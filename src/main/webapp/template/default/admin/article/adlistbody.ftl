<#import "/common/app.ftl" as app>
<link rel="stylesheet" href="${app.basePath}/static/js/easyui/easyui.css"/>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a data-height="190" data-width="450" data-id="add" data-title="添加广告" href="javascript:;" class="add fb J_showdialog" onclick="editAd(0)"><em>添加广告</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<div class="J_tablelist table_list">
	<div id="district">
		<!--
		<input type="button" value="批量删除" onclick="batchDelete()">&nbsp;&nbsp;&nbsp;
		<input type="button" value="添加" onclick="addDistrict()">
		-->
    </div>
    
<table width="100%" cellspacing="0">
	<thead>
		<tr>
			<th width="4%"><input type="checkbox" class="J_checkall" id="J_checkall" name="checkall"></th>
			<th width="15%">ID</th>
			<th align="left">广告标题</th>
			<th align="left">广告位ID</th>
			<th align="center" width="15%">图片</th>
			<th align="center" width="15%">链接地址</th>
			<th align="center">更新时间</th>
			<th align="center">状态</th>
			<th align="center">地区ID</th>
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
		        <td align="center">${ad.adsId?c}</td>
		        <td align="center"><img width='100' height='60' src='${ad.imageUrl!''}'/></td>
		        <td align="center">${ad.linkUrl!''}</td>
		        <td align="center">${ad.updateTime?string('yyyy-MM-dd')}</td>
		        <td align="center"><#if ad.status==1>正常<#else>屏蔽</#if></td>
		        <td align="center">${ad.regionId!''}</td>
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
<div class="btn_wrap_fixed"></div>
<div class="btn_wrap_fixed">
<#assign pageId="Ads" />
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
