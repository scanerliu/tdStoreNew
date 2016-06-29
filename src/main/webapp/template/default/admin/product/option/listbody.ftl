<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/admin/product/option.js" type="text/javascript"></script>
<div class="subnav">
	<div class="content_menu ib_a blue line_x">
		<a href="javascript:;" class="add fb J_showdialog" onclick="refreshList()"><em>返回列表</em></a>&#12288;
		<#--<a href="/admin/productattribute/list" class="add fb J_showdialog" ><em>返回列表</em></a>&#12288;-->
		<a data-height="190" data-width="450" data-id="add" data-title="添加类型" href="javascript:;" class="add fb J_showdialog" onclick="editOption(${attriId?c},0)">
		<em>添加类容</em></a>&#12288;
	</div>
</div>
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
			<th width="4%">ID</th>
			<th align="center">类型ID</th>
			<th align="center">属性值</th>
			<th align="center">状态</th>
			<th align="center">排序</th>
			<th >操作</th>
		</tr>
	</thead>
<tbody>
<#if optionList?? && optionList?size gt 0>
		<#list optionList as op>
			<tr>
		        <td align="center">
		        	<input type="checkbox" value="${op.id?c}" name="subbox" class="J_checkitem">
		        </td>
		        <td align="center">${op.id?c}</td>
		        <td align="center">${op.attriId?c}</td>
		        <td align="center">${op.name!''}</td>
		        <td align="center"><#if op.status==1>正常<#elseif op.status ==2>屏蔽<#else>待审核</#if></td>
		        <td align="center">${op.sort!'99'}</td>
		        <td align="center">
		            <a class="J_confirmurl" href="javascript:;" onclick="delOption(${op.id?c})">删除</a>
		           |<a class="J_confirmurl" href="javascript:;" onclick="editOption(${attriId?c},${op.id?c})">编辑</a>
		        </td>
	    	</tr>
	    </#list>
	</#if>
</tbody>
</table>
</div>
<#--
<div class="btn_wrap_fixed"></div>
<div class="btn_wrap_fixed">
<#assign pageId="attributes" />
<#include "/admin/common/common_postpage.ftl" />
</div>
-->
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
