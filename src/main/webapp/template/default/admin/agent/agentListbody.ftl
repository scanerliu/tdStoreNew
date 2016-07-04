<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x">
</div></div>
<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
	<th width="40"><input type="checkbox" class="J_checkall" id="J_checkall" name="checkall"></th>
	<th>ID</th>
	<th align="center">用户</th>
	<th align="center">级别</th>
	<th align="center">地区</th>
	<th align="center">产品类别</th>
	<th align="center">操作</th>
</tr>
</thead>
<tbody>
<#if agentList??>
<#list agentList as agent>
    <tr>
    	<td align="center">
        	<input type="checkbox" value="${agent.id?c}" name="subbox" class="J_checkitem">
        </td>
        <td align="center">${agent.id?c}</td>
        <td align="center">${agent.user.uname!''}</td>
        <td align="center">${agent.levelStr!''}</td>
        <td align="center">${agent.district.name!''}</td>
        <td align="center">${agent.productType.name!''}</td>
        <td align="center">
            <a class="J_showdialog" href="javascript:;" onclick="delAgent(${agent.id?c})">删除</a>
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
<#-- 搜索条件 begin-->
<input type="hidden" name="username" value="${sc.username!''}">
<input type="hidden" name="level" value="${sc.level!'-1'}">
<input type="hidden" name="regionId" value="<#if sc.regionId??>${sc.regionId?c}<#else>-1</#if>">
<input type="hidden" name="productTypeId" value="<#if sc.productTypeId??>${sc.productTypeId?c}<#else>-1</#if>">
<#-- 搜索条件 end-->
<#assign pageId="Agent" />
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
