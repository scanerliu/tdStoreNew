<#import "/common/app.ftl" as app>
<link rel="stylesheet" href="${app.basePath}/static/js/easyui/easyui.css"/>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="pad_lr_10">
<div class="J_tablelist table_list">  
<table width="100%" cellspacing="0">
	<thead>
		<tr>
			<th width="4%"><input type="checkbox" class="J_checkall" id="J_checkall" name="checkall"></th>
			<th width="15%">ID</th>
			<th align="left">地区名称</th>
			<th align="center" width="15%">地区层级</th>
			<th align="center" width="15%">上级ID</th>
			<th width="25%">管理操作</th>
		</tr>
	</thead>
<tbody>
<#if districtList??>
	<#list districtList as district>
		<tr>
	        <td align="center">
	        	<input type="checkbox" value="${district.id?c}" name="subbox" class="J_checkitem">
	        </td>
	        <td align="center">${district.id?c}</td>
	        <td>${district.name}</td>
	        <td align="center">${district.level}</td>
	        <td align="center">${district.upid}</td>
	        <td align="center">
	            <a class="J_confirmurl" href="javascript:;" onclick="delDistrict(${district.id?c})">删除</a>
	            |<a class="J_confirmurl" href="javascript:;" onclick="editDistrict(${district.id?c})">编辑</a>
	        </td>
	    </tr>
    </#list>
</#if>
</tbody>
</table>
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
