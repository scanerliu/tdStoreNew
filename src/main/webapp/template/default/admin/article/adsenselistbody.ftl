<#import "/common/app.ftl" as app>
<link rel="stylesheet" href="${app.basePath}/static/js/easyui/easyui.css"/>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a data-height="190" data-width="450" data-id="add" data-title="添加广告位" href="javascript:;" class="add fb J_showdialog" onclick="editAdsense(0)"><em>添加广告位</em></a>&#12288;</div></div>
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
			<th align="left">广告位名称</th>
			<th align="center" width="15%">建议宽度</th>
			<th align="center" width="15%">建议高度</th>
			<th align="center">更新时间</th>
			<th align="center">更新人</th>
			<th >操作</th>
		</tr>
	</thead>
<tbody>
<#if adsenselist?? && adsenselist?size gt 0>
		<#list adsenselist as ads>
			<tr>
		        <td align="center">
		        	<input type="checkbox" value="${ads.id?c}" name="subbox" class="J_checkitem">
		        </td>
		        <td align="center">${ads.id?c}</td>
		        <td>${ads.name!''}</td>
		        <td align="center">${ads.width!''}px</td>
		        <td align="center">${ads.height!''}px</td>
		        <td align="center">${ads.updateTime?string('yyyy-MM-dd')}</td>
		        <td align="center"><#if ads.updateUser??>${ads.updateUser.uname}</#if></td>
		        <td align="center">
		            <a class="J_confirmurl" href="javascript:;" onclick="delAdsense(${ads.id?c})">删除</a>
		           |<a class="J_confirmurl" href="javascript:;" onclick="editAdsense(${ads.id?c})">编辑</a>
		        </td>
	    	</tr>
	    </#list>
	</#if>
</tbody>
</table>
</div>
<div class="btn_wrap_fixed">
</div>
</div>
<div class="btn_wrap_fixed">
<#assign pageId="AdType" />
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
