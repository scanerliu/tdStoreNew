<#import "/common/app.ftl" as app>
<div class="subnav">
	<div class="content_menu ib_a blue line_x">
		<a data-height="190" data-width="450" data-id="add" data-title="添加类型" href="javascript:;" class="add fb J_showdialog" onclick="editProductAttribute(0)">
		<em>添加类型</em></a>&#12288;
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
			<th align="center">名称</th>
			<th align="center">状态</th>
			<th align="center">内容</th>
			<th >操作</th>
		</tr>
	</thead>
<tbody>
<#if attributorList?? && attributorList?size gt 0>
		<#list attributorList as at>
			<tr>
		        <td align="center">
		        	<input type="checkbox" value="${at.attriId?c}" name="subbox" class="J_checkitem">
		        </td>
		        <td align="center">${at.attriId?c}</td>
		        <td align="center">${at.name!''}</td>
		        <td align="center"><#if at.status==1>正常<#else>屏蔽</#if></td>
		        <td align="center">
	            	<a class="J_confirmurl" href="javascript:;" onclick="searchOption(${at.attriId?c})">查看内容</a>
		          <#--  <a class="J_confirmurl" href="${app.basePath}/admin/attribute/option/list?attriId=${at.attriId?c}">查看内容</a>-->
		        </td>
		        <td align="center">
		            <a class="J_confirmurl" href="javascript:;" onclick="delProductAttribute(${at.attriId?c})">删除</a>
		           |<a class="J_confirmurl" href="javascript:;" onclick="editProductAttribute(${at.attriId?c})">编辑</a>
		        </td>
	    	</tr>
	    </#list>
	</#if>
</tbody>
</table>
</div>
<div class="btn_wrap_fixed"></div>
<div class="btn_wrap_fixed">
<#assign pageId="attributes" />
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
