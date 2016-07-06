<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="roleForm" action="${app.basePath}/admin/manager/saverole" method="post">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
	<thead>
		<tr>
			<th width="40"><input type="checkbox" class="J_checkall" id="J_checkall" name="checkall"></th>
			<th align="left">角色名称</th>
		</tr>
	</thead>
	<tbody>
		<#if roleList??>
		<#list roleList as role>
		    <tr>
		        <td align="center">
		        	<input type="checkbox" value="${role.id}" name="subbox" class="J_checkitem" <#if manager.hasRoleId(role.id)>checked=checked</#if>>
		        </td>
		        <td align="left">
		        	${role.title}
		        </td>
		    </tr>
		</#list>
		</#if>
	    <tr>
	        <td width="8"><input type="hidden" id="uId" name="uid" value="${manager.uid!''}"></td>
	        <td>
	            <button type="button" class="smt mr10" onclick="saveRoles()">保存</button>
	        </td>
	    </tr>
	</tbody>
</table>
</div>
</form>
</div>
<script>
$(function(){
	 $("#roleForm #J_checkall").click(function() {
        $('#roleForm input[name="subbox"]').prop("checked",this.checked); 
    });
    var $subBox = $("#roleForm input[name='subbox']");
    $subBox.click(function(){
        $("#roleForm #J_checkall").prop("checked",$subBox.length == $("#roleForm input[name='subbox']:checked").length ? true : false);
    });   
	
});
</script>
