<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="permissionForm" action="${app.basePath}/admin/role/savepermission" method="post">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
<th width="8"><input type="checkbox" class="J_checkall" id="J_checkall" name="checkall"></th>
<th align="left">全部选择/取消</th>
</tr>
</thead>
</table>
<ul id="menuTree"></ul>
<table width="100%" cellspacing="0">
<tbody>
    <tr>
        <td width="8"><input type="hidden" id="roleId" name="id" value="${role.id!''}"></td>
        <td>
            <button type="button" class="d-button" onclick="savePermissions()">保存</button>
        </td>
    </tr>
</tbody>
</table>
</div>
</form>
</div>
<script>
$(function(){
	var menuData = ${jsonTree};
	
	$("#permissionForm #J_checkall").click(function() {
        treeChecked(this.checked); 
    });
    $('#menuTree').tree({
	    lines:true,
	    checkbox:true,
	    animate:true,
	    cascadeCheck: false,
		data: menuData,
		onCheck: function (node, checked) {
            if (checked) {
                var parentNode = $("#menuTree").tree('getParent', node.target);
                if (parentNode != null) {
                    $("#menuTree").tree('check', parentNode.target);
                }
            } else {
                var childNode = $("#menuTree").tree('getChildren', node.target);
                if (childNode.length > 0) {
                    for (var i = 0; i < childNode.length; i++) {
                        $("#menuTree").tree('uncheck', childNode[i].target);
                    }
                }
            }
        }
	});
	
});
function treeChecked(checked){
		var roots = $('#menuTree').tree('getRoots');//返回tree的所有根节点数组  
	    if (checked) {  
	        for ( var i = 0; i < roots.length; i++) {  
	            var node = $('#menuTree').tree('find', roots[i].id);//查找节点  
	            $('#menuTree').tree('check', node.target);//将得到的节点选中  
	            var childNode = $("#menuTree").tree('getChildren', node.target);
                if (childNode.length > 0) {
                    for (var j = 0; j < childNode.length; j++) {
                        $("#menuTree").tree('check', childNode[j].target);
                    }
                }
	        }  
	    } else {  
	        for ( var i = 0; i < roots.length; i++) {  
	            var node = $('#menuTree').tree('find', roots[i].id);  
	            $('#menuTree').tree('uncheck', node.target);  
	        }  
	    }  
		
	}
</script>
